/*
 * BatchDAO
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */

package com.yash.ota.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import com.yash.ota.domain.Batch;
import com.yash.ota.exception.DuplicateEntryException;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.rowmapper.BatchRowMapper;
/**
 * This interface provides a data access object for the Batch data model.
 *
 * @author Madhuri Vutukury, whitney.fout, Kanika Gandhi
 */



public interface BatchDAO {


    /**
     * Method to get all data from batches database for overall-batch reports
     * Method returns a list of  objects from the database
     * @return list of all batch in database
     *
     * @author Kanika Gandhi, nick.stone
     */
    public List<Batch> list();

	/**
	 * Method to insert a Batch into the database
	 * @param batch Batch the batch to be inserted
	 */
    public void insert(Batch batch) throws DuplicateEntryException;




    /**
     * Used for JUnit testing
     * @param mockedJdbcTemplate
     *
     * @author Kanika Gandhi
     */
    public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);


    /**
     * Used for JUnit testing
     * @return UserRowMapper that is used by this BatchDAO
     *
     * @author Kanika Gandhi
     */
    public BatchRowMapper getBatchRowMapper();


    /**
     * This method updates current batch details
     * @param batch
     * @author Amit Kolluri
     */
    public void update(Batch batch);



    /**
     * Finds a batch by the batch id.
     * @param id int the batch's id
     * @return Batch
     * @author scotrenz
     */
    Batch findById(int id)throws InvalidIdException;;


    public int addBatch(Batch batch) throws DuplicateEntryException;
    
	
    

}
