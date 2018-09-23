/*
 * TimeTaken
 *
 * Version 0.1
 *
 * Date: 08/18/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.domain;

import java.sql.Time;

/**
 * This contains the total amount of time taken for a user on a specific test
 * @author connor.brown
 *
 */
public class TimeTaken {
	/**
	 * The unique id of this object
	 */
	private int id;
	/**
	 * The id of the test this object corresponds to
	 */
	private int testId;
	/**
	 * The id of the user this object corresponds to
	 */
	private int userId;
	/**
	 * The id of the result this object corresponds to
	 */
	private int resultId;
	/**
	 * The amount of time the user took to complete the test 
	 */
	private Time timeTaken;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public Time getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(Time timeTaken) {
		this.timeTaken = timeTaken;
	}
	
}
