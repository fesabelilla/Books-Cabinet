package com.project.Books_Cabinet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shopId;
	
	@Size(min=5, max=40)
	@Column(unique = true)
	private String shopName;
	
	@Size(min=5, max=40)
	private String owner;
	
	@Size(max=50)
	private String type;
	
	@Size(min=11, max=14)
	private String phoneNumber;
	
	@Size(min=11, max=200)
	private String address;
	
	@Size(max=10)
	private String status;

	
	public Shop() {
		
	}
	
	public Shop(int shopId, @Size(min = 5, max = 40) String shopName, @Size(min = 5, max = 40) String owner,
			@Size(max = 50) String type, @Size(min = 11, max = 14) String phoneNumber,
			@Size(min = 11, max = 200) String address, @Size(max = 10) String status) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.owner = owner;
		this.type = type;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", shopName=" + shopName + ", owner=" + owner + ", type=" + type
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", status=" + status + "]";
	}
	
	
	
}
