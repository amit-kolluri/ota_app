/*
 * ResultRowMapper
 *
 * Version 0.1
 *
 * Date: 08/18/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.rowmapper;
import com.yash.ota.domain.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides object relational mapping functionality.
 *
 * @author karl.roth
 */
public class QuestionRowMapper implements RowMapper<Question> {
    public Question mapRow(ResultSet resultSet, int i) throws SQLException {
        Question question = new Question();
        question.setId(resultSet.getInt("id"));
        question.setQuestion(resultSet.getString("question"));
        question.setCorrectAnswer(resultSet.getString("correct_answer"));
        question.setOption1(resultSet.getString("option_1"));
        question.setOption2(resultSet.getString("option_2"));
        question.setOption3(resultSet.getString("option_3"));
        question.setOption4(resultSet.getString("option_4"));
        question.setTopicId(resultSet.getInt("topic_id"));
        question.setStatusId(resultSet.getInt("status_id"));
        question.setCreatedBy(resultSet.getInt("created_by"));
        question.setCreatedDate(resultSet.getDate("created_date"));
        question.setModifiedBy(resultSet.getInt("modified_by"));
        question.setModifiedDate(resultSet.getDate("modified_date"));
        return question;
    }

}
