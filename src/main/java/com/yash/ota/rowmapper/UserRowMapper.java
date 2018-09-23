/*
 * UserRowMapper
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.rowmapper;

import com.yash.ota.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This class provides object relational mapping functionality.
 *
 * This file is for reference only. Do not edit or delete this code!
 * @author nipun.dayanath
 */
public class UserRowMapper implements RowMapper<User> {
			
	@Override
	public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		User user =  new User();
		user.setId(resultSet.getInt("id"));
		user.setFirstName(resultSet.getString("first_name"));
		user.setLastName(resultSet.getString("last_name"));
		user.setEmail(resultSet.getString("email"));
		user.setUserName(resultSet.getString("user_name"));
		user.setPassword(resultSet.getString("password"));
		user.setPhoneNumber(resultSet.getString("phone_number"));
		user.setBatchId(resultSet.getInt("batch_id"));
		user.setRoleId(resultSet.getInt("role_id"));
		user.setStatusId(resultSet.getInt("status_id"));
		user.setCreatedBy(resultSet.getInt("created_by"));
		user.setCreatedDate(resultSet.getDate("created_date"));
		user.setModifiedBy(resultSet.getInt("modified_by"));
		user.setModifiedDate(resultSet.getDate("modified_date"));
		
		return user;
	}

}
