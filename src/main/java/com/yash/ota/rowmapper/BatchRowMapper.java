/*
 * BatchRowMapper
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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.yash.ota.domain.Batch;

/**
 * This class provides object relational mapping functionality.
 *
 * @author karl.roth
 */
@Component
public class BatchRowMapper implements RowMapper<Batch> {

	@Override
	public Batch mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Batch batch = new Batch();
		
		batch.setId(resultSet.getInt("id"));
		batch.setName(resultSet.getString("name"));
		batch.setDescription(resultSet.getString("description"));
		batch.setStatusId(resultSet.getInt("status_id"));
		batch.setCreatedBy(resultSet.getInt("created_by"));
		batch.setCreatedDate(resultSet.getDate("created_date"));
		batch.setModifiedBy(resultSet.getInt("modified_by"));
		batch.setModifiedDate(resultSet.getDate("modified_date"));
		
		return batch;
	}
}
