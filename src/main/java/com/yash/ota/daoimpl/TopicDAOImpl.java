/*
 * TopicDAOImpl
 *
 * Version 0.1
 *
 * Date: 08/20/2018
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

import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.Topic;
import com.yash.ota.rowmapper.TopicRowMapper;
import com.yash.ota.util.Queries;


/**
 * This class is the implementation of the topicDAO interface
 *
 * @author connor.brown,fahmida.joyti
 *
 */
@Repository
public class TopicDAOImpl implements TopicDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private Logger log = Logger.getLogger(TopicDAOImpl.class);
    private TopicRowMapper topicRowMapper = new TopicRowMapper();




    public TopicRowMapper getTopicRowMapper() {
        return topicRowMapper;
    }
 
    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }





	@Override
	public List<Topic> list() {
		String sql = "SELECT * FROM topics";
		return jdbcTemplate.query(sql, topicRowMapper);
	}

	/**
	 * @author Lalu shaik
	 * @return update the topic name.
	 */
	@Override
	public void update(Topic topic) {
		log.info("UPDATE function is called form topic");
	
		String sql = "UPDATE topics SET "
				+ "name=?,"
				+ "technology_id=?,"
				+ "created_by=?,"
				+ "created_date=?,"
				+ "modified_by=?,"
				+ "modified_date=?"
				+ " WHERE id=?";
				
		Object[] params = 
				new Object[] {
					topic.getName(),
					topic.getTechnologyId(),
					topic.getCreatedBy(),
					topic.getCreatedDate(),
					topic.getModifiedBy(),
					topic.getModifiedDate(),
					topic.getId()
				};
		
		jdbcTemplate.update(sql, params);
				
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate= mockedJdbcTemplate;
		
	}


    /**
     * Method to insert a Topic into the database
     *
     * @param topic the test to be inserted
     */
    public void insert(Topic topic) {

    }

    /**
     * Method returns a findAll of topic objects from the database
     *
     * @return findAll of all users in database
     * @author Lhito.camson
     */
    public List<Topic> findAll() {
    	String sql = Queries.FIND_ALL_TOPICS;
		return jdbcTemplate.query(sql, topicRowMapper);
    }

    /**
     * Method to delete a topic object from the database.
     *
     * @param topicId
     */
    public void delete(int topicId) {

    }


    /**
     * @author justin.dilks connor.brown
     */
    @Override
    public Topic findById(int id) {
        log.info("Finding topic by id");
        return jdbcTemplate.queryForObject(Queries.FIND_TOPIC, topicRowMapper, id);
    }
}
