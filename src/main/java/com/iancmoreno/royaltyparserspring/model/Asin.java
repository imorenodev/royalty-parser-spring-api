package com.iancmoreno.royaltyparserspring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "author")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Asin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="book_title")
	private String bookTitle;
	
	@Column(name="book_asin")
	private String bookAsin;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	// the key that points back to the asin's author 
	@JoinColumn(name="author_id")
	private Author author;

	public Asin() {
		// no-arg constructor
	}

	public Asin(String bookTitle, String bookAsin) {
		super();
		this.bookTitle = bookTitle;
		this.bookAsin = bookAsin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		return "Asin [id=" + id + ", bookTitle=" + bookTitle + ", bookAsin=" + bookAsin + ", author=" + author + "]";
	}
}
