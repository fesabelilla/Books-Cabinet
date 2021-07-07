package com.project.Books_Cabinet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Users {
	
	@Id
	private int UserId;
	@Column(name = "FullName")
	@Size(min = 4, max = 40)
	private String FullName;
	@Column(unique = true)
	private String email;
	private String password;
	private String userType;
	
	public Users() {
		super();
	}

	public Users(int userId, String fullName, String email, String password, String userType) {
		super();
		UserId = userId;
		FullName = fullName;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

}
