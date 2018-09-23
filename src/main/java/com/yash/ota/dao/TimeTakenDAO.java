/**
 * TimeTakenDAO
 *
 * Version 0.1
 *
 * Date: 08/20/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.dao;

import com.yash.ota.domain.TimeTaken;
import com.yash.ota.rowmapper.TimeTakenRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * This interface is for the generic DAO object for timeTaken
 * @author connor.brown
 *
 */
public interface TimeTakenDAO {
	
	/**
	 * Used for JUnit testing
	 * @param mockedJdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);
	
	/**
	 * Used for JUnit testing
	 * @return TimeTakenRowMapper that is used by this TraineeDAO
	 */
	public TimeTakenRowMapper getTimeTakenRowMapper();
	
	/**
	 * Finds the amount of time a user took based on the test id
	 * @param id int the id of the test
	 * @return TimeTaken
	 */
	public TimeTaken findById(int id);

	/**
	 * Inserts a new timeTaken into the database
	 * @param timeTaken the object to be inserted
	 * @author connor.brown
	 */
	public void insert(TimeTaken timeTaken);
	
}
