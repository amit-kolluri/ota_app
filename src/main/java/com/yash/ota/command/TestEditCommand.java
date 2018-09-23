package com.yash.ota.command;

import java.sql.Time;

public class TestEditCommand {
	
	private String topic_name;
	private Time timeAlloted;

	public Time getTimeAlloted() {
		return timeAlloted;
	}

	public void setTimeAlloted(Time timeAlloted) {
		this.timeAlloted = timeAlloted;
	}
	

	public String getTopic_name() {
		return topic_name;
	}

	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}
	
	

}
