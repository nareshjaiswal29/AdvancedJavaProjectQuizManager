package fr.epita.services.dto;

import java.util.List;

import fr.epita.datamodel.Question;

public class QuestionDTO {
	
	private Integer id;
	
	private String questionTitle;
	
	private String topics;
	private String quizId;
	
	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public String getQuizId() {
		return quizId;
	}

	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}

	private List<MCQChoiceDTO> choices;

	
	
	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public List<MCQChoiceDTO> getChoices() {
		return choices;
	}

	public void setChoices(List<MCQChoiceDTO> choices) {
		this.choices = choices;
	}
	
	
	public Question toDataModel() {
		Question question = new Question();
		question.setQuestionId(id);
		question.setQuestionTitle(questionTitle);
		question.setQuizId(quizId);
		question.setTopics(topics);
		return question;
	}
	
	public void fromDataModel(Question question) {
		this.questionTitle = question.getQuestionTitle();
		this.id = question.getQuestionId();
		this.quizId = question.getQuizId();
		this.topics = question.getTopics();
	}

	@Override
	public String toString() {
		return "QuestionDTO [id=" + id + ", questionTitle=" + questionTitle + ", choices=" + choices + "]";
	}

	public Integer getQuestionId() {
		
		return this.id;
	}

	public void setQuestionId(Integer questionId) {
		this.id = questionId;
		
	}

}
