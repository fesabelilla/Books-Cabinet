package com.project.Books_Cabinet.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Books_Cabinet.model.Seller;
import com.project.Books_Cabinet.model.User;
import com.project.Books_Cabinet.repository.SellerRepo;
import com.project.Books_Cabinet.repository.UserRepo;

@Controller
@SessionAttributes("SessionId")
public class UserController {
	
	@Autowired
	SellerRepo sellerRepo;
	
	@Autowired
	UserRepo userRepo;
	
	String msg="";
	
	@ModelAttribute
	public void message(Model model) {
		model.addAttribute("msg", msg);
	}
	
	@PostMapping("/userForm")
	private String userForm(@Valid @ModelAttribute User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, @ModelAttribute Seller seller) {
		
		try {
			if(bindingResult.hasErrors()) {
				return "/users/userRegistration.html";
			}
			else {
				
				int sellerLastId;
				
				Collection<Seller> lastSellerInTheTable = sellerRepo.finds();
				sellerLastId = lastSellerInTheTable.iterator().next().getsId();
				user.setUserId(sellerLastId+1);
			
				System.out.println(sellerLastId+1);
				userRepo.save(user);
				
				seller.setAddress(user.getAddress());
				seller.setBirthday(user.getBirthday());
				seller.setEmail(user.getEmail());
				seller.setFullName(user.getFullName());
				seller.setGender(user.getGender());
				seller.setNid(user.getNid());
				seller.setPassword(user.getPassword());
				seller.setPhoneNumber(user.getPhoneNumber());
				seller.setSellerType(user.getUserType());
				seller.setShopOrPublicationName("MyShop");
				
				sellerRepo.save(seller);
				
				
				redirectAttributes.addFlashAttribute("message", "Account created!");
			    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				
				return "redirect:/home";
			}
			
		} catch (Exception e) {
			msg = "Phone Number or NID or Email used before. Check again and give new one.";
			return "redirect:/userRegistration";
		}
		
		
		
	}
	
	
	
	
	@RequestMapping("/userRegistration")
	private String userRegistration(User user) {
		msg = "";
		return "/users/userRegistration.html";
	}
	
	

}
