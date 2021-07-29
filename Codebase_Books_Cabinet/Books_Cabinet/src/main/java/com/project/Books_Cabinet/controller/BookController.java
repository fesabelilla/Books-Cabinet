package com.project.Books_Cabinet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.project.Books_Cabinet.imageFileHandling.FileUploadUtil;
import com.project.Books_Cabinet.model.Book;
import com.project.Books_Cabinet.model.Category;
import com.project.Books_Cabinet.repository.BookRepo;
import com.project.Books_Cabinet.repository.CategoryRepo;

@Controller
@SessionAttributes("SessionId")
public class BookController {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	BookRepo bookRepo;

	@GetMapping("/book/advertise/add")
	private String addBookForAdvertising(Model model) {
		
		if (model.getAttribute("SessionId") == null) {
			return "pageNotFound.html";
		}
		
		else {
			List<Category> allCategories = categoryRepo.findAll();
			
			model.addAttribute("allCategories", allCategories);
			
			return "/books/addBook.html";
		}
		
		
	}
	
	
	@PostMapping("/book/advertise/add")
	private String storeBookInforamtion(@ModelAttribute Book book, Model model, @RequestParam("image") MultipartFile multipartFile,HttpServletRequest request) throws IOException {
		
		if (model.getAttribute("SessionId") == null) {
			return "pageNotFound.html";
		}
		else {
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        book.setPhotos(fileName);
			
	        String sessionId = (String) request.getSession().getAttribute("SessionId");
	        book.setUserId(Integer.valueOf(sessionId));
	        
	        bookRepo.save(book);
	        
	        
	        String uploadDir = "./src/main/resources/static/image/";
	        
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	        
			
			System.out.println(book);
			return "redirect:/book/advertise/add";
		}
		
	}
	
	@GetMapping("/book/all")
	public String GetAllBooks(Model model) {
		 List<Book> allBooks = bookRepo.findAll();
		 model.addAttribute("allBooks", allBooks);
		return "/books/allBooks.html";
	}
	
	
	
}
