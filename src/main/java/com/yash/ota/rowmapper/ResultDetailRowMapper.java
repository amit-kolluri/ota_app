package com.yash.ota.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yash.ota.domain.ResultDetail;

/**
 * This class provides object relational mapping functionality for the ResultDetail object.
 *
 * @author nick.parker
 */
public class ResultDetailRowMapper implements RowMapper<ResultDetail>{

	@Override
	public ResultDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultDetail resultDetail = new ResultDetail();
		resultDetail.setUserId(rs.getInt("user_id"));
		resultDetail.setQuestion(rs.getString("question"));
		resultDetail.setOption1(rs.getString("option_1"));
		resultDetail.setOption2(rs.getString("option_2"));
		resultDetail.setOption3(rs.getString("option_3"));
		resultDetail.setOption4(rs.getString("option_4"));
		resultDetail.setUserAnswer(rs.getString("user_answer"));
		resultDetail.setCorrectAnswer(rs.getString("correct_answer"));
		return resultDetail;
	}
	

}
