/*
 * ImageDAOImpl
 *
 * Version 0.1
 *
 * Date: 08/27/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.daoimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Types;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.yash.ota.dao.ImageDAO;
import com.yash.ota.rowmapper.UserImageRowMapper;
import com.yash.ota.util.Queries;

/**
 * This is the implementation of the Image DAO interface
 * @author Almedin
 *
 */
@Repository
public class ImageDAOImpl implements ImageDAO{

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private Logger log = Logger.getLogger(ImageDAOImpl.class);
	
	private UserImageRowMapper userImageRowMapper = new UserImageRowMapper();
	/**
	 * @author almedin.mulalic
	 */
	@Override
	public void insert(File file, int userId) {
	      try { 
	    	  final InputStream imageIs = new FileInputStream(file); 
	    	   LobHandler lobHandler = new DefaultLobHandler(); 
	    	   jdbcTemplate.update(
	    	         Queries.INSERT_IMAGE,
	    	         new Object[] {
	    	           new SqlLobValue(imageIs, (int)file.length(), lobHandler),
	    	           userId,
	    	         },
	    	         new int[] {Types.BLOB, Types.INTEGER});
	    	  } catch (DataAccessException e) {
	    		  log.error("DataAccessException " + e.getMessage());
	    	  } catch (FileNotFoundException e) {
	    		  log.error("FileNotFoundException " + e.getMessage());
	    	  }
	}
	
	/**
	 * @author almedin.mulalic
	 */
	@Override
	public void update(File file, int userId) {
	      try {
	    	  
	    	   final File image = new File(file.getAbsolutePath());
	    	   final InputStream imageIs = new FileInputStream(image);  
	    	   LobHandler lobHandler = new DefaultLobHandler(); 
	    	   jdbcTemplate.update(
	    	         Queries.UPDATE_IMAGE,
	    	         new Object[] {
	    	           new SqlLobValue(imageIs, (int)image.length(), lobHandler),
	    	           userId,
	    	           userId,
	    	         },
	    	         new int[] {Types.BLOB, Types.INTEGER, Types.INTEGER});
    	  } catch (DataAccessException e) {
    		  log.error("DataAccessException " + e.getMessage());
    	  } catch (FileNotFoundException e) {
    		  log.error("FileNotFoundException " + e.getMessage());
    	  }
	}
	
	/**
	 * @author almedin.mulalic
	 */
	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * @author almedin.mulalic
	 */
	@Override
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate) {
		this.jdbcTemplate = mockedJdbcTemplate;
	}
    
    /**
     * @author phoutsaksit.keomala
     */
	@Override
	public UserImageRowMapper getUserImageRowMapper() {
		return userImageRowMapper;
	}
	
	/**
	 * @author phoutsaksit.keomala
	 */
	public void setUserImageRowMapper(UserImageRowMapper userImageRowMapper) {
		this.userImageRowMapper = userImageRowMapper;
	}
}
