package com.project.Books_Cabinet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class OrderTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@NotNull
	private int userId;
	
	@NotNull
	private int sellerId;
	
	@NotNull
	private double price;
	
	@NotNull
	private int bookID;
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String sellerName;
	
	@NotEmpty
	private String userPhoneNo;
	
	@NotEmpty
	private String sellerPhoneNo;
	
	@NotEmpty
	private String userAddress;
	
	@NotEmpty
	private String sellerAddress;
	
	@NotEmpty
	private String userType;
	
	@NotEmpty
	private String sellerType;
	
	@NotNull
	private double deliveryPrice;
	
	@NotNull
	private double totalPrice;
	
	@NotEmpty
	private String orderStatus;
	
	@NotEmpty
	private String bookName;
	
	@Column(nullable = true, length = 64)
    private String photos;

	public OrderTable() {
		super();
	}

	public OrderTable(int orderId, @NotNull int userId, @NotNull int sellerId, @NotNull double price,
			@NotNull int bookID, @NotEmpty String userName, @NotEmpty String sellerName, @NotEmpty String userPhoneNo,
			@NotEmpty String sellerPhoneNo, @NotEmpty String userAddress, @NotEmpty String sellerAddress,
			@NotEmpty String userType, @NotEmpty String sellerType, @NotNull double deliveryPrice,
			@NotNull double totalPrice, @NotEmpty String orderStatus,String photos,@NotNull @Size(max = 70) String bookName) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.sellerId = sellerId;
		this.price = price;
		this.bookID = bookID;
		this.userName = userName;
		this.sellerName = sellerName;
		this.userPhoneNo = userPhoneNo;
		this.sellerPhoneNo = sellerPhoneNo;
		this.userAddress = userAddress;
		this.sellerAddress = sellerAddress;
		this.userType = userType;
		this.sellerType = sellerType;
		this.deliveryPrice = deliveryPrice;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
		this.bookName = bookName;
		this.photos = photos;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public String getSellerPhoneNo() {
		return sellerPhoneNo;
	}

	public void setSellerPhoneNo(String sellerPhoneNo) {
		this.sellerPhoneNo = sellerPhoneNo;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSellerType() {
		return sellerType;
	}

	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}

	public double getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	
}
