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

import com.yash.ota.dao.UserDAO;
import com.yash.ota.domain.User;
import com.yash.ota.exception.DuplicateUserException;
import com.yash.ota.exception.UserAuthenticationException;
import com.yash.ota.rowmapper.UserRowMapper;
import com.yash.ota.service.UserService;
import com.yash.ota.util.Queries;

/**
 * This class is for testing the UserServiceImpl
 * 
 * This file is for reference only. Do not edit or delete this code!
 * 
 * @author karl.roth
 */
public class UserServiceImplTest {

	private UserService userService;
	private ApplicationContext context;
	private UserDAO mockedDAO;
	private JdbcTemplate mockedJdbcTemplate;

	@Before
	public void Setup() {
	context = new ClassPathXmlApplicationContext("resources/dispatcher-servlet.xml");
		userService = context.getBean("userServiceImpl", UserService.class);

		mockedDAO = Mockito.mock(UserDAO.class);
		mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);

		mockedDAO.setJdbcTemplate(mockedJdbcTemplate);
		userService.setJdbcTemplate(mockedJdbcTemplate);
		userService.setDAO(mockedDAO);
	}

	@Test
	public void testLogin_CorrectLoginGiven_ShouldReturnUserWithId105() throws Exception {
		String email = "karl.test@yash.com";
		String password = "GoodP@ss";
		int id = 105;

		User mockedUser = new User();
		mockedUser.setId(id);

		String sql = Queries.LOGIN_QUERY;
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, userService.getUserRowMapper(), email, password))
				.thenReturn(mockedUser);

		User user = userService.login(email, password);
		assertEquals(user.getId(), mockedUser.getId());
	}

	@Test(expected = UserAuthenticationException.class)
	public void testLogin_IncorrectLoginGiven_ShouldThrow() throws Exception {
		String email = "whitney.test@yash.com";
		String password = "NoMatch";
		int id = 105;

		User mockedUser = new User();
		mockedUser.setId(id);

		String sql = Queries.LOGIN_QUERY;
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, userService.getUserRowMapper(), email, password))
				.thenReturn(null);

		userService.login(email, password);
	}

	@Test
	public void testRegister_UserTestGiven_ShouldCallInsertInDAO() throws Exception {
		User user = new User();
		user.setEmail("karl.test@yash.com");
		user.setPassword("test1234");
		user.setBatchId(1);

		String sql = "select count(*) from users where email = ?";
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, new Object[] { user.getEmail() }, Integer.class))
				.thenReturn(0);

		userService.register(user);
		Mockito.verify(mockedDAO).insert(user);
	}

	@Test
	public void testGetAllTrainees_NoInputGiven_ShouldReturnTrue() {
		List<User> mockedList = new ArrayList<User>();
		User mockedUser = new User();
		int roleId = 1;
		mockedUser.setRoleId(roleId);
		mockedList.add(mockedUser);
		mockedList.add(mockedUser);

		String sql = Queries.FIND_TRAINEES;
		Mockito.when(mockedJdbcTemplate.query(sql, userService.getUserRowMapper())).thenReturn(mockedList);

		List<User> traineeList = userService.getAllTrainees();

		assertEquals(traineeList, mockedList);
	}

	@Test
	public void testDelete_UserIdGiven_ShouldDeleteUser() {
		int userId = 1;
		userService.deleteUser(userId);
		Mockito.verify(mockedDAO).delete(userId);
	}

	@Test(expected = DuplicateUserException.class)
	public void testValidateUser_GivenDuplicateEmail_ShouldThrow() throws Exception {
		String email = "wfout@yash.com";
		User user = new User();
		user.setEmail(email);

		String sql = "select count(*) from users where email = ?";
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class)).thenReturn(1);
		User user2 = new User();
		user2.setEmail(email);
		userService.register(user2);

	}


    @Test
    public void testGetTraineesByBatchName_BatchNameGiven_ShouldReturnListOfTrainees() {
        int batchId = 1;
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.* FROM users u join batches b on u.batch_id = b.id join roles r on u.role_id = r.id where b.id = " + batchId + " and r.id = 1";
        Mockito.when(mockedJdbcTemplate.query(sql, mockedDAO.getUserRowMapper())).thenReturn(users);
        List<User> trainees = userService.getTraineesByBatchId(batchId);
        assertEquals(users, trainees);
    }

	@Test
	public void testGetTraineesByBatchName_IncorrectBatchNameGiven_ShouldGiveSizeOfRecordsAsZero(){
		int batchId = 123;
		List<User> users = new ArrayList<>();
		String sql = "SELECT u.* FROM users u join batches b on u.batch_id = b.id join roles r on u.role_id = r.id where b.id = "+batchId+" and r.id = 1";
		Mockito.when(mockedJdbcTemplate.query(sql, userService.getUserRowMapper())).thenReturn(users);
		assertEquals(users.size(), 0);
	}

	@Test
	public void getTotalTraineesByTrainer_UserTestGiven_ShouldReturnNumber() {
		User user = new User();
		user.setId(1);
		String sql = "select count(U.id) from users U" + 
				" inner join batches B on U.batch_id=B.id" + 
				" inner join roles R on U.role_id=R.id where U.role_id=1 and U.status_id=1 and R.created_by = ?";
		int total = 5;
		Mockito.when(mockedJdbcTemplate.queryForObject(sql, new Object[] { user.getId() }, Integer.class)).thenReturn(total);
		int output = userService.getTotalTraineesByTrainer(user);
		assertEquals(output, total);

	}

	public void testUpdateUser_UserIdGiven_ShouldReturnNothing() {
		User user = new User();
		userService.updateUser(user);
		Mockito.verify(mockedDAO).update(user);
 
}
		
	


	@Test
	public void testGetTraineesByBatchId_GivenValidId_ReturnUserList() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users WHERE batch_id=?";
		Mockito.when(mockedJdbcTemplate.query(sql, mockedDAO.getUserRowMapper())).thenReturn(users);


	}
	
	@Test
	public void testFindUserByEmail_UserEmailGiven_ShouldReturnSameUser() throws Exception {
		String email = "harish.arava@yash.com";
		User mockedUser = new User();
		mockedUser.setEmail(email);
		
		String sql = "Select * from users where email = ? ";
		Mockito.when(mockedJdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(UserRowMapper.class), Mockito.anyString())).thenReturn(mockedUser);
	
		User user = userService.findUserByEmail(email);
		assertEquals(user.getEmail(), mockedUser.getEmail());
	}
	
	@Test
	public void testResetPassword_UserPasswordGiven_ShouldReturnPassword() throws Exception {
		
		String password = "123456789";
		int id = 104;
	
		User mockedUser = new User();
		mockedUser.setPassword(password);
		mockedUser.setId(104);
		
		String sql = "UPDATE users SET password=? WHERE id=?";
			
		userService.resetPassword(password, id);
		Mockito.verify(mockedJdbcTemplate).update(sql, password , id);
		
	}

	@Test
	public void testGetAwaitingUsersList_givenStatusIdAndRoleId_ReturnsTraineesListWhoNeedApproval(){
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users where status_id=3 and role_id=1";
		Mockito.when(mockedJdbcTemplate.query(sql,mockedDAO.getUserRowMapper())).thenReturn(users);
		List<User> waitingApprovalList = userService.getAwaitingUsersList();
		assertEquals(users, waitingApprovalList);
	}

	@Test
	public void testApproveUser_givenTraineeId_ShouldUpdateStatusIdOfTrainee(){
		int userId=101;
		User user = new User();
		user.setId(userId);

		Mockito.when(mockedDAO.findById(userId)).thenReturn(user);

		userService.approveUser(userId);

		Mockito.verify(mockedDAO).findById(userId);
		Mockito.verify(mockedDAO).update(user);
	}

}
