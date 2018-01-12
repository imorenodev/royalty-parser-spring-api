package com.iancmoreno.royaltyparserspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iancmoreno.royaltyparserspring.model.Author;
import com.iancmoreno.royaltyparserspring.model.Publisher;
import com.iancmoreno.royaltyparserspring.respository.AuthorRepository;
import com.iancmoreno.royaltyparserspring.respository.PublisherRepository;

@RestController
@RequestMapping("/api/publishers/{publisherId}/authors")
public class AuthorController {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	PublisherRepository publisherRepository;
	
	// Get ALL Authors
	@GetMapping("/")
	public ResponseEntity<List<Author>> getAllAuthors(@PathVariable(value="publisherId") Long publisherId) {
		Publisher publisher = publisherRepository.findOne(publisherId);
		
		if (publisher == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(publisher.getAuthors());
	}
	
	// Create new Author
	@PostMapping("/")
	public ResponseEntity<?> createAuthor(@PathVariable(value="publisherId") Long publisherId,
										 @RequestBody Author author) {
		author.setPublisher(publisherRepository.findOne(publisherId));

		return new ResponseEntity<>(authorRepository.save(author), HttpStatus.CREATED);
	}
	
	// Get single Author
	@GetMapping("/{authorId}")
	public ResponseEntity<Author> getAuthorById(@PathVariable(value="authorId") Long authorId) {
		Author theAuthor = authorRepository.findOne(authorId);
		return ResponseEntity.ok(theAuthor);
	}
	
	// Update an Author
	@PutMapping("/{authorId}")
	public ResponseEntity<Author> updateAuthor(@PathVariable(value="authorId") Long authorId,
													@RequestBody Author authorDetails) {
		Author theAuthor = authorRepository.findOne(authorId);
		
		if (theAuthor == null) {
			return ResponseEntity.notFound().build();
		}
		
		theAuthor.setFirstName(authorDetails.getFirstName());
		theAuthor.setLastName(authorDetails.getLastName());
		theAuthor.setEmail(authorDetails.getEmail());
		
		Author updatedAuthor = authorRepository.save(theAuthor);

		return ResponseEntity.ok(updatedAuthor);
	}
	
	// Delete an Author 
	@DeleteMapping("{authorId}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable(value="authorId") Long authorId) {
		Author theAuthor = authorRepository.findOne(authorId);
		
		if (theAuthor == null) {
			return ResponseEntity.notFound().build();
		}

		// delete the author from db
		authorRepository.delete(theAuthor);

		return ResponseEntity.ok().build();
	}


}
