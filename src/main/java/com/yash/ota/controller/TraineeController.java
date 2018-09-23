/*
 * TraineeController
 *
 * Version 0.1
 *
 * Date: 08/18/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.ota.command.UserAnswerCommand;
import com.yash.ota.domain.EndOfTestResult;
import com.yash.ota.domain.PartialQuestion;
import com.yash.ota.domain.ReportItem;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.Technology;
import com.yash.ota.domain.Test;
import com.yash.ota.domain.TimeTaken;
import com.yash.ota.domain.Topic;
import com.yash.ota.domain.TraineeReportItem;
import com.yash.ota.domain.User;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.exception.RetakenTestException;
import com.yash.ota.service.QuestionService;
import com.yash.ota.service.ResultService;
import com.yash.ota.service.TechnologyService;
import com.yash.ota.service.TestService;
import com.yash.ota.service.TimeTakenService;
import com.yash.ota.service.TopicService;
import com.yash.ota.service.UserService;

/**
 * This class provides a controller for the trainees
 * @author karl.roth, connor.brown, austin.plogman
 *
 */

@Controller
@RequestMapping("/trainee")
public class TraineeController {

	@Autowired
	private UserService userService;

	@Autowired
	private TechnologyService technologyService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private TopicService topicService;

	@Autowired
	private TestService testService;
	
	@Autowired
	private TimeTakenService timeTakenService;
	@Autowired
	private QuestionService questionService;

	private Logger log = Logger.getLogger(TraineeController.class.getName());
	

	/**
	 * This function will retrieve the user from the session,
	 * It will then find all the results based on the user id
	 * and populate a map so that those results are sorted by 
	 * technology id. The map will be used as data for the jsp page
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/report.htm", method=RequestMethod.GET)
	public String showTraineeReportPage(Model model, HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");

			List<Technology> techListForUser = technologyService.listTechForUser(user.getId());
			
			Map<Integer, String> sortedResults = resultService.getChartData(user.getId(), techListForUser);
			
	 		model.addAttribute("techListForUser", techListForUser);
			model.addAttribute("sortedResults",sortedResults);

			return "trainee/trainee-report";

		} catch(Exception ex) {
			log.info(ex.getMessage());
			log.info(ex.getStackTrace());
			return "trainee/trainee-dashboard";
		}
	}

	
	/** Gets the report of the test just taken and redirects the user to the end test page
	 * @param session The session for this servlet
	 * @return String
	 * @author connor.brown
	 */
	@RequestMapping("/test/{testid}/report.htm")
	public String showEndOfTestReport(HttpSession session, Model model, @PathVariable("testid") int testId) {
		EndOfTestResult endOfTestResult = null;
		log.info("Redirecting user to endTest page");
		User user = (User) session.getAttribute("user");
		
		try {
			Test test = testService.getTestById(testId);
			Topic topic = topicService.getTopicById(test.getTopicId());
	        TimeTaken timeTaken = timeTakenService.getTimeTakenByTestIdAndUserId(testId, user.getId());
	        Result result = resultService.getResultByTestIdAndUserId(testId, user.getId());
	        Technology technology = technologyService.getTechnologyById(result.getTechnologyId());
	        if (topic != null && timeTaken != null && result != null && technology != null) {
	            endOfTestResult = new EndOfTestResult();
	            endOfTestResult.setDate((java.sql.Date)result.getCreatedDate());
	            endOfTestResult.setTechnologyName(technology.getName());
	            endOfTestResult.setUsername(user.getUserName());
	            endOfTestResult.setCorrectAnswers(result.getObtainedMarks());
	            endOfTestResult.setTotalAnswers(result.getTotalMarks());
	            endOfTestResult.setTestName(topic.getName());
	            endOfTestResult.setTimeTaken(timeTaken.getTimeTaken());
	        }
	        
			List<Result> results = resultService.getResultsByUserId(user.getId());
			List<EndOfTestResult> previousTests = new ArrayList<>();
			for (Result prevResult:results) {
				EndOfTestResult endResult = new EndOfTestResult();
				endResult.setDate((java.sql.Date)prevResult.getCreatedDate());
				endResult.setUsername(user.getUserName());
				endResult.setTechnologyName(technologyService.getTechnologyById(prevResult.getTechnologyId()).getName());
				endResult.setTestName(topicService.getTopicById(prevResult.getTopicId()).getName());
				endResult.setCorrectAnswers(prevResult.getObtainedMarks());
				endResult.setTotalAnswers(prevResult.getTotalMarks());
				endResult.setTimeTaken(prevResult.getTimeTaken());
				float percent = (prevResult.getObtainedMarks() * 100) / prevResult.getTotalMarks();
				endResult.setPercent(String.valueOf(percent));
				previousTests.add(endResult);
			}
			model.addAttribute("PreviousTests", previousTests);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/users/landing.htm";
		}
        
		model.addAttribute("EndOfTestResult", endOfTestResult);
		return "trainee/test/end-test";
	}

	
	/**
	 * Trainee dashboard is shown 
	 * Assigned tests are shown to user
	 * Previous tests reports are shown to user
	 * Chart is shown with total scores within technologies
	 * TODO : Finishing touches need to be done to improve
	 * @param session
	 * @return redirect user to trainee dashboard
	 * @author austin.plogman 
	 */
	@RequestMapping(value = "/dashboard.htm", method = RequestMethod.GET)
    public String showTraineeDashboard(Model model, HttpSession session) throws InvalidIdException {
		//User loggedInUser = (User)session.getAttribute("user");
		User loggedInUser = userService.findById(103); // For Testing
		if(loggedInUser == null) {
			return "login";
		}
		else {
			log.info("Redirecting "+loggedInUser.getUserName()+" trainee dashboard");
			session.setAttribute("loggedInUser", loggedInUser );
			session.setAttribute("assignedTests", testService.getAssignedTestsByUserId(loggedInUser.getId()));		
			List<Result> results = resultService.getResultsByUserId(loggedInUser.getId());
			List<ReportItem> technologies = resultService.individualTraineeTechwiseReport(loggedInUser.getId());
	        Map<String, Integer> technologyHashMap = new HashMap<>();
	        for (ReportItem reportItem : technologies){
	            String technologyName = reportItem.getName();
	            technologyHashMap.put(technologyName,technologyService.getTechnologyByName(technologyName));
	        }
	        model.addAttribute("traineeId", loggedInUser.getId());
	        model.addAttribute("technologies", technologies);
	        model.addAttribute("techMap", technologyHashMap);

			
			return "trainee/trainee-dashboard";
		} 

		}
	
