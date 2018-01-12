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
import com.iancmoreno.royaltyparserspring.model.Author;
import com.iancmoreno.royaltyparserspring.respository.AsinRepository;
import com.iancmoreno.royaltyparserspring.respository.AuthorRepository;

@RestController
@RequestMapping("/api/publishers/{publisherId}/authors/{authorId}/asins")
public class AsinController {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	AsinRepository asinRepository;

	
	// Get ALL Asin belonging to authorId
	// url endpoint: /api/publishers/{publisherId}/authors/{authorId}/
	@GetMapping("/")
	public ResponseEntity<List<Asin>> getAllAsins(@PathVariable(value="authorId") Long authorId) {

		Author theAuthor = authorRepository.findOne(authorId);
		
		if (theAuthor == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(theAuthor.getAsins());
	}
	
	// Create new Asin
	@PostMapping("/")
	public ResponseEntity<?> createAuthor(@PathVariable(value="authorId") Long authorId,
										 @RequestBody Asin theAsin) {
		if (theAsin == null) {
			return ResponseEntity.notFound().build();
		}

		theAsin.setAuthor(authorRepository.findOne(authorId));

		return new ResponseEntity<>(asinRepository.save(theAsin), HttpStatus.CREATED);
	}
	
	// Get single Asin 
	@GetMapping("/{asinId}")
	public ResponseEntity<Asin> getAsinById(@PathVariable(value="asinId") Long asinId) {

		Asin theAsin= asinRepository.findOne(asinId);

		return ResponseEntity.ok(theAsin);
	}
	
	// Update an Asin 
	@PutMapping("/{asinId}")
	public ResponseEntity<Asin> updateAsin(@PathVariable(value="asinId") Long asinId,
													@RequestBody Asin asinDetails) {
		
		Asin theAsin = asinRepository.findOne(asinId);
		
		if (theAsin == null) {
			return ResponseEntity.notFound().build();
		}
		
		theAsin.setBookTitle(asinDetails.getBookTitle());
		theAsin.setBookAsin(asinDetails.getBookAsin());
		
		// update author in db
		Asin updatedAsin = asinRepository.save(theAsin);

		return ResponseEntity.ok(updatedAsin);
	}
	
	// Delete an Asin
	@DeleteMapping("/{asinId}")
	public ResponseEntity<Asin> deleteAsin(@PathVariable(value="asinId") Long asinId) {
		Asin theAsin = asinRepository.findOne(asinId);
		
		if (theAsin == null) {
			return ResponseEntity.notFound().build();
		}

		// delete the asin from db
		asinRepository.delete(theAsin);

		return ResponseEntity.ok().build();
	}

}
