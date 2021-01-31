package fr.epita.services.dto;

import fr.epita.datamodel.MCQAnswer;

public class MCQAnswerDTO {

	private Integer id;
	private Integer questionId;
	private String StudenId;
	private String Answer;
	private Integer QuizId;
//	public Integer getAnswerId() {
//		return AnswerId;
//	}
//	public void setAnswerId(Integer answerId) {
//		AnswerId = answerId;
//	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getQuizId() {
		return QuizId;
	}
	public void setQuizId(Integer quizId) {
		QuizId = quizId;
	}
	public String getStudenId() {
		return StudenId;
	}
	public void setStudenId(String studenId) {
		StudenId = studenId;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	
	public MCQAnswer toDataModel() {
		MCQAnswer mcqAnswer = new MCQAnswer();
		mcqAnswer.setQuestionId(questionId);
		mcqAnswer.setStudenId(StudenId);
		mcqAnswer.setAnswer(Answer);
		mcqAnswer.setQuizId(QuizId);
		return mcqAnswer;
	}
	
	public void fromDataModel(MCQAnswer mcqAnswer) {
		this.questionId = mcqAnswer.getQuestionId();
		this.StudenId = mcqAnswer.getStudenId();
		this.Answer = mcqAnswer.getAnswer();
		this.QuizId = mcqAnswer.getQuizId();
	}

//	@Override
//	public String toString() {
//		return "QuestionDTO [id=" + id + ", questionTitle=" + questionTitle + ", choices=" + choices + "]";
//	}

	public Integer getAnswerId() {
		
		return this.id;
	}

	public void setAnswerId(Integer AnswrerId) {
		this.id = AnswrerId;
		
	}
}
