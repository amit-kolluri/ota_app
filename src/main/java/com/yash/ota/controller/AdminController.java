/*
 * Admin Controller
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.ota.domain.Batch;
import com.yash.ota.domain.PartialQuestion;
import com.yash.ota.domain.ReportItem;
import com.yash.ota.domain.ResultDetail;
import com.yash.ota.domain.Technology;
import com.yash.ota.domain.Test;
import com.yash.ota.domain.Topic;
import com.yash.ota.domain.User;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.service.BatchService;
import com.yash.ota.service.QuestionService;
import com.yash.ota.service.ResultDetailService;
import com.yash.ota.service.ResultService;
import com.yash.ota.service.TechnologyService;
import com.yash.ota.service.TestService;
import com.yash.ota.service.TopicService;
import com.yash.ota.service.UserService;

/**
 * This class provides a controller for the Admin data model.
 * 
 * @author fahmida.joyti, karl.roth, nipun.dayanath, nick.stone, scot.renz
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BatchService batchService;
	@Autowired
	private ResultService resultService;
	@Autowired
	private UserService userService;
	@Autowired
	private TechnologyService technologyService;
	@Autowired

	private TestService testService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private ResultDetailService resultDetailService;

	private Logger log = Logger.getLogger(AdminController.class);

	// DASHBOARD FUNCTIONALITY
	/**
	 * Returns the url for the showAdminDashboard function (used for the home
	 * button)
	 * 
	 * @param model
	 *            The spring model for the page
	 * @return the url pattern for the dashboard
	 * @author Michael Arp
	 */
	@RequestMapping(value = "/returnhome.htm", method = RequestMethod.GET)
	public String returnToAdminDashboard(Model model) {
		return "redirect:/admin/dashboard.htm";
	}

	/**
	 * fetches some data required in the admin dashboard cards, then directs to the
	 * admin dashboard
	 * 
	 * @param model
	 *            the spring model for the page
	 * @return url pattern for the admin dashboard
	 * @author Michael Arp
	 */
	@RequestMapping(value = "/dashboard.htm", method = RequestMethod.GET)
	public String showAdminDashboard(Model model) {
		model.addAttribute("numberOfTrainees", userService.getCountOfAttribute("Trainees"));
		model.addAttribute("numberOfBatches", userService.getCountOfAttribute("Batches"));
		model.addAttribute("numberOfTests", userService.getCountOfAttribute("Tests"));
		return "admin/dashboard";
	}

	// TRAINEE FUNCTIONALITY
	/**
	 * This method shows the trainee list page.
	 * 
	 * @param model
	 *            Add batches and trainees to page
	 * @return trainee-default.jsp
	 * @author neel.patel
	 */
	@RequestMapping(value= "/trainees.htm", method=RequestMethod.GET)
	public String showTraineeListPage(Model model) {
		model.addAttribute("traineeList", userService.getAllTrainees());
		model.addAttribute("batchList", batchService.listBatches());
		log.info("SHOW TRAINEE LIST");
		return "admin/trainee/trainee-default";
	}

	/**
	 * Shows the admin's view of the trainee details page
	 * 
	 * @author karl.roth
	 */
	@RequestMapping(value = "/trainee/{traineeId}/details.htm", method = RequestMethod.GET)
	public String showAdminTraineeDetailsPage(Model model, @PathVariable("traineeId") int traineeId) {
		List<ReportItem> technologyList = resultService.individualTraineeTechwiseReport(traineeId);
		User trainee = userService.findById(traineeId);

		model.addAttribute("trainee", trainee);
		model.addAttribute("reportItem", new ReportItem());
		model.addAttribute("technologies", technologyList);
		return "admin/trainee/detail/trainee-details";
	}

	/**
	 * Returns JSON data for a given trainee's techwise report charts.
	 * 
	 * @author karl.roth, nipun.dayanath
	 */
	@RequestMapping("/trainee/{traineeId}/techwise/data.json")
	@ResponseBody
	public List<ReportItem> indvReportData(@PathVariable("traineeId") int traineeId) {
		return resultService.individualTraineeTechwiseReport(traineeId);
	}

	/**
	 * redirects to individualTraineeDetailTechwiseDetail jsp page
	 * 
	 * @param model
	 *            used to send list of result details to jsp page
	 * @return path to jsp page in file structure
	 * @author nick.parker
	 */
	@RequestMapping(value = "/trainee/{id}/test.htm", method = RequestMethod.GET)
	public String showSpecificTraineeTest(Model model, @PathVariable("id") int traineeId,
			@PathVariable("resultId") int resultId) {
		log.info("Calling showSpecificTraineeTest with traineeId: " + traineeId);
		List<ResultDetail> resultDetails = resultDetailService.getResultDetails(resultId);
		model.addAttribute("resultDetails", resultDetails);
		return "trainer/trainee/detail/techwise-detail";
	}

	// TEST FUNCTIONALITY

	/**
	 * Handles request to export a test. Exports a new test in downloads folder.
	 * Name starts with output.
	 * 
	 * @param testId
	 *            test to export
	 * @return redirects back to dashboard
	 * @author Zachary Karamanlis
	 */
	@RequestMapping("/export/{testId}.htm")
	public String exportTest(@PathVariable("testId") int testId) {

		testService.exportToExcel(testId);

		return "redirect:/admin/tests.htm";
	}

	/**
	 * Show's the admin's view of the trainees test results for a given technology
	 * 
	 * @param report
	 * @param model
	 * @author karl.roth
	 */
	@RequestMapping(value = "/trainee/{traineeId}/tech/details.htm", method = RequestMethod.POST)
	public String showAdminTraineeTopicwiseDetailsPage(@PathVariable("traineeId") int traineeId,
			@ModelAttribute("reportItem") ReportItem report, Model model) {
		int techId = report.getId();
		List<ReportItem> testReports = resultService.individualTraineeTopicwiseReport(traineeId, techId);
		User trainee = userService.findById(traineeId);
		Technology technology = technologyService.getTechnologyById(techId);
		
		model.addAttribute("trainee",trainee);
		model.addAttribute("techId", techId);
		model.addAttribute("tech", technology);
		model.addAttribute("technologies", testReports);
		return "admin/trainee/detail/trainee-test-details";
	}


	/**
	 * Returns JSON data for a given trainee's report charts for a given technology.
	 * 
	 * @author karl.roth
	 */
	@RequestMapping("/trainee/{traineeId}/{techId}/data.json")
	@ResponseBody
	public List<ReportItem> indvTechReportData(@PathVariable("traineeId") int traineeId,
			@PathVariable("techId") int techId) {
		return resultService.individualTraineeTopicwiseReport(traineeId, techId);
	}

	
	/**
     * Shows admin test-default page
     * @param model
     * @return test-default page for admin
     * @author fahmida.joyti
     */
    @RequestMapping(value = "/tests.htm",method=RequestMethod.GET)
    public String showAdminTestDefault(Model model){
        List<Test> testList = testService.getAvailableTestsForTopics();
        model.addAttribute("testslistDurationQuestion",testList);
        return "admin/test/test-default";
    }
    /**
     * Shows admin test-default page
     * @param model
     * @param testId
	 * @throws InvalidIdException
	 * @return test-details page for admin
	 * @author fahmida.joyti
	 */
	@RequestMapping(value = "/test/{testId}/details.htm", method = RequestMethod.GET)
	public String showAdminTestDetail(Model model, @PathVariable("testId") int testId) {
		Map<String, Object> map = new HashMap<>();
		try {
			Test test = testService.getTestById(testId);
			Topic topic = topicService.getTopicById(test.getTopicId());
			test.setTestName(topic.getName());
			List<PartialQuestion> questions = questionService.getAllQuestionsByTestId(testId);
			test.setNum_questions(questions.size());
			map.put("test", test);
			map.put("questions", questions);
			model.addAllAttributes(map);
		} catch (InvalidIdException e) {
			log.error("error: " + e.getMessage());
			model.addAttribute("error", "The following test id is invalid");
		}
		return "admin/test/test-details";
	}

	// TECH FUNCTIONALITY

	/**
	 * ; Returns JSON data for techwise report charts.
	 * 
	 * @author nipun.dayanath
	 */
	@RequestMapping("/report/tech/{batchId}/{techId}/data.json")
	@ResponseBody
	public List<ReportItem> techwiseReportData(@PathVariable("batchId") int batchId,
			@PathVariable("techId") int techId) {
		return resultService.techwiseReport(batchId, techId);
	}

	// BATCH FUNCTIONALITY

	/**
	 * fetches batch list data
	 * 
	 * @param model the spring model for the page
	 * @return url pattern for the batch default page for the admin
	 * 
	 * @author kanika gandhi
	 */
	@RequestMapping("/batches.htm")
	public String showAdminBatchDefault( Model model)
	{
//		model.addAttribute("namesList",batchService.listNamesOfAllBatches());
		model.addAttribute("numUsers",batchService.countUsersForEachBatch());
//		model.addAttribute("statusList",batchService.listStatusForAllBatches());
		model.addAttribute("batchList", batchService.listBatches());
		return "admin/batch/batch-default";
	}

	/**
	 * @param batchId
	 *            to redirect to
	 * @return redirect to individual batch details
	 * @author scotrenz
	 */
	@RequestMapping("/batch/{batchid}/details.htm")
	public String showBatchDetailsPage(Model model, @PathVariable("batchid") int batchId) {
		try {
			model.addAttribute("batchDetails", batchService.getBatchById(batchId));
		} catch (InvalidIdException e) {
			log.error(e.getMessage());
		}
		model.addAttribute("traineeList", userService.getTraineesByBatchId(batchId));
		return "admin/batch/batch-details";
	}

	// REPORT FUNCTIONALITY
	/**
	 * Show view for overall report charts by batch.
	 * 
	 * @author nick.stone
	 */
	@RequestMapping("/report/overall/chart.htm")
	public String overallReport(@RequestParam(value = "batchId", required = false) Integer batchId, Model model) {
		List<Batch> batchList = batchService.listBatches();
		if (batchId == null)
			batchId = batchList.get(0).getId();
		model.addAttribute("batchList", batchList);
		model.addAttribute("selectedBatch", batchId);
		return "admin/reports/overall-reports";
	}

	/**
	 * Returns JSON data for overall report charts by batch.
	 * 
	 * @author nick.stone
	 */
	@RequestMapping("/report/overall/{batchId}/data.json")
	@ResponseBody
	public List<ReportItem> overallReportData(@PathVariable("batchId") int batchId) {
		return resultService.batchReport(batchId);
	}

	/**
	 * Processes the selection made on the previous page and directs to the proper
	 * details page
	 * 
	 * @param traineeId
	 *            the id of the trainee to get details for
	 * @return a redirect to the selected details page
	 * @author Michael Arp
	 */
	@RequestMapping(value = "/report/processInput.htm", method = RequestMethod.POST)
	public String processTraineeSelection(@RequestParam("traineeId") String traineeId) {
		return "redirect:/admin/trainee/" + traineeId + "/details.htm";
	}
	
	/**
	 * Directs to the batch selector that lets the trainer choose which batch of trainees to get details from
	 * @param model Spring model for the page, list of batches is added to it
	 * @return the direction to the batch selector jsp
	 * @author Michael Arp
	 */
	@RequestMapping(value="/report/trainee/select.htm",method=RequestMethod.GET)
	public String showBatchSelectorForIndividualReport(Model model)
	{
		List<Batch> batches = batchService.listBatches();
		model.addAttribute("listOfBatches", batches);
		return "admin/batch/batch-selector";
	}

	/**
	 * Processes the selected batch from the previous page, then adds the list of trainees in that batch to show in the dropdown
	 * @param batchId Id of batch we're selecting from
	 * @param model Spring model for the page
	 * @return direct to the trainee selector jsp
	 * @author Michael Arp
	 */
	@RequestMapping(value="/report/processSelectBatch.htm",method=RequestMethod.POST)
	public String showTraineeSelectorGivenBatch(@RequestParam("batchId") String batchId, Model model)
	{
		List<User> trainees = userService.getTraineesByBatchId(Integer.parseInt(batchId));
		model.addAttribute("listOfTrainees", trainees);
		return "admin/trainee/trainee-selector";
	}

	/**
	 * Show view for techwise report charts.
	 *
	 * @author nipun.dayanath
	 * @throws InvalidIdException
	 */
	@RequestMapping("/report/tech/chart.htm")
	public String showTechwiseReport(@RequestParam(value = "batchId", required = false) Integer batchId,
									 @RequestParam(value = "techId", required = false) Integer techId,
									 Model model) throws InvalidIdException {
		List<Batch> batchList = batchService.listBatches();
		List<Technology> techList = technologyService.getTechnologies();
		System.out.println(techList);
		if(batchId == null)
			batchId = batchList.get(0).getId();
		if(techId == null)
			techId = techList.get(0).getId();
		model.addAttribute("batchList", batchList);
		model.addAttribute("techList", techList);
		model.addAttribute("selectedBatchId", batchId);
		model.addAttribute("selectedTechId", techId);
		model.addAttribute("selectedBatch", batchService.getBatchById(batchId));
		model.addAttribute("selectedTech", technologyService.getTechnologyById(techId));
		return "admin/reports/techwise-reports";
	}
	

}
