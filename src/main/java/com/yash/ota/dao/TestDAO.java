/**
 * TestDAO
 *
 * Version 0.1
 *
 * Date: 08/18/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.dao;

import com.yash.ota.domain.Test;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.rowmapper.TestRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * This interface provides a data access object for the Test data model. All
 * other functions should go in the Service layer.
 *
 * @author connor.brown,fahmida.joyti
 */
public interface TestDAO {


	/**
	 * Method to insert a Test into the database
	 * 
	 * @param test
	 *            the test to be inserted author: Madhuri Vutukury
	 */
	public void insert(Test test);

	/**
	 * Method returns a findAll of test objects from the database
	 * 
	 * @return findAll of all users in database
	 */
	public List<Test> findAll();

	/**
	 * Finds the test by its id
	 * 
	 * @param id
	 *            int the id of the test
	 * @return Test
	 * @throws InvalidIdException
	 *             if id <= 0
	 */
	public Test findById(int id) throws InvalidIdException;

	/**
	 * Method to update a test object in the database.
	 * 
	 * @param test
	 */
	public void update(Test test);

	/**
	 * Method to delete a test object from the database.
	 * 
	 * @param testId
	 */
	public void delete(int testId);

	/**
	 * Used for JUnit testing
	 * 
	 * @param mockedJdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);

	/**
	 * Used for JUnit testing
	 * 
	 * @return UserRowMapper that is used by this UserDAO
	 */
	public TestRowMapper getTestRowMapper();

}
