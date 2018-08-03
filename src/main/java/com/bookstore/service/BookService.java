package com.bookstore.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.exception.BookIsBorrowedException;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;

@Service
@EnableTransactionManagement
public class BookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

	private BookRepository bookRepo;

	public BookService(BookRepository bookRepo, UserRepository userRepo) {
		super();
		this.bookRepo = bookRepo;
	}
	
	public Book getBook(Long id) {
		return bookRepo.findById(id).orElseThrow(BookNotFoundException::new);
	}
	
	@Transactional(readOnly=true, isolation=Isolation.REPEATABLE_READ)
	public List<Book> findAll() {
		LOGGER.info(bookRepo.findByStatus(true).toString());
		return bookRepo.findByStatus(true);
		
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Book addBook(Book book) {
		Book returnValue;
		Book oldBook = bookRepo.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
		if (oldBook == null) {
			LOGGER.info("A new book has been added" + book.getTitle());
			returnValue = bookRepo.save(book);
		} else {
			LOGGER.info("An old existing book has been reactivated");
			oldBook.setStatus(true);
			returnValue = bookRepo.save(oldBook);
		}
		return returnValue;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public Book borrowBook(User user, Book book) {
//		Book book = bookRepo.findById(book_id).orElseThrow(BookNotFoundException::new);
//		User user = userRepo.findById(user_id).orElseThrow(UserNotFoundException::new);
		if(book.getBorrower()!=null) {
			LOGGER.warn("WARNING:........ THE BOOK IS CURRENTLY BEING BORROWED");
			throw new BookIsBorrowedException("THE BOOK IS CURRENTLY BEING BORROWED");
		}
		book.setBorrower(user);
		LOGGER.info("User: "+user.getName()+" is borrowing book:"+book.getTitle());
		return bookRepo.save(book);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Book returnBook(Book book) {
//		Book book = bookRepo.findById(id).orElseThrow(BookNotFoundException::new);
		LOGGER.info("User: "+book.getBorrower().getName()+" is returning book:"+book.getTitle());
		book.setBorrower(null);
		return bookRepo.save(book);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public Book deleteBook(Book book) {
//		Book book = bookRepo.findById(id).orElseThrow(BookNotFoundException::new);
		LOGGER.info("Admin is deleting the book: "+book.getTitle());
		book.setStatus(false);
		return bookRepo.save(book);
	}

	@Transactional(readOnly=true)
	public List<Book> findAllBorrowedBook(User user){
		return bookRepo.findByBorrower(user);
	}

}
