package com.yash.ota.serviceimpl;

import com.yash.ota.dao.TestDAO;
import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.Topic;
import com.yash.ota.rowmapper.TopicRowMapper;
import com.yash.ota.service.TopicService;
import java.util.List;

import javax.sql.DataSource;

import com.yash.ota.util.Queries;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.Topic;
import com.yash.ota.domain.User;
import com.yash.ota.rowmapper.TopicRowMapper;
import com.yash.ota.service.TopicService;

/**
 * Provides service object for Topic domain
 *
 * @author Justin Dilks, connor.brown, Zachary Karamanlis
 */

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicDAO dao;

	@Autowired
	private TopicRowMapper topicRowMapper = new TopicRowMapper();

	private Logger log = Logger.getLogger(TestServiceImpl.class);

	/**
	 * @author Lalu shaik.
	 */
	@Override
	public void update(Topic topic) {
		log.info("update function called from topic service");

		topicDAO.update(topic);
	}

	/**
	 * @author Lalu shaik.
	 */
	@Override
	public List<Topic> list() {
		return topicDAO.list();
	}

	@Override
	public void setDAO(TestDAO mockedDAO) {
		// TODO Auto-generated method stub

	}

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TopicDAO topicDAO;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setDAO(TopicDAO mockedDAO) {
		this.topicDAO = mockedDAO;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;
	}

	@Override
	public void setTopicDAO(TopicDAO topicDAO) {
		this.topicDAO = topicDAO;
	}

	@Override
	public TopicRowMapper getTopicRowMapper() {
		return topicRowMapper;
	}

	/**
	 * @author justin.dilks
	 */
	@Override
	public Topic getTopicById(int id) {
		return topicDAO.findById(id);
	}

	/**
	 * Returns a List of Topics that the trainer teaches
	 * 
	 * @return List<String>
	 * @author Geon Gayles
	 */
	@Override
	public List<Topic> getListOfTopicByTrainer(User user) {

		String sql = "select * from topics T where T.created_by = ?";

		List<Topic> topics = jdbcTemplate.query(sql, new Object[] { user.getId() }, new TopicRowMapper());

		return topics;
	}

	/**
	 * @param techId
	 * @return list of topics based on techid
	 * @Author Madhuri Vutukury
	 */

	public List<Topic> getTopicsByTechId(int techId) {
		log.info("sql query to get list of topics based o technology Id");
		String sql = "SELECT * FROM topics where technology_id=?";
		return jdbcTemplate.query(sql, topicRowMapper, techId);
	}

	/**
	 * Used for JUnit tests to pass a mocked JdbcTemplate
	 * 
	 * @param temp
	 *            the JdbcTemplate to use
	 * @author Zachary Karamanlis
	 */
	@Override
	public void setMockedJdbcTemplate(JdbcTemplate temp) {
		this.jdbcTemplate = temp;
	}

	public TopicDAO getDao() {
		return topicDAO;
	}

	public void setDao(TopicDAO dao) {
		this.topicDAO = dao;
	}

	@Override
	public List<Topic> getTopicsWithTest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getTopicsByTechnology(int techId) {
		return jdbcTemplate.query(Queries.GET_ALL_TOPICS_BY_TECH_ID, topicRowMapper, techId);
	}

	/**
	 * @author Lhito Camson
	 */
	@Override
	public List<Topic> getTopics() {

		return topicDAO.findAll();
	}

}
