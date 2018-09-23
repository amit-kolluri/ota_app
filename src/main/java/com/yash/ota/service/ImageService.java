package com.yash.ota.service;

import java.io.File;

import com.yash.ota.domain.UserImage;
import com.yash.ota.rowmapper.UserImageRowMapper;

/**
* This interface provides a service object for the Image data model. 
* 
* @author almedin.mulalic
*/
public interface ImageService {
	
	/**
	 * This method will save the provided image to the database
	 * @param file file to be saved
	 * @param userId id of the user
	 */
	public void saveImage(File file, int userId);
	
	/**
	 * This method will update the provided image to the database
	 * @param file file to be saved
	 * @param userId id of the user
	 * @author phoutsaksit.keomala, almedin.mulalic
	 */
	public void updateImage(File file, int userId);
	
	/**
	 * This method will retrieve the image from the database based on userId
	 * @param userId associated with image
	 * @return Image from database
	 * @author phoutsaksit.keomala
	 */
	public UserImage getProfileImageByUserId(int userId);
	
	/**
	 * This method will return UserImageRowMapper
	 * @return UserImageRowMapper
	 */
	public UserImageRowMapper getUserImageRowMapper();
}
