package com.bookstore.Service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.exception.BookIsBorrowedException;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

	@MockBean
	private BookRepository bookRepo;
	
	@Autowired
	private BookService bookService;
	
	private Book book;
	private User user;
	
	@Before
	public void setUp() {
		book = new Book();
		book.setId(10L);
		book.setTitle("Star Wars");
		book.setAuthor("George Lucas");
		
		user= new User();
		user.setId(9L);
		user.setName("TestUser");
	}
	
	@Test
	public void whenAvailableBooks_thenDisplay() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		when(bookRepo.findByStatus(true)).thenReturn(bookList);
		assertThat(bookService.findAll()).isEqualTo(bookList);
	}
	
	@Test
	public void whenNoAvailableBooks_thenDisplayEmptyList() {
		when(bookRepo.findByStatus(true)).thenReturn(new ArrayList<>());
		assertThat(bookService.findAll()).isEmpty();
	}
	@Test
	public void whenAddingABook() {
		when(bookRepo.save(book)).thenReturn(book);
		assertThat(bookService.addBook(book)).isEqualTo(book);
	}
	
	@Test
	public void whenABookIsBorrowed_thenStillDisplay() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		when(bookRepo.findByBorrower(user)).thenReturn(bookList);
		assertThat(bookService.findAllBorrowedBook(user)).containsAll(bookList);
	}
	
	@Test
	public void whenReturningBook() {
		when(bookRepo.save(book)).thenReturn(book);
		book.setBorrower(user);
		assertThat(bookService.returnBook(book)).isEqualTo(book);
	}
	
	@Test
	public void whenBookIsAvailable_thenCanBeBorrowed() {
		when(bookRepo.save(book)).thenReturn(book);
		assertThat(bookService.borrowBook(user, book)).isEqualTo(book);
	}
	
	@Test
	public void whenBookIsDeleted() {
		when(bookRepo.save(book)).thenReturn(book);
		assertThat(bookService.deleteBook(book)).isEqualTo(book);
	}
	
	@Test(expected = BookIsBorrowedException.class)
	public void whenBookIsBorrowed_theCantBeBorrowed() {
		when(bookRepo.save(book)).thenReturn(book);
		book.setBorrower(user);
		bookService.borrowBook(user, book);
	}
	
	@Test
	public void whenBookIsReturned_thenNotInBorrowedBooks() {
		when(bookRepo.save(book)).thenReturn(book);
		assertThat(bookService.findAllBorrowedBook(user)).doesNotContain(book);
	}
	
}
