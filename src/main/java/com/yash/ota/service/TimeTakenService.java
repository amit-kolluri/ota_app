package com.yash.ota.service;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.TimeTakenDAO;
import com.yash.ota.domain.TimeTaken;
import com.yash.ota.rowmapper.TimeTakenRowMapper;

/**
 * This interface provides a service object for the TimeTaken data model.
 *
 * @author connor.brown
 */
public interface TimeTakenService {

    public void setDAO(TimeTakenDAO mockedDAO);
/*
     * Gets the timeTaken based on the id
     * @param id by timeTaken
     * @return TimeTaken based on id
     */
    TimeTaken getTimeTakenById(int id);
    
    /**
	 * Used for JUnit testing
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	/**
	 * Used for JUnit testing in ServiceImplTimeTaken.class
	 * @param timeTakenDAO
	 */
	public void setTimeTakenDAO(TimeTakenDAO timeTakenDAO);
	
	/**
	 * Used for JUnit testing in TimeTakenServiceImplTimeTaken.class
	 * @return TimeTakenRowMapper
	 */
	public TimeTakenRowMapper getTimeTakenRowMapper();
    
    /**
	 * Function to retrieve a TimeTaken object with the given id,
	 * pulls list of TimeTakens from dao layer
	 *
	 * @param id The id of the user in question
	 * @return Returns the TimeTaken object with the given id if found, null otherwise
	 * @author nick.parker
	 */
	public TimeTaken findTimeTakenById(int id);
    
    /**
     * Gets the TimeTaken object by test id and user id
     * @param testId the test id of the object
     * @param userId the user if of the object
     * @return TimeTaken
     * @author connor.brown
     * @throws Exception 
     */
    public TimeTaken getTimeTakenByTestIdAndUserId(int testId, int userId) throws Exception;
    
    /**
     * Adds a new time taken object to the database
     * @param testId The test id of the new object
     * @param userId The user id of the new object
     * @param timeTaken the time taken in milliseconds
     * @author connor.brown
     */
	public void addTimeTaken(int testId, int userId, long timeTaken);

}
