package com.project.Books_Cabinet.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Books_Cabinet.model.Seller;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Integer>{
	
	@Query("from Seller where email = ?1 and password = ?2")
	Collection<Seller> ValidUser(String email, String password);

	Collection<Seller> findBysId(int parseInt);
	
	

	
	
}
