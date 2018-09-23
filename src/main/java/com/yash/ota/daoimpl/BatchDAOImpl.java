/*
 * BatchDAOImpl
 *
 * Version 1.0
 *
 * Date: 08/22/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.yash.ota.dao.BatchDAO;
import com.yash.ota.domain.Batch;
import com.yash.ota.exception.DuplicateEntryException;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.rowmapper.BatchRowMapper;

/**
 * This implementation provides a data access object for the Batch data model.
 */

@Repository
public class BatchDAOImpl implements BatchDAO {


    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private Logger log = Logger.getLogger(BatchDAOImpl.class);

    private BatchRowMapper batchRowMapper = new BatchRowMapper();

    public BatchRowMapper getBatchRowMapper() {
        return batchRowMapper;
    }

    public void setBatchRowMapper(BatchRowMapper batchRowMapper) {
        this.batchRowMapper = batchRowMapper;
    }


    /**
     *
     * @param batch, batch to be inserted
     * @return
     * @throws DuplicateEntryException
     * @author Madhuri.Vutukury
     */
    public int addBatch(Batch batch) throws DuplicateEntryException {
        log.info("checking batch is exists");
        String countSql = "SELECT count(*) FROM batches where name=?";
        //to get the count
        int count = jdbcTemplate.queryForObject(countSql, new Object[]{batch.getName()}, Integer.class);
        if (count == 0) {
            log.info("adding Batch");
            String sql = "INSERT INTO batches ( name, description, created_by, created_date, modified_by, modified_date) values(?,?,?,?,?,?)";
            Object[] params =
                    new Object[]{
                            batch.getName(),
                            batch.getDescription(),
                            batch.getCreatedBy(),
                            batch.getCreatedDate(),
                            batch.getModifiedBy(),
                            batch.getModifiedDate(),

                    };


            return jdbcTemplate.update(sql, params);
        } else {
            throw new DuplicateEntryException("Batch Name already exists");
        }

    }



	@Override
	/**
	 * @author Amit Kolluri
	 *
	 * this method updates the name of the batch in database
	 */
	public void update(Batch batch) {
		String sql = "UPDATE batches SET "
				+ "name=?, "
				+ "status_id=?,"
				+ "modified_by=?, "
				+ "modified_date=? "
				+ "WHERE id=?";

		Object[] params =
				new Object[] {
						batch.getName(),
						batch.getStatusId(),
						batch.getModifiedBy(),
						batch.getModifiedDate(),

						batch.getId()
				};

		jdbcTemplate.update(sql,params);
		log.info("record updated");

	}


	/**
	 * @param id int the batch's id
	 * @return batch matching id
	 * @author scotrenz, kanika gandhi
	 */
	@Override
	public Batch findById(int id) throws InvalidIdException {
		if (id <= 0) {
			throw new InvalidIdException("Batch Query Id: Cannot Be Negative");
		}
		String sql = "SELECT * FROM batches WHERE id=?;";
		Batch batch = jdbcTemplate.queryForObject(sql, batchRowMapper, id);
		if (batch == null) {
			throw new InvalidIdException("Batch Query Id: Returns Null");
		}
		return batch;
	}

	/**
	 *
	 * @param batch, batch to be inserted
	 *
	 * @throws DuplicateEntryException
	 * @author Madhuri.Vutukury, kanika gandhi
	 */
	@Override
	public void insert(Batch batch) throws DuplicateEntryException {
		log.info("checking batch is exists");
		String countSql = "SELECT count(*) FROM batches where name=?;";
		//to get the count
		int count = jdbcTemplate.queryForObject(countSql, Integer.class, batch.getName());
		if (count == 0) {
			log.info("adding Batch");
			String sql = "INSERT INTO batches ( name, description, created_by, created_date, modified_by, modified_date) values(?,?,?,?,?,?)";
			Object[] params =
					new Object[]{
							batch.getName(),
							batch.getDescription(),
							batch.getCreatedBy(),
							batch.getCreatedDate(),
							batch.getModifiedBy(),
							batch.getModifiedDate(),
					};
			jdbcTemplate.update(sql, params);
		} else {
			throw new DuplicateEntryException("Batch Name already exists");
		}
	}

	/**
	 * (non-Javadoc)
	 * @see com.yash.ota.dao.BatchDAO#list()
	 * @return list of batches
	 * @author kanika gandhi, whitney.fout
	 */

	@Override
	public List<Batch> list() {
		String sql = "SELECT * FROM batches";
		return jdbcTemplate.query(sql, batchRowMapper);
	}


	/**
	 * (non-Javadoc)
	 * @see com.yash.ota.dao.BatchDAO#setJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate)
	 * @author kanika gandhi
	 */
	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;		
	}
	
	




	}



