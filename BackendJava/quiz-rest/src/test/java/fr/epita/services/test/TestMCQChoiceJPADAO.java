package fr.epita.services.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

import fr.epita.datamodel.MCQChoice;
import fr.epita.datamodel.Question;
import fr.epita.exception.CreationFailedException;
import fr.epita.services.MCQChoiceJPADAO;
import fr.epita.services.QuestionJPADAO;
import fr.epita.services.QuizCreationDataService;
import fr.epita.services.QuizReportingDataService;
//import fr.epita.services.dto.QuestionDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestMCQChoiceJPADAO {
	
	//private static final Logger LOGGER = LogManager.getLogger(TestMCQChoiceJPADAO.class);
	
	@Inject
	MCQChoiceJPADAO dao;
	@Inject
	QuestionJPADAO questionDao;
	
	@Inject
	QuizCreationDataService creationDataService;
	
	@Inject
	QuizReportingDataService reportingService;
	
	
	@Inject
	@Named("datasourceH2")
	DataSource ds;
	

	@Test
	public void testCreation() {
		Question question = new Question();
		question.setQuestionTitle("What is Java ?");
		
		MCQChoice choice = new MCQChoice();
		choice.setValid(true);
		choice.setChoice("an indonesian island?");
		
		MCQChoice choice2= new MCQChoice();
		choice2.setValid(true);
		choice2.setChoice("a programming language?");
		
		MCQChoice choice3= new MCQChoice();
		choice3.setValid(true);
		choice3.setChoice("a term that designs coffee");
		
		Question question2 = new Question();
		question2.setQuestionTitle("What is Python ?");
		
		MCQChoice choice2_1 = new MCQChoice();
		choice2_1.setValid(true);
		choice2_1.setChoice("a sort of snake?");
		
		MCQChoice choice2_2= new MCQChoice();
		choice2_2.setValid(true);
		choice2_2.setChoice("a programming language?");
		
		MCQChoice choice2_3= new MCQChoice();
		choice2_3.setValid(true);
		choice2_3.setChoice("none of the other choices");
		
		
		
//		//when
//		try {
//			
//			//creationDataService.createMCQQuestion(question, Arrays.asList(choice, choice2, choice3));
//			//screationDataService.createMCQQuestion(question2, Arrays.asList(choice2_1, choice2_2, choice2_3));
//		
//		} catch (CreationFailedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

		// then
		try (Connection connection = ds.getConnection();
				PreparedStatement stmt = connection
						.prepareStatement("select choice from MCQCHOICES where choice = ?")) {
			stmt.setString(1,choice.getChoice() );
			ResultSet rs = stmt.executeQuery();
			int i = 0; 
			while (rs.next()) {
				System.out.println(rs.getString("choice"));
				i++;
			}
			Assert.assertNotEquals(0, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
//	@Test
//	public void testSearchAll() throws JsonProcessingException {
//		
//		LOGGER.info("entering the TestSearchAll method");
//		Question question = new Question();
//		question.setQuestionTitle("What is Java ?");
//		
//		MCQChoice choice = new MCQChoice();
//		choice.setValid(true);
//		choice.setChoice("an indonesian island?");
//		
//		MCQChoice choice2= new MCQChoice();
//		choice2.setValid(true);
//		choice2.setChoice("a programming language?");
//		
//		MCQChoice choice3= new MCQChoice();
//		choice3.setValid(true);
//		choice3.setChoice("a term that designs coffee");
//		
//		Question question2 = new Question();
//		question2.setQuestionTitle("What is Python ?");
//		
//		MCQChoice choice2_1 = new MCQChoice();
//		choice2_1.setValid(true);
//		choice2_1.setChoice("a sort of snake?");
//		
//		MCQChoice choice2_2= new MCQChoice();
//		choice2_2.setValid(true);
//		choice2_2.setChoice("a programming language?");
//		
//		MCQChoice choice2_3= new MCQChoice();
//		choice2_3.setValid(true);
//		choice2_3.setChoice("none of the other choices");
//		LOGGER.debug("test");
//		
//		
//		//when
//		try {
//			
//			creationDataService.createMCQQuestion(question, Arrays.asList(choice, choice2, choice3));
//			creationDataService.createMCQQuestion(question2, Arrays.asList(choice2_1, choice2_2, choice2_3));
//			List<QuestionDTO> allQuestionsWithChoices = reportingService.getAllQuestionsWithChoices();
//			//then
//			Assert.assertEquals(2, allQuestionsWithChoices.size());
//			
//			//jackson
//			ObjectMapper mapper = new ObjectMapper();
//			String output = mapper.writer().writeValueAsString(allQuestionsWithChoices);
//			LOGGER.info(output);
//		} catch (CreationFailedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	
//	}

}
