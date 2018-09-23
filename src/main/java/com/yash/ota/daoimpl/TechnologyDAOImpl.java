/*
 * TechnologyDAOImpl
 *
 * Version 1.0
 *
 * Date: 08/22/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yash.ota.dao.TechnologyDAO;
import com.yash.ota.domain.Technology;
import com.yash.ota.rowmapper.TechnologyRowMapper;
import com.yash.ota.util.Queries;

import java.util.List;

/**
 * This implementation provides a data access object for the User data model.
 * @author karl.roth
 */
@Repository
public class TechnologyDAOImpl implements TechnologyDAO {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	private Logger log = Logger.getLogger(TechnologyDAOImpl.class);
	private TechnologyRowMapper technologyRowMapper = new TechnologyRowMapper();
	
	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * @author karl.roth
	 */	
	@Override
	public void insert(Technology technology) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method get list of technologies
	 * @return
	 * @author Madhuri Vutukury
	 */
	@Override
	public List<Technology> findAll() {
		log.info("list of technologies");
		String sql = "SELECT * FROM technologies";
		return jdbcTemplate.query(sql, technologyRowMapper);
	}


	/**
	 * @author karl.roth
	 */
	@Override
	public Technology findById(int id) {
		String sql = Queries.FIND_TECHNOLOGY_BY_ID;
		return jdbcTemplate.queryForObject(sql, technologyRowMapper, id);
	}

	@Override
	public void update(Technology technology) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int topicId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;
	}


	@Override
	public TechnologyRowMapper getTechnologyRowMapper() {
		return technologyRowMapper;
	}
	
	public void setTechnologyRowMapper(TechnologyRowMapper technologyRowMapper) {
		this.technologyRowMapper = technologyRowMapper;
	}


}
