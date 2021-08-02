package com.project.Books_Cabinet.controller;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Books_Cabinet.model.Login;
import com.project.Books_Cabinet.model.Seller;
import com.project.Books_Cabinet.model.User;
import com.project.Books_Cabinet.repository.LoginRepo;
import com.project.Books_Cabinet.repository.SellerRepo;
import com.project.Books_Cabinet.repository.UserRepo;

@SessionAttributes("SessionId")
@Controller
public class SellerController {
	
	@Autowired
	SellerRepo sellerRepo;
	@Autowired
	LoginRepo loginRepo;
	@Autowired
	UserRepo userRepo;
	
	String msg="";

	@ModelAttribute
	public void welcome(Model m) {
		m.addAttribute("msg",msg);
	}
	
	
	@PostMapping("/sellerForm")
	private String contactForm(@Valid @ModelAttribute Seller seller, BindingResult bindingResult, @ModelAttribute Login login,RedirectAttributes redirectAttributes ) {
	
		try {
			
			if(bindingResult.hasErrors()) {	
				return "/seller/sellerRegistration.html";
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
		return "/seller/sellerRegistration.html";
	}
	
	@RequestMapping("/sellerProfile")
	private String viewSellerProfile(HttpServletRequest request, Model model) {
		
		msg = "";
		String sessionID = (String) request.getSession().getAttribute("SessionId");
		
		if(sessionID == null) {
			return "pageNotFound.html";
		}
		
		else {
			Collection<Seller> user = sellerRepo.findBysId(Integer.parseInt(sessionID));

			String fullName = user.iterator().next().getFullName();
			String phoneNumber = user.iterator().next().getPhoneNumber();
			String address = user.iterator().next().getAddress();
			String birthday = user.iterator().next().getBirthday();
			String sellerType = user.iterator().next().getSellerType();
			String gender = user.iterator().next().getGender();
			String nid = user.iterator().next().getNid();
			String email = user.iterator().next().getEmail();
			String password = user.iterator().next().getPassword();
			String shopOrPublicationName = user.iterator().next().getShopOrPublicationName();


			model.addAttribute("fullName",fullName);
			model.addAttribute("phoneNumber",phoneNumber);
			model.addAttribute("address",address);
			model.addAttribute("birthday",birthday);
			model.addAttribute("sellerType",sellerType);
			model.addAttribute("gender",gender);
			model.addAttribute("nid",nid);
			model.addAttribute("email",email);
			model.addAttribute("password",password);
			model.addAttribute("shopOrPublicationName",shopOrPublicationName);


			System.out.println(sessionID);
			System.out.println( user.iterator().next().getFullName()+" "+user.iterator().next().getAddress());


			return "/seller/sellerProfile.html";
		}
	}
	
	
	@PostMapping("/updateSeller")
	private String updateSeller(@ModelAttribute Seller seller,@ModelAttribute User user,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		String sessionId = (String) request.getSession().getAttribute("SessionId");
		
		try {
		
		if(sessionId == null) {
			return "pageNotFound.html";
		}
		
		else {
			Seller updateSeller = sellerRepo.getOne(Integer.parseInt(sessionId));
			updateSeller.setFullName(seller.getFullName());
			updateSeller.setPhoneNumber(seller.getPhoneNumber());
			updateSeller.setAddress(seller.getAddress());
			sellerRepo.save(updateSeller);
			
			if(seller.getSellerType().equals("user")) {
				User updateUser = userRepo.getOne(Integer.parseInt(sessionId));
				updateUser.setFullName(seller.getFullName());
				updateUser.setPhoneNumber(seller.getPhoneNumber());
				updateUser.setAddress(seller.getAddress());
				userRepo.save(updateUser);
			}
			
				
			redirectAttributes.addFlashAttribute("message", "Updated Successfully!");
		    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			
			return "redirect:/sellerProfile";
		}
		
		}
		catch (Exception e) {
			msg = "This phone number used before. Please give new one";
			
			return "redirect:/sellerProfile";
		}
		
	}
	
	

}
