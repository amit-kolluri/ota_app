package com.yash.ota.command;

/**
 * This class provides a data from the create batch front page of the OTA.
 *
 *
 * @author Madhuri Vutukury
 */

public class BatchCreateCommand {

    private String batchName;

    private String batchDescription;

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchDescription() {
        return batchDescription;
    }

    public void setBatchDescription(String batchDescription) {
        this.batchDescription = batchDescription;
    }
}
