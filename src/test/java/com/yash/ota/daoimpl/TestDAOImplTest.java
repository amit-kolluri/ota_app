/*
 * TestDAOImplTest
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

import com.yash.ota.dao.TestDAO;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.rowmapper.TestRowMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;





/**
 * This class is for testing the testDAOImpl.
 * 
 * @author karl.roth, nipun.dayanath, connor.brown
 */
public class TestDAOImplTest extends TestDAOImpl {

	private TestDAO testDAO;
	private ApplicationContext context;
	private JdbcTemplate mockedJdbcTemplate;
	
	@Before
	public void Setup() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		testDAO = context.getBean("testDAOImpl",TestDAO.class);
		
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		testDAO.setJdbcTemplate(mockedJdbcTemplate);
	}
	
	@Test
	public void testGetTotalMarks_TestIdGiven_ShouldReturnNull() throws Exception {
		com.yash.ota.domain.Test mockedtest = new com.yash.ota.domain.Test();
		mockedtest.setTotalMarks(60);
		
		String sql = "SELECT * FROM tests WHERE id=?";
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, testDAO.getTestRowMapper(), 110)).thenReturn(mockedtest);
		
		com.yash.ota.domain.Test test = testDAO.findById(110);
		assertEquals(mockedtest, test);
	}

	/**
	 * tests find by id method of TestDAOImpl
	 * 
	 * @author Zachary Karamanlis
	 * @throws InvalidIdException
	 */
	@Test
	public void testFindById_TestIdGiven_AccessesDatabase() throws InvalidIdException {
		testDAO.findById(1);
		verify(mockedJdbcTemplate).queryForObject(anyString(), any(TestRowMapper.class), any(Integer.class));
	}

    @Test(expected = InvalidIdException.class)
	public void testFindById_InvalidTestIdGiven_ShouldThrow() throws Exception {
	    //TODO: WHY IS THIS IMPORT HERE?
		com.yash.ota.domain.Test test = testDAO.findById(-110);
	}

    /**
     * @author lalu.shaik
     */
    @Test
    public void testFor_updateTheTimeAlloted_FromTest() {
		com.yash.ota.domain.Test mockedtest = new com.yash.ota.domain.Test();
		mockedtest.setId(1);
           String sql = "UPDATE tests SET "
				
					+ "topic_id=?,"
					+ "total_marks=?,"
					+ "time_alloted=?,"
					+ "status_id=?,"
					+ "created_by=?,"
					+ "modified_by=?,"
					+ "modified_date=?"
					+ "WHERE id=?";
					
			Object[] params = 
					new Object[] {
							mockedtest.getTopicId(),
							mockedtest.getTotalMarks(),
							mockedtest.getTimeAlloted(),
							mockedtest.getStatusId(),
							mockedtest.getCreatedBy(),
							mockedtest.getModifiedBy(),
							mockedtest.getModifiedDate(),
							mockedtest.getId()
					};
			testDAO.update(mockedtest);
			Mockito.verify(mockedJdbcTemplate).update(sql, params);
			
			
    }

	/**
	 * @author: Madhuri Vutukury
	 */
	@Test
    public void insertTest_WhenTestGiven_SholudReturnNothing() {
        Mockito.when(mockedJdbcTemplate.update(Mockito.anyString(), (Object) anyObject())).thenReturn(1);
        testDAO.insert(new com.yash.ota.domain.Test());
    }


}
