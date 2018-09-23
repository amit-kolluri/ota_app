/**
 * TimeTakenDAOImpl
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

import com.yash.ota.dao.TimeTakenDAO;
import com.yash.ota.domain.TimeTaken;
import com.yash.ota.rowmapper.TimeTakenRowMapper;
import com.yash.ota.util.Queries;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
/**
 * This class is the implementation of the timeTakenDAO interface
 * 
 * @author connor.brown
 *
 */
@Repository
public class TimeTakenDAOImpl implements TimeTakenDAO{
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private Logger log = Logger.getLogger(ResultDAOImpl.class);

	private TimeTakenRowMapper timeTakenRowMapper = new TimeTakenRowMapper();

	public TimeTakenRowMapper getTimeTakenRowMapper() {
		return timeTakenRowMapper;
	}
	
	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		jdbcTemplate = mockedJdbcTemplate;
	}

    /**
	 * @params id of time taken object
	 * @return returns time taken object
     * @author connor.brown
     */
    @Override
    public TimeTaken findById(int id) {
        log.info("Finding time taken by id: " + id);
        return jdbcTemplate.queryForObject(Queries.FIND_TIME_TAKEN_BY_ID, timeTakenRowMapper, id);
    }
    
    /**
     * @author connor.brown
     */
    @Override
    public void insert(TimeTaken timeTaken) {
    	String sql = Queries.INSERT_NEW_TIME_TAKEN;
		Object[] params = new Object [] {
				timeTaken.getTestId(),
				timeTaken.getUserId(),
				timeTaken.getResultId(),
				timeTaken.getTimeTaken()
		};
		jdbcTemplate.update(sql, params);
    }
	
}
