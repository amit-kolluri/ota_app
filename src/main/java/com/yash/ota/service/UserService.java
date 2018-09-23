/*
 * UserService
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.UserDAO;
import com.yash.ota.domain.Test;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.User;
import com.yash.ota.exception.DuplicateUserException;
import com.yash.ota.exception.UserAuthenticationException;
import com.yash.ota.rowmapper.UserRowMapper;

/**
 * This interface provides a service object for the User data model. 
 * 
 * This file is for reference only. Do not edit or delete this code! 
 * @author karl.roth
 */
public interface UserService {
	
	
	/**
	 * Authenticates user, by user_name and password
	 * @param userName String login name for the user.
	 * @param password String password for the user
	 * @return User that was logged in.
	 * @throws UserAuthenticationException 
	 */
	public User login(String userName, String password) throws UserAuthenticationException;
	
	/**
	 * Adds a User to the database
	 * @param user User the user to be registered.
	 * @throws DuplicateUserException 
	 * @author whitney.fout
	 */
	public void register(User user) throws DuplicateUserException;

	
	/**
	 * Returns a list of test results for the given user
	 * @param user
	 * @return a list of test results
	 */
	public List<Result> getResultsForUser(User user); 
	
	
	/**
	 * Used for JUnit testing in ServiceImplTest.class
	 * @param mockedDAO
	 */
	public void setDAO(UserDAO mockedDAO);
	/**
	 *  update the testname and duratipn
	 * @param test
	 */
	public void updateTest(Test test);

	
	/**
	 * Finding user, by email
	 * @param String email of the user.
	 * @return User redirects to reset password.
	 */
	public User findUserByEmail(String email);
	
	
	/**
	 * User needs to enter the new password
	 * @param String password of the user.
	 * @return User will get updated with new password.
	 */
	public void resetPassword(String password, int id);
	
	/**
	 * @param id of User
	 * @return User with matching id
	 * @author Almedin
	 */
	public User findById(int id);

	/**
	 * Used to return counts of the given attribute. If an unhandled attribute has been inputted, returns null
	 * @param attribute a string listing the attribute to be counted
	 * @return Integer count of the given attribute
	 * @author Michael Arp
	 */
	public Object getCountOfAttribute(String attribute);
  
  /**
	 * Getting trainees list from the batch ID
	 * @param batchId for which trainees are to be listed
	 * @return List of users that belong to the batchId specified
	 * @author - Jay Shah, scotrenz
	 */
    List<User> getTraineesByBatchId(int batchId);

    /**
     * gets all users along with their profile images
     * @return List of users
     * @author phoutsaksit.keomala
     */
    public List<User> getAllCreators();

	/**
	 * Setting the mockedJdbcTemplate object
	 * @param mockedJdbcTemplate
	 * @author - Jay Shah
	 */
	void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);

	/**
	 * Getting the user object using UserRowMapper
	 * @return UserRowMapper
	 * @author - Jay Shah
	 */
	UserRowMapper getUserRowMapper();
	
	/**
	 * Returns total number of trainess trained by Trainer
	 * @param user
	 * @return total number of trainees
	 * @author Geon Gayles
	 */
	public int getTotalTraineesByTrainer(User user);
	
	/**
	 * update the trainee details
	 * @param trainee
	 * @author kanika gandhi
	 */

	public void updateUser(User trainee);

	/**
	 * Set the userRowMapper
	 * @param userRowMapper
	 */
	public void setUserRowMapper(UserRowMapper userRowMapper);

	
	/**
	 * Method to get a list of all trainees from database
	 * @return List of User Objects with role_id of (1) "trainee" ordered by created_date
	 * @author neel.patel
	 */
	public List<User> getAllTrainees();

	/**
	 * Method to call delete method from userDAO
	 * @param userId Id of user to be deleted
	 * @author neel.patel
	 */
	public void deleteUser(int userId);

	/**
	 * getting awaiting users list for trainer to approve requests
	 * @return list of users waiting for trainer approval
	 * @author amit kolluri
	 */

	public void approveUser(int userId);

	/**
	 * used for getting list of awaiting users for trainer approval
	 * @return list of users waiting for trainer approval
	 * @author amit kolluri
	 *
	 */
	public List<User> getAwaitingUsersList();

}

