/*
 * Test
 *
 * Version 0.1
 *
 * Date: 08/15/2018

 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.domain;

import java.sql.Time;
import java.util.Date;

/**
 * This class provides a data model for all the tests in the OTA.
 *
 * @author karl.roth, whitney.fout,fahmida.joyti
 */
public class Test {



    /**
     * The id of the test

     */
    private int id;

    /**
     * The foreign id of the topic associated with the test
     */
    private int topicId;

    /**
     * The highest score possible on the test
     */
    private int totalMarks;

    /**
     * Time allowed for the test
     */
    private Time timeAlloted;
    /**
     * Status of the test
     * 1 for active
     * 2 for inactive
     */
    private int statusId;
	
    /*
	number of questions for the test
	 */	

	
	/**
	 * The id of the user that activated this test
	 */
	private int createdBy;
	
	/**
	 * The date that the test was created
	 */
	private Date createdDate;
	
	/**
	 * The id of the user that modified this test
	 */
	private int modifiedBy;
	
	/**
	 * The date that the test was most recently modified
	 */
	private Date modifiedDate;
	/**
	 * Number of questions assigned to this test
	 */
	private int num_questions;
	
	/**
	 * The test Name
	 */
	private String testName;
	
	/**
	 * The technology name
	 */
	private String technology_name;
    

    public Time getTimeAlloted() {
        return timeAlloted;
    }

    public void setTimeAlloted(Time timeAlloted) {
        this.timeAlloted = timeAlloted;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
    

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
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

    public int getNum_questions() {
        return num_questions;
    }

    public void setNum_questions(int num_questions) {
        this.num_questions = num_questions;
    }

	public String getTechnology_name() {
		return technology_name;
	}

	public void setTechnology_name(String technology_name) {
		this.technology_name = technology_name;
	}

    
    
}