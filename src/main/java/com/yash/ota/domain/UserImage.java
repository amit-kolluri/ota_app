package com.yash.ota.domain;

/**
 * This class provides a data model for images stored in the ota
 * @author Almedin
 *
 */
public class UserImage {
	
	/**
	 * Id of time image
	 */
	private int id;
	
	/**
	 * byte data of the image
	 */
	private byte[] byteImage;
	
	/**
	 * id of user image belongs to
	 */
	private int userId;
	
	/**
	 * base 64 version of image
	 */
	private String base64Image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getByteImage() {
		return byteImage;
	}

	public void setByteImage(byte[] byteImage) {
		this.byteImage = byteImage;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
}
