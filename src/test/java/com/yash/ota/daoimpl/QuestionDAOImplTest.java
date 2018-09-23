package com.yash.ota.daoimpl;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.QuestionDAO;
import com.yash.ota.domain.Question;

/**
 * This class is for testing the QuestionDAOImpl
 * 
 * @author Lhito Camson
 *
 */
public class QuestionDAOImplTest {

	private QuestionDAO questionDAO;
	private ApplicationContext context;
	private JdbcTemplate mockedJdbcTemplate;

	@Before
	public void Setup() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		questionDAO = context.getBean("questionDAOImpl", QuestionDAO.class);
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		questionDAO.setJdbcTemplate(mockedJdbcTemplate);

	}
	
	 @Test
	    public void testDelete_QuestionIDIsGiven_ShouldReturnTrue() {
	    	Question question = new Question();
	    	question.setId(1);
	    	questionDAO.delete(question);
			verify(mockedJdbcTemplate).update(anyString(), any(Integer.class));
	    	
	    }
	    
	   

	@Test
	public void testInsert_QuestionGiven_ShouldInsertIntoDatabase() {
		Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
		Question question = new Question();
		question.setCorrectAnswer("correctAnswer");
		question.setCreatedBy(1);
		question.setCreatedDate(date);
		question.setModifiedBy(1);
		question.setModifiedDate(date);
		question.setOption1("option1");
		question.setOption2("option2");
		question.setOption3("option3");
		question.setOption4("option4");
		question.setQuestion("question");
		question.setStatusId(1);
		question.setTopicId(1);

		String sql = "INSERT INTO questions (question,correct_answer,option_1,option_2,option_3,option_4,topic_id,"
				+ "status_id,created_by,created_date,modified_by,modified_date) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { question.getQuestion(), question.getCorrectAnswer(), question.getOption1(),
				question.getOption2(), question.getOption3(), question.getOption4(), question.getTopicId(),
				question.getStatusId(), question.getCreatedBy(), question.getCreatedDate(), question.getModifiedBy(),
				question.getModifiedDate() };

		questionDAO.insert(question);
		Mockito.verify(mockedJdbcTemplate).update(sql, params);

	}


}
