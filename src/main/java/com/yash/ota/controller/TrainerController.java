package com.yash.ota.controller;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.ota.command.BatchCreateCommand;
import com.yash.ota.command.BatchEditCommand;
import com.yash.ota.command.EditTraineeDetailCommand;
import com.yash.ota.command.ImportQuestionCommand;
import com.yash.ota.command.QuestionCreateCommand;
import com.yash.ota.command.TestCreateCommand;
import com.yash.ota.command.TestEditCommand;
import com.yash.ota.domain.Batch;
import com.yash.ota.domain.Question;
import com.yash.ota.domain.ReportItem;
import com.yash.ota.domain.ResultDetail;
import com.yash.ota.domain.Technology;
import com.yash.ota.domain.Test;
import com.yash.ota.domain.Topic;
import com.yash.ota.domain.User;
import com.yash.ota.exception.DuplicateEntryException;
import com.yash.ota.exception.EmptyFieldException;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.service.BatchService;
import com.yash.ota.service.QuestionService;
import com.yash.ota.service.ResultDetailService;
import com.yash.ota.service.ResultService;
import com.yash.ota.service.TechnologyService;
import com.yash.ota.service.TestService;
import com.yash.ota.service.TopicService;
import com.yash.ota.service.UserService;
import com.yash.ota.util.CommonUtil;

/**
 * This class provides a controller for trainee users to use
 *
 * @author nick.parker
 */
@Controller
@RequestMapping("/trainer")
public class TrainerController {



	@Autowired
	private TestService testService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private BatchService batchService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private ResultService resultService;

	@Autowired
	private UserService userService;

	@Autowired
	private ResultDetailService resultDetailService;
	

	@Autowired
	private TechnologyService technologyService;

	private Logger log = Logger.getLogger(TrainerController.class);

	//DASHBOARD
	/**
	 * This method shows the Trainer Dashboard Page
	 * @author Geon Gayles
	 */
	@RequestMapping(value= "/dashboard.htm", method=RequestMethod.GET)
	public String showTrainerDashboard(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		model.addAttribute("totalTrainees", userService.getTotalTraineesByTrainer(user));
		model.addAttribute("totalReports", resultService.getTotalNumberOfResultsByTrainer(user));
		model.addAttribute("totalQuestions", questionService.getTotalNumberOfQuestionsByTrainer(user));
		model.addAttribute("totalBatches", batchService.getTotalBatchesByTrainer(user));
		model.addAttribute("currentBatch", batchService.getCurrentBatchByTrainer(user));
		model.addAttribute("totalTests", testService.getTotalNumberOfTestsByTrainer(user));

		return "trainer/dashboard";
	}

	// TEST FUNCTIONALITY

	/**
	 * to get the list of the quetions using test id
	 * 
	 * @param model
	 * @param session
	 * @param testId
	 *            inorder to get the testid
	 * @return list of the questions
	 * @author Lalu shaik.
	 */
	@RequestMapping(value = "/test/{testId}/edit.htm", method = RequestMethod.GET)
	public String showEditTrainerPage(Model model, HttpSession session, @PathVariable("testId") int testId) {
		log.info("test list of questions edit function called from controller");

		try {
			Test test;

			test = testService.findById(testId);

			Topic topic = topicService.getTopicById(test.getTopicId());
			model.addAttribute("topicName", topic.getName());
			model.addAttribute("duration", test.getTimeAlloted());
			session.setAttribute("testId", test.getId());
			List<Question> questions = questionService.getQuestionByTestId(testId);
			model.addAttribute("questions", questions);

		} catch (InvalidIdException e) {
			log.error("error: " + e.getMessage());
		}

		return "trainer/test/edit-test";
	}

	/**
	 * update the testname based on testid
	 * 
	 * @param model
	 * @param request
	 * @param testEditCommand
	 * @return update the test name
	 * @author Lalu Shaik.
	 */
	@RequestMapping(value = "/test/{testId}/update.htm", method = RequestMethod.POST)

	public String updateTopicName(Model model, HttpServletRequest request,
			@ModelAttribute("command") TestEditCommand testEditCommand) {
		log.info("test name edit function called from controller");

		try {
			int testId = (Integer) request.getSession().getAttribute("testId");
			Test test;

			test = testService.findById(testId);

			Topic topic = topicService.getTopicById(test.getTopicId());
			String newTopicName = testEditCommand.getTopic_name();
			topic.setName(newTopicName);
			topicService.update(topic);
			test.setTestName(newTopicName);
			model.addAttribute("topicName", topic.getName());
			model.addAttribute("duration", test.getTimeAlloted());
			List<Question> questions = questionService.getQuestionByTestId(testId);
			model.addAttribute("questions", questions);
		} catch (InvalidIdException e) {
			log.error("error: " + e.getMessage());
		}

		return "trainer/test/edit-test";

	}

