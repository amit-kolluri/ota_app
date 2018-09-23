package com.yash.ota.serviceimpl;


import java.util.ArrayList;
import java.util.List;




import com.yash.ota.daoimpl.BatchDAOImpl;


import java.util.ArrayList;


import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yash.ota.dao.BatchDAO;
import com.yash.ota.domain.Batch;
import com.yash.ota.domain.User;
import com.yash.ota.exception.DuplicateEntryException;

import com.yash.ota.rowmapper.BatchRowMapper;

import com.yash.ota.exception.InvalidIdException;

import com.yash.ota.service.BatchService;
import javax.sql.DataSource;


@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private BatchDAO batchDAO;

    private JdbcTemplate jdbcTemplate;

    private Logger log = Logger.getLogger(BatchServiceImpl.class);
    
	
	
	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setMockedJdbcTemplate(JdbcTemplate temp) {
	        this.jdbcTemplate = temp;
	    }


   


    /**
    * @author Amit Kolluri
	*/
    @Override
    public void updateBatch(Batch batch) {
        batchDAO.update(batch);
    }


    /**
	 * Method returns the total number of Batches according to Trainer
	 * @return int
	 * @author Geon Gayles
	 */
    @Override
	public int getTotalBatchesByTrainer(User user) {
		String sql = "select count(B.id) from batches B inner join users U on B.created_by = ? and U.role_id = 2;";
		int total = jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, Integer.class);
		return total;
	}

    /**
	 * Returns the Current Batch that the trainer is actively teaching
	 * @return String
	 * @author Geon Gayles
	 */
	@Override
	public String getCurrentBatchByTrainer(User user) {
		String sql = "select B.name from batches B\n" + 
				"inner join users U on B.id = U.batch_id and U.role_id = 2 and U.status_id = 1 and B.created_by = ?";
		String currentBatch = jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, String.class);
		
		return currentBatch;
	}

   

    /**
	 * @author Amit Kolluri, kanika gandhi
     */
    @Override
    public Batch getBatchById(int batchId) throws InvalidIdException{
        return batchDAO.findById(batchId);
    }



    /**
     * Adds batch to the database
     * @param batch
     *
     * Author: Madhuri Vutukury
     */
    public void insertBatch(Batch batch) throws DuplicateEntryException {
        batchDAO.insert(batch);
    }


	
    
    /**	
	 * Used for JUnit testing	
	 * @author whitney.fout,kanika gandhi	
	 */	
	public void setDAO(BatchDAO batchDAO) {	
		this.batchDAO = batchDAO;	
 	}


	/**
	 * returns the count of users for a single batch
	 * @see com.yash.ota.service.BatchService#countUsersInBatch(int)
	 * @author kanika gandhi
	 */
	@Override
	public int countUsersInBatch(int batchId) {
		String sql = "SELECT COUNT(*) FROM users WHERE batch_id = ?";
		return (int)jdbcTemplate.queryForObject(sql, new Object[] {batchId}, Integer.class);
	}
	

	/**
	 * returns a list containing the status of every batch
	 * @see com.yash.ota.service.BatchService#findNamesAll()
	 * @author kanika gandhi
	 * 
	 */
	@Override
	public List<Integer> listStatusForAllBatches(){
		List<Integer> statusList = new ArrayList<Integer>();
		String sql ="select status_id from batches order by id";
		statusList = jdbcTemplate.queryForList(sql,Integer.class);
		return statusList;
	}
	

	/**

	 * Used for JUnit testing
	 * returns a list containing the count of users in each batch
	 * @see com.yash.ota.service.BatchService#findUsersAll()
	 * @author kanika gandhi, whitney.fout
	 *
	 */
	@Override
	public List<Integer> countUsersForEachBatch() {
		List<Integer> countList = new ArrayList<Integer>();
		String sql = "select count(*) as number_of_trainees_in_batch\r\n" + 
				"from users U\r\n" + 
				"inner join batches B on U.batch_id=B.id\r\n" + 
				"group by U.batch_id;";
		countList = jdbcTemplate.queryForList(sql,Integer.class);
		return countList;
	}


	/**
	 * returns a list containing the names of all batches 
	 * @see com.yash.ota.service.BatchService#findStatusAll()
	 * @author kanika gandhi
	 * 
	 */
	@Override
	public List<String> listNamesOfAllBatches(){
		List<String> nameList = new ArrayList<String>();
		String sql ="select name from batches";
		nameList = jdbcTemplate.queryForList(sql,String.class);
		return nameList;
	}
	
	/**
	 * Returns a list containing the Id for every batch
	 * @return List of Batch Ids
	 * @author Andrew.Beier, kanika gandhi
	 */
	@Override
	public List<Integer> listIdForAllBatches() {
		List<Integer> idList = new ArrayList<Integer>();
		String sql ="select id from batches";
		idList = jdbcTemplate.queryForList(sql,Integer.class);
		return idList;
	}




	/**
	 * @return list of batches  
	 * @author whitney.fout
	 */
	@Override
	public List<Batch> listBatches() {
		return batchDAO.list();
	}


	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate =mockedJdbcTemplate;
	}


	@Override
	public void addBatch(Batch batch) throws DuplicateEntryException {
		// TODO Auto-generated method stub
		
	}	

	private BatchRowMapper batchRowMapper = new BatchRowMapper();

    public BatchRowMapper getBatchRowMapper() {
        return batchRowMapper;
    }

    public void setBatchRowMapper(BatchRowMapper batchRowMapper) {
        this.batchRowMapper = batchRowMapper;
    }
	
    /**
	 * change the status of a batch
	 * @param batchId
	 * @author Andrew
	 */
	@Override
	public void changeBatchStatusToOpposite(int batchId) {
		Batch batch = new Batch();
		try {
			batch = getBatchById(batchId);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		if (batch.getStatusId() == 1) {
			batch.setStatusId(2);
		}
		else {
			batch.setStatusId(1);
		}
		updateBatch(batch);
	}
	


}
