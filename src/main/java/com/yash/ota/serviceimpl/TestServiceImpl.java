
/**
 * TestServiceImpl
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

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yash.ota.dao.ResultDAO;
import com.yash.ota.dao.TestDAO;
import com.yash.ota.dao.TimeTakenDAO;
import com.yash.ota.dao.TopicDAO;
import com.yash.ota.domain.EndOfTestResult;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.Test;
import com.yash.ota.domain.TimeTaken;
import com.yash.ota.domain.Topic;
import com.yash.ota.domain.User;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.rowmapper.ResultRowMapper;
import com.yash.ota.rowmapper.TestRowMapper;
import com.yash.ota.rowmapper.TimeTakenRowMapper;
import com.yash.ota.rowmapper.TopicRowMapper;
import com.yash.ota.service.TestService;
import com.yash.ota.util.Queries;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * This implementation provides a service object for the User data model.
 * 
 * @author karl.roth, connor.brown
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private ResultDAO resultDAO;

	@Autowired
	private TopicDAO topicDAO;

	@Autowired
	private TimeTakenDAO timeTakenDAO;

	private TimeTakenRowMapper timeTakenRowMapper = new TimeTakenRowMapper();
	private TopicRowMapper topicRowMapper = new TopicRowMapper();
	private ResultRowMapper resultRowMapper = new ResultRowMapper();

	public TimeTakenRowMapper getTimeTakenRowMapper() {
		return timeTakenRowMapper;
	}

	public TopicRowMapper getTopicRowMapper() {
		return topicRowMapper;
	}

	public ResultRowMapper getResultRowMapper() {
		return resultRowMapper;
	}

	public void setResultDAO(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}

	public void setTopicDAO(TopicDAO topicDAO) {
		this.topicDAO = topicDAO;
	}

	public void setTimeTakenDAO(TimeTakenDAO timeTakenDAO) {
		this.timeTakenDAO = timeTakenDAO;
	}

	@Autowired
	private TestDAO testDAO;

	private JdbcTemplate jdbcTemplate;
	private Logger log = Logger.getLogger(TestServiceImpl.class);

	private TestRowMapper testRowMapper = new TestRowMapper();

	@Override
	public TestRowMapper getTestRowMapper() {
		return testRowMapper;
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;
	}
	@Override
	public void setTestDAO(TestDAO testDAO) {
		this.testDAO = testDAO;
	}

	/**
	 * @author connor.brown
	 */
	@Override
	public Test getTestById(int id) throws InvalidIdException {
		Test test = null;
		test = testDAO.findById(id);
		return test;
	}

	

	/**
	 * Used for JUnit tests to pass a mocked JdbcTemplate
	 * 
	 * @param temp
	 *            the JdbcTemplate to use
	 * @author Zachary Karamanlis
	 */
	public void setDao(TestDAO dao) {
		this.testDAO = dao;
	}

    	/*
    	* get all tests
    	* @Author Madhuri Vutukury
   	*/
    	@Override
    	public void insertTest(Test test) {
    		log.info("test is inserted");
        	testDAO.insert(test);
    	}


    	/**
    	* @author caleb.reiter
   	     */
     	@Override

	public List<Test> getAssignedTestsByUserId(int userId) throws InvalidIdException {
		if (userId <= 0) {
			String errMsg = "user id: " + userId + " cannot be <= 0";
			throw new InvalidIdException(errMsg);

		}
		String sql = Queries.FIND_ASSIGNED_TESTS_FOR_USER;
		List<Test> tests = jdbcTemplate.query(sql, new TestRowMapper(), userId);
		return tests;
	}

	/**
	 * Update TestDuration.
	 * 
	 * @author:Lalu
	 */
	@Override
	public void update(Test test) {

		log.info(test.getTimeAlloted());
		testDAO.update(test);
	}

	@Override
	public Test findById(int id) throws InvalidIdException {

		return testDAO.findById(id);
	}
		/**
	 * Returns list of maps associated with given CreatedById, each of which
	 * contains a test alloted time, test id, topic name, and technology name
	 * 
	 * technologies.name as techName topics.technology_id=technologies.id;
	 * 
	 * @param createdBy
	 *            id of who created the test
	 * @return list of maps
	 * @author Zachary Karamanlis
	 */
	public List<Map<String, Object>> getTestsWithTopicNameAndTechName(int createdBy) {
		return jdbcTemplate.queryForList(
				"SELECT tests.time_alloted, tests.id, tests.status_id, topics.name as topicName, technologies.name as techName "
						+ "FROM tests " + "INNER JOIN topics ON tests.topic_id=topics.id "
						+ "INNER JOIN technologies ON topics.technology_id=technologies.id "
						+ "WHERE tests.created_by=?",
				createdBy);
	}

	/**
	 * Method to return the total number of Tests by Trainer
	 * @param user
	 * @return
	 * @author Geon Gayles
	 */
	@Override
	public int getTotalNumberOfTestsByTrainer(User user) {
		// TODO Auto-generated method stub
		String sql = "select count(T.id) from tests T " + 
				"where T.created_by = ? and T.status_id = 1";
		int total = jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, Integer.class);
		return total;
		
	}
	


	@Override
	public Test getAssignedTestById(int testId) throws InvalidIdException {
		if (testId <= 0) {
			String errMsg = "test id: " + testId + " cannot be <= 0";
			throw new InvalidIdException(errMsg);
		}
		String sql = Queries.FIND_ASSIGNED_TEST;
		Test test = jdbcTemplate.queryForObject(sql, new TestRowMapper(), testId);
		if (test == null) {
			String errMsg = "test id: " + testId + " is not in database";
			throw new InvalidIdException(errMsg);
		}
		return test;
	}


    /**
     * get List of Tests for Each Topic
     * @author Fahmida Joyti
     * @return list of tests for all topics
     */
    public List<Test> getAvailableTestsForTopics(){
	    return jdbcTemplate.query(Queries.FIND_TESTS_FOR_EACH_TOPIC,new TestRowMapper());
    }

	/**
	 * Export and individual test to an excel file
	 * 
	 * @param testId test to export
	 * @author Zachary Karamanlis
	 */
	@Override
	public void exportToExcel(int testId) {
		WritableWorkbook workbook = null;
		
		try {
			
			//create output file at downloads folder
			//starts as output.xls. Adds number at end if it exists.
			String home = System.getProperty("user.home");
			File file = new File(home+"/Downloads/output.xls");
			if(file.exists()) {
				int end = 1;
				while(file.exists()) {
					file = new File(home+"/Downloads/output_" + end + ".xls");
					end++;
				}
			}
			
			//get test
			Test test = getTestById(testId);
			
			workbook = createsFormattedWorkbook(test, file);
			
			workbook.write();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(workbook != null) {
				try {
					workbook.close();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Helper method that creates the workbook portion of the excel sheet
	 * 
	 * @param test Test to put into workbook
	 * @param file File to print to
	 * @return formatted workbook
	 * @throws IOException
	 * @throws WriteException
	 * @author Zachary Karamanlis
	 */
	public WritableWorkbook createsFormattedWorkbook(Test test, File file) throws IOException, WriteException {
		//create excel sheet
		WritableWorkbook workbook = Workbook.createWorkbook(file);
		WritableSheet excelSheet = workbook.createSheet("Test", 0);
		
		//format for header
		WritableCellFormat format = new WritableCellFormat();
		format.setAlignment(Alignment.CENTRE);
		format.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
		format.setBackground(Colour.BLUE);
		
		//merge first two, crate header
		excelSheet.mergeCells(0, 0, 1, 0);
		Label label = new Label(0,0,"Test Info", format);
		excelSheet.addCell(label);
		
		//auto set sizes
		CellView cell = excelSheet.getColumnView(0);
		cell.setAutosize(true);
		excelSheet.setColumnView(0, cell);
		cell = excelSheet.getColumnView(1);
		cell.setAutosize(true);
		excelSheet.setColumnView(1, cell);
		
		//format for data
		format = new WritableCellFormat();
		format.setAlignment(Alignment.CENTRE);
		format.setBorder(Border.LEFT, BorderLineStyle.THIN);
		format.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		
		//add data
		label = new Label(0,1,"id", format);
		excelSheet.addCell(label);
		label = new Label(1,1,Integer.toString(test.getId()), format);
		excelSheet.addCell(label);
		
		label = new Label(0,2,"Topic id", format);
		excelSheet.addCell(label);
		label = new Label(1,2,Integer.toString(test.getTopicId()), format);
		excelSheet.addCell(label);
		
		label = new Label(0,3,"Total Marks", format);
		excelSheet.addCell(label);
		label = new Label(1,3,Integer.toString(test.getTotalMarks()), format);
		excelSheet.addCell(label);
		
		label = new Label(0,4,"Time Allotted", format);
		excelSheet.addCell(label);
		label = new Label(1,4,test.getTimeAlloted().toString(), format);
		excelSheet.addCell(label);
		
		label = new Label(0,5,"Status", format);
		excelSheet.addCell(label);
		label = new Label(1,5,Integer.toString(test.getStatusId()), format);
		excelSheet.addCell(label);
		
		label = new Label(0,6,"Created By", format);
		excelSheet.addCell(label);
		label = new Label(1,6,Integer.toString(test.getCreatedBy()), format);
		excelSheet.addCell(label);
		
		label = new Label(0,7,"Created Date", format);
		excelSheet.addCell(label);
		excelSheet.addCell(label);
		
		label = new Label(0,8,"Modified By", format);
		excelSheet.addCell(label);
		label = new Label(1,8,Integer.toString(test.getModifiedBy()), format);
		excelSheet.addCell(label);
		
		label = new Label(0,9,"Modified Date", format);
		excelSheet.addCell(label);
		label = new Label(1,9,test.getModifiedDate().toString(), format);
		excelSheet.addCell(label);
		
		return workbook;
	}
	
	/**
	 * updates status of given test by given status
	 * 
	 * @param testId
	 *            test to update
	 * @param statusId
	 *            status to set on test
	 * @author Zachary Karamanlis
	 */
	@Override
	public void updateStatus(int testId, int statusId) {
		jdbcTemplate.update("UPDATE tests SET status_id=? WHERE id=?", statusId, testId);
	}

	/**
	 * returns a list of tests given a topic id
	 * @param topicId id of topic
	 * @return list of tests
	 * @author phoutsaksit.keomala
	 */
	@Override
	public List<Test> getTestsByTopicId(int topicId) {
		return jdbcTemplate.query(Queries.GET_TESTS_BY_TOPIC_ID, testRowMapper, topicId);
	}

	@Override
	public List<Test> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> getTests() {
		return jdbcTemplate.query(Queries.GET_ALL_TESTS, testRowMapper);
	}

}
