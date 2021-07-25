package com.project.Books_Cabinet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Books_Cabinet.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
