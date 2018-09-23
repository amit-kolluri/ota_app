package com.yash.ota.serviceimpl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yash.ota.domain.ResultDetail;
import com.yash.ota.rowmapper.ResultDetailRowMapper;
import com.yash.ota.service.ResultDetailService;

@Service
public class ResultDetailServiceImpl implements ResultDetailService {
	
	private Logger log = Logger.getLogger(ResultDetailServiceImpl.class);
	private JdbcTemplate jdbcTemplate;
	private ResultDetailRowMapper resultDetailRowMapper = new ResultDetailRowMapper();

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ResultDetailRowMapper getResultDetailRowMapper() {
		return resultDetailRowMapper;
	}

	public void setResultDetailRowMapper(ResultDetailRowMapper resultDetailRowMapper) {
		this.resultDetailRowMapper = resultDetailRowMapper;
	}
	
	/**
	 * @author nick.parker
	 * @param resultId the id of the result whose details should be fetched
	 */
	@Override
	public List<ResultDetail> getResultDetails(int resultId) {
		log.info("Getting Specific Result Details to display on trainer-trainee-detail-techwise page with resultId: " + resultId);
		String sql = "select *" + 
				"from questions Q\r\n" + 
				"inner join result_details RD on RD.question_id=Q.id\r\n" + 
				"inner join users U on RD.user_id=U.id\r\n" + 
				"where RD.result_id=?";
		return jdbcTemplate.query(sql, resultDetailRowMapper, resultId);
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;
		
	}

}
