/*
 * TechnologyDAO
 *
 * Version 1.0
 *
 * Date: 08/22/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.domain.Technology;
import com.yash.ota.rowmapper.TechnologyRowMapper;

import java.util.List;

/**
 * This interface provides a data access object for the Technology data model.
 *
 *
 * @author connor.brown, karl.roth
 */
public interface TechnologyDAO {
    /**
     * Method to insert a User into the database
     * @param technology to be inserted
     */
    public void insert(Technology technology);

    /**
     * Method returns a findAll of topic objects from the database
     * @return findAll of all technologies in database
     */
    public List<Technology> findAll();
    
    /**
	 * Finds a technology by its id
	 * @param id
	 * @return a technology
	 */
	public Technology findById(int id);
	
    /**
     * Method to update a topic object in the database.
     * @param technology
     */
    public void update(Technology technology);

    /**
     * Method to delete a technology object from the database.
     * @param technologyId
     */
    public void delete(int technologyId);

    /**
     * Used for JUnit technologying
     * @param mockedJdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);


    /**
     * Used for JUnit technologying
     * @return UserRowMapper that is used by this UserDAO
     */
    public TechnologyRowMapper getTechnologyRowMapper();
}
