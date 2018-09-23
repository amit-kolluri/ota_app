package com.yash.ota.daoimpl;


import java.sql.Types;


import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yash.ota.dao.QuestionDAO;
import com.yash.ota.domain.Question;

import com.yash.ota.rowmapper.TestRowMapper;

import com.yash.ota.domain.User;
import com.yash.ota.rowmapper.QuestionRowMapper;
import com.yash.ota.rowmapper.UserRowMapper;

@Repository
public class QuestionDAOImpl implements QuestionDAO {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private Logger log = Logger.getLogger(QuestionDAOImpl.class);

	private QuestionRowMapper questionRowMapper = new QuestionRowMapper();

	/**
	 * @author Lhito Camson
	 * @return
	 */
	public QuestionRowMapper getQuestionRowMapper() {
		return questionRowMapper;
	}

	/**
	 * @author Lhito Camson
	 * @return
	 */
	public void setQuestionRowMapper(QuestionRowMapper questionRowMapper) {
		this.questionRowMapper = questionRowMapper;
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @author karl.roth
	 */
	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;
	}

	/**
	 * @author Lhito Camson
	 */
	@Override
	public void insert(Question question) {
		String sql = "INSERT INTO questions (question,correct_answer,option_1,option_2,option_3,option_4,topic_id,"
				+ "status_id,created_by,created_date,modified_by,modified_date) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { question.getQuestion(),question.getCorrectAnswer(),question.getOption1(),question.getOption2(),
				question.getOption3(),question.getOption4(),question.getTopicId(),question.getStatusId(),question.getCreatedBy(),question.getCreatedDate(),
				question.getModifiedBy(),question.getModifiedDate()
				};
		jdbcTemplate.update(sql, params);
	}
	
	/**
     * Method to delete the Question in DB
     * @param question
     * @author Geon Gayles
     */
	@Override
	public void delete(Question question) {
		// TODO Auto-generated method stub
		String sql = "Delete from questions where id = ?";
		jdbcTemplate.update(sql, new Object[] {question.getId()});
	}

	/**
	 * Method to update the Question
	 * @param question
	 * @author Geon Gayles
	 */
	@Override
    public void update(Question question) {
    	String sql = "UPDATE questions SET question = ?, option_1 = ?, option_2 = ?, option_3 = ?, option_4 = ?, correct_answer = ?,"
    			+ " modified_by = ?, modified_date = ? WHERE id = ?";
    	Object[] params = {
    		question.getQuestion(),
    		question.getOption1(),
    		question.getOption2(),
    		question.getOption3(),
    		question.getOption4(),
    		question.getCorrectAnswer(),
    		question.getModifiedBy(),
    		question.getModifiedDate(),
    		question.getId()
    	};
    	
    	int types[] = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.DATE};
    	
    	jdbcTemplate.update(sql, params);
    	
    }


}
