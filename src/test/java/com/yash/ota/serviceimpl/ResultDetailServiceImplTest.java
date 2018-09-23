package com.yash.ota.serviceimpl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.domain.ResultDetail;
import com.yash.ota.rowmapper.ResultDetailRowMapper;
import com.yash.ota.service.ResultDetailService;

/**
 * Used for testing Result Detail Service Implementation
 * 
 * @author nick.parker
 */
public class ResultDetailServiceImplTest {

	private ResultDetailService resultDetailService;
	private ApplicationContext applicationContext;
	private JdbcTemplate mockedJdbcTemplate;
	
	
	@Before
	public void Setup() {
		applicationContext = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		resultDetailService = applicationContext.getBean("resultDetailServiceImpl", ResultDetailService.class);
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		resultDetailService.setJdbcTemplate(mockedJdbcTemplate);
		
	}
	
	@Test
	public void testgetResultDetails_GivenResultId1_ShouldReturnListOfDetails() {
		List<ResultDetail> resultDetails = new ArrayList();
		ResultDetail resultDetail = new ResultDetail();
		resultDetail.setQuestion("Which ground breaking Java API was invented by Bob Ross?");
		resultDetails.add(resultDetail);
		String sql = "select *" + 
				"from questions Q\r\n" + 
				"inner join result_details RD on RD.question_id=Q.id\r\n" + 
				"inner join users U on RD.user_id=U.id\r\n" + 
				"where RD.result_id=?";
		Mockito.when(mockedJdbcTemplate.query(sql, resultDetailService.getResultDetailRowMapper(), 55)).thenReturn(resultDetails);
		List<ResultDetail> testResultDetails = resultDetailService.getResultDetails(55);
		assertEquals(resultDetails, testResultDetails);
	}
	
}
