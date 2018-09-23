/**
 * Question
 *
 * Version 0.1
 *
 * Date: 08/17/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.domain;

import java.util.Date;

/**
 * This class provides a data model for all Questions of the OTA.
 * 
 * @author whitney.fout, nick.parker
 */
public class Question {

	/**
	 * Id of the question
	 */
	private int id;
	
	/**
	 * The question itself that the trainee will attempt to answer
	 */
	private String question;
	
	/**
	 * The correct answer
	 */
	private String correctAnswer;
	
	/**
	 * option1
	 */
	private String option1;
	
	/**
	 * option2
	 */
	private String option2;
	
	/**
	 * option3
	 */
	private String option3;

	/**
	 * option4
	 */
	private String option4;
	
	/**
	 * The foreign id of the topic associated with the test
	 */
	private int topicId;
	
	/**
	 * The foreign id of the status associated with the test
	 */
	private int statusId;
	
	/**
	 * The id of the user that activated this test
	 */
	private int createdBy;
	
	/**
	 * The date that the technology was created
	 */
	private Date createdDate;
	
	/**
	 * The id of the user that modified this technology
	 */
	private int modifiedBy;
	
	/**
	 * The date that the technology was most recently modified
	 */
	private Date modifiedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}	
}
