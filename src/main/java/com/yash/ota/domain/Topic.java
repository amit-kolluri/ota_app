/**
 * Topic
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
 * This class provides a data model for all topics of the OTA.
 * 
 * @author whitney.fout, karl.roth
 */
public class Topic {

	/**
	 * Id of the topic
	 */
	private int id;
	
	/**
	 * Name of the topic
	 */
	private String name;
	
	/**
	 * The foreign id of the technology associated with this topic
	 */
	private int technologyId;
	
	/**
	 * The id of the user that activated this topic
	 */
	private int createdBy;
	
	/**
	 * The date that the topic was created
	 */
	private Date createdDate;
	
	/**
	 * The id of the user that modified this topic
	 */
	private int modifiedBy;
	
	/**
	 * The date that the topic was most recently modified
	 */
	private Date modifiedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTechnologyId() {
		return technologyId;
	}

	public void setTechnologyId(int technologyId) {
		this.technologyId = technologyId;
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
