package com.project.Books_Cabinet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	String msg="";
	@ModelAttribute
	public void welcome(Model m) {
		m.addAttribute("msg",msg);
	}
	
	@RequestMapping("/home")
	private String homepage() {

		return "homepage.html";
	}

	@PostMapping("/sellerForm")
	private String contactForm( @ModelAttribute Seller seller) {
	
		//System.out.println();
		
		
		try {
			if(seller.getEmail() != null && !seller.getEmail().isEmpty() && seller.getAddress() != null && !seller.getAddress().isEmpty() && 
					seller.getBirthday() != null && !seller.getBirthday().isEmpty() && seller.getFullName() != null && !seller.getFullName().isEmpty() &&
					seller.getGender() != null && !seller.getGender().isEmpty() && seller.getNid() != null && !seller.getNid().isEmpty() &&
					seller.getPassword() != null && !seller.getPassword().isEmpty() && seller.getPhoneNumber() != null && !seller.getPhoneNumber().isEmpty() &&
					seller.getSellerType() != null && !seller.getSellerType().isEmpty()) {
				
				int passwordLength = seller.getPassword().length();
				
				if(passwordLength<8){
					msg = "Password must be 8 characters or more";
					return "redirect:/sellerRegistration";
				}
				
				else {
					sellerRepo.save(seller);
					return "redirect:/home";
				}
				
			}
			
			else {
				msg = "Please give all information correctly";
				return "redirect:/sellerRegistration";
			}
				
		}
		catch (Exception e) {
			msg = "Phone Number or NID or Email used before. Check again and give new one.";
			return "redirect:/sellerRegistration";
		}
	}
	
	@RequestMapping("/sellerRegistration")
	private String sellerRegistration() {
		msg = "";
		return "sellerRegistration.html";
	}
	
	
}
