/**
 * Result
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

import java.sql.Time;
import java.util.Date;

/**
 * This class provides a data model for all Questions of the OTA.
 * 
 * @author whitney.fout, nick.parker
 */
public class Result {

	/**
	 * Id of the result
	 */
	private int id;
	
	/**
	 * The foreign id of the user associated with the result
	 */
	private int userId;
	
	/**
	 * The foreign id of the test associated with the result
	 */
	private int testId;
	
	/**
	 * The foreign id of the topic associated with the result
	 */
	private int topicId;
	
	/**
	 * The foreign id of the technology associated with the result
	 */
	private int technologyId;
	
	/**
	 *	The highest score possible on the test
	 */
	private int totalMarks;
	
	/**
	 * Marks earned by trainee
	 */
	private int obtainedMarks;
	
	/**
	 * The foreign id of the status associated with the result
	 */
	private int statusId;
	
	/**
	 * The id of the user that created this result
	 */
	private int createdBy;
	
	/**
	 * The date that the result was created
	 */
	private Date createdDate;
	
	/**
	 * The id of the user that modified this result
	 */
	private int modifiedBy;
	
	/**
	 * The date that the result was most recently modified
	 */
	private Date modifiedDate;

	/**
	 * The time it took for the user to take the test
	 */

	private Time timeTaken;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public int getTechnologyId() {
		return technologyId;
	}

	public void setTechnologyId(int technologyId) {
		this.technologyId = technologyId;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public int getObtainedMarks() {
		return obtainedMarks;
	}

	public void setObtainedMarks(int obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
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

	public Time getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(Time timeTaken) {
		this.timeTaken = timeTaken;
	}
}
