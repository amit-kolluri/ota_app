/*
 * UserDAOImpl
 *
 * Version 0.1
 *
 * Date: 08/15/2018
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

import com.yash.ota.dao.UserDAO;
import com.yash.ota.domain.User;
import com.yash.ota.rowmapper.UserRowMapper;
import com.yash.ota.util.Queries;

/**
 * This implementation provides a data access object for the User data model.
 *
 * This file is for reference only. Do not edit or delete this code!
 */
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private Logger log = Logger.getLogger(UserDAOImpl.class);
			
	private UserRowMapper userRowMapper = new UserRowMapper();

	/**
	 * @author nipun.dayanath
	 */
	public UserRowMapper getUserRowMapper() {
		return userRowMapper;
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * @author karl.roth
	 */
	@Override
	public void insert(User user) {
		String sql = "INSERT INTO users (email,password,batch_id) values(?,?,?)";
		log.info(user.getUserName());
		Object[] params = 
				new Object[] { 
						user.getEmail(),
						user.getPassword(), 
						user.getBatchId()
				};
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * @author karl.roth
	 */
	@Override
	public List<User> list() {
		String sql = "SELECT * FROM users";
		return jdbcTemplate.query(sql, userRowMapper);
	}
	
	/**
	 * @author karl.roth
	 */
	@Override
	public User findById(int id) {
		String sql = "SELECT * FROM users WHERE id=?";
		return jdbcTemplate.queryForObject(sql, userRowMapper, id);
	}


	/**
	 * @author kanika gandhi
	 */
	@Override
	public void update(User user) {
		Object[] params =
				new Object[] {
						user.getFirstName(),
						user.getLastName(),
						user.getEmail(),
						user.getUserName(),
						user.getPassword(),
						user.getPhoneNumber(),
						user.getBatchId(),
						user.getRoleId(),
						user.getStatusId(),
						user.getCreatedBy(),
						user.getCreatedDate(),
						user.getModifiedBy(),
						user.getModifiedDate(),
						user.getId()
				};
		
		jdbcTemplate.update(Queries.UPDATE_USER,params);
				
	}

	/**
	 * @author karl.roth
	 */
	@Override
	public void delete(int userId) {
		String sql = "DELETE FROM users WHERE id=?";
		jdbcTemplate.update(sql, userId);
	}

	/**
	 * @author karl.roth
	 */
	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;
	}

	

}