package com.iancmoreno.royaltyparserspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iancmoreno.royaltyparserspring.model.Book;
import com.iancmoreno.royaltyparserspring.model.Author;
import com.iancmoreno.royaltyparserspring.respository.BookRepository;
import com.iancmoreno.royaltyparserspring.respository.AuthorRepository;

@RestController
@RequestMapping("/api/publishers/{publisherId}/authors/{authorId}/books")
public class BookController {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BookRepository bookRepository;

	
	// Get ALL Book belonging to authorId
	// url endpoint: /api/publishers/{publisherId}/authors/{authorId}/
	@GetMapping("/")
	public ResponseEntity<List<Book>> getAllBooks(@PathVariable(value="authorId") Long authorId) {

		Author theAuthor = authorRepository.findOne(authorId);
		
		if (theAuthor == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(theAuthor.getBooks());
	}
	
	// Create new Book
	@CrossOrigin
	@PostMapping("/")
	public ResponseEntity<?> createAuthor(@PathVariable(value="authorId") Long authorId,
										 @RequestBody Book theBook) {
		if (theBook == null) {
			return ResponseEntity.notFound().build();
		}

		theBook.setAuthor(authorRepository.findOne(authorId));

		return new ResponseEntity<>(bookRepository.save(theBook), HttpStatus.CREATED);
	}
	
	// Get single Book 
	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable(value="bookId") Long bookId) {

		Book theBook= bookRepository.findOne(bookId);

		return ResponseEntity.ok(theBook);
	}
	
	// Update an Book 
	@PutMapping("/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable(value="bookId") Long bookId,
													@RequestBody Book bookDetails) {
		
		Book theBook = bookRepository.findOne(bookId);
		
		if (theBook == null) {
			return ResponseEntity.notFound().build();
		}
		
		theBook.setBookTitle(bookDetails.getBookTitle());
		theBook.setBookAsin(bookDetails.getBookAsin());
		
		// update author in db
		Book updatedBook = bookRepository.save(theBook);

		return ResponseEntity.ok(updatedBook);
	}
	
	// Delete an Book
	@CrossOrigin
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Book> deleteBook(@PathVariable(value="bookId") Long bookId) {
		Book theBook = bookRepository.findOne(bookId);
		
		if (theBook == null) {
			return ResponseEntity.notFound().build();
		}

		// delete the book from db
		bookRepository.delete(theBook);

		return ResponseEntity.ok().build();
	}

}
