package com.yash.ota.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.ResultDAO;
import com.yash.ota.domain.ReportItem;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.Technology;
import com.yash.ota.domain.TraineeReportItem;
import com.yash.ota.domain.User;
import com.yash.ota.exception.RetakenTestException;
import com.yash.ota.rowmapper.ResultRowMapper;


/**
 * This interface provides a service object for the Result data model.
 *
 * @author justin.dilks
 */
public interface ResultService {
	
	/**
	 * Used for JUnit testing
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	
	/**
	 * Used for JUnit testing in ServiceImplResult.class
	 * @param resultDAO
	 */
	public void setResultDAO(ResultDAO resultDAO);
	
	/**
	 * Used for JUnit testing in ResultServiceImplResult.class
	 * @return ResultRowMapper
	 */
	public ResultRowMapper getResultRowMapper();
	
	/**
	 * Returns results based on user
	 *
	 * @param id User id to get the results for that user
	 * @return List of results for the user
	 */
	List<Result> getResultsByUserId(int id);

	/**
	 * Function to retrieve a Result object with the given id,
	 * pulls list of Results from dao layer
	 *
	 * @param id The id of the user in question
	 * @return Returns the Result object with the given id if found, null otherwise
	 * @author nick.parker
	 */
	public Result findResultById(int id);

	/**
	 * The function will be used to retrieve a list of report objects
	 * based on a user id and tech id
	 * @param techId id of tech
	 * @param userId id of the user
	 * @return List of chart data objects
	 * @author phoutsaksit.keomala
	 */
	public List<TraineeReportItem> getReportItemsByTechIdUserId(int techId, int userId);
	/**
	 * deletes result from DB
	 * 
	 * @param resultId id of result to delete
	 * @author Zachary Karamanlis, though nick.parker did DAO
	 */
	public void delete(int resultId);
	
	/**
	 * Changes status of result to given status id
	 * 
	 * @param statusId
	 */
	public void updateStatus(int statusId, int resultId);
	
	 /** Returns a Result object by the given testId and userId, or null if not found
	 * @param testId int The id of the desired test
	 * @param userId int The id of the desired user
	 * @return Result
	 * @author connor.brown
	 */
	public Result getResultByTestIdAndUserId(int testId, int userId);


	
	/**
	 * Used for Testing
	 * @param mockedResultDAO
	 */
	public void setDAO(ResultDAO mockedResultDAO);

	/**
	 * Method to generate data for JSON conversion in AMCharts
	 * @param batchId, techId
	 * @return List average techwise score for all trainees in a given batch
	 * @author  nipun.dayanath
	 */
	public List<ReportItem> techwiseReport(int batchId, int techId);

	/**
	 * Method to generate data for JSON conversion in AMCharts
	 * @param batchId,
	 * @return List average score for all trainees in a given batch
	 * @author  nick.stone
	 */
	public List<ReportItem> batchReport(int batchId);


	/**
	 * Method to generate data for JSON conversion in AMCharts
	 * @param traineeId
	 * @return List average techwise score for a given trainee
	 * @author karl.roth, nipun.dayanath
	 */
	public List<ReportItem> individualTraineeTechwiseReport(int traineeId);

	/**
	 * Method to generate data for JSON conversion in AMCharts
	 * @param traineeId
	 * @param techId id of the specific tech to display
	 * @return
	 */
	public List<ReportItem> individualTraineeTopicwiseReport(int traineeId, int techId);
	
	/**
	 * Method to return the Total Number of Results by Trainer
	 * @param user
	 * @return
	 * @author Geon Gayles
	 */
	public int getTotalNumberOfResultsByTrainer(User user);

	
	/**
	 * Adds a new result to the database based on the given test id and user id
	 * @param userAnswers The list of the users answers
	 * @param testId The id of the test just taken
	 * @param userId The id of the user who just took the test
	 * @param timeTaken The amount of time the user took to complete the test in milliseconds
	 * @author connor.brown
	 * @throws RetakenTestException 
	 */
	public Result findResultByTestIdAndUserId(int testId, int userId);

	public void addNewResults(List<String> userAnswers, int testId, int userId, long timeTaken) throws RetakenTestException;

	/**
	 * Method to generate data for JSON Conversion in AmCharts
	 * @param traineeId The id for the trainee for which data is to be extracted
	 * @param techId The id for the technology for which data is to be extracted
	 * @return List of score in individual tests taken by the trainee for a particular technology
	 * @author - Jay Shah
	 */
	List<ReportItem> individualTraineeTechwiseSpecificTestReport(int traineeId, int techId);

	/**
	 * Method to generate data for JSON Conversion in AmCharts
	 * @param batchId The id for the batch for which data is to be extracted
	 * @return List of batch averages for a particular technology
	 * @author - Jay Shah
	 */
	List<ReportItem> individualBatchTechWiseReport(int batchId);

	/**
	 * Updates a result in the database
	 * @param userAnswer the users answers for this test
	 * @param testId the id of the test just taken
	 * @param userId the id of the user who took the test
	 * @param timeTaken the amount of time taken in the test
	 * @author connor.brown
	 */
	public void updateResult(List<String> userAnswer, int testId, int userId, long timeTaken);

	/**
	 * checks if test result already exists in the database
	 * @param testId int test to check
	 * @param userId int user to check
	 * @return false if result does not exist in the database
	 * @throws RetakenTestException if result exists in the database
	 * @author connor.brown
	 */
	boolean checkResultExists(int testId, int userId) throws RetakenTestException;

	/**
	 * Gets the report of the test just taken and redirects the user to the end test page
	 * This helper function is used to create a hashmap of the sorted results for a user
	 * It will take all technologies and query for results to add to the map
	 * @return Map<Integer, List<Result>> map of the sorted results
	 * @author phoutsaksit.keomala
	 */
	public Map<Integer, String> getChartData(int userId, List<Technology> techListForUser);
}
