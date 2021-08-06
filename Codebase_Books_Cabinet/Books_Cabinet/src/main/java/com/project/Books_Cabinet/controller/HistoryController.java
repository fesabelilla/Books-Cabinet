package com.project.Books_Cabinet.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.Books_Cabinet.model.Book;
import com.project.Books_Cabinet.model.OrderTable;
import com.project.Books_Cabinet.model.Seller;
import com.project.Books_Cabinet.repository.BookRepo;
import com.project.Books_Cabinet.repository.OrderBookRepo;
import com.project.Books_Cabinet.repository.SellerRepo;

@Controller
@SessionAttributes("SessionId")
public class HistoryController {
	
	@Autowired
	OrderBookRepo orderBookRepo;
	@Autowired
	SellerRepo sellerRepo;
	@Autowired
	BookRepo bookRepo;

	
	Book book;
	
	@GetMapping("/history")
	private String history(Model model,HttpServletRequest request) {
		String sessionId =  (String) request.getSession().getAttribute("SessionId");
		
		if(sessionId == null) {
			return "pageNotFound.html";
		}
		
		else {
			int userId = Integer.parseInt(sessionId);
			
			Collection<Seller> user = sellerRepo.findBysId(userId);
			
			if((user.iterator().next().getSellerType()).equals("user")) {
				List<OrderTable> buyBooks = orderBookRepo.findByUserId(userId);
				model.addAttribute("userOrder","user");
				model.addAttribute("buyBooks",buyBooks);
			}
			List<Book> listOfUnsoldBooks = bookRepo.findbyUserId(userId);
			model.addAttribute("listOfUnsoldBooks",listOfUnsoldBooks);
			
			List<OrderTable> listOfSoldBooksList = orderBookRepo.findBySellerId(userId);
			model.addAttribute("listOfSoldBooksList",listOfSoldBooksList);
			
			return "/history/history.html";
		}
		
	}

}
