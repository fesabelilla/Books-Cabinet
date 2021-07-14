package com.project.Books_Cabinet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Books_Cabinet.model.Seller;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Integer>{
	
}
