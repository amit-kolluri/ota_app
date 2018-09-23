/*
 * TopicDAOImplTest
 *
 * Version 0.1
 *
 * Date: 08/18/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */

package com.yash.ota.daoimpl;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import com.yash.ota.dao.ResultDAO;
import com.yash.ota.dao.TimeTakenDAO;
import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.TimeTaken;
import com.yash.ota.domain.Topic;
import com.yash.ota.rowmapper.TopicRowMapper;

import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.Topic;
import com.yash.ota.rowmapper.TopicRowMapper;
import com.yash.ota.util.Queries;


@RunWith(MockitoJUnitRunner.class)
public class TopicDAOImplTest {

    @InjectMocks
    private TopicDAO topicDAO = new TopicDAOImpl();

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private TopicRowMapper topicRowMapper;
    
    private Topic mockedTopic = new Topic();
    

    @Before
	public void Setup() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void testFindById_GivenId101_ShouldReturnTopic101() {
        mockedTopic.setId(101);
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(TopicRowMapper.class), Mockito.eq(101))).thenReturn(mockedTopic);
        Topic topic = topicDAO.findById(101);
        assertEquals(topic.getId(),mockedTopic.getId());
    }

    @Test
    public void testFindById_GivenWrongId_ShouldReturnNull() {
        mockedTopic.setId(101);
        Mockito.when(jdbcTemplate.queryForObject(Queries.FIND_TOPIC, topicRowMapper, 105)).thenReturn(null);
        Topic topic = topicDAO.findById(105);
        assertEquals(topic,null);
    }
    
	@Test
	public void testFindAll_NothingGiven_ListOfTopicsReturned() {
		List<Topic> mockedList = new ArrayList<Topic>();
		Topic topic = new Topic();
		topic.setName("hi");
		mockedList.add(topic);
		
		String sql = "SELECT * FROM topics";
		Mockito.when(jdbcTemplate.query(sql,topicDAO.getTopicRowMapper())).thenReturn(mockedList);
		
		List<Topic> topicList = topicDAO.findAll();
		
		assertEquals(topicList, mockedList);
	}
	
	@Test
	public void testGetTopicName_TopicIdGiven_ShouldReturnNameOfTheTopic() {
		Topic mockedTopic = new Topic();
		mockedTopic.setId(110);
		mockedTopic.setName("Basics of Java");
		
		String sql = "Select * from topics where id=?";
		Mockito.when(jdbcTemplate.queryForObject(sql, topicDAO.getTopicRowMapper(), 110)).thenReturn(mockedTopic);
		
		Topic topic = topicDAO.findById(110);
		assertEquals(mockedTopic, topic);
	}
	
	@Test
	public void testGetTopicName_InvalidTestIdGiven_ShouldReturnNull() {
		//test_id 50000 should be too high of an id for a test (there shouldn't by 50000 tests)
		String sql = "SELECT * FROM topics WHERE id=?";
		
		Topic topic = topicDAO.findById(50000);
		assertEquals(null, topic);
	}
	/**
	 * Tests findById method of Topic dao
	 * 
	 * @author Zachary Karamanlis
	 */
	@Test
	public void testFindById_GivenTopicId_ShouldAccessDatabase() {
		topicDAO.findById(1);
		verify(jdbcTemplate).queryForObject(anyString(), any(TopicRowMapper.class), any(Integer.class));
	}

	
	@Test
	public void test_ForUpdateTheTestName() {
		Topic topic = new Topic();
		topic.setId(1);
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
		
		  topicDAO.update(topic);
			Mockito.verify(jdbcTemplate).update(sql,params);
	}

}

