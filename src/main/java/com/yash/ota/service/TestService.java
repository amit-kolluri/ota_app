
/**
 * TestService
 *
 * Version 0.1
 *
 * Date: 08/18/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.service;
import com.yash.ota.dao.TestDAO;
import com.yash.ota.domain.EndOfTestResult;
import com.yash.ota.domain.Test;
 import com.yash.ota.rowmapper.ResultRowMapper;


import com.yash.ota.domain.User;

import com.yash.ota.exception.InvalidIdException;

import com.yash.ota.rowmapper.TestRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * This interface provides a service object for the User data model.
 * 
 * @author karl.roth connor.brown,fahmida.joyti
 */
public interface TestService {
	
	/**
	 * Returns list of maps associated with given CreatedById, 
	 * each of which contains a test alloted time, test id, topic name, and technology name
	 * 
	 * @param createdBy id of who created the test
	 * @return list of maps
	 * @author Zachary Karamanlis
	 */
	public List<Map<String, Object>> getTestsWithTopicNameAndTechName(int createdBy);

	/**
	 * Used for JUnit testing
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	/**
	 * Used for JUnit testing in ServiceImplTest.class
	 * @param testDAO
	 */
	public void setTestDAO(TestDAO testDAO);

	/**
	 * returns a list of active tests that the user has not completed by their user id
	 * @param userId int the user id
	 * @return List/<Test/> the list of tests
	 * @throws InvalidIdException if userId <= 0
	 * @author caleb.reiter
	 */
	List<Test> getAssignedTestsByUserId(int userId) throws InvalidIdException;

	/**
	 * Used for JUnit testing in TestServiceImplTest.class
	 * 
	 * @return TestRowMapper
	 */
	public TestRowMapper getTestRowMapper();

	/**
	 * updates status of given test by given status
	 * 
	 * @param testId
	 *            test to update
	 * @param statusId
	 *            status to set on test
	 * @author Zachary Karamanlis
	 */
	public void updateStatus(int testId, int statusId);
	
	public void setDao(TestDAO dao);

	
	/**
	 * update the test duration
	 * @author Lalu.
	 */

	public void update(Test test);
 
	/**
	 * @return:TestObject
	 * @author:Lalu.
	 * @throws InvalidTestIdException 
	 * @throws InvalidIdException 
	 */
	
	public Test findById(int id) throws  InvalidIdException;

	public List<Test> getAvailableTestsForTopics();

	
	/**
	 * Finds a test by its id
	 * @param testId int the id of the test
	 * @return Test
	 * @author connor.brown
	 * @throws InvalidIdException
	 */
	public Test getTestById(int testId) throws InvalidIdException;
	
	/**
	 * returns test by id from the database
	 * @param testId int the Test Id
	 * @return Test the test
	 * @throws InvalidIdException if testId <= 0 or no test entry for testId
	 * @author caleb.reiter
	 */


	public List<Test> listAll();
	
	/**
	 * Method to return the total number of Tests by Trainer
	 * @param user
	 * @return
	 * @author Geon Gayles
	 */
	public int getTotalNumberOfTestsByTrainer(User user);

	

	Test getAssignedTestById(int testId) throws InvalidIdException;
	
	/**
	 * Export and individual test to an excel file
	 * 
	 * @param testId test to export
	 * @author Zachary Karamanlis
	 */
	public void exportToExcel(int testId);
	
	/**
	 * Method to insert a Test into the database
	 * @param test the test to be inserted
	 * @author   Madhuri Vutukury
	 */
	public void insertTest(Test test);
	
	/**
	 * returns the test by the topic id from the database
	 * @param topicId id of the topic
	 * @return list of tests with a specific topic id
	 * @author phoutsaksit.keomala
	 */
	public List<Test> getTestsByTopicId(int topicId);

	/**
	 * returns all tests 
	 * @return list of all tests
	 * @author phoutsaksit.keomala
	 */
	public List<Test> getTests();

}
