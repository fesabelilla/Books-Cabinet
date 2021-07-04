package com.project.Books_Cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.project.Books_Cabinet.model.Users;
import com.project.Books_Cabinet.repository.UserRepo;

@Controller
@SessionAttributes("UserSessId")
public class HomeController {
	
	@Autowired
	UserRepo ur;

	@RequestMapping("/home")
	private String homepage(ModelMap mm, Model model) {
		
		model.addAttribute("UserSessId", mm.getAttribute("UserSessId"));
		
		String fullName = ur.getById((Integer) mm.getAttribute("UserSessId")).getFullName();
		System.out.println(fullName);
		model.addAttribute("UserFullName", fullName);

		return "/books/books_home.html";
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
}
