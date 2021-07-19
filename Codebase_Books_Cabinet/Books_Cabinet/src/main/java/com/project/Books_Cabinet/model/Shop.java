package com.project.Books_Cabinet.model;

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
	
	
}
