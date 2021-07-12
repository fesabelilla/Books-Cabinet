package com.project.Books_Cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.Books_Cabinet.model.Seller;
import com.project.Books_Cabinet.repository.SellerRepo;

@Controller
public class HomeController {

	@Autowired
	SellerRepo sellerRepo;
	
	@RequestMapping("/home")
	private String homepage() {

		return "homepage.html";
	}

	@PostMapping("/sellerForm")
	private String contactForm(@ModelAttribute Seller seller) {
		
		sellerRepo.save(seller);
		
		return "redirect:/home";
	}
	
	@RequestMapping("/sellerRegistration")
	private String sellerRegistration() {

		return "sellerRegistration.html";
	}
	
	
}
