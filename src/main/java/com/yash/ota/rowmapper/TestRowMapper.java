/*
 * TestRowMapper
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
import org.springframework.stereotype.Component;

import com.yash.ota.domain.Test;

/**
 * This class provides object relational mapping functionality.
 *
 * @author karl.roth
 */
@Component
public class TestRowMapper implements RowMapper<Test> {
	Logger log = Logger.getLogger(TestRowMapper.class);

	public Test mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Test test = new Test();
		test.setId(resultSet.getInt("id"));
		test.setTopicId(resultSet.getInt("topic_id"));
		test.setTotalMarks(resultSet.getInt("total_marks"));
		test.setTimeAlloted(resultSet.getTime("time_alloted"));
		test.setStatusId(resultSet.getInt("status_id"));
		test.setCreatedDate(resultSet.getDate("created_date"));
		test.setModifiedBy(resultSet.getInt("modified_by"));
		test.setModifiedDate(resultSet.getDate("modified_date"));
	    //test.setTestName(resultSet.getString("testName"));
	    //test.setNumQuestion(resultSet.getString("numQuestion"));

		
		try {
		    test.setTestName(resultSet.getString("name"));
		} catch (Exception e) {
			//do nothing because name is not getting queried
		}
		try {
		    test.setNum_questions(resultSet.getInt("number_of_questions"));
		} catch (Exception e) {
			//do nothing because number_of_questions is not getting queried
		}
		try {
		    test.setTechnology_name(resultSet.getString("technology_name"));
		} catch (Exception e) {
			//do nothing because technology_name is not getting queried
		}
		return test;
	}

}
