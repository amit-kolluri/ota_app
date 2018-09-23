package com.yash.ota.serviceimpl;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.ota.dao.ResultDAO;
import com.yash.ota.domain.PartialQuestion;
import com.yash.ota.domain.ReportItem;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.Technology;
import com.yash.ota.domain.Test;
import com.yash.ota.domain.Topic;
import com.yash.ota.domain.TraineeReportItem;
import com.yash.ota.domain.User;
import com.yash.ota.exception.RetakenTestException;
import com.yash.ota.rowmapper.IndividualBatchTechWiseExtractor;
import com.yash.ota.rowmapper.IndividualTraineeTechSpecificTestWiseExtractor;
import com.yash.ota.rowmapper.IndividualTraineeTechwiseExtractor;
import com.yash.ota.rowmapper.IndividualTraineeTopicwiseExtractor;
import com.yash.ota.rowmapper.PartialQuestionRowMapper;
import com.yash.ota.rowmapper.ReportExtractor;
import com.yash.ota.rowmapper.ResultRowMapper;
import com.yash.ota.rowmapper.TechwiseReportExtractor;
import com.yash.ota.rowmapper.TestRowMapper;
import com.yash.ota.rowmapper.TopicRowMapper;
import com.yash.ota.rowmapper.TraineeReportItemExtractor;
import com.yash.ota.service.ResultService;
import com.yash.ota.util.Queries;

/**
 * This implementation provides a service object for the Result data model.
 */
