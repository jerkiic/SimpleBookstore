package com.bookstore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String role;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "book_record", joinColumns= {@JoinColumn(name="borrower_id", referencedColumnName="id")},
	inverseJoinColumns= {@JoinColumn(name="book_id",referencedColumnName="id")})
	private List<Book> borrowedBooks;
	
//	@ManyToMany(cascade=CascadeType.ALL)
//	@JoinTable(
//			name="user_roles",
//			joinColumns = @JoinColumn(
//					name = "user_id", referencedColumnName = "id"),
//			inverseJoinColumns = @JoinColumn(
//					name = "role_id", referencedColumnName = "id"))	
//	private Set<Role> roles;
	
	@JsonIgnore
	public List<Book> getBook() {
		return borrowedBooks;
	}
	
	@JsonIgnore
	public void setBook(List<Book> book) {
		this.borrowedBooks = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public User() {}
}
