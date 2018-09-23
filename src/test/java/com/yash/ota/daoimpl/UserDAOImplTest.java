package com.yash.ota.daoimpl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.ota.dao.UserDAO;
import com.yash.ota.domain.User;
import com.yash.ota.util.Queries;

/**
 * This class is for testing the UserDAOImpl.
 * 
 * This file is only for reference only. Do not edit, or delete this code!
 * 
 * @author karl.roth, nipun.dayanath
 */
public class UserDAOImplTest extends UserDAOImpl {

	private UserDAO userDAO;
	private ApplicationContext context;
	private JdbcTemplate mockedJdbcTemplate;

	@Before
	public void Setup() {
		context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		userDAO = context.getBean("userDAOImpl",UserDAO.class);
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);
		userDAO.setJdbcTemplate(mockedJdbcTemplate);

	}

	@Test
	public void testInsert_UserGiven_ShouldInsertIntoDatabase() {
		User user = new User();
		user.setEmail("karl.test@yash.com");
		user.setPassword("test1234");
		user.setBatchId(1);
		
		String sql = "INSERT INTO users (email,password,batch_id) values(?,?,?)";
		Object[] params = 
				new Object[] { 
						user.getEmail(),
						user.getPassword(), 
						user.getBatchId()
				};
		userDAO.insert(user);
		Mockito.verify(mockedJdbcTemplate).update(sql, params);

	}

	@Test
	public void testFindById_Id105Given_ShouldReturnUserKarl() {
		User mockedUser = new User();
		mockedUser.setFirstName("Karl");

		String sql = "SELECT * FROM users WHERE id=?";
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, userDAO.getUserRowMapper(), 105)).thenReturn(mockedUser);

		User user = userDAO.findById(105);
		assertEquals(user, mockedUser);
	}

	@Test
	public void testList_NoInputGiven_ShouldReturnListOfUsers() {
		List<User> mockedList = new ArrayList<User>();
		User user = new User();
		user.setFirstName("Karl");
		mockedList.add(user);

		String sql = "SELECT * FROM users";
		Mockito.when(mockedJdbcTemplate.query(sql, userDAO.getUserRowMapper())).thenReturn(mockedList);

		List<User> userList = userDAO.list();
		assertEquals(userList, mockedList);

	}

	
	@Test 
	public void testUpdate_UserGiven_ShouldUpdate() {
		User user = new User();
		user.setId(104);
		String sql = Queries.UPDATE_USER;
		Object[] params = 
				new Object[] {
						user.getFirstName(),
						user.getLastName(),
						user.getEmail(),
						user.getUserName(),
						user.getPassword(),
						user.getPhoneNumber(),
						user.getBatchId(),
						user.getRoleId(),
						user.getStatusId(),
						user.getCreatedBy(),
						user.getCreatedDate(),
						user.getModifiedBy(),
						user.getModifiedDate(),
						user.getId()
				};
		userDAO.update(user);

		Mockito.verify(mockedJdbcTemplate).update(sql,params);
	}
		


	@Test
	public void testUpdate_UserGiven_ShouldUpdateInDatabase() {
		User user = new User();
		user.setId(101);

		String sql = Queries.UPDATE_USER;
		
		Object[] params = new Object[] { user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserName(),
				user.getPassword(), user.getPhoneNumber(), user.getBatchId(), user.getRoleId(), user.getStatusId(),
				user.getCreatedBy(), user.getCreatedDate(), user.getModifiedBy(), user.getModifiedDate(),user.getId() };

		userDAO.update(user);
		Mockito.verify(mockedJdbcTemplate).update(sql,params);
	}

	@Test
	public void testDelete_UserIdGiven_ShouldDeleteFromDatabase() {
		String sql = "DELETE FROM users WHERE id=?";
		userDAO.delete(101);
		Mockito.verify(mockedJdbcTemplate).update(sql, 101);
	}

	/**
	 * @author phoutsaksit.keomala
	 */
	@Test
	public void testTraineeList_GivenDatabase_ShouldReturnListOfTrainees() {
		List<User> mockTraineeList = new ArrayList<>();
		mockTraineeList.add(new User());
		String sql = "SELECT * FROM users";

		Mockito.when(mockedJdbcTemplate.query(sql, userDAO.getUserRowMapper())).thenReturn(mockTraineeList);
		List<User> traineeList = userDAO.list();

		assertEquals(mockTraineeList, traineeList);
	}

}