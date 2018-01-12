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

import com.iancmoreno.royaltyparserspring.model.Asin;
import com.iancmoreno.royaltyparserspring.model.Publisher;
import com.iancmoreno.royaltyparserspring.respository.AsinRepository;
import com.iancmoreno.royaltyparserspring.respository.AuthorRepository;
import com.iancmoreno.royaltyparserspring.respository.PublisherRepository;

@RestController
@RequestMapping("/api/publishers/{publisherId}/authors/{authorId}")
public class AsinController {

	@Autowired
	PublisherRepository publisherRepository;
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	AsinRepository asinRepository;

	
	// Get ALL Publishers
	// NOTE: @GetMapping("/publishers") is short form of @RequestMapping(value="/notes", method=RequestMethod.GET)
	// url endpoint: /api/publishers
	@GetMapping("/publishers")
	public List<Publisher> getAllPublishers() {
		return publisherRepository.findAll();
	}
	
	// Create new asins
	@PostMapping("/{id}/authors/{authorId}/asin")
	public ResponseEntity<?> createAuthor(@PathVariable(value="authorId") Long authorId,
			 @RequestBody Asin asin) {
		asin.setAuthor(authorRepository.findOne(authorId));

		return new ResponseEntity<>(asinRepository.save(asin), HttpStatus.CREATED);
	}
	
	// Get a single Publisher
	// url endpoint: /api/publishers/{id}
	@GetMapping("/publishers/{id}")
	public ResponseEntity<Publisher> getPublisherById(@PathVariable(value="id") Long publisherId) {
		Publisher publisher = publisherRepository.findOne(publisherId);
		
		if (publisher == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(publisher);
	}
	
	// Update a Publisher
	@PutMapping("/publishers/{id}")
	public ResponseEntity<Publisher> updatePublisher(@PathVariable(value="id") Long publisherId,
													@RequestBody Publisher publisherDetails) {
		Publisher publisher = publisherRepository.findOne(publisherId);
		
		if (publisher == null) {
			return ResponseEntity.notFound().build();
		}
		
		publisher.setFirstName(publisherDetails.getFirstName());
		publisher.setLastName(publisherDetails.getLastName());
		publisher.setEmail(publisherDetails.getEmail());
		
		Publisher updatedPublisher = publisherRepository.save(publisher);

		return ResponseEntity.ok(updatedPublisher);
	}
	
	// Delete a Publisher
	@DeleteMapping("/publishers/{id}")
	public ResponseEntity<Publisher> deletePublisher(@PathVariable(value="id") Long publisherId) {
		Publisher publisher = publisherRepository.findOne(publisherId);
		
		if (publisher == null) {
			return ResponseEntity.notFound().build();
		}

		publisherRepository.delete(publisher);

		return ResponseEntity.ok().build();
	}


}
