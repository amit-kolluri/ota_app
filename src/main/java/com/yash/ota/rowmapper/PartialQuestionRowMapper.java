/*
 * PartialQuestionRowMapper
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
import com.yash.ota.domain.PartialQuestion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides object relational mapping functionality.
 *
 * @author karl.roth, connor.brown
 */
public class PartialQuestionRowMapper implements RowMapper<PartialQuestion> {
    public PartialQuestion mapRow(ResultSet resultSet, int i) throws SQLException {
        PartialQuestion question = new PartialQuestion();
        question.setId(resultSet.getInt("id"));
        question.setQuestion(resultSet.getString("question"));
        question.setCorrectAnswer(resultSet.getString("correct_answer"));
        question.setOption1(resultSet.getString("option_1"));
        question.setOption2(resultSet.getString("option_2"));
        question.setOption3(resultSet.getString("option_3"));
        question.setOption4(resultSet.getString("option_4"));
        question.setTestId(resultSet.getInt("test_id"));
        return question;
    }

}
