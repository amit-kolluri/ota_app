package com.yash.ota.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.QuestionDAO;
import com.yash.ota.domain.PartialQuestion;
import com.yash.ota.domain.Question;
import com.yash.ota.domain.Topic;
import com.yash.ota.domain.User;
import com.yash.ota.rowmapper.PartialQuestionRowMapper;
import com.yash.ota.rowmapper.QuestionRowMapper;
import com.yash.ota.service.QuestionService;
import com.yash.ota.util.Queries;



/**
 * JUnit test class for QuestionServiceImpl
 * 
 * @author Zachary Karamanlis, phoutsaksit.keomala
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {


	
	/**
	 * QuestionService object that will be tested
	 */
	@InjectMocks
	QuestionService questionService = new QuestionServiceImpl();
	
	/**
	 * JdbcTemplate that will have to be mocked
	 */
	@Mock
	JdbcTemplate jdbcTemplate;
	
	/**
	 * QuestionDAO object that will be tested
	 */
	@Mock
	QuestionDAO questionDAO;
	
	/**
	 * PartialQuestionRowMapper object for jdbcTemplate calls
	 */
	@Mock
	PartialQuestionRowMapper partialQuestionRowMapper;
	
	/**
	 * QuestionRowMapper object for jdbcTemplate calls
	 */
	@Mock
	QuestionRowMapper questionRowMapper;
	
	/**
	 * Creates QuestionService before each test
	 * 
	 * @author Zachary Karamanlis
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Gets Total number of Questions by Trainer
	 * @author Geon
	 */
	@Test
	public void testGetTotalNumberOfQuestionsByTrainer_UserGiven_ReturnInt() {
		User user = new User();
		user.setId(1);
		String sql = "select count(Q.id) from questions Q where Q.created_by = ?";

		int total = 10;
		Mockito.when(jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, Integer.class)).thenReturn(total);
		int output = questionService.getTotalNumberOfQuestionsByTrainer(user);
		assertEquals(total, output);
		
	}
	
	@Test
	public void testGetQuestionsAccordingToTopicAndTrainer_UserAndTopicGiven_ReturnList(){
		User user = new User();
		user.setId(1);
		
		Topic topic = new Topic();
		topic.setId(11);
		
		String sql = "select * from questions Q\n" + 
    			"where Q.created_by = ? and Q.topic_id = ?";
    	
    	List<Question> questions = jdbcTemplate.query(sql, new Object[] {user.getId(), topic.getId()},new QuestionRowMapper());
    	
		
		System.out.println(questions);
	}
	
	/**
	 * Tests count class of QuestionServiceImpl
	 * 
	 * @author Zachary Karamanlis
	 */
	@Test
	public void testQuestionCount_TestIdGiven_AccessesDatabase() {
		Mockito.doReturn(9).when(jdbcTemplate).queryForObject(anyString(),any(),eq(Integer.class));
		questionService.questionCount(1);
		verify(jdbcTemplate).queryForObject(anyString(),any(),eq(Integer.class));

	}
	
	/**
	 * Tests count class of QuestionServiceImpl
	 * 
	 * @author Zachary Karamanlis
	 */
	@Test
	public void testGetByTestId_TestIdGiven_AccessesDatabase() {
		questionService.getByTopicId(1);
		verify(jdbcTemplate).query(anyString(), any(Object[].class), any(QuestionRowMapper.class));
	}
	
	
	/**
	 * @author connor.brown
	 */
	@Test
	public void testGetAllQuestionsByTestId_GivenTestId_ShouldReturnListOfQuestions() {
		List<PartialQuestion> mockList = new ArrayList<PartialQuestion>();
		Mockito.when(jdbcTemplate.query(Queries.FIND_ALL_QUESTIONS_BASED_ON_TEST_ID, partialQuestionRowMapper, 101)).thenReturn(mockList);
		List<PartialQuestion> list = questionService.getAllQuestionsByTestId(101);
		assertEquals(mockList, list);
	}


	/**
	 * Tests create question 
	 * @author Lhito
	 */
	@Test
	public void testCreateQuestion_QuestionGiven_ShouldCallInsert() {
		Question questionToBeInserted = new Question();
		questionService.createQuestion(questionToBeInserted);
		Mockito.verify(questionDAO).insert(questionToBeInserted);;
	}

	/**
	 * Test adding bulk question list
	 * @author phoutsaksit.keomala
	 */
	@Test
	public void testCreateListOfQuestions_QuestionListGiven_ShouldCallInsert() {
		List<Question> mockQuestionList = new ArrayList<>();
		Question mockQuestion = new Question();
		mockQuestionList.add(mockQuestion);
		
		questionService.addQuestionListToDB(mockQuestionList);
		Mockito.verify(questionDAO).insert(mockQuestion);
	}

	
}
