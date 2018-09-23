package com.yash.ota.serviceimpl;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.BatchDAO;
import com.yash.ota.daoimpl.BatchDAOImpl;
import com.yash.ota.domain.Batch;
import com.yash.ota.domain.User;
import com.yash.ota.exception.DuplicateEntryException;
import com.yash.ota.exception.InvalidIdException;
import com.yash.ota.service.BatchService;

@RunWith(MockitoJUnitRunner.class)
public class BatchServiceImplTest {
	
	private ClassPathXmlApplicationContext context;
	private BatchDAO mockedDAO;
	private JdbcTemplate mockedJdbcTemplate;
	
	  @InjectMocks
	    private BatchServiceImpl batchService;


	    @Mock
	    private BatchDAOImpl batchDAO;
	    
	    @Mock
	    private JdbcTemplate jdbcTemplate;
	

	private BatchService mockedBatchService;

	
	@Before
	public void Setup() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		mockedBatchService = context.getBean("batchServiceImpl",BatchService.class);;
		
		mockedDAO = Mockito.mock(BatchDAO.class);
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		mockedDAO.setJdbcTemplate(mockedJdbcTemplate);
		batchService.setDAO(mockedDAO);
	}
	
	
	
	@Test
	public void testRegister_NothingGiven_ShouldReturnZero() {
		int res = mockedBatchService.countUsersInBatch(1004);
		assertEquals(res,0);
	}
	
	@Test
	public void testGetAllTrainees_NoInputGiven_ShouldReturnTrue() {
		List<Batch> mockedList = new ArrayList<Batch>();
		Mockito.when(mockedDAO.list()).thenReturn(mockedList);		
		List<Batch> batchList = batchService.listBatches();
		assertEquals(batchList, mockedList);
	}
	

	
//	@Test
//	public void testRegister_NothingGiven_ShouldReturnZero() {
//		int res = mockedBatchService.findUsersInBatch(1004);
//		assertEquals(res,0);
//	}


    @Test
    public void testaddBatch_BatchGiven_Returns1() throws DuplicateEntryException {
        Batch batch = new Batch();
        batch.setDescription("Hello");
        batchDAO.insert(batch);
        Mockito.verify(batchDAO).insert(batch);
    }
    
    /**
     * Gets Total Batches by Trainer
     * @author Geon Gayles
     */
    @Test
	public void getTotalBatchesByTrainer_UserTestGiven_ShouldReturnNumber() {
		User user = new User();
		user.setId(1);
		String sql = "select count(B.id) from batches B inner join users U on B.created_by = ? and U.role_id = 2;";
		
		int total = 3;
		Mockito.when(jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, Integer.class)).thenReturn(total);
		int output =  batchService.getTotalBatchesByTrainer(user);
		assertEquals(output, total);
	}
    /**
     * Gets Current Batche by Trainer
     * @author Geon Gayles
     */
	@Test
	public void getCurrentBatchByTrainer_UserTestGiven_ShouldReturnNumber() {
		User user = new User();
		user.setId(1);
		String sql = "select B.name from batches B\n" + 
				"inner join users U on B.id = U.batch_id and U.role_id = 2 and U.status_id = 1 and B.created_by = ?";
		String name = "June_2018";
		Mockito.when(jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, String.class)).thenReturn(name);
		String currentBatch =  batchService.getCurrentBatchByTrainer(user);
		assertEquals("June_2018", currentBatch);
	}


	
	
	
	
	
    @Test
    public void testaddBatch_BatchGiven_Returns() throws DuplicateEntryException {
        Batch batch = new Batch();
        batch.setDescription("Hello");
        batchDAO.insert(batch);
        Mockito.verify(batchDAO).insert(batch);
    }

    public void testGetBatchById_GivenValidId_FindByIdCalled() throws InvalidIdException {
		batchService.getBatchById(999);
		Mockito.verify(batchDAO).findById(999);
	}


	@Test
	public void testInsertBatch_BatchGiven_VerifyInsertIsCalled() throws DuplicateEntryException {
		Batch batch = new Batch();
		batch.setDescription("Hello");
		batchDAO.insert(batch);
		Mockito.verify(batchDAO).insert(batch);
	}
	
	@Test
	public void testFindUsersAll_BatchGiven_ShouldReturnNumberofUsers() throws DuplicateEntryException {
		List<Integer> mockedList = new ArrayList<Integer>();
		mockedList.add(30);
		
		String sql = "select count(*) as number_of_trainees_in_batch\r\n" + 
				"from users U\r\n" + 
				"inner join batches B on U.batch_id=B.id\r\n" + 
				"group by U.batch_id;";
		
		Mockito.when(jdbcTemplate.queryForList(sql,Integer.class)).thenReturn(mockedList);
		
		List<Integer> countList = batchService.countUsersForEachBatch();
		assertEquals(countList,mockedList);
	}
	
	@Test
	public void testFindStatusAll_BatchGiven_ShouldReturnStatusOfBatches() {
		List<Integer> mockedList = new ArrayList<Integer>();
		mockedList.add(1);
		
		String sql ="select status_id from batches order by id";
		Mockito.when(jdbcTemplate.queryForList(sql,Integer.class)).thenReturn(mockedList);
		
		List<Integer> statusList = batchService.listStatusForAllBatches();
		assertEquals(statusList,mockedList);
	}
	
	@Test
	public void testFindIdAll_BatchGiven_ShouldReturnIdOfBatches() {
		List<Integer> mockedList = new ArrayList<Integer>();
		mockedList.add(1001);
		
		String sql ="select id from batches";
		Mockito.when(jdbcTemplate.queryForList(sql,Integer.class)).thenReturn(mockedList);
		
		List<Integer> idList = batchService.listIdForAllBatches();
		assertEquals(idList,mockedList);
	}
	
	@Test
	public void testNamesAll_BatchGiven_ShouldReturnNamesOfBatches() {
		List<String> mockedList = new ArrayList<String>();
		mockedList.add("TESTBATCH");
		
		String sql ="select name from batches";
		Mockito.when(jdbcTemplate.queryForList(sql,String.class)).thenReturn(mockedList);
		
		List<String> nameList = batchService.listNamesOfAllBatches();
		assertEquals(nameList,mockedList);
	}

	@Test
	public void testCountUsersInBatch_NothingGiven_ShouldReturnZero() {
		Mockito.when(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE batch_id = ?", 
				new Object[] {1004}, Integer.class)).thenReturn(0);
		int res = batchService.countUsersInBatch(1004);
		assertEquals(res,0);
	}

}

