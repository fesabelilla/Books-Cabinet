package com.project.Books_Cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.Books_Cabinet.model.Users;
import com.project.Books_Cabinet.repository.UserRepo;

import java.util.Collection;

@Controller
@SessionAttributes("UserSessId")
public class UserController {

	@Autowired
	UserRepo ur;

	@GetMapping("/user/signup")
	public String UserRegistration() {
		return "UserRegistration.html";
	}

	@PostMapping("/user/signup")
	public String UserRegistration(@ModelAttribute Users user) {
		ur.save(user);
		return "redirect:/user/login";
	}

	@GetMapping("/user/login")
	public String LoginPage() {
		return "Login.html";
	}

	@PostMapping("/user/login")
	public String LoginPage(@ModelAttribute Users user, Model model, ModelMap modelmap) {
		Collection<Users> users = ur.ValidUser(user.getEmail(), user.getPassword());
		
		if (users.size() == 0) {
			System.out.println("no user");
			//return "No user";
			return "redirect:/user/login";
		}else {
			modelmap.put("UserSessId", users.iterator().next().getUserId());
			System.out.println(users.iterator().next().getEmail());
		}
		return "redirect:/home";
	}

}
