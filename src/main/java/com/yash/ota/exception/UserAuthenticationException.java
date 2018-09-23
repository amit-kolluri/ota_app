/*
 * UserAuthenticationException
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: Karl
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.exception;

/**
 * This class provides a User Authentication Exception for the User login.
 *
 * This file is for reference only. Do not edit or delete this code!
 * @author karl.roth
 */
public class UserAuthenticationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAuthenticationException() {
		
	}
	
	public UserAuthenticationException(String errorMessage) {
		super(errorMessage);
	}
}
