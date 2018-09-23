
/**

 * TestDAOImpl
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yash.ota.dao.TestDAO;
import com.yash.ota.domain.Test;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.rowmapper.TestRowMapper;
import com.yash.ota.util.Queries;

@Repository
public class TestDAOImpl implements TestDAO {
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private Logger log = Logger.getLogger(TestDAOImpl.class);
	private TestRowMapper testRowMapper = new TestRowMapper();

	/**
	 *@author Lalu shaik.
	 * 
	 * @return
	 */
	public TestRowMapper getTestRowMapper() {
		return testRowMapper;
	}

	public void setTestRowMapper(TestRowMapper testRowMapper) {
		this.testRowMapper = testRowMapper;
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
    /**
     * Method to insert a User into the database
     *
     * @param test the test to be inserted
     * @author Madhuri.Vutukury
     */
    public void insert(Test test) {
        log.info("adding Test");
        String sql = "INSERT INTO tests (topic_id, time_alloted, status_id, created_by,  total_marks, modified_by, modified_date) values(?,?,?,?,?,?,?,?)";
        Object[] params =
                new Object[]{
                        test.getTopicId(),
                        test.getTimeAlloted(),
                        test.getStatusId(),
                        test.getCreatedBy(),
                        test.getTotalMarks(),
                        test.getModifiedBy(),
                        test.getModifiedDate()
                };
         jdbcTemplate.update(sql, params);

    }




	    /**
	     * Method returns a findAll of user objects from the database
	     *
	     * @return findAll of all users in database
	     * @author Lhito.Camson.
	     */
	    public List<Test> findAll() {
	        String sql = "SELECT * FROM tests";

	        return jdbcTemplate.query(sql, testRowMapper);
	    }


	    /**
	     * Method to update a test object in the database.
	     *@author Lalu shaik.
	     * @param test
	     */
	    @Override
	    public void update(Test test) {
	    	log.info("Calling update test from TestDAOImpl");
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
							test.getTopicId(),
							test.getTotalMarks(),
							test.getTimeAlloted(),
							test.getStatusId(),
							test.getCreatedBy(),
							test.getModifiedBy(),
							test.getModifiedDate(),
							test.getId()
					};
			
			jdbcTemplate.update(sql,params);
					

	    }

	    /**
	     * Method to delete a test object from the database.
	     *
	     * @param testId
	     */
	    public void delete(int testId) {

	    }

	    /**
	     * Used for JUnit testing
	     *
	     * @param mockedJdbcTemplate
	     */
	    public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
	        this.jdbcTemplate = mockedJdbcTemplate;

	    }


		/**
		 * @author connor.brown
		 * @throws InvalidIdException
		 */
		@Override
		public Test findById(int id)  throws InvalidIdException {
			if (id <= 0) {
				String errMsg = "test with id: "+id+" is invalid!";
				throw new InvalidIdException(errMsg);
			}
			log.info("Finding test by id: " + id);
			try {
			return jdbcTemplate.queryForObject(Queries.FIND_TEST_BY_ID, testRowMapper, id);
			} catch (EmptyResultDataAccessException e) {
				e.printStackTrace();
				throw new InvalidIdException("couldn't find a test with id " + id);
			}
		}

		
		
		



	


  

 
 



	
	
}
