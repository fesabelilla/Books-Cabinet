package com.project.Books_Cabinet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Books_Cabinet.model.Book;
import com.project.Books_Cabinet.model.OldBooksHistoryAfterSell;
import com.project.Books_Cabinet.model.OrderTable;
import com.project.Books_Cabinet.model.Seller;
import com.project.Books_Cabinet.model.User;
import com.project.Books_Cabinet.repository.BookRepo;
import com.project.Books_Cabinet.repository.OldBooksHistoryAfterSellRepo;
import com.project.Books_Cabinet.repository.OrderBookRepo;
import com.project.Books_Cabinet.repository.SellerRepo;
import com.project.Books_Cabinet.repository.UserRepo;

@Controller
@SessionAttributes("SessionId")
public class OrderController {
	@Autowired
	BookRepo bookRepo;
	@Autowired
	SellerRepo userRepo;
	@Autowired
	OrderBookRepo orderBookRepo;
	@Autowired
	OldBooksHistoryAfterSellRepo oldBooksHistoryAfterSellRepo;
	
	Book book;
	Seller user;
	int userId;
	
	
	@RequestMapping("/buyBook")
	private String buyBook(@RequestParam String bookId ,Model model, HttpServletRequest request) {
		
		book = bookRepo.getById(Integer.parseInt(bookId));
		model.addAttribute(book);
		
		String sessionId = (String) request.getSession().getAttribute("SessionId");
		userId = Integer.parseInt(sessionId);
		user = userRepo.getById(userId);
		//System.out.println(user.getUserId());
		model.addAttribute("user",user);
		
		return "/books/buyBooks.html";
	}
	
	@PostMapping("/buyBook/orderTable")
	private String orderTable(@ModelAttribute OrderTable order,RedirectAttributes redirectAttributes) {
		Seller seller = userRepo.getById(book.getUserId());
		if(user.getSellerType().equals("user")) {
			if(seller.getsId()!=userId) {
				order.setUserId(userId);
				order.setSellerId(seller.getsId());
				order.setBookID(book.getBookId());
				order.setSellerName(seller.getFullName());
				order.setSellerPhoneNo(seller.getPhoneNumber());
				order.setSellerAddress(seller.getAddress());
				order.setUserType(user.getSellerType());
				order.setSellerType(seller.getSellerType());
				order.setDeliveryPrice(60.0);
				order.setOrderStatus("Complete");
				order.setBookName(book.getBookName());
				order.setPhotos(book.getPhotos());
				
				orderBookRepo.save(order);
				
				if(book.getBookCondition().equals("Used")) {
					
					OldBooksHistoryAfterSell historyAfterSell = new OldBooksHistoryAfterSell();
					historyAfterSell.setBookCondition(book.getBookCondition());
					historyAfterSell.setBookId(book.getBookId());
					historyAfterSell.setBookName(book.getBookName());
					historyAfterSell.setCategoryId(book.getCategoryId());
					historyAfterSell.setLanguage(book.getLanguage());
					historyAfterSell.setPhotos(book.getPhotos());
					historyAfterSell.setPrice(book.getPrice());
					historyAfterSell.setPublishingYear(book.getPublishingYear());
					historyAfterSell.setRating(book.getRating());
					historyAfterSell.setUserId(book.getUserId());
					historyAfterSell.setWriter(book.getWriter());
					
					oldBooksHistoryAfterSellRepo.save(historyAfterSell);
					
					bookRepo.deleteById(book.getBookId());
				}
				
				redirectAttributes.addFlashAttribute("message","Order completed Successfully!");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				
				return "redirect:/home";
			}
			else {
				redirectAttributes.addFlashAttribute("message","This book is yours. So you can't buy this book!!");
				redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
				return "redirect:/home";
				
			}
		}
		
		else {
			
			redirectAttributes.addFlashAttribute("message","You are seller so you can't buy book!!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			
			return "redirect:/home";
		}
		
	}
	
}
