package com.yash.ota.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author connor.brown
 *
 */
public class UserAnswerCommand {
	
	private List<String> userAnswer = new ArrayList<String>();

	public List<String> getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(List<String> userAnswer) {
		this.userAnswer = userAnswer;
	}
	
}
