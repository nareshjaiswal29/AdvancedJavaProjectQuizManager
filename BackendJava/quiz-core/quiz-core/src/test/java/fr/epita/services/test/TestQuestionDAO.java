package fr.epita.services.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.datamodel.Question;
import fr.epita.exception.CreationFailedException;
import fr.epita.services.QuestionDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")

public class TestQuestionDAO {
	
	@Inject
	QuestionDAO dao;
	
	
	@Test
	public void testCreation() {
		//given
	
		Question question = new Question();
		question.setQuestionTitle("What is Java?");
		question.setDifficulty(2);
		
		//when
		try {
			dao.create(question);
		} catch (CreationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//todo check result
	}

}
