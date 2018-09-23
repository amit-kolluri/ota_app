/**

 * TechnologyServiceImpl
 * 
 * Version 1.0
 * 
 * Date: 08/23/2018
 * 
 * Author: YASH Trainees
 * 
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.serviceimpl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yash.ota.dao.TechnologyDAO;
import com.yash.ota.domain.Technology;
import com.yash.ota.rowmapper.TechnologyRowMapper;
import com.yash.ota.service.TechnologyService;
import com.yash.ota.util.Queries;

/**
 *  This implementation provides a service object for the technology data model.
 *
 * 
 * @author connor.brown, karl.roth, phoutsaksit.keomala
 *
 */
@Service
public class TechnologyServiceImpl implements TechnologyService{

	@Autowired
	private TechnologyDAO technologyDAO;

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
    private TechnologyRowMapper technologyRowMapper = new TechnologyRowMapper();
    private Logger log = Logger.getLogger(TechnologyServiceImpl.class);

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Technology> listTechForUser(int userId) {
		return jdbcTemplate.query(Queries.GET_TECH_FOR_USER, technologyRowMapper, userId);
	}

	@Override
	public Technology getTechnologyById(int id) {
		return technologyDAO.findById(id);
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void setTechnologyDAO(TechnologyDAO technologyDAO) {
		this.technologyDAO = technologyDAO;
	}

	@Override
	public TechnologyRowMapper getTechnologyRowMapper() {
		return this.technologyRowMapper;
	}

	/**
	 * @author Madhuri Vutukury
	 */
	@Override
	public List<Technology> findAll() {
		log.info("list of technogies");
		return technologyDAO.findAll();
	}

	@Override
	public void setDAO(TechnologyDAO mockedDAO) {
		this.technologyDAO = mockedDAO;
	}

	/**
	 * author:Madhuri Vutukury
	 */
	@Override
	public List<Technology> getTechnologies() {
		log.info("list of technologies");
		return technologyDAO.findAll();
	}

	@Override
	public int getTechnologyByName(String technologyName) {
		return jdbcTemplate.queryForObject("Select id from technologies where name = ?", Integer.class, technologyName);
	}

}