	/**
	 * @author Lalu Shaik.
	 * @param model
	 * @param request
	 * @param testCommand
	 * @return update the test duration
	 */
	@RequestMapping(value = "/test/{testId}/editTime.htm", method = RequestMethod.POST)
	public String updateDuration(Model model, HttpServletRequest request,
			@ModelAttribute("command") TestEditCommand testCommand) {
		log.info("test duration edit function called from controller");
		try {
			int testId = (Integer) request.getSession().getAttribute("testId");
			Test test;

			test = testService.findById(testId);

			Time newDuaration = testCommand.getTimeAlloted();
			test.setTimeAlloted(newDuaration);
			testService.update(test);
			Topic topic = topicService.getTopicById(test.getTopicId());
			model.addAttribute("topicName", topic.getName());
			model.addAttribute("duration", test.getTimeAlloted());
			List<Question> questions = questionService.getQuestionByTestId(testId);
			model.addAttribute("questions", questions);
		} catch (InvalidIdException e) {
			log.error("error: " + e.getMessage());
		}

		return "trainer/test/edit-test";

	}
	/**
	 * This methods shows the Edit Question Page
	 * @param model, topic
	 * @return
	 * @author Geon Gayles
	 */
	@RequestMapping(value= "/edit.htm", method=RequestMethod.GET)
	public String showEditQuestion(@ModelAttribute("topic") Topic topic, Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		List<Topic> topics = topicService.getListOfTopicByTrainer(user);

		model.addAttribute("topics", topics);

		List<Question> questions = questionService.getQuestionsAccordingToTopicAndTrainer(user, topic);

		model.addAttribute("questions", questions);

		return "trainer/question/edit-question";
	}
	
	/**
	 * Changes the Questions displayed according to Tests selected by User in dropdown
	 * @param topic
	 * @param model
	 * @param request
	 * @return
	 * @author Geon Gayles
	 */
	@RequestMapping(value="/processChangeQuestionsbyTests.htm", method=RequestMethod.POST)
	public String processChangeQuestionsbyTests(@ModelAttribute("topic") Topic topic, Model model, HttpServletRequest request) {

		
		User user = (User) request.getSession().getAttribute("user");
		
		model.addAttribute("id", topic.getId());
		request.getSession().setAttribute("id", topic.getId());
		
		List<Question> questions = questionService.getQuestionsAccordingToTopicAndTrainer(user, topic);
		model.addAttribute("questions", questions);
		
		return "redirect:./edit.htm";
	}

	/**
	 * When Edit Button is pressed then Question is updated in DB with current values from Question Edit Form
	 * @param model
	 * @param request
	 * @return
	 * 
	 * @author Geon Gayles
	 */
	@RequestMapping(value = "/question/processedit.htm", method= RequestMethod.POST)
	public String processEditQuestion(@ModelAttribute("topic") Topic topic, Model model,  
			 HttpServletRequest request) {
		Question question = new Question();
		User user = (User) request.getSession().getAttribute("user");
		Date date = new Date();
		
		int topicId = (Integer)(request.getSession().getAttribute("id"));
		int id = Integer.parseInt((request.getParameter("qid")));
	
		question.setId(id);
		question.setQuestion(request.getParameter("question"));
		question.setCorrectAnswer(request.getParameter("correctAnswer"));
		question.setOption1(request.getParameter("option1"));
		question.setOption2(request.getParameter("option2"));
		question.setOption3(request.getParameter("option3"));
		question.setOption4(request.getParameter("option4"));
		question.setCorrectAnswer(request.getParameter("correctAnswer"));
	
		question.setModifiedBy(user.getId());
		question.setModifiedDate(date);
		
		questionService.updateQuestion(question);
		
		return "redirect:./edit.htm?id=" + topicId;
	}
    
    /**
	 * Handles request to export a test.
	 * Exports a new test in downloads folder. Name starts with output.
	 * 
	 * @param testId test to export
	 * @return redirects back to dashboard
	 * @author Zachary Karamanlis
	 */
	@RequestMapping("/export/{testId}.htm")
	public String exportTest(@PathVariable("testId") int testId) {

		testService.exportToExcel(testId);

		return "redirect:/trainer/tests.htm";
	}


	/**
	 * When Delete Button is pressed then Question is deleted
	 * @param model
	 * @param request
	 * @return
	 * @author Geon Gayles
	 */
	@RequestMapping(value = "/question/delete.htm", method= RequestMethod.POST)
	public String processDeleteQuestion(Model model, HttpServletRequest request) {
		Question question = new Question();
	
		
		int id = Integer.parseInt((request.getParameter("qid")));
	
		question.setId(id);
		
		questionService.deleteQuestion(question);
		return "redirect:./edit.htm";
	}

	/**
	 * Shows trainer test default page
	 *
	 * @return location of default trainer test page
	 * @author Zachary Karamanlis
	 */
	@RequestMapping(value = "/tests.htm", method = RequestMethod.GET)
	public String showTestDefaultPage(Model model , HttpSession session) {

		// get list of tests
		Map<String, Object> map = new HashMap<String, Object>();

		User user = (User) session.getAttribute("user");
		int createdBy = user.getCreatedBy();

		// get all needed test info
		List<Map<String, Object>> tests = testService.getTestsWithTopicNameAndTechName(createdBy);

		// count questions for each test and assign to map
		for (Map<String, Object> map2 : tests) {
			int count = questionService.questionCount((Integer) map2.get("id"));
			map2.put("count", count);
		}

		map.put("tests", tests);
		model.addAllAttributes(map);

		return "./trainer/test/test-default";
	}

