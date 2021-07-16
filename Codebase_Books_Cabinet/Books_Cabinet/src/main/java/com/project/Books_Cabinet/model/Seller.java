package com.project.Books_Cabinet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;


@Entity
public class Seller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sellerId")
	private int sId;
	
	@NotEmpty(message =  "Must not be empty")
	@Size(min = 5, message = "The Full Name should have at least 5 characters")
	private String fullName;
	
	@NotEmpty(message =  "Must not be empty")
	@Column(unique=true)
	@Size(min = 11, max = 11, message = "Phone number should be 11 digits")
	private String phoneNumber;
	
	@NotEmpty(message =  "Must not be empty")
	private String address;
	@NotEmpty(message =  "Must not be empty")
	private String birthday;
	@NotEmpty(message =  "Select seller type")
	private String sellerType;
	@NotEmpty(message =  "Select gender")
	private String gender;
	
	@NotEmpty(message =  "Must not be empty")
	@Column(unique=true)
	private String nid;
	
	@NotEmpty(message =  "Must not be empty")
	@Column(unique=true)
	private String email;
	
	@NotEmpty(message =  "Must not be empty")
	@Size(min = 8, message = "The Full Name should have at least 8 characters")
	private String password;
	
	
	public Seller() {
		super();
	}


	public Seller(int sId, String fullName, String phoneNumber, String address, String birthday, String sellerType,
			String gender, String nid, String email, String password) {
		super();
		this.sId = sId;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.birthday = birthday;
		this.sellerType = sellerType;
		this.gender = gender;
		this.nid = nid;
		this.email = email;
		this.password = password;
	}


	public int getsId() {
		return sId;
	}


	public void setsId(int sId) {
		this.sId = sId;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
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


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getSellerType() {
		return sellerType;
	}


	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getNid() {
		return nid;
	}


	public void setNid(String nid) {
		this.nid = nid;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	

}
