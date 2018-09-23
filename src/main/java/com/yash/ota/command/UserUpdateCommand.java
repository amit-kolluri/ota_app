package com.yash.ota.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * This class holds form data for user update information form
 * @author almedin.mulalic
 *
 */
public class UserUpdateCommand {
	@NotNull(message = "First name must be entered")
	@Size(max = 20, message = "First name cannot be greater than 20 characters")
	private String firstName;
	
	@NotNull(message = "Last name must be entered")
	@Size(max = 20, message = "Last name cannot be greater than 20 characters")
	private String lastName; 
	
	@NotNull(message = "Email must be entered")
	@Size(min = 11, max = 50, message = "Email must be between 11 and 50 characters")
	@Email(message = "Email is not valid")
	private String email;
	
	@NotNull(message = "Phone number must be entered")
	@Size(max = 15, message = "Phone number cannot be greater than 15 characters")
	private String phoneNumber;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
