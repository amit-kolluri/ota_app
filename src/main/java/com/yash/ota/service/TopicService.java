package com.yash.ota.service;

import com.yash.ota.dao.TestDAO;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.Topic;
import com.yash.ota.domain.User;
import com.yash.ota.rowmapper.TopicRowMapper;

/**
 * Provides service object for Topic domain
 * 
 * @author Justin Dilks, Zachary Karamanlis
 *
 */
public interface TopicService {

	public void setDAO(TopicDAO mockedDAO);

	/**
	 * Gets the topic based on the id
	 * 
	 * @param id
	 *            by topic
	 * @return Topic based on id
	 */
	public Topic getTopicById(int id);

	/**
	 * Returns a List of Topics that the trainer teaches
	 * 
	 * @return List<String>
	 * @author Geon Gayles
	 */
	public List<Topic> getListOfTopicByTrainer(User user);

	/**
	 * Used for JUnit testing
	 * 
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	/**
	 * Used for JUnit testing in ServiceImplTest.class
	 * 
	 * @param topicDAO
	 */
	public void setTopicDAO(TopicDAO topicDAO);

	/**
	 * Used for JUnit testing in TestServiceImplTest.class
	 * 
	 * @return TopicRowMapper
	 */
	public TopicRowMapper getTopicRowMapper();

	/**
	 * gets list of all topics with an associated test
	 * 
	 * @return list of topics
	 */
	public List<Topic> getTopicsWithTest();

	/**
	 * Get the list of topics based on technologyId
	 * 
	 * @param techId
	 * @return list of topics
	 * @author Madhuri Vutukury
	 */
	List<Topic> getTopicsByTechId(int techId);

	public void setDao(TopicDAO dao);

	/**
	 * @author Lalu Shaik.
	 * @param topic
	 * @return update the topic name.
	 */
	public void update(Topic topic);

	/**
	 * @author Lalu Shaik.
	 * @return
	 * @return get the list of the topics.
	 */
	public List<Topic> list();

	public void setDAO(TestDAO mockedDAO);

	public void setMockedJdbcTemplate(JdbcTemplate temp);

	public List<Topic> getTopicsByTechnology(int techId);

	/**
	 * Used to list the topics
	 * 
	 * @return List of topics
	 * @author Lhito.camson
	 */
	public List<Topic> getTopics();

}
