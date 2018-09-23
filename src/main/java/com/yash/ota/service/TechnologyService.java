/*
 * TechnologyService
 *
 * Version 0.1
 *
 * Date: 08/20/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.service;

import java.util.List;
import com.yash.ota.domain.Technology;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.TechnologyDAO;
import com.yash.ota.rowmapper.TechnologyRowMapper;

import java.util.List;

/**
 * This interface provides a service object for the Technology data model. 
 * 
 * @author karl.roth, phoutsaksit.keomala
 */
public interface TechnologyService {


	/**
	 * List all technologies from the database given a user id
	 * @return List<Technology> current list of technologies for a user
	 */
	public List<Technology> listTechForUser(int userId);
	/**
	 * Finds a technology by its id
	 * @param id
	 * @return a technology
	 */
	public Technology getTechnologyById(int id);
	/**
	 * Used for Testing
	 * @param mockedDAO
	 */
	public void setDAO(TechnologyDAO mockedDAO); 
    
    /**
	 * Used for JUnit testing
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	
	/**
	 * Used for JUnit testing in ServiceImplTest.class
	 * @param technologyDAO
	 */
	public void setTechnologyDAO(TechnologyDAO technologyDAO);
	
	/**
	 * Used for JUnit testing in TestServiceImplTest.class
	 * @return TechnologyRowMapper
	 */
	public TechnologyRowMapper getTechnologyRowMapper();


	/**
	 * Method to get list of technologies from the database
	 * @author Madhuri.Vutukury
	 */

	public List<Technology> findAll();

	/**
	 * gets list of all technologies with an associated test
	 * 
	 * 
	 * @return list of technologies
	 */
	public List<Technology> getTechnologies();
	
	/**
	 * Returns the technology Id for a particular technology
	 * @param technologyName valid name of the technology
	 * @return technologyId Id corresponding to the particular name
	 * @author - Jay Shah
	 */
    int getTechnologyByName(String technologyName);
}
	
