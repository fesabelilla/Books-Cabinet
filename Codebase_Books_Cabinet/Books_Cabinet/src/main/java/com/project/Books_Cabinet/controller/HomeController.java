package com.project.Books_Cabinet.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Books_Cabinet.model.Login;
import com.project.Books_Cabinet.model.Seller;
import com.project.Books_Cabinet.repository.LoginRepo;
import com.project.Books_Cabinet.repository.SellerRepo;



@SessionAttributes("SessionId")
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
	
	@PostMapping("/loginForm")
	private String logIn(@Valid @ModelAttribute Login login,Seller seller,BindingResult bindingResult,HttpServletRequest request
			, Model model, RedirectAttributes redirectAttributes,ModelMap modelMap){
		
			if(bindingResult.hasErrors()) {	
				return "login.html";
			}
			
			
			else {
				Collection<Seller> user = sellerRepo.ValidUser(login.getEmail(), login.getPassword());
				
				if (user.size() == 0) {
					System.out.println("no user");
					msg = "Wrong Email or Password";
					return "redirect:/login";
				}
				else {
					
					@SuppressWarnings("unchecked")
					//List<String> sessionID = (List<String>) request.getSession().getAttribute("SessionId");
					String userID = Integer.toString( user.iterator().next().getsId());
					String sessionID = (String) request.getSession().getAttribute("SessionId");
					
					if(sessionID == null) {
						sessionID = "";
						request.getSession().setAttribute("SessionId", sessionID);
					}
					sessionID = userID;
					request.getSession().setAttribute("SessionId", sessionID);					
					
					model.addAttribute("sellerName", sessionID);
					
					redirectAttributes.addFlashAttribute("message", "Login Successfully!");
				    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					
					System.out.println(user.iterator().next().getFullName()+"   "+sessionID);
					return "redirect:/home";
					
				}
				
			}
	
		
	}
	
	
	@PostMapping("/destroy")
	public String destroySession(HttpServletRequest request,SessionStatus status) {
		status.setComplete();
		request.getSession().invalidate();
		return "redirect:/home";
	}
	
	@RequestMapping("/login")
	private String logIn(Login login){
			return "login.html";
	}
	
	
	
	
}