	/**
     * Provides data for the graph to be displayed
     * TODO : Finishing touches need to be done to improve
     * @param traineeId - Id of the trainee for which details are to be fetched
     * @return json data for a given trainee technology wise
     * @author austin.plogman
     */
    @RequestMapping(value = "/trainee/data.json")
    @ResponseBody
    public List<ReportItem> traineeReportData(HttpSession session, User traineeId){
		//traineeId = (User)session.getAttribute("user");
    	traineeId = userService.findById(103);
        return resultService.individualTraineeTechwiseReport(traineeId.getId());
    }
	
 	
	/**
	 *  Displays two tables showing user available tests and previous tests
	 * TODO : Finishing touches need to be done to improve
	 * @param session
	 * @return displays tests default page showing available tests and previous tests
	 * @author austin.plogman 
	 */
	@RequestMapping(value = "/tests.htm", method = RequestMethod.GET)
    public String showTestsDefault(HttpSession session) throws InvalidIdException {
		try {
			User loggedInUser = userService.findById(101); // For Testing
			//User loggedInUser = (User)session.getAttribute("user");
			session.setAttribute("loggedInUser", loggedInUser);
			if(loggedInUser == null) {
				return "login";
			}
			else {
				session.setAttribute("availableTests",testService.getAssignedTestsByUserId(loggedInUser.getId()));
				List<Result> results = resultService.getResultsByUserId(loggedInUser.getId());
				List<EndOfTestResult> previousTests = new ArrayList<>();
				for (Result result:results) {
					EndOfTestResult endResult = new EndOfTestResult();
					endResult.setTestName(topicService.getTopicById(result.getTopicId()).getName());
					endResult.setCorrectAnswers(result.getObtainedMarks());
					endResult.setTotalAnswers(result.getTotalMarks());
					endResult.setTimeTaken(result.getTimeTaken());
					float percent = (result.getObtainedMarks() * 100) / result.getTotalMarks();
					endResult.setPercent(String.valueOf(percent));
					previousTests.add(endResult);
				}
				session.setAttribute("previousTests", previousTests);
			}
		} 
	catch (Exception ex){
			log.error(ex.getMessage());
			log.error(ex.getStackTrace());
			return "login";
		}
		return "trainee/test/test-default";

		}
	
