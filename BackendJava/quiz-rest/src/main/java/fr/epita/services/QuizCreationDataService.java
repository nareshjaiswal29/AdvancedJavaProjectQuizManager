package fr.epita.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.epita.datamodel.MCQChoice;
import fr.epita.datamodel.Question;
import fr.epita.datamodel.QuizName;
import fr.epita.datamodel.Student;
import fr.epita.exception.CreationFailedException;
import fr.epita.services.dto.MCQChoiceDTO;
import fr.epita.services.dto.QuestionDTO;
import fr.epita.services.dto.QuizNameDTO;
import fr.epita.services.dto.StudentDTO;

public class QuizCreationDataService {
	
	@Inject
	MCQChoiceJPADAO mcqChoiceDAO;
	

	@Inject
	QuestionJPADAO questionDAO;
	
	@Inject
	QuizDAOJPA quizDAO;
	
	@PersistenceContext
	EntityManager em;
	
	/**
	 * this method is transactional
	 * @param question
	 * @param choices
	 * @throws CreationFailedException 
	 */

	
	@Transactional(value = TxType.REQUIRED)
	public void createMCQQuestion(QuestionDTO dto) throws CreationFailedException {
		
		Question dataModel = dto.toDataModel();
		List<MCQChoiceDTO> DTOChoices = dto.getChoices();
		List<MCQChoice> choices = DTOChoices.stream()
				.map(MCQChoiceDTO::toDataModel)
				.collect(Collectors.toList());
		
		
		questionDAO.create(dataModel);
		for (MCQChoice choice : choices) {
			choice.setQuestion(dataModel);
			mcqChoiceDAO.create(choice);
		}
		dto.setQuestionId(dataModel.getQuestionId());
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void UpdateMCQQuestion(QuestionDTO dto) throws CreationFailedException {
		
		Question dataModel = dto.toDataModel();
		List<MCQChoiceDTO> DTOChoices = dto.getChoices();
		List<MCQChoice> choices = DTOChoices.stream()
				.map(MCQChoiceDTO::toDataModel)
				.collect(Collectors.toList());
		
		
		questionDAO.Update(dataModel);
		dto.setQuestionId(dataModel.getQuestionId());
		System.out.println(dataModel.getQuestionId());
		
		for (MCQChoice choice : choices) {
			choice.setQuestion(dataModel);
			mcqChoiceDAO.update(choice);
		}
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void DeleteMCQQuestion(String Qid) throws CreationFailedException {
		
		QuestionDTO dto = new QuestionDTO();
		MCQChoice mcqChoiceCriteria = new MCQChoice();
		Question mcqQuestionCriteria = new Question();
		mcqQuestionCriteria.setQuestionId(Integer.valueOf(Qid));
		mcqChoiceCriteria.setQuestion(mcqQuestionCriteria);

		List<MCQChoice> DTOChoices = mcqChoiceDAO.search(mcqChoiceCriteria);
		
		
		
		for (MCQChoice choice : DTOChoices) {
			mcqChoiceDAO.delete(choice);
		}
		questionDAO.Delete(mcqQuestionCriteria);
	}
	
	
	
	public QuestionDTO getById(String questionId) {
		
		List<Question> qst = questionDAO.searchAll();
		int count = qst.size();
		int Qid = qst.get(0).getQuestionId();

		
		QuestionDTO dto = new QuestionDTO();
		MCQChoice mcqChoiceCriteria = new MCQChoice();
		Question mcqQuestionCriteria = new Question();
		mcqQuestionCriteria.setQuestionId(Integer.valueOf(questionId));
		mcqChoiceCriteria.setQuestion(mcqQuestionCriteria);
		
		List<MCQChoice> choices = mcqChoiceDAO.search(mcqChoiceCriteria);
		Question question = choices.get(0).getQuestion();
		
		
		dto.fromDataModel(question);
		List<MCQChoiceDTO> choicesDTO = choices.stream()
			.map(choice ->  {
				MCQChoiceDTO mcqChoiceDTO = new MCQChoiceDTO();
				mcqChoiceDTO.fromDataModel(choice);
				return mcqChoiceDTO;
			})
			.collect(Collectors.toList());
		
		dto.setChoices(choicesDTO);
		return dto;
		
	}
	
	public List<QuestionDTO> getAll() {
		
		List<QuestionDTO> hList = new ArrayList<QuestionDTO>();
		
		List<Question> qst = questionDAO.searchAll();
		int count = qst.size();
		for(int i=0;i<count;i++) {
			int Qid = qst.get(i).getQuestionId();
			
			QuestionDTO dto = new QuestionDTO();
			MCQChoice mcqChoiceCriteria = new MCQChoice();
			Question mcqQuestionCriteria = new Question();
			mcqQuestionCriteria.setQuestionId(Integer.valueOf(Qid));
			mcqChoiceCriteria.setQuestion(mcqQuestionCriteria);
			
			List<MCQChoice> choices = mcqChoiceDAO.search(mcqChoiceCriteria);
			Question question = choices.get(0).getQuestion();
			
			
			dto.fromDataModel(question);
			List<MCQChoiceDTO> choicesDTO = choices.stream()
				.map(choice ->  {
					MCQChoiceDTO mcqChoiceDTO = new MCQChoiceDTO();
					mcqChoiceDTO.fromDataModel(choice);
					return mcqChoiceDTO;
				})
				.collect(Collectors.toList());
			
			dto.setChoices(choicesDTO);
			hList.add(dto);
			}
		
		return hList;
		
	}
	
public List<QuestionDTO> getAllByQuizId(String quizId) {
		
	
		List<QuestionDTO> hList = new ArrayList<QuestionDTO>();
		Question questionByQuizId = new Question();
		questionByQuizId.setQuizId(quizId);
		List<Question> qst = questionDAO.searchbyId(questionByQuizId);
		int count = qst.size();
		for(int i=0;i<count;i++) {
			int Qid = qst.get(i).getQuestionId();
			
			QuestionDTO dto = new QuestionDTO();
			MCQChoice mcqChoiceCriteria = new MCQChoice();
			Question mcqQuestionCriteria = new Question();
			mcqQuestionCriteria.setQuestionId(Integer.valueOf(Qid));
			mcqChoiceCriteria.setQuestion(mcqQuestionCriteria);
			
			List<MCQChoice> choices = mcqChoiceDAO.search(mcqChoiceCriteria);
			Question question = choices.get(0).getQuestion();
			
			
			dto.fromDataModel(question);
			List<MCQChoiceDTO> choicesDTO = choices.stream()
				.map(choice ->  {
					MCQChoiceDTO mcqChoiceDTO = new MCQChoiceDTO();
					mcqChoiceDTO.fromDataModel(choice);
					return mcqChoiceDTO;
				})
				.collect(Collectors.toList());
			
			dto.setChoices(choicesDTO);
			hList.add(dto);
			}
		
		return hList;
		
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void createQuiz(QuizNameDTO dto) throws CreationFailedException {
		
		QuizName dataModel = dto.toDataModel();
		quizDAO.create(dataModel);
		dto.setId(dataModel.getQuizId());
	}
	
	public List<QuizNameDTO> getAllQuiz() {
		
		List<QuizName> student = quizDAO.searchAll();
		List<QuizNameDTO> studentsDTO = student.stream()
				.map(choice ->  {
					QuizNameDTO studentDTO = new QuizNameDTO();
					studentDTO.fromDataModel(choice);
					return studentDTO;
				})
				.collect(Collectors.toList());
		
		return studentsDTO;		
		}
}
