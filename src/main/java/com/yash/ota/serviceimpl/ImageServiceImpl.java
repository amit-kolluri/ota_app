/*
 * ImageServiceImpl
 *
 * Version 0.1
 *
 * Date: 08/27/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.serviceimpl;

import java.io.File;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yash.ota.dao.ImageDAO;
import com.yash.ota.domain.UserImage;
import com.yash.ota.rowmapper.UserImageRowMapper;
import com.yash.ota.service.ImageService;
import com.yash.ota.util.Queries;

/**
 *  This implementation provides a service object for the UserImage data model.
 *
 */
@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	private ImageDAO imageDAO;
	
    private JdbcTemplate jdbcTemplate;
	
    private UserImageRowMapper userImageRowMapper = new UserImageRowMapper();
    private Logger log = Logger.getLogger(ImageServiceImpl.class);
	
    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	/**
	 * @author almedin.mulalic
	 */
	@Override
	public void saveImage(File file, int userId) {
		imageDAO.insert(file, userId);
	}

	/**
	 * @author almedin.mulalic
	 */	@Override
	public void updateImage(File file, int userId) {
		imageDAO.update(file, userId);	
	}

	/**
	 * @author phoutsaksit.keomala
	 */
	@Override
	public UserImage getProfileImageByUserId(int userId) {
		try {
			return jdbcTemplate.queryForObject(Queries.GET_CREATOR_IMAGE, userImageRowMapper, userId);
		} catch (Exception ex) {
			log.error("Could not find profile picture for user with id: " + userId + ex.getMessage());
			return null;
		}
	}
	
	/**
	 * @author phoutsaksit.keomala
	 */
	@Override
	public UserImageRowMapper getUserImageRowMapper() {
		return userImageRowMapper;
	}	
}
