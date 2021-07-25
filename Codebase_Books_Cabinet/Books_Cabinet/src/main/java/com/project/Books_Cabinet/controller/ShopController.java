package com.project.Books_Cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.Books_Cabinet.model.Shop;
import com.project.Books_Cabinet.repository.ShopRepo;

@Controller
@SessionAttributes("UserSessId")
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

	@GetMapping("/shop/details/{id}")
	public String ShopById(@PathVariable int id, Model model) {

		if (shopRepo.existsById(id)) {
			Shop s1 = shopRepo.getById(id);
			model.addAttribute("shop", s1);
			return "/seller/shop-details.html";

		}
		return "404.html";

	}
}
