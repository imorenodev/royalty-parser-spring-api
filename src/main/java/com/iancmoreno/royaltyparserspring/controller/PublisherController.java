package com.iancmoreno.royaltyparserspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iancmoreno.royaltyparserspring.model.Publisher;
import com.iancmoreno.royaltyparserspring.respository.PublisherRepository;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

	@Autowired
	PublisherRepository publisherRepository;
	
	// Get ALL Publishers
	// NOTE: @GetMapping("/publishers") is short form of @RequestMapping(value="/notes", method=RequestMethod.GET)
	// url endpoint: /api/publishers
	@GetMapping("/")
	public List<Publisher> getAllPublishers() {
		return publisherRepository.findAll();
	}
	
	// Create a new Publisher 
	@PostMapping("/")
	public Publisher createPublisher(@RequestBody Publisher publisher) {
		return publisherRepository.save(publisher);
	}
	
	// Get a single Publisher
	// url endpoint: /api/publishers/{id}
	@GetMapping("/{id}")
	public ResponseEntity<Publisher> getPublisherById(@PathVariable(value="id") Integer publisherId) {
		Publisher publisher = publisherRepository.findOne(publisherId);
		
		if (publisher == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(publisher);
	}
	
	// Update a Publisher
	@PutMapping("/{id}")
	public ResponseEntity<Publisher> updatePublisher(@PathVariable(value="id") Integer publisherId,
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
	@DeleteMapping("/{id}")
	public ResponseEntity<Publisher> deletePublisher(@PathVariable(value="id") Integer publisherId) {
		Publisher publisher = publisherRepository.findOne(publisherId);
		
		if (publisher == null) {
			return ResponseEntity.notFound().build();
		}

		publisherRepository.delete(publisher);

		return ResponseEntity.ok().build();
	}


}
