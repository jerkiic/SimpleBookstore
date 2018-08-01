package com.bookstore.testService;


import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Book;
import com.bookstore.entity.Role;
import com.bookstore.entity.User;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookServiceTest {

	@MockBean
	private BookRepository bookRepo;
	
	@Autowired
	private BookService bookService;
	
	@Before
	public void setUp() {
		Book book = new Book();
		User user = new User();
		Role role = new Role();
		role.setId(10L);
		role.setName("USER");
		book.setId(10L);
		book.setTitle("Test Book");
		book.setAuthor("Test Author");
		user.setId(10L);
		user.setName("Test User");
		user.setRoles((Set<Role>) role);
		
	}
	
	@Test
	public void whenBookIsAvaible_thenCanBeBorrowed() {
		when(bookService.borrowBook(10L, 10L)).thenReturn(arg0)
	}
}
