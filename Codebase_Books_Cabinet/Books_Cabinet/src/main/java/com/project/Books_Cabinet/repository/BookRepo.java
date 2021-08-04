package com.project.Books_Cabinet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.Books_Cabinet.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{

	@Query("from Book where bookCondition='Used'")
	List<Book> findUsedBook();
	
	@Query("from Book where bookCondition='New'")
	List<Book> findNewBook();

	@Query("from Book where bookName LIKE %:search%")
	List<Book> findSearchBook(@Param("search") String search);
	
	@Query("from Book Where userId = ?1")
	List<Book> findbyUserId(int userId);

}
