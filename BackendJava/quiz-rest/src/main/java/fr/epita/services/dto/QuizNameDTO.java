package fr.epita.services.dto;

import fr.epita.datamodel.Question;
import fr.epita.datamodel.QuizName;

public class QuizNameDTO {

	private Integer id;
	
	private String quizTitle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuizTitle() {
		return quizTitle;
	}

	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}
	
	public QuizName toDataModel() {
		QuizName question = new QuizName();
		question.setQuizId(id);
		question.setQuizTitle(quizTitle);
		return question;
	}
	
	public void fromDataModel(QuizName question) {
		this.quizTitle = question.getQuizTitle();
		this.id = question.getQuizId();
	}
}
