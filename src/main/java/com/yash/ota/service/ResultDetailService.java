package com.yash.ota.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.domain.ResultDetail;
import com.yash.ota.rowmapper.ResultDetailRowMapper;

public interface ResultDetailService {
	/**
	 * Return a list of result detail objects to be displayed on the web page
	 * 
	 * @param resultId the id of the result whose details should be pulled
	 * @return list of ResultDetailObjects to be displayed
	 * @author nick.parker
	 */
	public List<ResultDetail> getResultDetails(int resultId);
	
	/**
	 * Used For Testing
	 * @return
	 */
	public ResultDetailRowMapper getResultDetailRowMapper();
	
	/**
	 * Used For Testing
	 * @param resultDetailRowMapper
	 */
	public void setResultDetailRowMapper(ResultDetailRowMapper resultDetailRowMapper);

	/**
	 * Used for Testing
	 * @param mockedJdbcTemplate
	 * @author karl.roth
	 */
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);
}
