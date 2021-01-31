package fr.epita.services.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.datamodel.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestSpringIntegration {
	
	@Inject
	@Named("questionSample")
	Question qSample;
	
	
	@Inject
	@Named("datasourceH2")
//	@Named("datasourcePGSQL")
	DataSource ds;
//	@Inject
//	DataSource ds;
	
	@Inject
	private String injectedString;
	
	@Test
	public void testStringFromSpring(){
		Assert.assertNotNull(injectedString);
		System.out.println(injectedString);
	}
	
	@Test
	public void testDS() throws SQLException {
		
		Assert.assertNotNull(ds);
		Connection connection = ds.getConnection();
		
		String schema = connection.getSchema();
		Assert.assertEquals("PUBLIC", schema);
		System.out.println(schema);
		
	}
	
	@Test
	public void testSimpleDataModelInjection() {
		Question question = new Question();
		question.setQuestionTitle("What is Java?");
		Assert.assertEquals(question.toString(), qSample.toString());
		System.out.println("original handmade question : " + question);
		System.out.println("injected question (spring) : " + question);
	}
	
	
	// Convert QuestionDAO from last semester to a "spring-enabled" DAO
	@Test
	public void TestQuestionDAO() throws SQLException {
		String SQL_QUERY = "INSERT INTO QUESTION(SUBJECT, TOPICS, DIFFICULTY) VALUES(?, ?, ?);";
		Connection connection = ds.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
		pstmt.setString(1, qSample.getQuestionTitle());
		String arrayAsString = String.valueOf(qSample.getTopics());
		pstmt.setString(2, arrayAsString);
		pstmt.setInt(3, qSample.getDifficulty());
		pstmt.execute();
			
	}

}
