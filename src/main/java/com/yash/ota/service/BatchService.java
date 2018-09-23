package com.yash.ota.service;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.BatchDAO;
import com.yash.ota.domain.Batch;
import com.yash.ota.domain.User;
import com.yash.ota.exception.DuplicateEntryException;
import com.yash.ota.exception.InvalidIdException;



/**
 * Interface provides service object for Batch data model
 * @author YASH 2108 GTP
 */

public interface BatchService {

    /**
     * This method update current batch details
     * @param batch
     * @author Amit Kolluri
     */
    void updateBatch(Batch batch);

    /**
     * This method get batch based on id
     * @param batchId
     * @return Batch
     * @author Amit Kolluri,kanika gandhi
     */

    public Batch getBatchById(int batchId) throws InvalidIdException;


	/**
	 *Adds batch to the database
	 * @param batch
	 *
	 * @author: Madhuri Vutukury
	 */
	public void insertBatch(Batch batch) throws DuplicateEntryException;




    public void addBatch(Batch batch) throws DuplicateEntryException;
    
	/**
	 * Returns the Current Batch that the trainer is actively teaching
	 * @return String
	 * @author Geon Gayles
	 */
	public String getCurrentBatchByTrainer(User user);

	/**
	 * Method returns the total number of Batches according to Trainer
	 * @return int
	 * @author Geon Gayles
	 */
	public int getTotalBatchesByTrainer(User user);

    
    

    /**
	 * Used for JUnit testing in ServiceImplTest.class
	 * @param mockedDAO
	 *
	 * @author kanika gandhi
	 */
	public void setDAO(BatchDAO mockedDAO);

	/**
     *Counts users for a batch
     * @param batchId
     * @return userCount
     * Author: Andrew.Beier,kanika gandhi
     */
	public int countUsersInBatch(int batchId);

	/**
	 * 

     *Lists the names of all batches
     * @return listofBatchNames
     * Author: Andrew.Beier,kanika gandhi
     */
	public List<String>  listNamesOfAllBatches();
	
	/**
     *Lists the number of users in each batch
     * @return listNumberOfUsers
     * Author: Andrew.Beier,kanika gandhi
     */
	public List<Integer> countUsersForEachBatch();
	
	/**
     *Lists the status of each batch
     * @return listofstatus
     * Author: Andrew.Beier,kanika gandhi
     */
	public List<Integer> listStatusForAllBatches();

	/**
     *Lists the Id of each batch
     * @return listofIds
     * @Author: Andrew.Beier,kanika gandhi
     */
	public List<Integer>  listIdForAllBatches();
	


    /**
     * Lists batches from database
     * @return List of batches
     * Author: whitney.fout
     */
    List<Batch> listBatches();


	/**
	 * Used to set MOCKED JdbcTemplate
	 * @param mockedJdbcTemplate
	 * @author neel.patel
	 */
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);
    
	/**
	 * Used to change the batch status
	 * @param batchId 
	 * @author Andrew.beier
	 */
    public void changeBatchStatusToOpposite(int batchId);

}
