package fr.epita.datamodel;

import java.util.Arrays;

public class Question {

	private String questionTitle;
	private String[] topics ;
	private Integer difficulty;
	
	
	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String[] getTopics() {
		return topics;
	}
	public void setTopics(String[] topics) {
		this.topics = topics;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	
	@Override
	public String toString() {
		return "Question [questionTitle=" + questionTitle + ", topics=" + Arrays.toString(topics) + ", difficulty="
				+ difficulty + "]";
	}
	
}
