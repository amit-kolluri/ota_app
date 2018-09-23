package com.yash.ota.util;

public class Queries {

    public static final String GET_RESULTS_FOR_USER = "select * from results R inner join time_taken on time_taken.result_id=R.id where R.user_id=?";

    public static final String FIND_TOPIC = "Select * from topics where id=?";

    public static final String FIND_TEST_BY_ID = "SELECT * FROM tests WHERE id=?";

    public static final String FIND_TIME_TAKEN_BY_ID = "SELECT * FROM time_taken WHERE id=?";

    public static final String FIND_RESULT_BY_TEST_ID_AND_USER_ID = "SELECT * FROM results WHERE test_id=? and user_id=? limit 1";

    public static final String FIND_TIME_TAKEN_BY_TEST_ID_AND_USER_ID = "SELECT * FROM time_taken WHERE test_id=? and user_id=? limit 1";

    public static final String FIND_TECHNOLOGY_BY_ID = "Select * from technologies where id=?";

    public static final String FIND_TRAINEES = "SELECT * FROM users U WHERE U.role_id=1 AND NOT U.status_id = 3 ORDER BY created_date;";
    
    public static final String FIND_ASSIGNED_TESTS_FOR_USER = "select tests.*, count(*) as number_of_questions,topics.name,status.status, "
    		+ "technologies.name as technology_name from tests "
    		+ "inner join test_details on test_details.test_id=tests.id "
    		+ "inner join questions on test_details.question_id=questions.id "
    		+ "inner join topics on tests.topic_id=topics.id "
    		+ "inner join technologies on topics.technology_id=technologies.id "
    		+ "inner join status on status.id=tests.status_id "
    		+ "where tests.status_id=1 and "
    		+ "tests.id not in (select R.test_id from results R inner join users U on R.user_id=U.id where U.id=?) "
    		+ "group by tests.id;";

    public static final String FIND_ALL_QUESTIONS_BASED_ON_TEST_ID = "select Q.correct_answer,Q.question,Q.id,Q.option_1,Q.option_2,Q.option_3,Q.option_4,T.test_id from questions Q inner join test_details T on Q.id=T.question_id where T.test_id = ? order by Q.id";

    public static final String FIND_ASSIGNED_TEST = "select tests.*, count(*) as number_of_questions,topics.name, "
    		+ "technologies.name as technology_name from tests "
			+ "inner join test_details on test_details.test_id=tests.id "
			+ "inner join questions on test_details.question_id=questions.id "
			+ "inner join topics on tests.topic_id=topics.id "
    		+ "inner join technologies on topics.technology_id=technologies.id "
			+ "inner join status on status.id=tests.status_id "
			+ "where tests.status_id=1 and tests.id = ? group by tests.id;";

    public static final String BATCH_REPORT = "SELECT users.first_name,"
            + " avg(obtained_marks/total_marks)*100 as marks"
            + " FROM results"
            + " left join users on users.id = results.user_id"
            + " left join batches on batches.id = users.batch_id"
            + " where batch_id = ? "
            + " group by results.user_id";

    public static final String UPDATE_USER = "UPDATE users SET "
			+ "first_name= ?, "
			+ "last_name= ?, "
			+ "email= ?, "
			+ "user_name= ?, "
			+ "password= ?, "
			+ "phone_number= ?, "
			+ "batch_id= ?, "
			+ "role_id= ?, "
			+ "status_id= ?, "
			+ "created_by= ?, "
			+ "created_date= ?, "
			+ "modified_by= ?, "
			+ "modified_date= ? "
			+ "WHERE id= ?";
    
    public static final String INSERT_IMAGE = "INSERT INTO user_images (user_image, user_id) VALUES (?, ?)";
    
    public static final String UPDATE_IMAGE = "UPDATE user_images SET user_image = ?, user_id = ? WHERE user_id = ?";
			
    public static final String LOGIN_QUERY = "SELECT * FROM users WHERE email=? AND password=?";
    public static final String FIND_TESTS_FOR_EACH_TOPIC = "select tests.*,topics.name, " +
            "(select count(*) from test_details where tests.id = test_details.test_id) " +
            "as number_of_questions from tests inner join topics on tests.topic_id=topics.id";

	public static final String FIND_RESULT_GET_RESULT_ID_BY_TRAINEE_ID_AND_TECH_ID = "SELECT " +
			"R.id as Result_Id, " +
			"Topic.name as Topic_Name, " +
			"(R.obtained_marks/R.total_marks)*100 as Percentage_Score " +
			"from results R " +
			"inner join technologies Tech on R.technology_id=Tech.id " +
			"inner join status S on R.status_id=S.id " +
			"inner join topics Topic on R.topic_id=Topic.id " +
			"where " +
			"R.status_id=1 and R.user_id= ? and R.technology_id=?";

