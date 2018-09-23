 package com.yash.ota.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ota.dao.QuestionDAO;
import com.yash.ota.domain.PartialQuestion;
import com.yash.ota.domain.Question;

import com.yash.ota.domain.Topic;
import com.yash.ota.domain.User;

import com.yash.ota.exception.EmptyFieldException;

import com.yash.ota.rowmapper.PartialQuestionRowMapper;
import com.yash.ota.rowmapper.QuestionRowMapper;
import com.yash.ota.service.QuestionService;
import com.yash.ota.util.Queries;



/**
 * Implementation of QuestionService that provides service to Test domain
 *
 * @author Zachary Karamanlis, connor.brown
 *
 */
@Service
public class QuestionServiceImpl implements QuestionService {



	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
 

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private QuestionDAO questionDAO;
    private JdbcTemplate jdbcTemplate;
    private Logger log = Logger.getLogger(QuestionServiceImpl.class);
    

    private QuestionRowMapper questionRowMapper = new QuestionRowMapper();
    private PartialQuestionRowMapper partialQuestionRowMapper = new PartialQuestionRowMapper();
    

    public void setDao(QuestionDAO mocked) {
    	this.questionDAO = mocked;
    }
    
  

 
	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	
    /**
     * gives a count of the questions related to a test id
     *
     * @param testId id of the test to count questions
     * @return the number of questions related to given testId
     * @author Zachary Karamanlis
     */
    public int questionCount(int testId) {
        String sql = "SELECT COUNT(*) FROM test_details WHERE test_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[] { testId }, Integer.class);
    }
    
    /**
	 * Method to return the Total Number of Questions by Trainer
	 * @param user
	 * @return
	 * @author Geon Gayles
	 */
    @Override
	public int getTotalNumberOfQuestionsByTrainer(User user) {
		// TODO Auto-generated method stub
		String sql = "select count(Q.id) from questions Q where Q.created_by = ?";
		int total = jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, Integer.class);
		return total;
	}
    
    /**
	 * Method to return the Total number of questions by Trainer
	 * @param user
	 * @param topic
	 * @return
	 * @author Geon Gayles
	 */
    @Override
    public List<Question> getQuestionsAccordingToTopicAndTrainer(User user, Topic topic){
    	String sql = "select * from questions Q\n" + 
    			"where Q.created_by = ? and Q.topic_id = ?";
    	
    	List<Question> questions = jdbcTemplate.query(sql, new Object[] {user.getId(), topic.getId()},new QuestionRowMapper());
    	
    	return questions;
    	
    }
    
    

	/**
	 * Used for JUnit tests to pass a mocked JdbcTemplate
	 * 
	 * @param temp
	 *            the JdbcTemplate to use
	 * @author Zachary Karamanlis
	 */
	@Override
	public void setMockedJdbcTemplate(JdbcTemplate temp) {
		this.jdbcTemplate = temp;
	}

	/**
	 * gets all questions related to a topic id
	 * 
	 * @param topicId
	 *            test id to search by
	 * @return list of associated questions
	 */
	@Override
	public List<Question> getByTopicId(int topicId) {
		String sql = "SELECT * FROM questions WHERE topic_id=?";
		return jdbcTemplate.query(sql, new Object[] { topicId }, questionRowMapper);
	}



	@Override
	public void deleteQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDAO.delete(question);
	}

	@Override
	public void updateQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDAO.update(question);
	}

	/**
	 * @param questionToBeInserted Question object to be inserted into the Database
	 * @throws EmptyFieldException 
	 * @author Lhito Camson
	 */
	@Override
	public void createQuestion(Question questionToBeInserted)  {
		questionDAO.insert(questionToBeInserted);
	}
		
	/**
	 * @author connor.brown
	 */
	@Override
	public List<PartialQuestion> getAllQuestionsByTestId(int testId) {
		log.info("Finding the list of questions by test id " + testId);
		return jdbcTemplate.query(Queries.FIND_ALL_QUESTIONS_BASED_ON_TEST_ID, partialQuestionRowMapper, testId);

	}
	
	
	/**
	 * Helper method to create the list of questions from the excel file
	 * @param file excel file uploaded from user
	 * @param topicId topic id sent from the import page
	 * @param user current trainer
	 * @return List of questions to be added to the database
	 * @author phoutsaksit.keomala
	 */
	public List<Question> constructQuestionListFromExcelFile(MultipartFile file, int topicId, User user) {
		List<Question> questionList = new ArrayList<>();

		if (file != null) {
			try {
				XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell testCell;
				
				int rowNum = 1;
				while (rowNum < sheet.getLastRowNum()-1) {
					row = sheet.getRow(rowNum);
					testCell = row.getCell(0, Row.RETURN_BLANK_AS_NULL);
				    if (testCell != null) {
				    	Question question = constructQuestionFromExcelRow(row, topicId, user);
						questionList.add(question);
				    }
					rowNum++;
				}
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
		}
		
		return questionList;
	}
	
	/**
	 *Helper method to construct a question obect from an excel row 
	 * @param row row of excel
	 * @param topicId topic id sent from the html page
	 * @param user trainer currently logged in
	 * @return question object to add to the question list
	 */
	public Question constructQuestionFromExcelRow(XSSFRow row, int topicId, User user) {
		DataFormatter fmt = new DataFormatter();
		Question question = new Question();
		question.setQuestion(row.getCell(0).getStringCellValue());
		question.setCorrectAnswer(row.getCell(1).getStringCellValue());
		question.setOption1(fmt.formatCellValue(row.getCell(2)));
		question.setOption2(fmt.formatCellValue(row.getCell(3)));
		question.setOption3(fmt.formatCellValue(row.getCell(4)));
		question.setOption4(fmt.formatCellValue(row.getCell(5)));
		question.setStatusId(1);
		question.setTopicId(topicId);
		question.setCreatedBy(user.getId());
		question.setModifiedBy(user.getId());
		question.setCreatedDate(new Date());
		question.setModifiedDate(new Date());
		
		return question;
	}
	
	
	/**
	 * bulk add a list of questions to the database
	 * @param questionList list of questions to be added to the DB
	 * @author phoutsaksit.keomala
	 */
	public void addQuestionListToDB(List<Question> questionList) {
		for(Question question: questionList) {
			questionDAO.insert(question);
		}
	}

	/**
	 * @author Lalu shaik.
	 */
	@Override
	public List<Question> getQuestionByTestId(int testId) {

		String sql = "select *\r\n" + "from questions Q\r\n" + "join topics TOP on TOP.id = Q.topic_id\r\n"
				+ "join tests on tests.topic_id = TOP.id\r\n" + "Where tests.id = ?";
		return jdbcTemplate.query(sql, questionRowMapper, testId);

	}


}
