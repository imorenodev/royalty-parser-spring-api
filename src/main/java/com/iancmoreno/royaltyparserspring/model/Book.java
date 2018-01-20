package com.iancmoreno.royaltyparserspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="book_id")
	private Long id;
	
	@Column(name="book_title")
	private String bookTitle;
	
	@Column(name="book_asin")
	private String bookAsin;

	@ManyToOne(fetch = FetchType.LAZY)
	// the key that points back to the asin's author
	@JoinColumn(name="author_id", nullable = false)
	@JsonBackReference
	private Author author;

	public Book() {
		// no-arg constructor
	}

	public Book(String bookTitle, String bookAsin) {
		this.bookTitle = bookTitle;
		this.bookAsin = bookAsin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAsin() {
		return bookAsin;
	}

	public void setBookAsin(String bookAsin) {
		this.bookAsin = bookAsin;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookTitle=" + bookTitle + ", bookAsin=" + bookAsin + ", author=" + author + "]";
	}
}
