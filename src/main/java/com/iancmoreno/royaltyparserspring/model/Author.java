package com.iancmoreno.royaltyparserspring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "author")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lasstName;

	@Column(name="email")
	private String email;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	// the key that points back to the author's publisher
	@JoinColumn(name="publisher_id")
	private Publisher publisher;
	
	@OneToMany(mappedBy="author", 
			cascade={CascadeType.ALL})
	private List<Asin> asins;

	public Author() {
		// no-arg constructor
	}

	public Author(String firstName, String lasstName, String email) {
		this.firstName = firstName;
		this.lasstName = lasstName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasstName() {
		return lasstName;
	}

	public void setLasstName(String lasstName) {
		this.lasstName = lasstName;
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
	
	public List<Asin> getAsins() {
		return asins;
	}

	public void setAsins(List<Asin> asins) {
		this.asins = asins;
	}

	public void addAsin(Asin theAsin) {
		if (asins == null) {
			asins = new ArrayList<>();
		}
		
		asins.add(theAsin);
		theAsin.setAuthor(this);
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", firstName=" + firstName + ", lasstName=" + lasstName + ", email=" + email
				+ ", publisher=" + publisher + ", asins=" + asins + "]";
	}
	
}
