package com.bookstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/all")
    public List<Book> findAllBook() {
		LOGGER.info("Find all available books");
        return bookService.findAll();
    }
	
	@RequestMapping(value="/all/borrowed/{id}")
    public List<Book> findAllBorrowedBook(@PathVariable Long id) {
		LOGGER.info("Find all borrowed book/s by a user");
        return bookService.findAllBorrowedBook(id);
    }
	
	@RequestMapping(value = "/add", method= RequestMethod.POST)
	public Book addBook(@RequestBody Book book) {
		LOGGER.info("Adding a new book");
        return bookService.addBook(book);
	}
	
	@RequestMapping(value = "/borrowuser/{user_id}", method = RequestMethod.PUT)
	public Book borrowBookWithUser(@RequestBody Long book_id, @PathVariable Long user_id) {
		LOGGER.info("A user is borrowing a book");
		return bookService.borrowBook(user_id, book_id);
	}
	
	@RequestMapping(value = "/return", method = RequestMethod.POST)
	public Book returnBook(@RequestBody Long id) {
		LOGGER.info("A user is returning a book");
		return bookService.returnBook(id);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	public Book deleteBook(@RequestBody Long id) {
		LOGGER.info("The admin is deleting a book");
		return bookService.deleteBook(id);
	}
	
}
