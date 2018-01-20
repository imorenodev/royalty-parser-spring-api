package com.iancmoreno.royaltyparserspring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "author")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="author_id")
	private Long id;
	
	@NotNull
	@Column(name="first_name")
	private String firstName;
	
	@NotNull
	@Column(name="last_name")
	private String lastName;

	@NotNull
	@Column(name="email")
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	// the key that points back to the author's publisher
	@JoinColumn(name="publisher_id", nullable = false)
	@JsonBackReference
	private Publisher publisher;
	
	@OneToMany(mappedBy="author", 
			cascade=CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Book> books;

	public Author() {
		// no-arg constructor
	}

	public Author(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	public List<Book> getBooks() {
		if (books == null) books = new ArrayList<>();
		return books;
	}

	public void setAsins(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book theBook) {
		if (books == null) {
			books = new ArrayList<>();
		}

		theBook.setAuthor(this);
		
		// persist to db
		books.add(theBook);
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", publisher=" + publisher + ", books=" + books + "]";
	}
	
}
