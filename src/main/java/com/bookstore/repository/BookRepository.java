package com.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
	 List<Book> findByStatus(boolean status);
	 Book findByTitleAndAuthor(String title, String author);
	 List<Book> findByBorrower(User id);
}
