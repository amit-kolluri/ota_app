/*
 * TimeTakenDAOImplTest
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

import com.yash.ota.dao.TimeTakenDAO;
import com.yash.ota.domain.TimeTaken;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Time;

import static org.junit.Assert.assertEquals;


/**
 * This class is for testing the timeTakenDAOImpl.
 * 
 * @author karl.roth, nipun.dayanath, connor.brown
 */
public class TimeTakenDAOImplTest extends ResultDAOImpl {

	private TimeTakenDAO timeTakenDAO;
	private ApplicationContext context;
	private JdbcTemplate mockedJdbcTemplate;
	
	@Before
	public void Setup() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		timeTakenDAO = context.getBean("timeTakenDAOImpl",TimeTakenDAO.class);
		
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		timeTakenDAO.setJdbcTemplate(mockedJdbcTemplate);
	}
	
	@Test
	public void testGetTestTimeTaken_TestIdGiven_ShouldReturnNumberOfMinutesTaken() {
		TimeTaken mockedTimeTaken = new TimeTaken();
		mockedTimeTaken.setTimeTaken(new Time(2400000));
		
		String sql = "SELECT * FROM time_taken WHERE id=?";
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, timeTakenDAO.getTimeTakenRowMapper(), 110)).thenReturn(mockedTimeTaken); //2400000 should be 40 minutes
		
		TimeTaken timeTaken = timeTakenDAO.findById(110);
		assertEquals(mockedTimeTaken, timeTaken);
	}
	
	@Test
	public void testGetTestTimeTaken_InvalidTestIdGiven_ShouldReturnNull() {
		//test_id 50000 should be too high of an id for a test (there shouldn't by 50000 tests)
		String sql = "SELECT * FROM time_taken WHERE id=?";
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, timeTakenDAO.getTimeTakenRowMapper(), 50000)).thenReturn(null);
		
		TimeTaken timeTaken = timeTakenDAO.findById(50000);
		assertEquals(null, timeTaken);
	}
	
}
