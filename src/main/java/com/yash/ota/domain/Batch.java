/**
 * Batch
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
 * This class provides a data model for all Batches of the OTA.
 * 
 * @author whitney.fout, karl.roth
 */
public class Batch {
	
	/**
	 * Id of the batch
	 */
	private int id;
	
	/**
	 * Name of the batch
	 */
	private String name;
	
	/**
	 * Description of the batch
	 */
	private String description;
	
	/**
	 * Status of the batch
	 * 1 for active 
	 * 2 for inactive
	 */
	private int statusId;
	
	/**
	 * The id of the user that activated this batch
	 */
	private int createdBy;
	
	/**
	 * The date that the batch was created
	 */
	private Date createdDate;
	
	/**
	 * The id of the user that modified this batch
	 */
	private int modifiedBy;
	
	/**
	 * The date that the batch was most recently modified
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
