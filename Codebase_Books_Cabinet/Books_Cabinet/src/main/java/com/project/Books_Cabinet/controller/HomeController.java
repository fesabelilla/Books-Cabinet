package com.project.Books_Cabinet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/home")
	private String homepage() {

		return "homepage.html";
	}

	@PostMapping("/contactForm")
	private ModelAndView contactForm(String fullname,String message, String title, Model model) {
		//model.addAttribute("fullname", fullname);
		//System.out.println(fullname);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("contact.html");
		
		mv.addObject("fullname", fullname);
		//mv.addObject("title", title);
		
		return mv;
	}
	
	@RequestMapping("/sellerRegistration")
	private String sellerRegistration() {

		return "sellerRegistration.html";
	}
	
	
}
