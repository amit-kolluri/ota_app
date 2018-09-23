package com.yash.ota.daoimpl;


import com.fasterxml.jackson.databind.Module.SetupContext;
import com.yash.ota.dao.BatchDAO;
import com.yash.ota.domain.Batch;
import com.yash.ota.exception.DuplicateEntryException;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Assert;
import org.junit.Before;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.creation.util.MockitoMethodProxy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.Date;

import static org.mockito.ArgumentMatchers.*;

import com.yash.ota.exception.InvalidIdException;


@RunWith(MockitoJUnitRunner.class)
public class BatchDAOImplTest {
	

	
	private ClassPathXmlApplicationContext context;
	private BatchDAO mockedBatchDAO;
	private JdbcTemplate mockedJdbcTemplate;
	
	
	@Before
	public void Setup() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		mockedBatchDAO = context.getBean("batchDAOImpl",BatchDAO.class);
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		mockedBatchDAO.setJdbcTemplate(mockedJdbcTemplate);
		
	}
	
	@Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BatchDAOImpl batchDAO;

    @Mock
    private DataSource dataSource;
	
	
	@Test
	public void testInsert_BatchGiven_VerifyInsertCalled() throws DuplicateEntryException {
		Batch batch = new Batch();
		Mockito.when(jdbcTemplate.queryForObject(anyString(), any(Class.class), any())).thenReturn(0);
		batchDAO.insert(batch);
		Mockito.verify(jdbcTemplate).queryForObject(anyString(), any(Class.class), any());
	}
	


	@Test(expected = DuplicateEntryException.class)
	public void testInsert_DuplicateBatchGiven_ThrowsDuplicateEntryException() throws DuplicateEntryException {
		Batch batch = new Batch();
		String countSql = "SELECT count(*) FROM batches where name=?";
		Mockito.when(jdbcTemplate.queryForObject(anyString(), any(Class.class), any())).thenReturn(1);
		batchDAO.insert(batch);
		Mockito.verify(jdbcTemplate).queryForObject(anyString(), any(Class.class), any());
	}
	
	

    @Test(expected = DuplicateEntryException.class)
    public void testaddBatch_DuplicateBatchGiven_ThrowsDuplicateEntryException() throws DuplicateEntryException {
        Batch batch = new Batch();
        batch.setName("batch");
        batch.setDescription("Desc");
        batch.setCreatedDate(new Date());
        Mockito.when(jdbcTemplate.queryForObject(anyString(), any(), Mockito.eq(Integer.class))).thenReturn(1);
        int result = batchDAO.addBatch(batch);
        Assert.assertEquals(1, result);

    }

    @Test(expected = InvalidIdException.class)
    public void testGetById_GivenInvalidId_ThrowException() throws InvalidIdException {
        batchDAO.findById(-1);
    }

    @Test
    public void testGetById_GivenValidId_ReturnBatch() throws InvalidIdException {
		Batch mockedBatch = new Batch();
		mockedBatch.setName("Moline");
		String sql = "SELECT * FROM batches WHERE id=?;";
		Mockito.when(jdbcTemplate.queryForObject(sql, batchDAO.getBatchRowMapper(), 999)).thenReturn(mockedBatch);
		assertEquals(batchDAO.findById(999).getName(), mockedBatch.getName());
	}

    
    
    @Test
	public void testList_NoInputGiven_ShouldReturnListOfBatches() {
		List<Batch> mockedList = new ArrayList<Batch>();
		Batch batch = new Batch();
		batch.setName("June_2018");
		mockedList.add(batch);
		
		String sql = "SELECT * FROM batches";
		Mockito.when(jdbcTemplate.query(sql,batchDAO.getBatchRowMapper())).thenReturn(mockedList);


		List<Batch> batchList = batchDAO.list();
		assertEquals(batchList,mockedList);
	}


}
