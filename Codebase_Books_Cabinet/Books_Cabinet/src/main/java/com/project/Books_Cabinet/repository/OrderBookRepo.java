package com.project.Books_Cabinet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.Books_Cabinet.model.OrderTable;

@Repository
public interface OrderBookRepo extends JpaRepository<OrderTable, Integer>  {

	@Query("from OrderTable where userId = ?1")
	List<OrderTable> findByUserId(int userId);

	@Query("from OrderTable where sellerId = ?1")
	List<OrderTable> findBySellerId(int userId);
	
	

}