	/**
	 * updates status of given testId to given statusid
	 *
	 * @param testId
	 *            test to update
	 * @param statusId
	 *            what to set status to
	 * @return ModelAndView for test-default
	 * @author Zachary Karamanlis
	 */
	@RequestMapping(value = "/test/{testId}/status/{statusId}.htm", method = RequestMethod.GET)
	public String updateTraineeTestStatus(@PathVariable("testId") int testId, @PathVariable("statusId") int statusId,
			Model model) {


		// update status
		testService.updateStatus(testId, statusId);

		//show test default again
		return "redirect:/trainer/tests.htm";
	}

	/**
	 * Gets test and question for given testId. Redirects to test details.
	 *
	 * @param testId
	 *            id of test to get info for
	 * @return ModelAndView for details page
	 * @author Zachary Karamanlis
	 * @throws InvalidIdException
	 */
	@RequestMapping(value = "/test/{testId}/details.htm", method = RequestMethod.GET)
	public String showTestDetailsPage(@PathVariable("testId") int testId, Model model) throws InvalidIdException {

		Map<String, Object> map = new HashMap<String, Object>();

		// get test
		Test test = testService.getTestById(testId);

		// get questions associated with test
		List<Question> questions = questionService.getByTopicId(test.getTopicId());
		map.put("questions", questions);
		test.setNum_questions(questions.size());
		map.put("test", test);

		// get associated topics and technologies
		Topic topic = topicService.getTopicById(test.getTopicId());
		Technology tech = technologyService.getTechnologyById(topic.getTechnologyId());

		// insert their names for the table
		map.put("topicName", topic.getName());
		map.put("techName", tech.getName());

		model.addAllAttributes(map);

		return "./trainer/test/test-details";
	}

    /**
     * This method loads the create -test page.
     * User can enter the details and Submit
     *
     * @author madhuri
     */
    @RequestMapping(value = "/test/create.htm", method = RequestMethod.GET)
    public String loadTechnologyList(Model model, @ModelAttribute("command") TestCreateCommand testCreateCommand) {
        List<Technology> technologyList = technologyService.findAll();
        model.addAttribute("technologyList", technologyList);
        log.info("list of technologies "+technologyList);
        return "trainer/test/create-test";
    }

