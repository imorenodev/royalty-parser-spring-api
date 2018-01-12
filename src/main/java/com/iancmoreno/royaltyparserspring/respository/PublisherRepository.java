package com.iancmoreno.royaltyparserspring.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iancmoreno.royaltyparserspring.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	// enables Spring to create JpaRepository CRUD methods during component scan
}