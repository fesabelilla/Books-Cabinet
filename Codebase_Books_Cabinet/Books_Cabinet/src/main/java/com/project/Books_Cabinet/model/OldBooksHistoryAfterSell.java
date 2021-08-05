package com.project.Books_Cabinet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class OldBooksHistoryAfterSell {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OldBooksHistoryAfterSellId;
	
	@NotNull
	private int bookId;

	@NotEmpty
	private String bookName;

	@NotNull
	private int categoryId;

	@NotEmpty
	private String writer;

	@NotNull
	private int publishingYear;

	
	@NotNull
	private double price;

	@NotEmpty
	private String bookCondition;

	@NotEmpty
	private String language;

	
	@Column(precision = 2, scale = 1)
	private double rating;
	
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	@NotNull
	private int userId;

	public OldBooksHistoryAfterSell() {
		super();
	}

	public OldBooksHistoryAfterSell(int oldBooksHistoryAfterSellId, @NotNull int bookId, @NotEmpty String bookName,
			@NotNull int categoryId, @NotEmpty String writer, @NotNull int publishingYear, @NotNull double price,
			@NotEmpty String bookCondition, @NotEmpty String language, double rating, String photos,
			@NotNull int userId) {
		super();
		OldBooksHistoryAfterSellId = oldBooksHistoryAfterSellId;
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

	public int getOldBooksHistoryAfterSellId() {
		return OldBooksHistoryAfterSellId;
	}

	public void setOldBooksHistoryAfterSellId(int oldBooksHistoryAfterSellId) {
		OldBooksHistoryAfterSellId = oldBooksHistoryAfterSellId;
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
