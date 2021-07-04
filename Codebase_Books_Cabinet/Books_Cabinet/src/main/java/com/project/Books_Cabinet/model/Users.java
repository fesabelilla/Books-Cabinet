package com.project.Books_Cabinet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserId;
	@Column(name = "FullName")
	private String FullName;
	@Column(unique = true)
	private String Email;
	private String Password;
	
	public Users() {
		super();
	}

	public Users(int userId, String fullName, String email, String password) {
		super();
		UserId = userId;
		FullName = fullName;
		Email = email;
		Password = password;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
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
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public String toString() {
		return "Person [UserId=" + UserId + ", FullName=" + FullName + ", Email=" + Email + "]";
	}
	
	

}
