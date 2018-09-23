/*
 * ResultRowMapper
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

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.yash.ota.domain.Result;
import org.springframework.stereotype.Component;

/**
 * This class provides object relational mapping functionality.
 *
 * @author karl.roth
 */
@Component
public class ResultRowMapper implements RowMapper<Result> {

	private Logger log = Logger.getLogger(ResultRowMapper.class);

	public Result mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Result result = new Result();
		result.setId(resultSet.getInt("id"));
		result.setUserId(resultSet.getInt("user_id"));
		result.setTestId(resultSet.getInt("test_id"));
		result.setTopicId(resultSet.getInt("topic_id"));
		result.setTechnologyId(resultSet.getInt("technology_id"));
		result.setTotalMarks(resultSet.getInt("total_marks"));
		result.setObtainedMarks(resultSet.getInt("obtained_marks"));
		result.setStatusId(resultSet.getInt("status_id"));
		result.setCreatedBy(resultSet.getInt("created_by"));
		result.setCreatedDate(resultSet.getDate("created_date"));
		result.setModifiedBy(resultSet.getInt("modified_by"));
		result.setModifiedDate(resultSet.getDate("modified_date"));
		try{
			result.setTimeTaken(resultSet.getTime("time_taken"));
		}
		catch (Exception e){
			log.info("Time_taken not joined from time_taken table " + e.getMessage());
		}
		return result;
	}

}
