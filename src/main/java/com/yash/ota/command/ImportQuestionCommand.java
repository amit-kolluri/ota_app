package com.yash.ota.command;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImportQuestionCommand {
	@NotNull(message = "File must be selected")
	MultipartFile file;

	@Min(message = "Topic must be selected", value = 1)
	int topicId;
	
	@Min(message = "Test must be selected", value = 1)
	int testId;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}
	
}
