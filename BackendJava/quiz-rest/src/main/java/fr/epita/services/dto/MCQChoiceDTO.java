package fr.epita.services.dto;

import fr.epita.datamodel.MCQChoice;

public class MCQChoiceDTO {
	
	

	private int id;
	
	private String choiceTitle;
	
	private boolean valid;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getChoiceTitle() {
		return choiceTitle;
	}

	public void setChoiceTitle(String choiceTitle) {
		this.choiceTitle = choiceTitle;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public MCQChoice toDataModel() {
		MCQChoice choice = new MCQChoice();
		choice.setId(this.id);
		choice.setChoice(choiceTitle);
		choice.setValid(valid);
		return choice;
	}
	
	public void fromDataModel(MCQChoice choice) {
		this.choiceTitle = choice.getChoice();
		this.valid = choice.isValid();
		this.id = choice.getId();
	}

	@Override
	public String toString() {
		return "MCQChoiceDTO [id=" + id + ", choiceTitle=" + choiceTitle + "]";
	}
	
	

}
