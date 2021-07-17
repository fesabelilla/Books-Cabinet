package com.project.Books_Cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.project.Books_Cabinet.Logics.LoginLogic;
import com.project.Books_Cabinet.model.Login;
import com.project.Books_Cabinet.model.Users;
import com.project.Books_Cabinet.repository.UserRepo;
import com.project.Books_Cabinet.repository.LoginRepo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes("UserSessId")
public class UserController {

	@Autowired
	UserRepo ur;
	@Autowired
	LoginRepo lr;

	@GetMapping("/user/signup")
	public String UserRegistration(Users users, Login login) {
		return "UserRegistration.html";
	}

	@PostMapping("/user/signup")
	public String UserRegistration(@Valid @ModelAttribute Users users, BindingResult bindingResult1,
			@Valid @ModelAttribute Login login, BindingResult bindingResult2) {

		List<Login> loginUsers = lr.findByemail(login.getEmail());
		if(!loginUsers.isEmpty()) {
			bindingResult2.rejectValue("email", "error.login", "Email is already used");
			return "UserRegistration.html";
		}
		//System.out.println(bindingResult1);
		if (bindingResult1.hasErrors() || bindingResult2.hasErrors()) {
			
			return "UserRegistration.html";
		}
		
		

		String passwordEncry =  LoginLogic.getMd5(login.getPassword());
		login.setPassword(passwordEncry);
		users.setPassword(passwordEncry);

		System.out.println(lr.save(login));

		users.setUserId(login.getLoginId());
		ur.save(users);
		return "redirect:/user/login";
	}

	@GetMapping("/user/login")
	public String LoginPage() {
		return "Login.html";
	}

	@PostMapping("/user/login")
	public String LoginPage(@ModelAttribute Users user, Model model, ModelMap modelmap) {
		// user.setPassword(LoginLogic.getMd5(user.getPassword()));
		Collection<Users> users = ur.ValidUser(user.getEmail(), LoginLogic.getMd5(user.getPassword()));

		if (users.size() == 0) {
			System.out.println("no user");
			// return "No user";
			model.addAttribute("wrongInfo", "Wrong Email or Password");
			return "Login.html";
		} else {
			modelmap.put("UserSessId", users.iterator().next().getUserId());
			System.out.println(users.iterator().next().getEmail());
		}
		return "redirect:/home";
	}

	@GetMapping("/user/logout")
	public String Logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/user/login";
	}

	@GetMapping("/user/profile")
	public String Profile(ModelMap mm, Model model) {
		if(mm.getAttribute("UserSessId") == null) {
			return "redirect:/user/login";
		}
		int userId = (int) mm.getAttribute("UserSessId");
		Users user = ur.getById(userId);
		model.addAttribute("user", user);
		return "user/profile.html";
	}

	@GetMapping("/user/profile/edit")
	public String EditProfile(Users user, ModelMap mm, Model model) {
		int userId = (int) mm.getAttribute("UserSessId");
		user = ur.getById(userId);
		
		model.addAttribute("user", user);
		return "user/editProfile.html";
	}
	
	@PostMapping("/user/profile/edit")
	public String UpdateProfile(@ModelAttribute Users user, ModelMap mm) {
		int userId = (int) mm.getAttribute("UserSessId");
		Users user2 = ur.getById(userId);
		
		user2.setFullName(user.getFullName());
		user2.setPhonenumber(user.getPhonenumber());
		user2.setGender(user.getGender());
		user2.setNidOrBirthId(user.getNidOrBirthId());
		
		ur.save(user2);
		
		return "redirect:/user/profile";
	}

}
