package com.project.Books_Cabinet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loginId;
	@Column(unique = true, nullable = false)
	@NotNull
	@Email
	@NotEmpty
	private String email;
	@Size(min=4, max=32)
	private String password;
	@NotNull
	private String userType;
	
	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(int loginId, String email, String password, String userType) {
		super();
		this.loginId = loginId;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
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

	@Override
	public String toString() {
		return "Login [loginId=" + loginId + ", email=" + email + ", password=" + password + ", userType=" + userType
				+ "]";
	}
	
	
	

}
