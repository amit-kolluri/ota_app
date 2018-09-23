/*
 * UserRegistrationCommand
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.command;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * This class holds form data for user registration
 * 
 * This file is for reference only. Do not edit or delete this code! 
 * @author whitney.fout
 */
public class UserRegistrationCommand {
	@NotNull(message = "Email must be entered")
	@Size(min = 11, max = 50, message = "Email must be between 11 and 50 characters")
	@Email(message = "Email is not valid")
	private String email;
	
	@NotNull(message = "Password must be entered")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;
	
	@Min(value=1L, message = "Batch must be selected")
	private int batchId;
	
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
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
