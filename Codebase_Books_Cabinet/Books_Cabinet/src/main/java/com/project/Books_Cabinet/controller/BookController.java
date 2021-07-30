package com.project.Books_Cabinet.controller;

import java.io.IOException;
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
import com.project.Books_Cabinet.repository.BookRepo;
import com.project.Books_Cabinet.repository.CategoryRepo;

@Controller
@SessionAttributes("SessionId")
public class BookController {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	BookRepo bookRepo;

	@RequestMapping("/addBook")
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
			HttpServletRequest request , RedirectAttributes redirectAttributes) throws IOException {
	
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
			        book.setUserId(Integer.valueOf(sessionId));
			        
			        bookRepo.save(book);
			        
			        System.out.println("Books : "+ book);
			        
			        String uploadDir = "./src/main/resources/static/images/";
			        
			        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			        
					redirectAttributes.addFlashAttribute("bookAddMessage","Book Added Successfully");
					redirectAttributes.addFlashAttribute("alertClass","alert-success");
			        
					
					return "redirect:/addBook";	
				
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			
			System.out.println(e);
			return "/books/addBook.html";
		}
		
		
	}
		
	
}
