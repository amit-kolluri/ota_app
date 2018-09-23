/*
 * UserDAO
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.domain.User;
import com.yash.ota.rowmapper.UserRowMapper;

/**
 * This interface provides a data access object for the User data model. 
 * 
 * This file is for reference only. Do not edit or delete this code! 
 * These are the only functions that should be allowed in the DAO layer.
 * All other functions should go in the Service layer.
 * 
 * @author karl.roth, nipun.dayanath
 */
public interface UserDAO {

	/**
	 * Method to insert a User into the database
	 * @param user User the user to be inserted
	 */
	public void insert(User user);
	
	/**
	 * Method returns a list of user objects from the database
	 * @return list of all users in database
	 */
	public List<User> list();
	
	/**
	 * Finds a user by the user's id.
	 * @param id int the user's id
	 * @return User 
	 */
	public User findById(int id);
	
	/**
	 * method to update a user in the database
	 * @param user
	 * @author kanika gandhi
	 */
	public void update(User user);
	
	/**
	 * Method to delete a test object from the database.
	 * @param userId
	 */
	public void delete(int userId);
 
	/**
	 * Used for JUnit testing
	 * @param mockedJdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);
	
	/**
	 * Used for JUnit testing
	 * @return UserRowMapper that is used by this UserDAO
	 */
	public UserRowMapper getUserRowMapper();
	
	
	
}
