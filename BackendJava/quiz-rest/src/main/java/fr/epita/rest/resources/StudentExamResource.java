package fr.epita.rest.resources;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.epita.exception.CreationFailedException;
import fr.epita.services.ExamAnswersDataService;
import fr.epita.services.QuizCreationDataService;
import fr.epita.services.dto.MCQAnswerDTO;
import fr.epita.services.dto.QuestionDTO;

@Path("/studentExam")
public class StudentExamResource {
	
	@Inject 
	ExamAnswersDataService service;
	
	@POST
	@Path("/{id}/answers")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddStudentAnswers(@PathParam("id") String id, MCQAnswerDTO dto) {
		if (dto == null) {
			return Response.status(400, "no Answers was provided").entity("bad request").build();
		}

		try {
			service.createMCQAnswer(dto);
		}catch(CreationFailedException cfe) {
			return Response.notModified().build();
		}
		
		return Response.created(URI.create(String.valueOf("quiz/"+id+"/Answer/"+ dto.getAnswerId()))).build();
	}
	
	@GET
	@Path("/answers/{answerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestion(@PathParam("answerId") String questionId) {
		//System.out.println(questionId);
		MCQAnswerDTO dto = service.getById(questionId);
  	    return Response.ok().entity(dto).build();
		//return "My name is naresh";
	}
	
//	@GET
//	@Path("/questions/{questionId}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getQuestion(@PathParam("questionId") String questionId) {
//		System.out.println(questionId);
////		QuestionDTO dto = service.getById(questionId);
////		return Response.ok().entity(dto).build();
//		List<QuestionDTO> dto = service.getAll();
//		return Response.ok().entity(dto).build();
//	}

}
