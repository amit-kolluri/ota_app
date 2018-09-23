/*
 * ResultDAOImplTest
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

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.sql.Time;

import com.yash.ota.dao.ResultDAO;
import com.yash.ota.domain.Result;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.ResultDAO;
import com.yash.ota.domain.Result;

import com.yash.ota.dao.TimeTakenDAO;
import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.TimeTaken;
import com.yash.ota.domain.Topic;
import static org.junit.Assert.assertEquals;


/**
 * This class is for testing the resultDAOImpl.
 * 
 * @author karl.roth, nipun.dayanath, connor.brown
 */
public class ResultDAOImplTest extends ResultDAOImpl {

	private ResultDAO resultDAO;
	private ApplicationContext context;
	private JdbcTemplate mockedJdbcTemplate;
	
	@Before
	public void Setup() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		resultDAO = context.getBean("resultDAOImpl",ResultDAO.class);
		
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		resultDAO.setJdbcTemplate(mockedJdbcTemplate);
	}
	
	/**
	 * Test for FindById
	 * 
	 * @author nick.parker
	 */
	@Test
	public void testFindById_GivenId1_ShouldReturnResultCreatedBy50() {
		Result result = new Result();
		result.setId(123);
		result.setCreatedBy(50);
		String sql = "SELECT * FROM results WHERE id=?";
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, resultDAO.getResultRowMapper(), 123)).thenReturn(result);
		Result testResult = resultDAO.findById(123);
		assertEquals(result.getId(), testResult.getId());
	}

	public void testGetTotalMarks_TestIdGiven_ShouldReturnNull() {
		Result mockedResult = new Result();
		mockedResult.setTotalMarks(60);
		
		String sql = "SELECT * FROM results WHERE id=?";
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, resultDAO.getResultRowMapper(), 110)).thenReturn(mockedResult);
		
		Result result = resultDAO.findById(110);
		assertEquals(mockedResult, result);
	}
	
	@Test
	public void testGetTotalMarks_InvalidTestIdGiven_ShouldReturnTotalMarksOfTheTest() {
		//test_id 50000 should be too high of an id for a test (there shouldn't by 50000 tests)
		String sql = "SELECT * FROM results WHERE id=?";
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, resultDAO.getResultRowMapper(), 50000)).thenReturn(null);
		
		Result result = resultDAO.findById(50000);
		assertEquals(null, result);
	}
}
