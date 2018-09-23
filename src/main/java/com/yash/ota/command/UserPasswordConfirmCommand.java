package com.yash.ota.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class holds form data for  password confirmation
 * 
 * 
 * @author harish
 */
public class UserPasswordConfirmCommand {
	
	@NotNull(message = "Password must be entered")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")

	private String password;
	
	
	private String confirmPassword;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
