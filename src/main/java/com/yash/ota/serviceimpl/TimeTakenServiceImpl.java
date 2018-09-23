package com.yash.ota.serviceimpl;

import com.yash.ota.dao.TimeTakenDAO;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.TimeTaken;
import com.yash.ota.exception.InvalidQueryException;
import com.yash.ota.rowmapper.ResultRowMapper;
import com.yash.ota.rowmapper.TimeTakenRowMapper;
import com.yash.ota.service.TimeTakenService;
import com.yash.ota.util.Queries;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

import java.sql.Time;

/**
 *  This implementation provides a service object for the TimeTaken data model.
 */

@Service
public class TimeTakenServiceImpl implements TimeTakenService {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private TimeTakenRowMapper timeTakenRowMapper = new TimeTakenRowMapper();
    
    private Logger log = Logger.getLogger(TimeTakenServiceImpl.class);

    @Autowired
    private TimeTakenDAO timeTakenDAO;


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

    public void setDAO(TimeTakenDAO mockedDAO) {
        this.timeTakenDAO = mockedDAO;
    }

	@Override
	public void setTimeTakenDAO(TimeTakenDAO timeTakenDAO) {
		this.timeTakenDAO = timeTakenDAO;
	}

	/**
     * @author justin.dilks
     */
    @Override
    public TimeTaken getTimeTakenById(int id) {
        return timeTakenDAO.findById(id);
    }
    
    /**
	 * Brilliant, I know
	 *
	 * @param id
	 *            The id of the TimeTaken object to be found
	 * @return the TimeTaken Object with the given id
	 * @author nick.paker
	 */
	@Override
	public TimeTaken findTimeTakenById(int id) {
		return timeTakenDAO.findById(id);
	}

	@Override
	public TimeTakenRowMapper getTimeTakenRowMapper() {
		return timeTakenRowMapper;
	}

	/**
	 * @author connor.brown
	 * @throws InvalidQueryException 
	 */
	@Override
	public TimeTaken getTimeTakenByTestIdAndUserId(int testId, int userId) throws Exception {
		log.info("Querying for timeTaken object based on test id " + testId + " and user id " + userId);
		try {
		String timeTakenSQL = Queries.FIND_TIME_TAKEN_BY_TEST_ID_AND_USER_ID;
		return jdbcTemplate.queryForObject(timeTakenSQL, timeTakenRowMapper, testId, userId);
		} catch(NullPointerException e) {
			throw new InvalidQueryException("The test id or user id failed");
		}
	}

	/**
	 * @author connor.brown
	 */
	@Override
	public void addTimeTaken(int testId, int userId, long timeTaken) {
		Result result = jdbcTemplate.queryForObject(Queries.FIND_RESULT_BY_TEST_ID_AND_USER_ID, new ResultRowMapper(), testId, userId);
		
		TimeTaken time = new TimeTaken();
		time.setResultId(result.getId());
		time.setTestId(testId);
		time.setUserId(userId);
		time.setTimeTaken(new Time(timeTaken));
		
		log.info("Adding a new timeTaken object into the database");
		timeTakenDAO.insert(time);
	}

}
