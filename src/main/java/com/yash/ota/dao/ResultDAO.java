/**
 * ResultDAO
 *
 * Version 0.1
 *
 * Date: 08/21/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.dao;

import com.yash.ota.domain.Result;
import com.yash.ota.rowmapper.ResultRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * This interface provides a data access object for the Result data model. 
 *
 * @author nick.parker
 */
public interface ResultDAO {
    /**
     * Returns an ArrayList object containing all Result objects from the database
     * @return ArrayList object
     * @author nick.parker
     */
    public List<Result> list();

    /**
     * Insert a Result into the database
     * @param result
     * @author nick.parker
     */
    public void insert(Result result);

    /**
     * Find a specific Result by its id
     * @param id  the id of the requested result
     * @return the Result object, null otherwise
     * @author nick.parker
     */
    public Result findById(int id);

    /**
     * Update the information of a given Result
     * @param result the given Result
     */
    public void update(Result result);

    /**
     * Delete a result from the database
     * @param resultId the Result to be deleted
     * @author nick.parker
     */
    public void delete(int resultId);

    /**
     * Used for JUnit testing
     * @param mockedJdbcTemplate
     * @author nick.parker
     */
    public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);

    /**
     * Used for Junit Testing
     * @return ResultRowMapper that is used by this resultDAO
     * @author nick.parker
     */
    public ResultRowMapper getResultRowMapper();
}
