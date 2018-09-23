/*
 * User
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.domain;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

/**
 * This class provides a data model for all users of the OTA.
 * 
 * This file is only for reference only. Do not edit or delete this code!
 * 
 * @author karl.roth, nipun.dayanath
 */
public class User {
	/**
	 * Constants to assign meaningful names to role and status values.
	 */
	public static final int ROLE_TRAINEE = 1;
	public static final int ROLE_TRAINER = 2;
	public static final int ROLE_ADMIN = 3;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 2;

	/**
	 * Id of the user
	 */
	private int id;

	/**
	 * First name of the user
	 */
	@NotNull(message = "First name must be entered")
	@Size(max = 20, message = "First name cannot be greater than 20 characters")
	private String firstName;

	/**
	 * Surname of the user
	 */
	@NotNull(message = "Last name must be entered")
	@Size(max = 20, message = "Last name cannot be greater than 20 characters")
	private String lastName;

	/**
	 * Email of the user
	 */
	@NotNull(message = "Email must be entered")
	@Size(min = 11, max = 50, message = "Email must be between 11 and 50 characters")
	@Email(message = "Email is not valid")
	private String email;

	/**
	 * Login name of the user
	 */
	private String userName;

	/**
	 * Password for the user
	 */
	@NotNull(message = "Password must be entered")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;

	/**
	 * Phone number of the user
	 */
	@NotNull(message = "Phone number must be entered")
	@Size(max = 15, message = "Phone number cannot be greater than 15 characters")
	private String phoneNumber;

	/**
	 * Id of the batch the user belongs to
	 */
	@Min(value=1L, message = "Batch must be selected")
	private int batchId;

	/**
	 * The role of the user 1 for trainee 2 for trainer 3 for admin
	 */
	private int roleId;

	/**
	 * Status of the user 1 for active 2 for inactive
	 */
	private int statusId;

	/**
	 * The id of the user that activated this user
	 */
	private int createdBy;

	/**
	 * The date that the user was created
	 */
	private Date createdDate;

	/**
	 * The id of the user that modified this user
	 */
	private int modifiedBy;

	/**
	 * The date that the user was most recently modified
	 */
	private Date modifiedDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String user_name) {
		this.userName = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phone_number) {
		this.phoneNumber = phone_number;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batch_id) {
		this.batchId = batch_id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int role_id) {
		this.roleId = role_id;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int status_id) {
		this.statusId = status_id;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int created_by) {
		this.createdBy = created_by;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date created_date) {
		this.createdDate = created_date;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modified_by) {
		this.modifiedBy = modified_by;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modified_date) {
		this.modifiedDate = modified_date;
	}	
	
}
