package com.project.Books_Cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.Books_Cabinet.model.Shop;
import com.project.Books_Cabinet.repository.ShopRepo;

@Controller
public class ShopController {
	
	@Autowired
	ShopRepo shopRepo;

	@GetMapping("/shop/create")
	private String CreateShop() {
		return "/seller/create-shop.html";
	}
	
	@PostMapping("/shop/create")
	private String SaveShop(@ModelAttribute Shop shop) {
		System.out.println(shop);
		shopRepo.save(shop);
		return "/seller/create-shop.html";
	}
}
