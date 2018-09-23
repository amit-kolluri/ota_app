package com.yash.ota.serviceimpl;

import com.yash.ota.dao.TimeTakenDAO;
import com.yash.ota.domain.TimeTaken;
import com.yash.ota.rowmapper.TimeTakenRowMapper;
import com.yash.ota.service.TimeTakenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TimeTakenServiceImplTest {

    @InjectMocks
    private TimeTakenService timeTakenService = new TimeTakenServiceImpl();

    @Mock
    private TimeTakenDAO timeTakenDAO;

    private TimeTaken timeTaken = new TimeTaken();

	private ApplicationContext context;
	private JdbcTemplate mockedJdbcTemplate;

	@Before
	public void Setup() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		timeTakenService = context.getBean("timeTakenServiceImpl", TimeTakenService.class);

		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		timeTakenDAO = Mockito.mock(TimeTakenDAO.class);
		timeTakenDAO.setJdbcTemplate(mockedJdbcTemplate);
		timeTakenService.setTimeTakenDAO(timeTakenDAO);
		timeTakenService.setJdbcTemplate(mockedJdbcTemplate);
	}
    
    @Test
    public void timeTakenGetTimeTakenByTimeTakenIdAndUserId_100And100Given_ShouldReturnTimeTaken() throws Exception {
    	timeTaken.setTestId(100);
    	timeTaken.setUserId(100);
//    	String timeTakenSQL = "SELECT * FROM time_taken WHERE test_id=? and id=?";
		Mockito.when(mockedJdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(TimeTakenRowMapper.class), Mockito.eq(100), Mockito.eq(100))).thenReturn(timeTaken);
		assertEquals(timeTaken, timeTakenService.getTimeTakenByTestIdAndUserId(100, 100));
    }
    
    @Test
    public void timeTakenGetTimeTakenByTimeTakenIdAndUserId_50000And60000Given_ShouldReturnTimeTaken() throws Exception {
		assertEquals(null, timeTakenService.getTimeTakenByTestIdAndUserId(50000, 60000));
    }

}
