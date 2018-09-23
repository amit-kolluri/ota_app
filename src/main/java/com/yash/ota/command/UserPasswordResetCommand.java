package com.yash.ota.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class holds form data for  password reset
 * 
 * 
 * @author harish
 */
public class UserPasswordResetCommand {
	
	private String email;
	
	@NotNull(message = "Password must be entered")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;

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