    public static final String GET_ALL_CREATORS = "SELECT * FROM users WHERE users.created_by = 1";

    public static final String GET_CREATOR_IMAGE = "SELECT * FROM user_images WHERE user_images.user_id = ?";

    public static final String GET_TRAINEE_REPORT_ITEMS_BY_TECH_AND_USER = "SELECT (results.obtained_marks/results.total_marks)*100 as score, "
    		+ "topics.name as test, "
			+ "AVG(results.obtained_marks/results.total_marks)*100 as average " + "FROM results "
			+ "INNER JOIN topics ON topics.id = results.topic_id "
			+ "WHERE results.technology_id = ? AND results.user_id = ? "
			+ "GROUP BY topics.name";

    public static final String GET_ALL_TECH = "SELECT * FROM technologies";

    public static final String GET_TECH_FOR_USER = "SELECT technologies.* FROM technologies "
			+ "INNER JOIN results ON results.technology_id = technologies.id "
			+ "INNER JOIN users ON users.id = results.user_id "
			+ "WHERE users.id = ? "
			+ "GROUP BY technologies.id";

    public static final String INSERT_NEW_RESULT = "INSERT INTO results (user_id, test_id, topic_id, technology_id, "
				+ "total_marks, obtained_marks, status_id, created_by, created_date, modified_by, modified_date)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?)";

	public static final String FIND_ALL_RESULTS = "SELECT * FROM results";

	public static final String FIND_RESULT_BY_ID = "SELECT * FROM results WHERE id=?";

	public static final String REMOVE_FROM_RESULT_BY_ID = "DELETE FROM results WHERE id=?";

	public static final String INSERT_NEW_TIME_TAKEN = "INSERT INTO time_taken (test_id, user_id, result_id, time_taken) VALUES(?,?,?,?)";

	public static final String UPDATE_RESULT = "UPDATE results SET "
				+ "("
				+ "user_id = ?, "
				+ "test_id = ?, "
				+ "topic_id = ?, "
				+ "technology_id = ?, "
				+ "total_marks = ?, "
				+ "obtained_marks = ?, "
				+ "status_id = ?, "
				+ "created_by = ?, "
				+ "created_date = ?, "
				+ "modified_by = ?, "
				+ "modified_date = ? ) "
				+ "WHERE id=?";

	public static final String GET_ALL_TOPICS_BY_TECH_ID = "SELECT * FROM topics WHERE topics.technology_id = ?";

	public static final String GET_TESTS_BY_TOPIC_ID = "SELECT tests.*, topics.name FROM tests "
			+ "INNER JOIN topics "
			+ "ON tests.topic_id = topics.id "
			+ "WHERE tests.topic_id = ?";

	public static final String FIND_RESULT_BY_TRAINEE_ID_AND_TECH_ID = "SELECT " +
			"Tech.id as Technology_Id, " +
			"Topic.name as Topic_Name, " +
			"(R.obtained_marks/R.total_marks)*100 as Percentage_Score " +
			"from results R " +
			"inner join technologies Tech on R.technology_id=Tech.id " +
			"inner join status S on R.status_id=S.id " +
			"inner join topics Topic on R.topic_id=Topic.id " +
			"where " +
			"R.status_id=1 and R.user_id= ? and R.technology_id=?";

	public static final String FIND_ALL_TOPICS = "SELECT * FROM topics";

	public static final String FIND_RESULT_BY_BATCH_ID_AND_TECH_ID = "SELECT " +
			"       b.id as batch_Id," +
			"       Tech.name as Technology_Name," +
			"       avg(R.obtained_marks / R.total_marks * 100) as Average_Marks" +
			"from results R" +
			"       inner join technologies Tech on R.technology_id = Tech.id" +
			"       inner join users u on R.user_id = u.id" +
			"       inner join batches b on u.batch_id = b.id" +
			"where R.status_id = 1 and b.id = ? " +
			"group by R.technology_id, b.id;";
	
	public static final String FIND_AVG_TECH_MARKS_BY_USER_ID = "SELECT " + 
			"Tech.id as Technology_Id, " +
			"Tech.name as Technology_Name, " + 
			"avg(R.obtained_marks/R.total_marks*100) as Average_Marks " +  
			"from results R " + 
			"inner join technologies Tech on R.technology_id=Tech.id " + 
			"inner join status S on R.status_id=S.id " + 
			"where " + 
			"R.status_id=1 and R.user_id= ? " + 
			"group by R.technology_id";

	public static final String GET_ALL_TESTS = "SELECT tests.*, topics.name FROM tests INNER JOIN topics ON tests.topic_id = topics.id";
	
}
