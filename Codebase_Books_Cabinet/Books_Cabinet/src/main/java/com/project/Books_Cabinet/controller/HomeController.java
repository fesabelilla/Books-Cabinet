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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Books_Cabinet.model.Login;
import com.project.Books_Cabinet.model.Seller;
import com.project.Books_Cabinet.repository.LoginRepo;
import com.project.Books_Cabinet.repository.SellerRepo;


@Controller
public class HomeController {

	@Autowired
	SellerRepo sellerRepo;
	@Autowired
	LoginRepo loginRepo;
	
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
	private String contactForm( @Valid @ModelAttribute Seller seller, BindingResult bindingResult, @ModelAttribute Login login,RedirectAttributes redirectAttributes ) {
	
		try {
			
			if(bindingResult.hasErrors()) {	
				return "sellerRegistration.html";
			}
			else {
				login.setEmail(seller.getEmail());
				login.setPassword(seller.getPassword());
				login.setType(seller.getSellerType());
				//System.out.println(seller.getEmail()+" "+seller.getPassword()+" "+seller.getSellerType());
				
				
				loginRepo.save(login);
				sellerRepo.save(seller);
				
				redirectAttributes.addFlashAttribute("message", "Account created!");
			    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				
				return "redirect:/home";
			}
				
		}
		catch (Exception e) {
			msg = "Phone Number or NID or Email used before. Check again and give new one.";
			return "redirect:/sellerRegistration";
		} 
		
		
	}
	
	@RequestMapping("/sellerRegistration")
	private String sellerRegistration(Seller seller) {
		msg = "";
		return "sellerRegistration.html";
	}
	
	@PostMapping("/login")
	private String logIn(){
		return "login.html";
	}
	
	
}
