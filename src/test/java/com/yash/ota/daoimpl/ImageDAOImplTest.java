package com.yash.ota.daoimpl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Types;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;

import com.yash.ota.dao.ImageDAO;
import com.yash.ota.domain.UserImage;
import com.yash.ota.util.Queries;

@RunWith(MockitoJUnitRunner.class)
public class ImageDAOImplTest {
	
	@InjectMocks
	private ImageDAO imageDAO = new ImageDAOImpl();
	private ApplicationContext context;
	
	@Mock
	private JdbcTemplate mockedJdbcTemplate;
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void testInsertImage_NoImageGiven_ShouldVerifyCall() throws DataAccessException, FileNotFoundException {
		File file;
		
		try {
			file = folder.newFile("myfile1.txt");
			FileWriter writer = new FileWriter(file);

	        while (file.length() <= 1e+4) {
	            writer.write("random data");
	            writer.write("\n");
	        }
	        writer.flush();
	        writer.close();
	        
			imageDAO.insert(file, 101);
			Mockito.verify(mockedJdbcTemplate).update(
	   	         Mockito.eq(Queries.INSERT_IMAGE),
	   	         Mockito.any(Object[].class),
	   	         Mockito.any(int[].class));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateImage_NoImageGiven_ShouldVerifyCall() throws DataAccessException, FileNotFoundException {
		File file;
		
		try {
			file = folder.newFile("myfile1.txt");
			FileWriter writer = new FileWriter(file);

	        while (file.length() <= 1e+4) {
	            writer.write("random data");
	            writer.write("\n");
	        }
	        writer.flush();
	        writer.close();
	        
			imageDAO.update(file, 101);
			Mockito.verify(mockedJdbcTemplate).update(
	   	         Mockito.eq(Queries.UPDATE_IMAGE),
	   	         Mockito.any(Object[].class),
	   	         Mockito.any(int[].class));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
