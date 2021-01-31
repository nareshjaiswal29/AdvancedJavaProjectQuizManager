package fr.epita.services;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import fr.epita.datamodel.MCQChoice;
import fr.epita.datamodel.Question;
import fr.epita.services.dto.MCQChoiceDTO;
import fr.epita.services.dto.QuestionDTO;

public class QuizReportingDataService {
	
	@Inject
	MCQChoiceJPADAO mcqChoiceDAO;
	@Inject
	QuestionJPADAO questionDAO;
	
	
	public List<QuestionDTO> getAllQuestionsWithChoices(){
		List<MCQChoice> results = mcqChoiceDAO.searchAll();
		Map<Integer, QuestionDTO> dtoList = new LinkedHashMap<Integer, QuestionDTO>();
		for (MCQChoice choice : results) {
			
			Question question = choice.getQuestion();
			Integer currentId = question.getQuestionId();
			QuestionDTO questionDTO = dtoList.get(currentId);
			if (questionDTO == null) {
				questionDTO = new QuestionDTO();
				questionDTO.fromDataModel(question);
				dtoList.put(currentId, questionDTO);
			}
			List<MCQChoiceDTO> choices = questionDTO.getChoices();
			if (choices == null) {
				choices = new ArrayList<>();
				questionDTO.setChoices(choices);
			}
			MCQChoiceDTO choiceDTO = new MCQChoiceDTO();
			choiceDTO.fromDataModel(choice);
			choices.add(choiceDTO);
		}
		
		return new ArrayList<>(dtoList.values());
		
	
	}
	

}
