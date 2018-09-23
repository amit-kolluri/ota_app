package com.yash.ota.serviceimpl;

import com.yash.ota.domain.ReportItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import com.yash.ota.dao.ResultDAO;
import com.yash.ota.domain.Result;

import com.yash.ota.domain.User;
import com.yash.ota.service.ResultService;


import com.yash.ota.rowmapper.IndividualTraineeTechSpecificTestWiseExtractor;

import com.yash.ota.rowmapper.ResultRowMapper;
import com.yash.ota.service.ResultService;
import com.yash.ota.util.Queries;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTest {

    @InjectMocks
    private ResultService resultService = new ResultServiceImpl();

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ResultRowMapper resultRowMapper;

    @Mock
    private IndividualTraineeTechSpecificTestWiseExtractor testWiseExtractor;
    
    @Mock
    ResultDAO resultDAO;

    Result result = new Result();
    ReportItem item = new ReportItem();
    private List<ReportItem> items = new ArrayList<>();
    private List<Result> mockedResults = new ArrayList<Result>();
    
    @Before
    public void init() {
    	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetResultsByUserId_100Given_ShouldReturnListOfResults() {
        result.setId(100);
        result.setUserId(100);
        mockedResults.add(result);
        Mockito.when(jdbcTemplate.query(Queries.GET_RESULTS_FOR_USER, resultRowMapper, 100)).thenReturn(mockedResults);
        List<Result> results = resultService.getResultsByUserId(100);
        assertEquals(mockedResults.get(0).getId(),results.get(0).getId());
    }

    @Test
    public void testGetResultsById_WrongIdGiven_ShouldReturnNull() {
        result.setId(100);
        result.setUserId(105);
        mockedResults.add(result);
        Mockito.when(jdbcTemplate.query(Queries.GET_RESULTS_FOR_USER, resultRowMapper, 200)).thenReturn(null);
        List<Result> results = resultService.getResultsByUserId(200);
        assertEquals(results,null);
    }
    
    @Test
    public void testGetResultByTestIdAndUserId_100And100Given_ShouldReturnResult() {
    	result.setTestId(100);
    	result.setUserId(100);
		Mockito.when(jdbcTemplate.queryForObject(Queries.FIND_RESULT_BY_TEST_ID_AND_USER_ID, resultRowMapper, 100, 100)).thenReturn(result);
		assertEquals(result, resultService.getResultByTestIdAndUserId(100, 100));
    }
    
    @Test
    public void testGetResultByTestIdAndUserId_50000And60000Given_ShouldReturnResult() {
		assertEquals(null, resultService.getResultByTestIdAndUserId(50000, 60000));
    }

    /**
     * @author - Jay Shah
     */
    @Test
    public void testIndividualTraineeTechWiseSpecificTestReport_1000And50Given_ShouldReturnNull(){
        assertNull(resultService.individualTraineeTechwiseSpecificTestReport(1000, 50));
    }

    /**
     * @author - Jay Shah
     */
    @Test
    public void testIndividualTraineeTechWiseSpecificTestReport_101And6Given_ShouldReturnResult(){
        item.setId(2);
        items.add(item);
        Mockito.when(jdbcTemplate.query(Queries.FIND_RESULT_GET_RESULT_ID_BY_TRAINEE_ID_AND_TECH_ID, testWiseExtractor, 101, 6)).thenReturn(items);
        List<ReportItem> reportItems = resultService.individualTraineeTechwiseSpecificTestReport(101,6);
        assertEquals(items, reportItems);
    }


    /**
	 * tests service delete method
	 * 
	 * @author Zachary Karamanlis
	 */
	@Test
	public void testDelete_GivenResultId_AccessesDatabase() {
		resultService.delete(1);
		verify(resultDAO).delete(1);
	}
	
	/**
	 * Tests update status method of result service
	 * 
	 * @author Zachary Karamanlis
	 */
	@Test
	public void updateStatusTest_GivenStatusIdAndResultId_AccessesDatabase() {
		resultService.updateStatus(2, 1);
		verify(jdbcTemplate).update(anyString(), anyInt());
	}

     	
   /**
    * Used for testing Result Service Implementation
    * 
    * @author nick.parker
    */
    @Test
    public void testFindResultById_GivenId_ShouldCallDAOLayer() {
    		Result result = new Result();
    		result.setId(90);
    		resultService.findResultById(90);
    		Mockito.verify(resultDAO).findById(90);
    	}
    
    /**
     * Gets Total Number of Results by Trainer
     * @author Geon Gayles
     */
    @Test
	public void getTotalResultsByTrainer_UserTestGiven_ShouldReturnNumber() {
		User user = new User();
		user.setId(1);
		String sql = "select count(R.id) from results R " +
				"inner join users U on R.user_id = U.id and U.created_by = ?";
		int total = 5;
		Mockito.when(jdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, Integer.class)).thenReturn(total);
		int output = resultService.getTotalNumberOfResultsByTrainer(user);
		assertEquals(output, total);

	}
}
