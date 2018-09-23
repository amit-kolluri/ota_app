/*
 * TimeTakenRowMapper
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

import com.yash.ota.domain.TimeTaken;

/**
 * This class provides object relational mapping functionality.
 * 
 * @author connor.brown
 *
 */
@Component
public class TimeTakenRowMapper implements RowMapper<TimeTaken>{

	@Override
	public TimeTaken mapRow(ResultSet resultSet, int numRow) throws SQLException {
		TimeTaken timeTaken = new TimeTaken();
		timeTaken.setId(resultSet.getInt("id"));
		timeTaken.setTestId(resultSet.getInt("test_id"));
		timeTaken.setUserId(resultSet.getInt("user_id"));
		timeTaken.setResultId(resultSet.getInt("result_id"));
		timeTaken.setTimeTaken(resultSet.getTime("time_taken"));
		return timeTaken;
	}

}
