package com.yash.ota.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;

import com.yash.ota.rowmapper.TechnologyRowMapper;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.TechnologyDAO;
import com.yash.ota.domain.Technology;
import com.yash.ota.util.Queries;

import java.util.ArrayList;
import java.util.List;

public class TechnologyDAOImplTest {

	@Mock
	private TechnologyDAO technologyDAO;
	private ApplicationContext context;
	@Mock
	private JdbcTemplate mockedJdbcTemplate;


	@Before
	public void Setup() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		technologyDAO = context.getBean("technologyDAOImpl", TechnologyDAO.class);
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		technologyDAO.setJdbcTemplate(mockedJdbcTemplate);
	}

	@Test
	public void testFindById_GivenId_ShouldReturnUser() {
		Technology mockedTech = new Technology();

		String sql = Queries.FIND_TECHNOLOGY_BY_ID;
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, technologyDAO.getTechnologyRowMapper(),101)).thenReturn(mockedTech);

		Technology tech = technologyDAO.findById(101);
		assertEquals(mockedTech, tech);

	}

	/**
	 * Author: Madhuri Vutukury
	 */
	@Test
	public void findAll_ShouldReturnListOfTechnologies() {
		Technology technology = new Technology();

		List<Technology> technologyList = new ArrayList<Technology>();
		technology.setName("Java");
		technology.setId(101);
		technology.setCreatedBy(1);
		technology.setModifiedBy(1);


		technologyList.add(technology);
		Mockito.when(mockedJdbcTemplate.query(anyString(), (TechnologyRowMapper) anyObject())).thenReturn(technologyList);
		int result = technologyDAO.findAll().size();
		Assert.assertEquals(technologyList.size(), result);


	}
		
	/**
	 * @author phoutsaksit.keomala
	 */
	@Test
	public void testGetById_TechnologyIdGiven_ShouldReturnTechnology() {
		Technology mockTech = new Technology();
		Mockito.when(mockedJdbcTemplate.queryForObject(Queries.FIND_TECHNOLOGY_BY_ID, technologyDAO.getTechnologyRowMapper(), 1)).thenReturn(mockTech);
		
		Technology tech = technologyDAO.findById(1);
		assertEquals(mockTech,tech);
	}
	
	/**
	 * @author phoutsaksit.keomala
	 */
	@Test
	public void testTechnologyList_NoInputGiven_ShouldReturnListOfTechnologies() {
		List<Technology> mockTechList = new ArrayList<>();
		mockTechList.add(new Technology());
		String sql = "SELECT * FROM technologies";

		Mockito.when(mockedJdbcTemplate.query(sql, technologyDAO.getTechnologyRowMapper())).thenReturn(mockTechList);
		List<Technology> techList = technologyDAO.findAll();

		assertEquals(mockTechList, techList);
	}
}
