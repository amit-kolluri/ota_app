package com.yash.ota.command;

import javax.validation.constraints.Size;

/**
 * command for edit trainee details
 * @author kanika gandhi
 *
 */
public class EditTraineeDetailCommand {
	
	@Size(min = 1, max = 20, message = "First Name must be between 1 and 20 characters")
	private String firstName;
	@Size(min = 1, max = 20, message = "Last Name must be between 1 and 20 characters")
	private String lastName;
	@Size(min = 10, max = 15, message = "Phone Number must be at least 10 digits")
	private String phoneNumber;
	private int batchId;
	private String batchName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}	
	
}
