/**
 * ResultDAOImpl
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.daoimpl;

import com.yash.ota.dao.ResultDAO;
import com.yash.ota.domain.Result;
import com.yash.ota.rowmapper.ResultRowMapper;
import com.yash.ota.util.Queries;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

/**
 * This implementation provides a data access object for the Result data model.
 *
 * @author nick.parker
 */
@Repository
public class ResultDAOImpl implements ResultDAO {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private Logger log = Logger.getLogger(ResultDAOImpl.class);

	private ResultRowMapper resultRowMapper = new ResultRowMapper();

	public void setResultRowMapper(ResultRowMapper resultRowMapper) {
		this.resultRowMapper = resultRowMapper;
	}

	public ResultRowMapper getResultRowMapper() {
		return resultRowMapper;
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @author nick.parker
	 */
	@Override
	public List<Result> list() {
		String sql = Queries.FIND_ALL_RESULTS;
		return jdbcTemplate.query(sql, resultRowMapper);
	}

	/**
	 * @author nick.parker
	 */
	@Override
	public void insert(Result result) {
		String sql = Queries.INSERT_NEW_RESULT;
		Object[] params = new Object[] { result.getUserId(), result.getTestId(), result.getTopicId(),
				result.getTechnologyId(), result.getTotalMarks(), result.getObtainedMarks(), result.getStatusId(),
				result.getCreatedBy(), result.getCreatedDate(), result.getModifiedBy(), result.getModifiedDate() };
		jdbcTemplate.update(sql, params);
	}

	/**
	 * @author nick.parker
	 */
	@Override
	public Result findById(int id) {
		String sql = Queries.FIND_RESULT_BY_ID;
		log.info("Called findById from ResultDAO");
		return jdbcTemplate.queryForObject(sql, resultRowMapper, id);
	}

	/**
	 * @author nick.parker
	 */
	@Override
	public void update(Result result) {
		String sql = Queries.UPDATE_RESULT;
		Object[] params = new Object[] { result.getUserId(), result.getTestId(), result.getTopicId(),
				result.getTechnologyId(), result.getTotalMarks(), result.getObtainedMarks(), result.getStatusId(),
				result.getCreatedBy(), result.getCreatedDate(), result.getModifiedBy(), result.getModifiedDate(),
				result.getId() };
		jdbcTemplate.update(sql, params);
	}

	/**
	 * Delete result from database
	 * 
	 * @author nick.parker
	 * @author Zachary Karamanlis
	 */
	@Override
	public void delete(int resultId) {
		String sql = Queries.REMOVE_FROM_RESULT_BY_ID;
		jdbcTemplate.update(sql, resultId);
	}

	/**
	 * @author nick.parker
	 */
	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;

	}

}