	/**
	 * Gets available tests for the user and redirects to the available-tests page
	 * Sends the user to the test page
	 * @param session The session for this user
	 * @param model a model object containing necessary information for the test
	 * @param testId The id of the test the user id taking
	 * @return a redirect to the test jsp page
	 * @author connor.brown
	 */
	@RequestMapping("/test/{testid}/questions.htm")
	public String takeTest(HttpSession session, Model model, @PathVariable("testid") int testId) {
		List<PartialQuestion> questionList = questionService.getAllQuestionsByTestId(testId);
		Test test = null;
		try {
			test = testService.getTestById(testId);
			resultService.checkResultExists(testId, ((User)session.getAttribute("user")).getId());
		} catch (InvalidIdException e) {
			e.printStackTrace();
			return "redirect:/users/landing.htm";
		} catch (RetakenTestException e) {
			e.printStackTrace();
			System.out.println("got here");
			return "redirect:/tests/assigned.htm";
		}
		model.addAttribute("UserAnswerCommand", new UserAnswerCommand());
		model.addAttribute("testName", test.getTestName());
		model.addAttribute("timeAllotted", test.getTimeAlloted());
		model.addAttribute("questionList", questionList);
		session.setAttribute("beginTime", System.currentTimeMillis());
		return "trainee/test/take-test";
	}
	
	/**
	 * Processes the users answers and redirects to the results page
	 * @param session The session for this user
	 * @param testId The id of the test just taken
	 * @param userAnswers The list of the users answers
	 * @return a redirect to the results jsp page
	 * @author connor.brown
	 */
	@RequestMapping("/test/{testid}/processTest.htm")
	public String processTestResults(HttpSession session, @PathVariable("testid") int testId, @ModelAttribute("UserAnswerCommand") UserAnswerCommand userAnswers) {
		log.info("Processing Test results");
		
		User user = (User) session.getAttribute("user");
		long timeTaken = System.currentTimeMillis() - ((Long)session.getAttribute("beginTime"));
		
		try {
			resultService.addNewResults(userAnswers.getUserAnswer(), testId, user.getId(), timeTaken);
			timeTakenService.addTimeTaken(testId, user.getId(), timeTaken);
		} catch (RetakenTestException e) {
			e.printStackTrace();
			return "redirect:/tests/assigned.htm";
		}
		
		log.info("Redirecting user to result controller");
		return "redirect:/trainee/test/" + testId + "/report.htm";
	}
	
	/**
	 * Gets assigned tests for the user and redirects to the assigned-tests page
	 * @param session The session for this servlet
	 * @return String the redirect to assigned-tests page
	 * @author caleb.reiter
	 */
	@RequestMapping("/tests/assigned.htm")
	public String showAssignedTests(HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		//User user = userService.findById(101); //For Testing
		log.info("Redirecting user " + user + " to assigned tests page");
		try {
			model.addAttribute("assignedTests", testService.getAssignedTestsByUserId(user.getId()));
		} catch (NullPointerException | InvalidIdException e) {
			return "redirect:/users/landing.htm";
		}
		return "trainee/test/assigned-tests";
	}
	
	/**
	 * Gets specific test and redirects to the begin-test page
	 * @param session HttpSession
	 * @param model Model
	 * @param testId int the test to begin
	 * @return String the redirect to begin-test page
	 * @author caleb.reiter
	 */
	@RequestMapping("/test/{testid}/instruction.htm")
	public String showBeginTest(HttpSession session, Model model, @PathVariable("testid") int testId) {
		try {
			User user = (User) session.getAttribute("user");
			if (!resultService.checkResultExists(testId, user.getId())) {
				model.addAttribute("test", testService.getAssignedTestById(testId));
				return "trainee/test/begin-test";
			} else {
				return "redirect:/tests/assigned.htm";
			}
		} catch (RetakenTestException | InvalidIdException e) {
			return "redirect:/tests/assigned.htm";
		} catch (NullPointerException e) {
			return "redirect:/users/landing.htm";
		}
	}
	

	/**
	 *
	 * @param session, model
	 * @return page to redirect to
	 * @author justin.dilks
	 */
	@RequestMapping(value="/tests/previous.htm", method=RequestMethod.GET)

	public String showPreviousTests(HttpSession session, Model model) {
		try{
			User user = (User) session.getAttribute("user");
			List<Result> results = resultService.getResultsByUserId(user.getId());
			List<EndOfTestResult> previousTests = new ArrayList<>();
			for (Result result:results) {
				EndOfTestResult endResult = new EndOfTestResult();
				endResult.setTestName(topicService.getTopicById(result.getTopicId()).getName());
				endResult.setCorrectAnswers(result.getObtainedMarks());
				endResult.setTotalAnswers(result.getTotalMarks());
				endResult.setTimeTaken(result.getTimeTaken());
				float percent = (result.getObtainedMarks() * 100) / result.getTotalMarks();
				endResult.setPercent(String.valueOf(percent));
				endResult.setTechnologyName(technologyService.getTechnologyById(result.getTechnologyId()).getName());
				previousTests.add(endResult);
			}
			model.addAttribute("PreviousTests", previousTests);
			log.info("Redirecting user with id " + user.getId() + " to previous tests page");
			return "trainee/test/previous-tests";
		}
		catch (Exception ex){
			log.error("Failed", ex);
			return "redirect:/users/landing.htm";
		}
	}

}

