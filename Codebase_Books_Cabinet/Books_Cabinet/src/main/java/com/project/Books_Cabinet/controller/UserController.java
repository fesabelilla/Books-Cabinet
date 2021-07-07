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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes("UserSessId")
public class UserController {

	@Autowired
	UserRepo ur;
	@Autowired
	LoginRepo lr;

	public static String getMd5(String input) {
		try {

			// Static getInstance method is called with hashing MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// digest() method is called to calculate message digest
			// of an input digest() return array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/user/signup")
	public String UserRegistration(Users users, Login login) {
		return "UserRegistration.html";
	}

	@PostMapping("/user/signup")
	public String UserRegistration(@Valid @ModelAttribute Users users, BindingResult bindingResult1,
			@Valid @ModelAttribute Login login, BindingResult bindingResult2) {

		if (bindingResult1.hasErrors() || bindingResult1.hasErrors()) {
			return "UserRegistration.html";
		}

		String passwordEncry = getMd5(login.getPassword());
		login.setPassword(passwordEncry);
		users.setPassword(passwordEncry);
		
		System.out.println(getMd5(login.getPassword()));
		System.out.println(getMd5(login.getPassword()));

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
			return "redirect:/user/login";
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

}
