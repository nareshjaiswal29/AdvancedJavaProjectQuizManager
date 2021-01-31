package fr.epita.services;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.epita.datamodel.MCQAnswer;
import fr.epita.datamodel.MCQChoice;
import fr.epita.datamodel.Question;
import fr.epita.exception.CreationFailedException;
import fr.epita.services.dto.MCQAnswerDTO;
import fr.epita.services.dto.QuestionDTO;

public class ExamAnswersDataService {

	@Inject
	AnswerJPADAO answerDAO;
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public void createMCQAnswer(MCQAnswerDTO dto) throws CreationFailedException 
	{

		MCQAnswer dataModel = dto.toDataModel();
		answerDAO.create(dataModel);
		dto.setAnswerId(dataModel.getAnswerId());
	}
	
	public MCQAnswerDTO getById(String AnswereId) {
		
		MCQAnswerDTO dto = new MCQAnswerDTO();
		MCQAnswer mcqAnswerCriteria = new MCQAnswer();
		mcqAnswerCriteria.setAnswerId(Integer.valueOf(AnswereId));
		MCQAnswer choices = answerDAO.searchbyId(mcqAnswerCriteria).get(0);
		dto.fromDataModel(choices);
		return dto;
	}
}
