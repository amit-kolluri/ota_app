package com.yash.ota.serviceimpl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.Topic;
import com.yash.ota.service.TopicService;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.Topic;
import com.yash.ota.service.TestService;
import com.yash.ota.service.TopicService;


@RunWith(MockitoJUnitRunner.class)
public class TopicServiceImplTest {

    @InjectMocks
    private TopicService topicService = new TopicServiceImpl();

    @Mock
    private TopicDAO topicDAO;

    private Topic mockedTopic = new Topic();
	private ApplicationContext context;
	private TopicDAO mockedDAO;	
	
    @Mock
	JdbcTemplate jdbcTemplate;
	
	private JdbcTemplate mockedJdbcTemplate;
	
	@Before
	public void Setup() {
	
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		
		topicService.setMockedJdbcTemplate(mockedJdbcTemplate);
		
	}
    
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

//	@Before
//	public void Setup() {
//		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
//		topicService = context.getBean("topicServiceImpl",TopicService.class);
//		
//		mockedDAO = Mockito.mock(TopicDAO.class);
//		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
//		mockedDAO.setJdbcTemplate(mockedJdbcTemplate);
//		topicService.setDAO(mockedDAO);
//	}
	@Test
	public void testFor_toGetTheListOfTopics() {
		List<Topic> list= new ArrayList<>();
		Topic topic= new Topic();
		topic.setId(1);
		String sql= "Select * from topics where id=?";
//		Mockito.when(jdbcTemplate.queryForObject(sql, topicDAO.getTopicRowMapper())).thenReturn(new Topic());
		topicService.list();
		Mockito.verify(topicDAO).list();
		
	}
	/**
	 * @author Lalu shaik.
	 */
	@Test
	public void testFor_upadateTheTopics() {
		Topic topic= new Topic();
		topic.setCreatedBy(1);
        topic.setName("java");
        topic.setTechnologyId(2);
        topic.setModifiedBy(3);
        topicService.update(topic);
        Mockito.verify(topicDAO).update(topic);
        
	}
    private TestService testService;


    @Test
    public void testGetTopicById_Id101Given_ShouldReturnTopic101() {
        mockedTopic.setId(101);
        Mockito.when(topicDAO.findById(101)).thenReturn(mockedTopic);
        Topic topic = topicService.getTopicById(101);
        assertEquals(topic.getId(),mockedTopic.getId());
    }

    @Test
    public void testFindById_WrongIdGiven_ShouldReturnNull() {
        mockedTopic.setId(101);
        Mockito.when(topicDAO.findById(105)).thenReturn(null);
        Topic topic = topicService.getTopicById(105);
        assertEquals(topic,null);
    }

    
	@Test
	public void testFindAll_NothingGiven_ListOfTopicsReturned() {
		List<Topic> topicList = topicService.getTopics();
		Topic topic = new Topic();
		
		int createdBy = 1;
		Date createdDate = new Date();
		int id = 1;
		int modifiedBy = 1;
		Date modifiedDate = new Date();
		String name = "Lhito";
		int technologyId = 1;
		
		topic.setCreatedBy(createdBy);
		topic.setCreatedDate(createdDate);
		topic.setId(id);
		topic.setModifiedBy(modifiedBy);
		topic.setModifiedDate(modifiedDate);
		topic.setName(name);
		topic.setTechnologyId(technologyId);
		
		Mockito.when(topicDAO.findAll()).thenReturn(Arrays.asList(topic));
		topicService.setDAO(topicDAO);
		
		topicList = topicService.getTopics();
		
		assertEquals(1,topicList.size());
	}


	
	/**
	 * tests FindById method of service
	 * 
	 * @author Zachary Karamanlis
	 */
	@Test
	public void testFindById_GivenTopicId_AccessesDAO() {
		topicService.getTopicById(1);
		verify(topicDAO).findById(1);
	}

}
