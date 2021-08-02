package com.project.Books_Cabinet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Books_Cabinet.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{

}
