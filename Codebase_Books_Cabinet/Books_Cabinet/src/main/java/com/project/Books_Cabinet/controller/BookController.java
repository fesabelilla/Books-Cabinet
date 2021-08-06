package com.project.Books_Cabinet.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Books_Cabinet.imageFileHandling.FileUploadUtil;
import com.project.Books_Cabinet.model.Book;
import com.project.Books_Cabinet.model.Category;
import com.project.Books_Cabinet.model.SellBooks;
import com.project.Books_Cabinet.model.Seller;
import com.project.Books_Cabinet.repository.BookRepo;
import com.project.Books_Cabinet.repository.CategoryRepo;
import com.project.Books_Cabinet.repository.OldBooksHistoryAfterSellRepo;
import com.project.Books_Cabinet.repository.OrderBookRepo;
import com.project.Books_Cabinet.repository.SellBooksRepo;
import com.project.Books_Cabinet.repository.SellerRepo;

@Controller
@SessionAttributes("SessionId")
public class BookController {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	SellerRepo sellerRepo;
	
	@Autowired
	SellBooksRepo sellBooksRepo;
	
	
	@Autowired
	OrderBookRepo orderBookRepo;
	@Autowired
	OldBooksHistoryAfterSellRepo oldBooksHistoryAfterSellRepo;
	
	Book book;
	int userId;
	int bookIds;
	String sessionId;
	
	
	

	@GetMapping("/addBook")
	private String bookAdd(Model model, Book book){
		if (model.getAttribute("SessionId") == null) {
			return "pageNotFound.html";
		}
		
		else {
			List<Category> allCategories = categoryRepo.findAll();
			
			model.addAttribute("allCategories", allCategories);
			
			return "/books/addBook.html";
		}
	}
	
	@PostMapping("/addBookForm")
	private String storeBookInforamtion(@Valid @ModelAttribute Book book,BindingResult bindingResult , Model model, @RequestParam("image") MultipartFile multipartFile,
			HttpServletRequest request , RedirectAttributes redirectAttributes, SellBooks sellBooks) throws IOException {
	
		try {
			 
			if(bindingResult.hasErrors()) {
				List<Category> allCategories = categoryRepo.findAll();
				
				model.addAttribute("allCategories", allCategories);
				
				return "/books/addBook.html";
			}
			
			else {
					String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			        book.setPhotos(fileName);
					
			        String sessionId = (String) request.getSession().getAttribute("SessionId");
			        
			        userId = Integer.valueOf(sessionId);
			        book.setUserId(userId);
			        
			        bookRepo.save(book);
			        
			        Collection<Seller> user = sellerRepo.findBysId(Integer.valueOf(sessionId));
			        
			        //System.out.println(user.iterator().next().getSellerType());
			        sellBooks.setType(user.iterator().next().getSellerType());
			        sellBooks.setSellerId(Integer.valueOf(sessionId));
			        sellBooks.setBookId(book.getBookId());
			        
			        
			        sellBooksRepo.save(sellBooks);
			        
			        
			        //System.out.println("Books : "+ book);
			        
			        String uploadDir = "./src/main/resources/static/images/";
			        
			        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			        
					redirectAttributes.addFlashAttribute("bookAddMessage","Book Added Successfully");
					redirectAttributes.addFlashAttribute("alertClass","alert-success");
			        
					
					return "redirect:/history";	
				
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			
			System.out.println(e);
			return "/books/addBook.html";
		}
		
		
	}
	
	@GetMapping("/editBook")
	private String updateBookInfo(@RequestParam String bookId, Model model,HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		sessionId = (String) request.getSession().getAttribute("SessionId");
		userId = Integer.valueOf(sessionId);
		
		if(sessionId == null) {
			return "pageNotFound.html";
		}
		else {
			bookIds = Integer.parseInt(bookId);
			book = bookRepo.getById(bookIds);
			
			if(book.getUserId() == userId) {
				model.addAttribute("book",book);
				return "/books/editBook.html";
			}
			else {
				redirectAttributes.addFlashAttribute("message", "Wrong Entry !!!");
				redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
				
				return "redirect:/history";
			}
			
			
			
		}

	}
	
	@PostMapping("/editBook/editBookForm")
	private String editBookForm(@ModelAttribute Book book,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String sessionId = (String) request.getSession().getAttribute("SessionId");
		
		
		if(sessionId == null) {
			return "pageNotFound.html";
		}
		
		else {
			Book updateInfoBook = bookRepo.getOne(bookIds);

			updateInfoBook.setPrice(book.getPrice());
			bookRepo.save(updateInfoBook);

			redirectAttributes.addFlashAttribute("message", "Price Updated Successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");

			return "redirect:/history";
			
		}
		
			
	}
		
	
}
