package com.iancmoreno.royaltyparserspring.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iancmoreno.royaltyparserspring.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	// enables Spring to create JpaRepository CRUD methods during component scan
}