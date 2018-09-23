
/**
 * TopicDAO
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

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.domain.Question;
import com.yash.ota.domain.Topic;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.domain.Test;

import com.yash.ota.rowmapper.TopicRowMapper;

/**
 * This interface provides a data access object for the Test data model.
 *
 *
 * @author connor.brown, fahmida.joyti
 */
public interface TopicDAO {

	/**
	 * Method to insert a User into the database
	 * 
	 * @param topic
	 *            the test to be inserted
	 */
	public void insert(Topic topic);

	/**
	 * Method returns a findAll of topic objects from the database
	 * 
	 * @return findAll of all users in database
	 */
	public List<Topic> findAll();

	/**
	 * Finds a topic by the topic's id.
	 * 
	 * @param id
	 *            int the user's id
	 * @return User
	 */
	public Topic findById(int id);

	/**
	 * Method to delete a topic object from the database.
	 * 
	 * @param topicId
	 */
	public void delete(int topicId);

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
	public TopicRowMapper getTopicRowMapper();

	/**
	 * method for updating topics
	 * 
	 * @author Lalu.
	 * @param topic
	 */
	public void update(Topic topic);

	/**
	 * method to get list of topics
	 * 
	 * @author Lalu.
	 * @return
	 */
	public List<Topic> list();

}
