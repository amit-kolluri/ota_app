package com.yash.ota.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ota.dao.QuestionDAO;
import com.yash.ota.domain.PartialQuestion;
import com.yash.ota.domain.Question;

import com.yash.ota.domain.Topic;
import com.yash.ota.domain.User;

import com.yash.ota.exception.EmptyFieldException;

/**
 * Provides service object for Question domain
 * 
 * @author Zachary Karamanlis
 *
 */
public interface QuestionService {


    
    /**
     * @author Lalu shaik.
     * @return list of questions depends on test id.
     */ 
    public List<Question>getQuestionByTestId(int testId);


	/**
	 * gives a count of the questions related to a test id
	 *
	 * @param testId
	 *            id of the test to count questions
	 * @return the number of questions related to given testId
	 */
	public int questionCount(int testId);

	/**
	 * Used for JUnit testing
	 * 
	 * @param temp
	 */
	public void setMockedJdbcTemplate(JdbcTemplate temp);

	/**
	 * Method to return the Total Number of Questions by Trainer
	 * 
	 * @param user
	 * @return
	 * @author Geon Gayles
	 */
	public int getTotalNumberOfQuestionsByTrainer(User user);

	/**
	 * Method to return the Total number of questions by Trainer
	 * 
	 * @param user
	 * @param topic
	 * @return
	 * @author Geon Gayles
	 */
	public List<Question> getQuestionsAccordingToTopicAndTrainer(User user, Topic topic);

	public List<Question> getByTopicId(int topicId);

	/**
	 * Method to delete the Question in DB
	 * 
	 * @param question
	 */
	public void deleteQuestion(Question question);

	/**
	 * Method to update the Questopm
	 * 
	 * @param question
	 * @author Geon Gayles
	 */
	public void updateQuestion(Question question);

	/**
	 * creates and inserts a new question into the database
	 * 
	 * @param questionToBeInserted
	 * @throws EmptyFieldException
	 * @author Lhito Camson
	 */
	public void createQuestion(Question questionToBeInserted);

	/**
	 * Returns a list of all the questions to a particular test
	 * 
	 * @param testId
	 *            The id of the test we are getting the questions for
	 * @return A list of question objects
	 * @author connor.brown
	 */
	public List<PartialQuestion> getAllQuestionsByTestId(int testId);

	public void setDao(QuestionDAO mocked);
	
	/**
	 * Helper method to create the list of questions from the excel file
	 * @param file excel file uploaded from user
	 * @param topicId topic id sent from the import page
	 * @param user current trainer
	 * @return List of questions to be added to the database
	 * @author phoutsaksit.keomala
	 */
	public List<Question> constructQuestionListFromExcelFile(MultipartFile file, int topicId, User user);
	
	/**
	 * bulk add a list of questions to the database
	 * @param questionList list of questions to be added to the DB
	 * @author phoutsaksit.keomala
	 */
	public void addQuestionListToDB(List<Question> questionList);

}
