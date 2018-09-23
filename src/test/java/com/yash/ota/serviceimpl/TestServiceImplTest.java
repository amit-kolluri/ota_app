
/*
 * testServiceImplTest
 *
 * Version 0.1
 *
 * Date: 08/18/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.serviceimpl;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Time;
import java.util.Date;
import com.yash.ota.dao.TestDAO;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.rowmapper.TestRowMapper;
import com.yash.ota.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.TopicDAO;
import com.yash.ota.service.TopicService;





import com.yash.ota.dao.ResultDAO;
import com.yash.ota.dao.TestDAO;
import com.yash.ota.dao.TimeTakenDAO;
import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.EndOfTestResult;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.TimeTaken;
import com.yash.ota.domain.Topic;
import com.yash.ota.rowmapper.ResultRowMapper;
import com.yash.ota.rowmapper.TestRowMapper;
import com.yash.ota.rowmapper.TimeTakenRowMapper;
import com.yash.ota.service.TestService;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import static org.mockito.ArgumentMatchers.anyInt;
import com.yash.ota.dao.ResultDAO;
import com.yash.ota.dao.TestDAO;
import com.yash.ota.dao.TimeTakenDAO;
import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.EndOfTestResult;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.TimeTaken;
import com.yash.ota.domain.Topic;

import com.yash.ota.domain.User;


import com.yash.ota.rowmapper.ResultRowMapper;
import com.yash.ota.rowmapper.TestRowMapper;
import com.yash.ota.rowmapper.TimeTakenRowMapper;
import com.yash.ota.service.TestService;

import static org.mockito.Mockito.when;


/**
 * This class is for testing the testServiceImpl
 * 
 * @author karl.roth connor.brown,fahmida.joyti
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceImplTest {

	private TestService testService;
	private ApplicationContext context;
	private TestDAO testDAO;
	@Mock
	private JdbcTemplate mockedJdbcTemplate;
	private TopicService topicService;
	
	private TestDAO mockedDAO;	

	@Before
	public void Setup() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		topicService = context.getBean("topicServiceImpl",TopicService.class);
		
		mockedDAO = Mockito.mock(TestDAO.class);
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		mockedDAO.setJdbcTemplate(mockedJdbcTemplate);
		topicService.setDAO(mockedDAO);
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		testService = context.getBean("testServiceImpl", TestService.class);

		testDAO = Mockito.mock(TestDAO.class);
		testDAO.setJdbcTemplate(mockedJdbcTemplate);
		testService.setTestDAO(testDAO);

		testService.setJdbcTemplate(mockedJdbcTemplate);
	}

	public void testGetAvailableTestsByUserId_GivenId101_ShouldReturnSomeTests() throws Exception {
		com.yash.ota.domain.Test t1 = new com.yash.ota.domain.Test();
		com.yash.ota.domain.Test t2 = new com.yash.ota.domain.Test();
		com.yash.ota.domain.Test t3 = new com.yash.ota.domain.Test();
		List<com.yash.ota.domain.Test> mockedTests = Arrays.asList(t1, t2, t3);
		Mockito.when(mockedJdbcTemplate.query(Mockito.anyString(), Mockito.any(TestRowMapper.class), Mockito.eq(101)))
				.thenReturn(Arrays.asList(t1, t2, t3));
		List<com.yash.ota.domain.Test> tests = testService.getAssignedTestsByUserId(101);
		assertEquals(tests, mockedTests);
	}

	@Test(expected = InvalidIdException.class)
	public void testGetAvailableTestsByUserId_InvalidUserIdGiven_ShouldThrow() throws Exception {
		List<com.yash.ota.domain.Test> tests = testService.getAssignedTestsByUserId(-101);
	}

	
	/**
	 * Tests findById method of test service
	 * 
	 * @author Zachary Karamanlis
	 * @throws InvalidIdException 
	 */
	@Test
	public void testFindById_GivenTestId_ShouldAccessDatabase() throws InvalidIdException {
		testService.getTestById(1);
		verify(testDAO).findById(1);
	}

	/**
	 * Tests that dao is called by findByCreatedBy
	 *
	 * Tests updateStatus in TestService by ensuring JdbcTemplate is called
	 *
	 * @author Zachary Karamanlis
	 */
	@Test
	public void updateStatusTest_GivenTestIdAndStatusId_AccessesDatabase() {
		testService.updateStatus(1, 1);
		verify(mockedJdbcTemplate).update(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt());
	}
	
	/**
	 * Tests updateStatus in TestService by ensuring JdbcTemplate is called
	 *
	 * @author Zachary Karamanlisgrtf764g4t5
	 */
	@Test
	public void getTestsWithTopicNameAndTechNameTest_GivenCreatedById_AccessesDatabase() {
		testService.getTestsWithTopicNameAndTechName(1);
		verify(mockedJdbcTemplate).queryForList(Mockito.anyString(), Mockito.anyInt());
	}

	/**
	 * Tests findById method of test service
	 *
	 * @author Zachary Karamanlis
	 * @throws InvalidIdException
	 */
	@Test
	public void findByIdTest_GivenTestId_AccessesDAO() throws InvalidIdException {
		testService.getTestById(1);
		verify(testDAO).findById(1);
	}
	
	/**
	 * Tests findById method of test service
	 * 
	 * @author Zachary Karamanlis
	 * @throws InvalidIdException 
	 */
	@Test
	public void findByIdTest_TestIdGiven_AccessesDAO() throws InvalidIdException {
		testService.getTestById(1);
		verify(testDAO).findById(1);
	}


	@Test
	public void testGetAssignedTestById_TestId7Given_ShouldReturnTest7() throws Exception {
		com.yash.ota.domain.Test t1 = new com.yash.ota.domain.Test();
		t1.setId(7);
		Mockito.when(
				mockedJdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(TestRowMapper.class), Mockito.eq(7)))
				.thenReturn(t1);
		com.yash.ota.domain.Test test = testService.getAssignedTestById(7);
		assertEquals(t1.getId(), test.getId());
	}

	@Test(expected = InvalidIdException.class)
	public void testGetAssignedTestById_NegativeTestIdGiven_ShouldThrow() throws Exception {
		com.yash.ota.domain.Test test = testService.getAssignedTestById(-7);
	}

	@Test(expected = InvalidIdException.class)
	public void testGetAssignedTestById_NonExistentTestIdGiven_ShouldThrow() throws Exception {
		com.yash.ota.domain.Test test = testService.getAssignedTestById(70000);
	}
	
	/**
     * Gets Total Tests created by Trainer
     * @author Geon Gayles
     */
	@Test
	public void getTotalTestsByTrainer_UserTestGiven_ShouldReturnNumberOfTests() {
		User user = new User();
		user.setId(1);
		testService.setJdbcTemplate(mockedJdbcTemplate);
		String sql = "select count(T.id) from tests T " + 
				"where T.created_by = ? and T.status_id = 1";
		int total = 17;
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, new Object[] { 1 }, Integer.class)).thenReturn(total);
		int output = testService.getTotalNumberOfTestsByTrainer(user);
		assertEquals(output, total);
	}
	@Test
	public void testGetAvailableTestsByTopic_ShouldReturnSomeTests(){
		com.yash.ota.domain.Test t1 = new com.yash.ota.domain.Test();
		com.yash.ota.domain.Test t2 = new com.yash.ota.domain.Test();
		List<com.yash.ota.domain.Test> mockedTests = Arrays.asList(t1,t2);
		Mockito.when(mockedJdbcTemplate.query(Mockito.anyString(), Mockito.any(TestRowMapper.class))).thenReturn(Arrays.asList(t1,t2));
		List<com.yash.ota.domain.Test> tests = testService.getAvailableTestsForTopics();
		assertEquals(tests,mockedTests);


	}
	
	/**
	 * Creates new output file in downloads folder. Open and check that values match test class
	 */
	@Test
	public void testExport_GivenTestId_CreatesXlsPage() {
		
		com.yash.ota.domain.Test test = new com.yash.ota.domain.Test();
		test.setCreatedDate(new Date(0));
		test.setId(0);
		test.setModifiedBy(2);
		test.setModifiedDate(new Date(0));
		test.setNum_questions(3);
		test.setStatusId(4);
		test.setTechnology_name("Tech");
		test.setTimeAlloted(new Time(0));
		test.setTopicId(5);
		test.setTotalMarks(6);
		
		try {
			Mockito.when(testDAO.findById(1)).thenReturn(test);
		} catch (InvalidIdException e) {
			e.printStackTrace();
		}
		
		testService.exportToExcel(1);
	}
}
