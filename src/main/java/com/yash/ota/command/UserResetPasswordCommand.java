package com.yash.ota.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.AssertTrue;

/**
 * This class holds form data for user reset password form
 * @author almedin.mulalic
 */

public class UserResetPasswordCommand{
	
	@NotNull(message = "Password must be entered")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;
	
	@NotNull(message = "Password must be entered")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String confirmPassword;
	
	
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
