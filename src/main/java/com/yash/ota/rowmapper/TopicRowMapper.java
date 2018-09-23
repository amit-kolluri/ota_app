/*
 * TopicRowMapper
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

import com.yash.ota.domain.Topic;
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
public class TopicRowMapper implements RowMapper<Topic> {

	public Topic mapRow(ResultSet resultSet, int rowNum) throws SQLException {


        Topic topic = new Topic();

		topic.setId(resultSet.getInt("id"));
		topic.setName(resultSet.getString("name"));
		topic.setTechnologyId(resultSet.getInt("technology_id"));
		topic.setCreatedBy(resultSet.getInt("created_by"));
		topic.setCreatedDate(resultSet.getDate("created_date"));
		topic.setModifiedBy(resultSet.getInt("modified_by"));
		topic.setModifiedDate(resultSet.getDate("modified_date"));
		return topic;
	}

}
