package com.yash.ota.dao;

import java.io.File;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.rowmapper.UserImageRowMapper;

/**
 * This interface provides a data access object for the Image data model. 
 * 
 * @author almedin.mulalic
 */
public interface ImageDAO {
	/**
	 * This method will insert a file to the database with user_id userId
	 * @param file to be inserted
	 * @param userId id of user associated with file
	 */
	public void insert(File file, int userId);
	
	
	/**
	 * This method will update an image from the database based on user_id
	 * @param file file to be updated
	 * @param userId id of the user associated with the image
	 * @return image in database or null if not found
	 */
	void update(File file, int userId);
	
	/**
	 * Used for JUnit testing
	 * @return UserImageRowMapper that is used by this ImageDAO
	 */
	public UserImageRowMapper getUserImageRowMapper();

	/**
	 * Used for JUnit testing
	 * @param mockedJdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate mockedJdbcTemplate);


}
