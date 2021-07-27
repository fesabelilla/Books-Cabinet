package com.project.Books_Cabinet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.Books_Cabinet.model.Book;
import com.project.Books_Cabinet.model.Category;
import com.project.Books_Cabinet.repository.BookRepo;
import com.project.Books_Cabinet.repository.CategoryRepo;

@Controller
@SessionAttributes("SessionId")
public class BookController {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	BookRepo bookRepo;

	@GetMapping("/book/advertise/add")
	private String AddBookForAdvertising(Model model) {
		
		if (model.getAttribute("SessionId") == null) {
			return "pageNotFound.html";
		}
		
		List<Category> allCategories = categoryRepo.findAll();
		
		model.addAttribute("allCategories", allCategories);
		
		return "/books/addBook.html";
	}
	
	
	@PostMapping("/book/advertise/add")
	private String StoreBookInforamtion(@ModelAttribute Book book, Model model) {
		
		if (model.getAttribute("SessionId") == null) {
			return "pageNotFound.html";
		}
		
		bookRepo.save(book);
		System.out.println(book);
		return "redirect:/book/advertise/add";
	}
	
	@GetMapping("/book/all")
	public String GetAllBooks(Model model) {
		 List<Book> allBooks = bookRepo.findAll();
		 model.addAttribute("allBooks", allBooks);
		return "/books/allBooks.html";
	}
	
	
	
}
