package com.project.Books_Cabinet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class SellBooks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sellBookId;
	
	
	@NotNull
	private int sellerId;
	
	@NotNull
	private int bookId;
	
	@NotEmpty
	private String type;

	public SellBooks() {
		super();
	}

	public SellBooks(int sellBookId, @NotNull int sellerId, @NotNull int bookId, @NotEmpty String type) {
		super();
		this.sellBookId = sellBookId;
		this.sellerId = sellerId;
		this.bookId = bookId;
		this.type = type;
	}

	public int getSellBookId() {
		return sellBookId;
	}

	public void setSellBookId(int sellBookId) {
		this.sellBookId = sellBookId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
