/*
 * TechnologyRowMapper
 *
 * Version 0.1
 *
 * Date: 08/18/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.rowmapper;

import com.yash.ota.domain.Technology;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides object relational mapping functionality.
 *
 * @author karl.roth
 */
@Component
public class TechnologyRowMapper implements RowMapper<Technology> {

	@Override
	public Technology mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Technology tech = new Technology();
		tech.setId(resultSet.getInt("id"));
		tech.setName(resultSet.getString("name"));
		tech.setCreatedBy(resultSet.getInt("created_by"));
		tech.setCreatedDate(resultSet.getDate("created_date"));
		tech.setModifiedBy(resultSet.getInt("modified_by"));
		tech.setModifiedDate(resultSet.getDate("modified_date"));
		
		return tech;
	}
}
