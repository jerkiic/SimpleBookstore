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
		System.out.println(bookService.findAll());
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
		book.setBorrower(user);
		when(bookRepo.findByBorrower(user)).thenReturn(bookList);
		assertThat(bookService.findAllBorrowedBook(user)).contains(book);
	}
	
	@Test
	public void whenReturningBook() {
		when(bookRepo.save(book)).thenReturn(book);
		book.setBorrower(user);
		assertThat(bookService.returnBook(book)).isEqualTo(book);
	}
	
	@Test
	public void bookIsAvailable_thenCanBeBorrowed() {
		
	}
}
