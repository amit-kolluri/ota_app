/**
 * EndOfTestResult
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

import java.sql.Date;
import java.sql.Time;

/**
 * This class is the object containing data for the end of a test report
 * @author connor.brown
 *
 */
public class EndOfTestResult {
	
	/**
	 * Name of the test
	 */
	private String testName;
	/**
	 * The time it took the user to take the test
	 */
	private Time timeTaken;
	/**
	 * Number of correct answers of the user
	 */
	private int correctAnswers;
	/**
	 * Total number of answers (or questions) on the test
	 */
	private int totalAnswers;
	/**
	 * Name of the technology the test was over
	 */
	private String technologyName;
	/**
	 * The name of the user who took the test
	 */
	private String username;
	/**
	 * The date the test was taken
	 */
	private Date date;
	/**
	 * Percentage correct
	 */
	private String percent;


	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Time getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(Time timeTaken) {
		this.timeTaken = timeTaken;
	}
	public int getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	public int getTotalAnswers() {
		return totalAnswers;
	}
	public void setTotalAnswers(int totalAnswers) {
		this.totalAnswers = totalAnswers;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(o.getClass() != this.getClass())
			return false;
		if(getCorrectAnswers() != ((EndOfTestResult)o).getCorrectAnswers() 
				&& getTotalAnswers() != ((EndOfTestResult)o).getTotalAnswers()
				&& !getTimeTaken().equals(((EndOfTestResult)o).getTimeTaken())
				&& !getTestName().equals(((EndOfTestResult)o).getTestName()))
			return false;
		return true;
	}
	
}
