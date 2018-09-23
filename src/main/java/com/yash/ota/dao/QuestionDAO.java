package com.yash.ota.dao;

import com.yash.ota.domain.Question;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.domain.Question;

/**
 * class to handle basic CRUD operations of Questions
 * @author Lhito Camson
 *
 */
public interface QuestionDAO {

	/**
	 * inserts the given question into the database
	 * @author Lhito Camson
	 */
	void insert(Question questionToBeInserted);

	void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);

	
	/**
     * Method to delete the Question in DB
     * @param question
     * @author Geon Gayles
     */
	public void delete(Question question);
	
	/**
	 * Method to update the Questopm
	 * @param question
	 * @author Geon Gayles
	 */
	public void update(Question question);
	

}