@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultDAO resultDAO;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ResultRowMapper resultRowMapper;

	@Autowired
	private IndividualTraineeTechSpecificTestWiseExtractor testWiseExtractor;

	@Autowired
	private IndividualBatchTechWiseExtractor batchTechWiseExtractor;

	private Logger log = Logger.getLogger(ResultServiceImpl.class);

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ResultDAO getResultDAO() {
		return resultDAO;
	}

	public void setDAO(ResultDAO mockedDAO) {
		this.resultDAO = mockedDAO;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;
	}

	/**
	 * @author justin.dilks
	 */
	@Override
	public List<Result> getResultsByUserId(int id) {
		String sql = Queries.GET_RESULTS_FOR_USER;
		return jdbcTemplate.query(sql, resultRowMapper, id);
	}

	/**
	 * Brilliant, I know
	 *
	 * @param id
	 *            The id of the Result object to be found
	 * @return the Result Object with the given id
	 * @author nick.paker
	 */
	@Override
	public Result findResultById(int id) {
		log.info("Getting Results with result id: " + id);
		return resultDAO.findById(id);
	}

	/**
	 * deletes result from DB
	 *
	 * @param resultId id of result to delete
	 * @author Zachary Karamanlis, though nick.parker did DAO
	 */
	@Override
	public void delete(int resultId) {
		resultDAO.delete(resultId);
	}

	/**
	 * Changes status of result to given status id
	 *
	 * @param statusId
	 * @author Zachary Karamanlis
	 */
	public void updateStatus(int statusId, int resultId) {
		String sql = "UPDATE results SET status_id=2 WHERE id=?";
		jdbcTemplate.update(sql, resultId);
  }


	@Override
	public ResultRowMapper getResultRowMapper() {
		return resultRowMapper;
	}

	/**
	 * @author connor.brown
	 */
	@Override
	public Result getResultByTestIdAndUserId(int testId, int userId) {
		try {
			String resultSQL = Queries.FIND_RESULT_BY_TEST_ID_AND_USER_ID;
			return jdbcTemplate.queryForObject(resultSQL, resultRowMapper, testId, userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<ReportItem> techwiseReport(int batchId, int techId) {
		String sql = "select users.first_name," + " avg(obtained_marks/total_marks)*100 as marks" + " from results"
				+ " left join users on users.id = user_id" + " left join batches on batches.id = users.batch_id"
				+ " where batch_id = ? and technology_id = ?" + " group by users.id";
		return jdbcTemplate.query(sql, new TechwiseReportExtractor(), batchId, techId);
	}

	/**
	 * @author karl.roth
	 */
	@Override
	public List<ReportItem> individualTraineeTechwiseReport(int traineeId) {
		String sql = Queries.FIND_AVG_TECH_MARKS_BY_USER_ID;
		return jdbcTemplate.query(sql, new IndividualTraineeTechwiseExtractor(), traineeId);
	}
	

	/**
	 * @author karl.roth
	 */
	@Override
	public List<ReportItem> individualTraineeTopicwiseReport(int traineeId, int techId) {
		return jdbcTemplate.query(Queries.FIND_RESULT_BY_TRAINEE_ID_AND_TECH_ID, new IndividualTraineeTopicwiseExtractor(), traineeId, techId);
	}
	/**
	 * This function will retrieve a list of results based on a given user id and
	 * test id
	 * 
	 * @param testId id of the tech
	 * @param userId id of the user
	 * @return List of Result Objects
	 * @author phoutsaksit.keomala
	 */
	@Override
	public List<TraineeReportItem> getReportItemsByTechIdUserId(int techId, int userId) {
		return jdbcTemplate.query(Queries.GET_TRAINEE_REPORT_ITEMS_BY_TECH_AND_USER, new TraineeReportItemExtractor(), techId, userId);
	}
	
	/**
	 * Method to return the Total Number of Results by Trainer
	 * @param user
	 * @return
	 * @author Geon Gayles
	 */
	@Override
	public int getTotalNumberOfResultsByTrainer(User user) {
		// TODO Auto-generated method stub
		String sql = "select count(R.id) from results R " +
				"inner join users U on R.user_id = U.id and U.created_by = ?";
		int total = jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, Integer.class);
		return total;
		
	}

	
	/**
	 * @author connor.brown
	 * @throws RetakenTestException
	 */
	@Override
	public void addNewResults(List<String> userAnswers, int testId, int userId, long timeTaken)
			throws RetakenTestException {
		Result result = getResultFromParameters(userAnswers, testId, userId, timeTaken);

		log.info("checking if user already took this test");
		if(!checkResultExists(testId, userId)) {
			log.info("Adding results to database");
			resultDAO.insert(result);
		}
	}

	/**
	 * @author connor.brown
	 */
	@Override
	public boolean checkResultExists(int testId, int userId) throws RetakenTestException {
		try {
			Result queriedResult = jdbcTemplate.queryForObject(Queries.FIND_RESULT_BY_TEST_ID_AND_USER_ID,
					resultRowMapper, testId, userId);
			if (queriedResult != null) {
				// user already took this test, throw an error
				throw new RetakenTestException("User already took this test");
			} else {
				log.info("There is no test in the database with test id " + testId + " user id " + userId);
				return false;
			}
		} catch (EmptyResultDataAccessException e) {
			return false;
		}

	}

	/**
	 * Compares the users answers to the questions to return an int representing the
	 * number of correct answers
	 *
	 * @param userAnswers
	 *            The list of user answers
	 * @param questions
	 *            The list of question objects
	 * @param testId
	 *            The test we are comparing
	 * @return an int representing the number of correct answers
	 * @author connor.brown
	 */
	private int parseUserAnswersForNumberCorrect(List<String> userAnswers, List<PartialQuestion> questions,
			int testId) {
		int numCorrect = 0;
		for (int i = 0; i < userAnswers.size(); i++) {
			if (userAnswers.get(i) != null
					&& questions.get(i).getCorrectAnswer().equalsIgnoreCase(userAnswers.get(i))) {
				numCorrect++;
			}
		}

		log.info("Number of correct answers is " + numCorrect);
		return numCorrect;
	}

	/**
	 * Helper method to get a result object from the given parameters
	 *
	 * @param userAnswers
	 *            the list of all the user answers on the given test
	 * @param testId
	 *            the id of the test
	 * @param userId
	 *            the user who took the test
	 * @param timeTaken
	 *            the amount of time the user took to complete the test
	 * @return Result object
	 * @author connor.brown
	 */
	private Result getResultFromParameters(List<String> userAnswers, int testId, int userId, long timeTaken) {
		List<PartialQuestion> questions = jdbcTemplate.query(Queries.FIND_ALL_QUESTIONS_BASED_ON_TEST_ID,
				new PartialQuestionRowMapper(), testId);
		Test test = jdbcTemplate.queryForObject(Queries.FIND_TEST_BY_ID, new TestRowMapper(), testId);
		Topic topic = jdbcTemplate.queryForObject(Queries.FIND_TOPIC, new TopicRowMapper(), test.getTopicId());
		int numCorrect = parseUserAnswersForNumberCorrect(userAnswers, questions, testId);
		log.info("Creating new result object for user " + userId + " from test " + testId + " with time elapsed being "
				+ new Time(timeTaken));

		Result result = new Result();
		result.setUserId(userId);
		result.setTestId(testId);
		result.setCreatedDate(new Date(System.currentTimeMillis()));
		result.setModifiedDate(new Date(System.currentTimeMillis()));
		result.setObtainedMarks(numCorrect);
		result.setCreatedBy(userId);
		result.setModifiedBy(userId);
		result.setTotalMarks(questions.size());
		result.setStatusId(1);
		result.setTechnologyId(topic.getTechnologyId());
		result.setTimeTaken(new Time(timeTaken));
		result.setTopicId(topic.getId());

		return result;
	}

	/**
	 * @author connor.brown
	 */
	@Override
	public void updateResult(List<String> userAnswer, int testId, int userId, long timeTaken) {
		Result result = getResultFromParameters(userAnswer, testId, userId, timeTaken);
		Result inDatabaseResult = jdbcTemplate.queryForObject(Queries.FIND_RESULT_BY_TEST_ID_AND_USER_ID,
				resultRowMapper, testId, userId);
		result.setId(inDatabaseResult.getId());

		log.info("updating result in database");
		resultDAO.update(result);
	}

	public List<ReportItem> individualTraineeTechwiseSpecificTestReport(int traineeId, int techId) {
		return jdbcTemplate.query(Queries.FIND_RESULT_GET_RESULT_ID_BY_TRAINEE_ID_AND_TECH_ID, testWiseExtractor, traineeId, techId);
	}

	@Override
	public List<ReportItem> individualBatchTechWiseReport(int batchId) {
		return jdbcTemplate.query(Queries.FIND_RESULT_BY_BATCH_ID_AND_TECH_ID, batchTechWiseExtractor, batchId);
	}

	public void setResultDAO(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}


	@Override
	public List<ReportItem> batchReport(int batchId) {
		return jdbcTemplate.query(Queries.BATCH_REPORT, new ReportExtractor(), batchId);
	}

	@Override
	public Result findResultByTestIdAndUserId(int testId, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the report of the test just taken and redirects the user to the end test page
	 * This helper function is used to create a hashmap of the sorted results for a user
	 * It will take all technologies and query for results to add to the map
	 * @return Map<Integer, List<Result>> map of the sorted results
	 * @author phoutsaksit.keomala
	 */
	public Map<Integer, String> getChartData(int userId, List<Technology> techListForUser) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<Integer, String> sortedResults = new HashMap<>();
		for(Technology tech: techListForUser) {
			List<TraineeReportItem> userResultsForTech = getReportItemsByTechIdUserId(tech.getId(), userId);
			String reportItemJson = null;
			try {
				String temp  = objectMapper.writeValueAsString(userResultsForTech);
				reportItemJson = temp.replaceAll("\\s", "whitespace");
				
			} catch (JsonProcessingException e) {
				log.error(e.getMessage());
				log.error(e.getStackTrace());
			}
			sortedResults.put(tech.getId(), reportItemJson);
			
		}
		return sortedResults;
	}


}