    /**
     *
     * @param techId
     * @return
     * @author: Madhuri Vutukury
     */
    @RequestMapping(value = "/getTopics.htm", method = RequestMethod.GET, headers = "Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getTopics(@RequestParam("techId") int techId) {
        List<Topic> topicList = topicService.getTopicsByTechId(techId);
        log.info("list of topics "+topicList);
        ObjectMapper mapper = new ObjectMapper();
        String topicsJson = "";
        try {
            topicsJson = mapper.writeValueAsString(topicList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topicsJson;
    }

    /**
     *
     * @param model
     * @param testCreateCommand
     * @param result
     * @return
     * @throws EmptyFieldException
     * author : Madhuri Vutukury
     */
    @RequestMapping(value = "/test/processCreateTest.htm", method = RequestMethod.POST)
    public String processCreateTest(Model model, @ModelAttribute("command") TestCreateCommand testCreateCommand, BindingResult result) throws EmptyFieldException {
        List<Technology> technologyList = technologyService.findAll();
        model.addAttribute("technologyList", technologyList);
        Test test = new Test();
        if(testCreateCommand.getTestDuration().trim().isEmpty()){
            ObjectError error = new ObjectError("testDuration", "Please enter the timeDuration");
            result.addError(error);
            return "trainer/test/create-test";
            }
            else if (!CommonUtil.validateTestDuration(testCreateCommand.getTestDuration())) {
            ObjectError error = new ObjectError("testDuration", "Please enter Correct format");
            result.addError(error);
            return "trainer/test/create-test";
        }
        else  {
            test.setTimeAlloted(Time.valueOf(testCreateCommand.getTestDuration()));
        }
        test.setStatusId(1);
        test.setTopicId(Integer.valueOf(testCreateCommand.getTopics()));
        test.setCreatedBy(1);
        test.setCreatedDate(new Date());
        test.setTotalMarks(20);
        test.setModifiedBy(1);
        test.setModifiedDate(new Date());
        testService.insertTest(test);
        ObjectError error = new ObjectError("testcreated", "Test Created successfully!");
        result.addError(error);
        log.info("test is inserted");


        return "trainer/test/create-test";
    }




    //BATCH FUNCTIONALITY
    /**
     * Displays the dashboard with basic information for each batch
     * @param model
     *
     * @return batch-default.jsp
     * @author Andrew.beier
     */
	@RequestMapping("/batches.htm")
	public String showBatchDefaultPage(Model model) {
		model.addAttribute("namesList",batchService.listNamesOfAllBatches());
		model.addAttribute("numUsers",batchService.countUsersForEachBatch());
		model.addAttribute("statusList",batchService.listStatusForAllBatches());
		model.addAttribute("idList",batchService.listIdForAllBatches());
		return "/trainer/batch/batch-default";
	}
    


	/**
	 * Changes the status of a batch when its button is clicked
	 * @param batchId id of the batch for status update
	 * @return
	 * @author Andrew.beier
	 */
	@RequestMapping("/batch/{batchId}/changeStatus.htm")
	public String chageStatusOfBatch(@PathVariable("batchId")int batchId) {
		batchService.changeBatchStatusToOpposite(batchId);


		return "redirect:/trainer/batches.htm";
	}


	/**
	 * trainer click on edit button for a specific trainee
	 * @param traineeid
	 * @param model
	 * @param session
	 * @param editTraineeDetailCommand
	 * @return trainer edits trainee detail page
	 * @author kanika gandhi
     * @throws InvalidIdException
	 */
	@RequestMapping(value="/batch/{traineeid}/edit-trainee-details.htm", method= RequestMethod.GET)
	public String showEditTraineeDetailPageInBatchSubmenu(@PathVariable("traineeid") int traineeid, Model model, HttpSession session,
			@ModelAttribute("command") EditTraineeDetailCommand editTraineeDetailCommand) throws InvalidIdException{
		int traineeId = traineeid;
		User trainee = userService.findById(traineeId);
		session.setAttribute("traineeID", trainee.getId());
		session.setAttribute("batchList", batchService.listBatches());
		int batchId = trainee.getBatchId();
		Batch batch = batchService.getBatchById(batchId);
		model.addAttribute("traineeCurrentBatch", batch.getName());
		model.addAttribute("traineeCurrentBatchId", batch.getId());
		model.addAttribute("traineeid", trainee.getId());
		model.addAttribute("firstName", trainee.getFirstName());
		model.addAttribute("lastName", trainee.getLastName());
		model.addAttribute("phoneNumber", trainee.getPhoneNumber());
		return "trainer/batch/edit-trainee-details";
	}

	/**
	 * list of trainees after editing trainee details
	 * @param model
	 * @param request
	 * @param editTraineeDetailCommand
	 * @param result
	 * @return list of trainees after editing trainee details
	 * @throws InvalidIdException
	 */
	@RequestMapping(value="/batch/editTraineeDetail.htm" , method=RequestMethod.POST)
	public String processTraineeEditDetailInBatchSubmenu(Model model, HttpServletRequest request,
			@Valid @ModelAttribute("command") EditTraineeDetailCommand editTraineeDetailCommand,
			BindingResult result) throws InvalidIdException {
		int traineeid = (int) request.getSession().getAttribute("traineeID");
		User trainee = userService.findById(traineeid);
		Batch batch = batchService.getBatchById(editTraineeDetailCommand.getBatchId());
		model.addAttribute("traineeCurrentBatch",batch.getName());
		if(result.hasErrors()) {
			return "trainer/batch/edit-trainee-details";
		}
		trainee.setFirstName(editTraineeDetailCommand.getFirstName());
		trainee.setLastName(editTraineeDetailCommand.getLastName());
		trainee.setPhoneNumber(editTraineeDetailCommand.getPhoneNumber());
		trainee.setBatchId(editTraineeDetailCommand.getBatchId());
		userService.updateUser(trainee);
		return "redirect:/trainer/batch/batch-default.htm";
	}

	/**
	 *
	 * @param batch_id
	 * @param model
	 * @param session
	 * @param batchEditCommand
	 * @return String url
	 * @author amit.kolluri
	 */
	@RequestMapping(value = "/batch/{batchid}/edit.htm", method = RequestMethod.GET)
	public String showEditBatchPage(@PathVariable("batchid") int batch_id, Model model, HttpSession session,
			@ModelAttribute("command") BatchEditCommand batchEditCommand) throws InvalidIdException {

		int batchId = batch_id;
		Batch batch = batchService.getBatchById(batchId);
		session.setAttribute("batchId", batch.getId());
		model.addAttribute("batchName", batch.getName());
		return "trainer/batch/edit-batch";
	}

	/**
	 * @param request
	 * @param batchEditCommand
	 * @return
	 * @author Amit.kolluri //TODO:UPDATE BATCH WITH ID PASSED THROUGH URL NOT
	 *         SESSION
	 */
	@RequestMapping(value = "/editbatch.htm", method = RequestMethod.POST)
	public String processUpdateBatch(HttpServletRequest request,
			@ModelAttribute("command") BatchEditCommand batchEditCommand) throws InvalidIdException {

		int batchId = (Integer) request.getSession().getAttribute("batchId");
		Batch batch = batchService.getBatchById(batchId);
		batch.setName(batchEditCommand.getNewBatchName());

		batchService.updateBatch(batch);

		return "trainer/batch/dashboard";
	}

	/**
	 * This method loads the create -batch page. User can enter the details and
	 * Submit
	 * 
	 * @author madhuri
	 */
    @RequestMapping(value = "batch/create.htm", method = RequestMethod.GET)
    public String showCreateBatchPage() {

        return "trainer/batch/create-batch";
    }

    /**
	 * Getting Trainee Details for the batch ID Specified.
     * @param model model object
     * @param batchId Id for the batch for which trainees are to listed
     * @return URL to batch-details page
     * @author - Jay Shah
     */
    @RequestMapping(value = "/batch/{batchId}/details.htm", method = RequestMethod.GET)
    public String getTraineesByBatchId(Model model, @PathVariable("batchId") int batchId) throws InvalidIdException {
        log.info("Listing trainees for batch with ID: " + batchId);
        Batch batch = batchService.getBatchById(batchId);
        List<User> trainees = userService.getTraineesByBatchId(batchId);
        model.addAttribute("traineesList", trainees);
        model.addAttribute("batch", batch);
        return "/trainer/batch/batch-details";
    }

    /**
	 * Getting the trainee details for the batch after status is updated
     * @param model Model object for storing attributes
     * @param batchId batchId for which trainees with updated statuses are to be loaded on the jsp file
     * @param userId Id for the trainee for whom the status is to be updated
     * @param statusId Id for the status to be updated in users table
     * @author - Jay Shah
     */
    @RequestMapping(value = "/{batchId}/{userId}/{statusId}/status.htm", method = RequestMethod.GET)
    public String updateTraineeStatus(Model model, @PathVariable("batchId") int batchId, @PathVariable("userId") int userId, @PathVariable("statusId") int statusId) throws InvalidIdException {
        log.info("Changing status of user ID " + userId + " to " + statusId);
        User user = userService.findById(userId);
        user.setStatusId(statusId);
        userService.updateUser(user);
        Batch batch = batchService.getBatchById(batchId);
        List<User> trainees = userService.getTraineesByBatchId(batchId);
        model.addAttribute("traineesList", trainees);
        model.addAttribute("batch", batch);
        return "/trainer/batch/batch-details";
    }
    //TRAINEE FUNCTIONALITY

	/**
	 * This controller trigger when the user submit the details. Saves the details
	 * in database
	 *
	 * @author Madhuri.Vutukury
	 */
    @RequestMapping(value = "batch/createbatch.htm", method = RequestMethod.POST)
    public String processCreateBatch(Model model, @ModelAttribute("command") BatchCreateCommand batchCreateCommand, BindingResult result) throws EmptyFieldException {
        Batch batch = new Batch();
        batch.setCreatedBy(1);
        Date date = new Date();
        batch.setCreatedDate(date);
        batch.setDescription(batchCreateCommand.getBatchDescription());
        if(batchCreateCommand.getBatchName().trim().isEmpty()){
            ObjectError error = new ObjectError("batchName", "Please enter batch name");
            log.info("It will add error ");
            result.addError(error);
            return "trainer/batch/create-batch";
        }else {
            batch.setName(batchCreateCommand.getBatchName());
        }
        batch.setModifiedBy(1);
        batch.setModifiedDate(date);
        try {
            batchService.insertBatch(batch);
            ObjectError error = new ObjectError("batchcreated", "Batch Created successfully!");
            result.addError(error);
        } catch (DuplicateEntryException e) {
            //object error, that is, for rejecting an object.
            ObjectError error = new ObjectError("batchName", e.getMessage());
            result.addError(error);
            return "trainer/batch/create-batch";
        }
        return "trainer/batch/create-batch";
    }

//    //TRAINEE FUNCTIONALITY
//	/**
//	 * @param model
//	 * @return URL to batch-details page
//	 * @author - Jay Shah
//	 */
//	@RequestMapping(value = "/getTraineesByBatchName.htm", method = RequestMethod.GET)
//	public String getTraineesByBatchName(Model model) {
//		// String batchName = (String) request.getSession().getAttribute("batchName");
//		String batchName = "June_2018";
//		List<User> trainees = userService.getTraineesByBatchName(batchName);
//		model.addAttribute("traineesList", trainees);
//		return "trainer/batch/batch-details";
//	}

	/**
	 * process the trainee approvals, change the status of trainees to active once trainer approves
	 *
	 * @param model
	 * @param request
	 * @return trainer dashboard after trainer approves the trainees
	 * @author amit kolluri
	 */
	@RequestMapping(value = "/processapprovedrequests.htm", method = RequestMethod.POST)
	public String processTraineeApprovals(Model model, HttpServletRequest request) {

		String[] status = request.getParameterValues("status");


		int[] approvedUserIds = new int[status.length];
		for (int i = 0; i < status.length; i++) {
			approvedUserIds[i] = Integer.parseInt(status[i]);
			userService.approveUser(approvedUserIds[i]);
		}

		return "trainer/dashboard";
	}


    /**
	 * Shows the Trainer's view of the trainee details page
	 * @param model - model object for adding attribute
	 * @param traineeId - Id of the trainee for which details are to be fetched
	 * @return URL to land on the trainee details page where the test taken by him will be showed
	 * @author - Jay Shah
	 */
	@RequestMapping(value = "/trainee/{traineeId}/details.htm", method = RequestMethod.GET)
	public String showTrainerTraineeDetailsPage(Model model, @PathVariable("traineeId") int traineeId){
		log.info("Fetching Technology Wise Details for Trainee with ID: " + traineeId);
		List<ReportItem> technologies = resultService.individualTraineeTechwiseReport(traineeId);
		model.addAttribute("traineeId", traineeId);
		model.addAttribute("technologies", technologies);
		return "trainer/trainee/detail/trainee-details";
	}

	/**
	 * Deletes test record from database
	 *
	 * @param resultId
	 *            id of record to delete
	 * @return record is deleted, so returns back to trainee-details page
	 * @author Zachary Karamanlis
	 */
	@RequestMapping(value = "/trainee/test/delete/{resultId}.htm", method = RequestMethod.GET)
	public String deleteResult(@PathVariable("resultId") int resultId) {

		resultService.updateStatus(2, resultId);

		// might need to change, depending on how others create their pages
		return "trainer/trainee/detail/trainee-details";
	}

	/**
	 * This method shows the trainee list page.
	 *
	 * @param model
	 *            Add batches and trainees to page
	 * @return trainee-default.jsp
	 * @author neel.patel
	 */
	@RequestMapping(value = "trainees.htm", method = RequestMethod.GET)
	public String showTraineeListPage(Model model) {
		model.addAttribute("traineeList", userService.getAllTrainees());
		model.addAttribute("batchList", batchService.listBatches());
		log.info("SHOW TRAINEE LIST");
		return "trainer/trainee/trainee-default";
	}

	/**
	 * This method deletes the user with the passed Id
	 *
	 * @param traineeId
	 *            Id of trainee passed from trainee-default.jsp page
	 * @return trainee-default.jsp page
	 * @author neel.patel
	 */
	@RequestMapping(value = "trainee/{id}/delete.htm", method = RequestMethod.GET)
	public String processDeleteTrainee(@PathVariable("id") int traineeId) {
		userService.deleteUser(traineeId);
		return "redirect:/trainer/trainees.htm";
	}

	/**
	 * redirects to individualTraineeDetailTechwiseDetail jsp page
	 *
	 * @param model
	 *            used to send list of result details to jsp page
	 * @return path to jsp page in file structure
	 * @author nick.parker
	 */
	@RequestMapping(value = "/trainee/{id}/{resultId}/tech.htm", method = RequestMethod.GET)
	public String showSpecificTraineeTest(Model model, @PathVariable("id") int traineeId,
			@PathVariable("resultId") int resultId) {
		log.info("Calling showSpecificTraineeTest with traineeId: " + traineeId);
		List<ResultDetail> resultDetails = resultDetailService.getResultDetails(resultId);
		model.addAttribute("resultDetails", resultDetails);
		return "trainer/trainee/detail/techwise-detail";
	}

	/**
	 * @param model
	 * @param session
	 * @return trainee awaiting approvals page with list of trainees awaiting for trainer approval
	 * @author amit kolluri
	 */
	@RequestMapping(value = "/trainee/approve-awaiting-requests.htm", method = RequestMethod.GET)
	public String showTraineeAwaitingRequestsPage(Model model, HttpSession session) {


		List<User> awaitingTraineesList = userService.getAwaitingUsersList();
		List<Batch> batchList = batchService.listBatches();

		model.addAttribute("awaitingtraineeslist", awaitingTraineesList);
		model.addAttribute("batchList",batchList);
		return "trainer/trainee/trainee-awaiting-requests";
	}

	// REPORT FUNCTIONALITY
	

	/**
	 *trainer click on edit button for a specific trainee
	 * @param traineeid
	 * @param model
	 * @param session
	 * @return edit trainee detail page for trainer
	 * @author kanika gandhi
	 * @throws InvalidIdException
	 */
	@RequestMapping(value="/trainee/{traineeid}/edit-trainee-details.htm", method= RequestMethod.GET)
	public String showEditTraineeDetailPageInTraineeSubmenu(@PathVariable("traineeid") int traineeid, Model model, HttpSession session,
															@ModelAttribute("command") EditTraineeDetailCommand editTraineeDetailCommand) throws InvalidIdException{
		int traineeId = traineeid;
		User trainee = userService.findById(traineeId);
		session.setAttribute("traineeID", trainee.getId());
		session.setAttribute("batchList", batchService.listBatches());
		int batchId = trainee.getBatchId();
		Batch batch = batchService.getBatchById(batchId);
		model.addAttribute("traineeCurrentBatch", batch.getName());
		model.addAttribute("traineeCurrentBatchId", batch.getId());
		model.addAttribute("traineeid", trainee.getId());
		model.addAttribute("firstName", trainee.getFirstName());
		model.addAttribute("lastName", trainee.getLastName());
		model.addAttribute("phoneNumber", trainee.getPhoneNumber());
		return "trainer/trainee/edit-trainee-details";
	}

	/**
	 * list of trainees after editing trainee details
	 * @param model
	 * @param request
	 * @param editTraineeDetailCommand
	 * @return list of trainees after editing trainee details
	 * @author kanika gandhi
	 * @throws InvalidIdException
	 */
	@RequestMapping(value="/trainee/editTraineeDetail.htm" , method=RequestMethod.POST)
	public String processTraineeEditDetailInTraineeSubmenu(Model model, HttpServletRequest request,
														   @Valid @ModelAttribute("command") EditTraineeDetailCommand editTraineeDetailCommand,
														   BindingResult result) throws InvalidIdException {
		int traineeid = (int) request.getSession().getAttribute("traineeID");
		User trainee = userService.findById(traineeid);
		Batch batch = batchService.getBatchById(editTraineeDetailCommand.getBatchId());
		model.addAttribute("traineeCurrentBatch",batch.getName());

		if(result.hasErrors()) {
			return "/trainer/trainee/edit-trainee-details";
		}

		trainee.setFirstName(editTraineeDetailCommand.getFirstName());
		trainee.setLastName(editTraineeDetailCommand.getLastName());
		trainee.setPhoneNumber(editTraineeDetailCommand.getPhoneNumber());
		trainee.setBatchId(editTraineeDetailCommand.getBatchId());
		userService.updateUser(trainee);
		return "redirect:/trainer/trainees.htm";
	}


	/**
	 * Show view for overall report charts by batch.
	 * @param batchId
	 * @return returns overall report for trainer
	 * @author erin.gehn
	 */
	@RequestMapping("/report/overall/chart.htm")
	public String overallReport(@RequestParam(value = "batchId", required = false) Integer batchId, Model model) {
		List<Batch> batchList = batchService.listBatches();
		if (batchId == null)
			batchId = batchList.get(0).getId();
		model.addAttribute("batchList", batchList);
		model.addAttribute("selectedBatch", batchId);
		return "trainer/report/overall-report";
	}

	/**
	 * Returns JSON data for overall report charts by batch.
	 * 
	 * @param batchId
	 * @return returns overall report data
	 * @author erin.gehn
	 */
	@RequestMapping("/report/overall/{batchId}/data.json")
	@ResponseBody
	public List<ReportItem> overallReportData(@PathVariable("batchId") int batchId) {
		return resultService.batchReport(batchId);
	}

	/**
	 * Processes the selection made on the previous page and directs to the proper details page
	 * @param traineeId the id of the trainee to get details for
	 * @return a redirect to the selected details page
	 * @author Michael Arp
	 */
	@RequestMapping(value="/report/processTraineeInput.htm",method=RequestMethod.POST)
	public String processTraineeSelection(@RequestParam("traineeId") String traineeId)
	{
		return "redirect:/trainer/trainee/"+ traineeId +"/details.htm";
	}

	/**
	 * Directs to the batch selector that lets the trainer choose which batch of trainees to get details from
	 * @param model Spring model for the page, list of batches is added to it
	 * @return the direction to the batch selector jsp
	 * @author Michael Arp
	 */
	@RequestMapping(value="/report/batch.htm",method=RequestMethod.GET)
	public String showBatchSelectorForIndividualReport(Model model)
	{
		List<Batch> batches = batchService.listBatches();
		model.addAttribute("listOfBatches", batches);
		return "trainer/trainee/batch-selector";
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
		return "trainer/trainee/trainee-selector";
	}

	/**
	 * Provides data for the graph to be displayed
	 * @param traineeId - Id of the trainee for which details are to be fetched
	 * @return json data for a given trainee technology wise
	 * @author - Jay Shah
	 */
	@RequestMapping(value = "/trainee/{traineeId}/techwise/data.json")
	@ResponseBody
	public List<ReportItem> traineeReportData(@PathVariable("traineeId") int traineeId){
		return resultService.individualTraineeTechwiseReport(traineeId);
	}

	/**
	 * Provides data for the graph to be displayed
	 * @param traineeId - Id of the trainee for which details are to be fetched
	 * @return json data for a given batch technology wise
	 * @author - Jay Shah
	 */
	@RequestMapping(value = "/trainee/{traineeId}/batchwise/data.json")
	@ResponseBody
	public List<ReportItem> batchReportData(@PathVariable("traineeId") int traineeId){
		int batchId = userService.findById(traineeId).getBatchId();
		return resultService.individualBatchTechWiseReport(batchId);
	}

	/**
	 * Provides the test details for an individual trainee technology wise to be displayed
	 * @param model model object for adding attribute
	 * @param traineeId Id for the trainee for whom details are to be fetched
	 * @param techId Id for the technology for which details are to be fetched
	 * @return URL to show technologies wise individual test details
	 * @author - Jay Shah
	 */
	@RequestMapping(value = "/trainee/{traineeId}/{techId}/tests.htm", method = RequestMethod.GET)
	public String showTraineeTechWiseDetails(Model model, @PathVariable("traineeId") int traineeId, @PathVariable("techId") int techId){
		log.info("Fetching Test Details for Trainee: " + traineeId + " With Technology ID: " + techId);
		List<ReportItem> testReport = resultService.individualTraineeTechwiseSpecificTestReport(traineeId, techId);
		model.addAttribute("traineeId", traineeId);
		model.addAttribute("topics", testReport);
		return "trainer/trainee/detail/trainee-test-details";
	}

	/**
	 * Provides data for the graph to be displayed
	 * @param traineeId - Id for the trainee for which data has to be fetched
	 * @param techId - Id for the technology for which data has to be fetched
	 * @return json data for a given trainee technology wise
	 * @author - Jay Shah
	 */
	@RequestMapping(value = "/trainee/{traineeId}/{techId}/testwise/data.json")
	@ResponseBody
	public List<ReportItem> traineeTechWiseReportData(@PathVariable("traineeId") int traineeId, @PathVariable ("techId") int techId){
		return resultService.individualTraineeTechwiseSpecificTestReport(traineeId, techId);
	}

	//QUESTION FUNCTIONALITY
	/**
	 * Show the import page for the questions
	 * add the technologies, topics, and tests to be sorted in javascript
	 * add the command for the import form 
	 * @return import questions page
	 * @author phoutsaksit.keomala //messy can be restructured
	 */
	@RequestMapping("/questions/import.htm")
	public String showImportQuestionsPage(Model model, @RequestParam(value="techId", required=false) Object objTechId,
			@RequestParam(value="topicId", required=false) Object objTopicId) {
		
		List<Technology> technologies = technologyService.getTechnologies();
		List<Topic> topics = topicService.getTopics();
		List<Test> tests = testService.getTests();
		model.addAttribute("technologies", technologies);
		model.addAttribute("topics", topics);
		model.addAttribute("tests", tests);
		model.addAttribute("command", new ImportQuestionCommand());
		return "trainer/question/import";
	}

	/**
	 * Method to process the import of a file from the import page
	 * @param session current session
	 * @param model model of the page
	 * @param importQuestionCommand command to be filled with topicId, testId, and MultipartFile (excel file)
	 * @param result error handling 
	 * @return a redirect to the page that the questions were uploaded for
	 * @author phoutsaksit.keomala
	 */
	@RequestMapping(value = "/questions/processImport.htm", method = RequestMethod.POST)
	public String processImport(HttpSession session, Model model,
			@Valid @ModelAttribute("command") ImportQuestionCommand importQuestionCommand, BindingResult result) {
		if (result.hasErrors() || importQuestionCommand.getFile() == null) {
			List<Technology> technologies = technologyService.getTechnologies();
			List<Topic> topics = topicService.getTopics();
			List<Test> tests = testService.getTests();
			model.addAttribute("technologies", technologies);
			model.addAttribute("topics", topics);
			model.addAttribute("tests", tests);
			model.addAttribute("fileErrorMsg", "Please select a file to upload");
			return "trainer/question/import";
		}
		int testId = importQuestionCommand.getTestId();
		int topicId = importQuestionCommand.getTopicId();
		MultipartFile file = importQuestionCommand.getFile();
		User user = (User) session.getAttribute("user");
		List<Question> questionList = questionService.constructQuestionListFromExcelFile(file, topicId, user);
		questionService.addQuestionListToDB(questionList);
		
		return "redirect:/trainer/test/" + testId + "/details.htm";
	}
	
	/**
	 * function to display the create question page
	 * @return create-question jsp
	 * @author Lhito.camson
	 */
	@RequestMapping(value="/question/create.htm", method=RequestMethod.GET)
	public String showCreateQuestionsPage(Model model) {
		List<Topic> topicList = topicService.getTopics();
		model.addAttribute("topicList", topicList);
		return "/trainer/question/create-question";
	}
	/**
	 * Function to take question details from create.jsp and inserts the new question into the database
	 * @param questionCreateCommand
	 * @param request
	 * @param radioanswer
	 * @return the create.jsp page so the user can create another question
	 * @author Lhito Camson
	 */
	@RequestMapping(value="/question/insert.htm", method=RequestMethod.POST)
	public String insertQuestion(@ModelAttribute("command") QuestionCreateCommand questionCreateCommand,HttpServletRequest request,BindingResult result,
			@RequestParam String radioanswer, Model model) throws EmptyFieldException{
		List<Topic> topicList = topicService.getTopics();
		model.addAttribute("topicList", topicList);
		if(questionCreateCommand.getOption1().trim().isEmpty()) {
			ObjectError error = new ObjectError("option1","Please fill out option 1");
			result.addError(error);
			return "/trainer/question/create-question";
		}
		if(questionCreateCommand.getOption2().trim().isEmpty()) {
			ObjectError error = new ObjectError("option2","Please fill out option 2");
			result.addError(error);
			return "/trainer/question/create-question";
		}
		if(questionCreateCommand.getOption3().trim().isEmpty()) {
			ObjectError error = new ObjectError("option3","Please fill out option 3");
			result.addError(error);
			return "/trainer/question/create-question";
		}
		if(questionCreateCommand.getOption4().trim().isEmpty()) {
			ObjectError error = new ObjectError("option4","Please fill out option 4");
			result.addError(error);
			return "/trainer/question/create-question";
		}
		if(questionCreateCommand.getTopicId() == 0) {
			ObjectError error = new ObjectError("topicId","Please select a test");
			result.addError(error);
			return "/trainer/question/create-question";
		}
		User loggedInUser = (User)request.getSession().getAttribute("user");
		Question questionToBeInserted = new Question();
		questionToBeInserted.setCorrectAnswer(radioanswer);
		questionToBeInserted.setCreatedBy(loggedInUser.getId());
		questionToBeInserted.setCreatedDate(new Date());
		questionToBeInserted.setModifiedBy(loggedInUser.getId());
		questionToBeInserted.setModifiedDate(new Date());
		questionToBeInserted.setOption1(questionCreateCommand.getOption1());
		questionToBeInserted.setOption2(questionCreateCommand.getOption2());
		questionToBeInserted.setOption3(questionCreateCommand.getOption3());
		questionToBeInserted.setOption4(questionCreateCommand.getOption4());
		questionToBeInserted.setQuestion(questionCreateCommand.getQuestion());
		questionToBeInserted.setStatusId(1);
		questionToBeInserted.setTopicId(questionCreateCommand.getTopicId());

		questionService.createQuestion(questionToBeInserted);
		// success, return to create.jsp
		return "/trainer/question/create-question";
	}


}
