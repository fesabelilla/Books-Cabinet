package com.project.Books_Cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Books_Cabinet.model.Book;
import com.project.Books_Cabinet.repository.BookRepo;

@Controller
public class OrderController {

	@Autowired
	BookRepo bookRepo;
	
	@GetMapping("/order/create")
	//@ResponseBody
	public String createAnOrder(@RequestParam String bookId, Model model) {
		
		Book book = bookRepo.getById(Integer.parseInt(bookId));
		model.addAttribute("book", book);
		
		
		return "/order/create-order.html";
	}
	
	@PostMapping("/order/create")
	@ResponseBody
	public String storeOrder() {
		return "order received";
	}
}
