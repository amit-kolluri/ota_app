/*
 * UserServiceImpl
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.serviceimpl;


import com.yash.ota.dao.UserDAO;
import com.yash.ota.domain.Test;
import com.yash.ota.domain.User;
import com.yash.ota.rowmapper.UserRowMapper;
import com.yash.ota.service.UserService;
import javax.sql.DataSource;
import java.util.List;
import javax.sql.DataSource;
import com.yash.ota.domain.Result;
import com.yash.ota.domain.User;
import com.yash.ota.exception.DuplicateUserException;
import com.yash.ota.exception.UserAuthenticationException;
import com.yash.ota.rowmapper.ResultRowMapper;
import com.yash.ota.rowmapper.UserImageRowMapper;
import com.yash.ota.rowmapper.UserRowMapper;
import com.yash.ota.service.UserService;
import com.yash.ota.util.Queries;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;


/**
 * This implementation provides a service object for the User data model.
 *
 * This file is for reference only. Do not edit or delete this code!
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

    private UserImageRowMapper userImageRowMapper = new UserImageRowMapper();
	private JdbcTemplate jdbcTemplate;
	private UserRowMapper userRowMapper = new UserRowMapper();
	private Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;
	}

	@Override
	public UserRowMapper getUserRowMapper() {
		return userRowMapper;
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

    /**
     * Checks database for correct email and password
     * @throws UserAuthenticationException 
     * @author whitney.fout
     */
    public User login(String email, String password) throws UserAuthenticationException {
    	User user = null;
    	try {
        String sql = Queries.LOGIN_QUERY;
        user = jdbcTemplate.queryForObject(sql, userRowMapper, email, password);
    	}catch(IncorrectResultSizeDataAccessException e){
    		
    	}
    	if(user == null) {
			throw new UserAuthenticationException("Email or password is incorrect");
		}
		return user;

	}

	/**
	 * @author karl.roth
	 */
	@Override
	public User findById(int id) {
		return userDAO.findById(id);
	}

	/**
	 * @author whitney.fout
	 * @throws DuplicateUserException
	 */
	public void register(User user) throws DuplicateUserException {
		log.info(user.getUserName());
		if (checkForDuplicateUser(user)) {
			userDAO.insert(user);
		}
	}

	/**
	 * 
	 * @param user
	 * @return boolean to show whether email has already been used to register
	 * @throws DuplicateUserException
	 * @author whitney.fout
	 */

	private boolean checkForDuplicateUser(User user) throws DuplicateUserException {
		String email = user.getEmail();
		boolean test = true;
		int count = 0;

		try {
			count = jdbcTemplate.queryForObject("select count(*) from users where email = ?", new Object[] { email },
					Integer.class);
		} catch (IncorrectResultSizeDataAccessException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			test = false;
			throw new DuplicateUserException("Email has already been taken");
		}

		return test;
	}

	public void setDAO(UserDAO mockedDAO) {
		this.userDAO = mockedDAO;

	}
	
	/**
	 * Getting the email for the forgot password
	 * 
	 * @author harish
	 */

	@Override
	public User findUserByEmail(String email) {
		String st2 = "Select * from users where email = ? ";
		return jdbcTemplate.queryForObject(st2, new UserRowMapper(), email);
	}

	/**
	 * Updating the new password in the database
	 * 
	 * @author harish
	 */

	@Override
	public void resetPassword(String password, int id) {
		String st1 = "UPDATE users SET password=? WHERE id=?";
		jdbcTemplate.update(st1,password, id);
		
	}

    /**
     * @author - Jay Shah, scotrenz
     */
    @Override
    public List<User> getTraineesByBatchId(int batchId) {
		int roleId = 1;
		String sql = "SELECT u.* FROM users u join batches b on u.batch_id = b.id join roles r on u.role_id = r.id where b.id = ? and r.id = ?";
        return jdbcTemplate.query(sql, userRowMapper, batchId, roleId);
    }



	/**
	 * @author karl.roth
	 */
	@Override
	public List<Result> getResultsForUser(User user) {
		int userId = user.getId();
		String sql = "SELECT * FROM results WHERE user_id=?";

		return jdbcTemplate.query(sql, new ResultRowMapper(), userId);
	}


	/**
	 * @author michael.arp
	 * TODO: Why does this have to be an object and not an int?
	 * EDITED: BY SCOT AGAINST NEEL'S WILL
	 */

	@Override
	public Integer getCountOfAttribute(String attribute) {
		String sql = "";
		if(attribute.equalsIgnoreCase("Trainees"))
		{
			sql="select count(*) from users where role_id=1";
		}
		else if(attribute.equalsIgnoreCase("Batches"))
		{
			sql="select count(*) from batches where batches.status_id=1";
		}
		else if(attribute.equalsIgnoreCase("Tests"))
		{
			sql="select count(*) from tests where tests.status_id=1";
		}

		return jdbcTemplate.queryForObject(sql, Integer.class);
	}



	/**
	 * Returns total number of trainess trained by Trainer
	 * @param user
	 * @return total number of trainees
	 * @author Geon Gayles
	 */
	@Override
	public int getTotalTraineesByTrainer(User user) {
		String sql = "select count(U.id) from users U" + 
				" inner join batches B on U.batch_id=B.id" + 
				" inner join roles R on U.role_id=R.id where U.role_id=1 and U.status_id=1 and R.created_by = ?";
		int total = jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, Integer.class);
		
		return total;
	}


	/**
	 * This method will get all users who were created by user with id 1(Pankaj)
	 * People created by user 1 will be known as creators, used rather than an additional status id
	 * @return list of users with created_by = 1 
	 */
	@Override
	public List<User> getAllCreators() {
		return jdbcTemplate.query(Queries.GET_ALL_CREATORS, userRowMapper);
	}


	/**
	 * This method returns the list of trainees from the database
	 * arranged by created_date
	 * 
	 * @return list of User objects which are trainees
	 * @author neel.patel
	 */
	@Override
	public List<User> getAllTrainees() {

		String sql = Queries.FIND_TRAINEES;
		List<User> trainees = jdbcTemplate.query(sql, userRowMapper);
		Collections.reverse(trainees);
		return trainees;
	}

	/**
	 * Pass an Id to be deleted in the UserDAO
	 * 
	 * @author neel.patel
	 */
	@Override
	public void deleteUser(int userId) {
		userDAO.delete(userId);
	}

	/**
	 * Set userRowMapper
	 * 
	 * @author neel.patel
	 */
	@Override
	public void setUserRowMapper(UserRowMapper userRowMapper) {
		this.userRowMapper = userRowMapper;

	}

	@Override
	public void updateTest(Test test) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * @author kanika gandhi
	 */
	@Override
	public void updateUser(User trainee) {
		userDAO.update(trainee);	
	}


	/**
	 * update the trainee status to active once the trainer approves the trainee
	 *
	 * @author amit kolluri
	 */
	@Override
	public void approveUser(int userId) {
		User user = userDAO.findById(userId);
		user.setStatusId(1);
		user.setCreatedBy(1);
		user.setModifiedBy(1);
		userDAO.update(user);

	}


	/**
	 * used for getting list of awaiting users for trainer approval
	 * @return list of users
	 * @author amit kolluri
	 *
	 */
	@Override
	public List<User> getAwaitingUsersList() {
		String sql = "SELECT * FROM users where status_id=3 and role_id=1";
		return jdbcTemplate.query(sql, userRowMapper);
	}
}

