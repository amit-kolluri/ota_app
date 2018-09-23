package com.yash.ota.rowmapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StreamUtils;

import com.yash.ota.domain.UserImage;

/**
 * This class provides object relational mapping functionality.
 *
 * @author almedin.mulalic
 */
public class UserImageRowMapper implements RowMapper<UserImage> {

	private Logger log = Logger.getLogger(UserImageRowMapper.class);

	@Override
	public UserImage mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		UserImage userImage = new UserImage();
		userImage.setId(resultSet.getInt("id"));
		userImage.setUserId(resultSet.getInt("user_id"));

		InputStream photoBinaryStream = resultSet.getBinaryStream("user_image");

		try {
			userImage.setByteImage(StreamUtils.copyToByteArray(photoBinaryStream));
		} catch (IOException e) {
			log.error("Unable to retrieve image from users_images" + e.getMessage());
		}

		byte[] encodeBase64 = Base64.encodeBase64(userImage.getByteImage());
		String base64Encoded;
		try {
			base64Encoded = new String(encodeBase64, "UTF-8");
			userImage.setBase64Image(base64Encoded);
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException " + e.getMessage());
		}

		return userImage;
	}

}
