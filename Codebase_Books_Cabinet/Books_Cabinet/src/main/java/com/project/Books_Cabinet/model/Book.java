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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;

	@NotNull
	//@NotBlank
	@Size(max = 70)
	private String bookName;

	@NotNull
	private int categoryId;

	@NotNull
	//@NotBlank
	private String writer;

	@NotNull
	//@NotBlank
	private int publishingYear;

	
	private double price;

	@NotNull
	//@NotBlank
	private String bookCondition;

	@NotNull
	//@NotBlank
	private String language;

	
	@Column(precision = 2, scale = 1)
	private double rating;

	
	private String bookSampleImage1;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int bookId, @NotNull @NotBlank @Size(max = 70) String bookName, @NotNull int categoryId,
			@NotNull @NotBlank String writer, @NotNull @NotBlank int publishingYear, @NotNull @NotBlank double price,
			@NotNull @NotBlank String bookCondition, @NotNull @NotBlank String language,
			@NotNull @NotBlank double rating, @NotNull @NotBlank String bookSampleImage1) {
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
		this.bookSampleImage1 = bookSampleImage1;
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

	public String getBookSampleImage1() {
		return bookSampleImage1;
	}

	public void setBookSampleImage1(String bookSampleImage1) {
		this.bookSampleImage1 = bookSampleImage1;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", categoryId=" + categoryId + ", writer=" + writer
				+ ", publishingYear=" + publishingYear + ", price=" + price + ", bookCondition=" + bookCondition
				+ ", language=" + language + ", rating=" + rating + ", bookSampleImage1=" + bookSampleImage1 + "]";
	}

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
//	private Category category;

	
	
}
