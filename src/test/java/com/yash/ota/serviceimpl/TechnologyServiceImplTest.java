package com.yash.ota.serviceimpl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.TechnologyDAO;
import com.yash.ota.domain.Technology;
import com.yash.ota.rowmapper.TechnologyRowMapper;
import com.yash.ota.service.TechnologyService;
import com.yash.ota.util.Queries;

import com.yash.ota.service.TechnologyService;

@RunWith(MockitoJUnitRunner.class)
public class TechnologyServiceImplTest {

	@InjectMocks
    private TechnologyService technologyService = new TechnologyServiceImpl();
	
	@Mock
	private TechnologyDAO technologyDAO;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private TechnologyRowMapper technologyRowMapper;
    
	private List<Technology> mockTechList = new ArrayList<>();
	private Technology mockTech = new Technology();


	private TechnologyService techService;
	private ApplicationContext context;
	private TechnologyDAO mockedDAO;
	private JdbcTemplate mockedJdbcTemplate;
	
	@Before
	public void SetUp() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		techService = context.getBean("technologyServiceImpl",TechnologyService.class);
		
		mockedDAO = Mockito.mock(TechnologyDAO.class);
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		mockedDAO.setJdbcTemplate(mockedJdbcTemplate);
		techService.setJdbcTemplate(mockedJdbcTemplate);
		techService.setDAO(mockedDAO);
	}
	
	@Test
	public void testFindById_GivenId_ShouldReturnUser() {
		Technology mockedTech = new Technology();
		Mockito.when(mockedDAO.findById(101)).thenReturn(mockedTech);

		Technology tech = techService.getTechnologyById(101);

		assertEquals(mockedTech, tech);
	}


	/**
	 * @author phoutsaksit.keomala
	 */
	@Test
	public void testListAllTech_UserIdGiven_ShouldReturnMockList() {
		mockTech.setName("mockTech");
		mockTechList.add(mockTech);
		
		Mockito.when(technologyDAO.findAll()).thenReturn(mockTechList);
		List<Technology> techList = technologyService.getTechnologies();
		assertEquals(mockTechList, techList);
	}
	
	/**
	 * @author phoutsaksit.keomala
	 */
	@Test
	public void testListTechForUser_UserIdGiven_ShouldReturnMockList() {
		Mockito.when(jdbcTemplate.query(Queries.GET_TECH_FOR_USER, technologyRowMapper, 101)).thenReturn(mockTechList);
		List<Technology> techList = technologyService.listTechForUser(101);
		assertEquals(mockTechList,techList);
	}
}
