package com.bookstore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String title;
	private String author;
	private boolean status = true;


	
	//	@JoinTable(name = "book_record", joinColumns = {@JoinColumn(name = "book_id", referencedColumnName="id")},
	//	inverseJoinColumns = {@JoinColumn(name = "borrower_id", referencedColumnName = "id")})
	@JsonProperty("borrower_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User borrower;

	@JsonIgnore
	public User getBorrower() {
		return borrower;
	}
	@JsonIgnore
	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public Book() {}

	//	public Book(Long id, String title, String author) {
	//	super();
	//	this.id = id;
	//	this.title = title;
	//	this.author = author;
	//}
}
