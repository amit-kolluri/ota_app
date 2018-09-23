package com.yash.ota.serviceimpl;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.ImageDAO;
import com.yash.ota.domain.UserImage;
import com.yash.ota.rowmapper.UserImageRowMapper;
import com.yash.ota.service.ImageService;
import com.yash.ota.util.Queries;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImplTest {

	@InjectMocks
	private ImageService imageService = new ImageServiceImpl();

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private ImageDAO imageDAO;
	
	@Mock
	private UserImageRowMapper userImageRowMapper;

	private UserImage mockUserImage = new UserImage();
	
	@Test
	public void testGetUserImage_UserIdGiven_ShouldReturnImage() {	
		Mockito.when(jdbcTemplate.queryForObject(Queries.GET_CREATOR_IMAGE, imageService.getUserImageRowMapper(), 101)).thenReturn(mockUserImage);		
		UserImage userImage = imageService.getProfileImageByUserId(101);
		assertEquals(mockUserImage, userImage);
	}
	
	@Test
	public void testSaveImage_NoImageGiven_ShouldCallInsertInDAO() {
		File file = new File("");
		imageService.saveImage(file, 101);
		Mockito.verify(imageDAO).insert(file, 101);
	}
	
	@Test
	public void testUpdateImage_NoImageGiven_ShouldCallUpdateInDAO() {
		File file = new File("");
		imageService.updateImage(file, 101);
		Mockito.verify(imageDAO).update(file, 101);
	}

}