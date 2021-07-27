package com.project.Books_Cabinet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Books_Cabinet.model.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{

}
