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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "publisher")
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="publisher_id")
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
	
	// mappedBy tells hibernate that Publisher is not responsible for Author, but rather
	// to look for a field named 'publisher' in the Author entity that will define the
	// JoinColumn/ForeignKey Column configuration
	@OneToMany(mappedBy="publisher", 
			cascade=CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Author> authors;

	public Publisher() {
		// no-arg constructor
	}

	public Publisher(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	// Getters and Setters

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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addAuthor(Author theAuthor) {
		if (authors == null) {
			authors = new ArrayList<>();
		}
		theAuthor.setPublisher(this);
		
		// persist to db
		authors.add(theAuthor);
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", authors=" + authors + "]";
	}
	
}
