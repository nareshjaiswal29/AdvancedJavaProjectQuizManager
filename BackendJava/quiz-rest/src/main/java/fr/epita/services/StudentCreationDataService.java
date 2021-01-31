package fr.epita.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.epita.datamodel.MCQAnswer;
import fr.epita.datamodel.MCQChoice;
import fr.epita.datamodel.Question;
import fr.epita.datamodel.Student;
import fr.epita.exception.CreationFailedException;
import fr.epita.services.dto.MCQAnswerDTO;
import fr.epita.services.dto.MCQChoiceDTO;
import fr.epita.services.dto.QuestionDTO;
import fr.epita.services.dto.StudentDTO;

public class StudentCreationDataService {
	@Inject
	StudentJPADAO studentDAO;
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public void createStudent(StudentDTO dto) throws CreationFailedException {
		
		Student dataModel = dto.toDataModel();
		studentDAO.create(dataModel);
		dto.setId(dataModel.getStudentId());
	}
	
public List<StudentDTO> getAll() {
		
	List<Student> student = studentDAO.searchAll();
	List<StudentDTO> studentsDTO = student.stream()
			.map(choice ->  {
				StudentDTO studentDTO = new StudentDTO();
				studentDTO.fromDataModel(choice);
				return studentDTO;
			})
			.collect(Collectors.toList());
	
	return studentsDTO;		
	}

}
