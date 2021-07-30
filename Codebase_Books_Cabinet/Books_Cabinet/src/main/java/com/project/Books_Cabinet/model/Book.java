package com.project.Books_Cabinet.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;

	@NotEmpty(message =  "Must not be empty")
	private String bookName;

	@NotNull
	private int categoryId;

	@NotEmpty(message =  "Must not be empty")
	private String writer;

	@NotNull(message =  "Must not be empty")
	private int publishingYear;

	
	@NotNull(message =  "Must not be empty")
	private double price;

	@NotEmpty(message =  "Must not be empty")
	private String bookCondition;

	@NotEmpty(message =  "Must not be empty")
	private String language;

	
	@Column(precision = 2, scale = 1)
	private double rating;
	
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	@NotNull
	private int userId;

	
	//private String bookSampleImage1;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Book(int bookId, @NotNull @Size(max = 70) String bookName, @NotNull int categoryId, @NotNull String writer,
			@NotNull int publishingYear, double price, @NotNull String bookCondition, @NotNull String language,
			double rating, String photos,@NotNull int userId) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.categoryId = categoryId;
		this.writer = writer;
		this.publishingYear = publishingYear;
		this.price = price;
		this.bookCondition = bookCondition;
		this.language = language;
		this.rating = rating;
		this.photos = photos;
		this.userId = userId;
	}


	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public int getPublishingYear() {
		return publishingYear;
	}


	public void setPublishingYear(int publishingYear) {
		this.publishingYear = publishingYear;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getBookCondition() {
		return bookCondition;
	}


	public void setBookCondition(String bookCondition) {
		this.bookCondition = bookCondition;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getPhotos() {
		return photos;
	}


	public void setPhotos(String photos) {
		this.photos = photos;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	
	
}
