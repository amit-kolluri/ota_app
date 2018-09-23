package com.yash.ota.command;

/**
 * This class holds form data to save Test
 *
 * @author Madhuri Vutukury
 */

public class TestCreateCommand {
    private String technologies;
    private String topics;
    private String testDuration;


    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(String testDuration) {
        this.testDuration = testDuration;
    }
}
