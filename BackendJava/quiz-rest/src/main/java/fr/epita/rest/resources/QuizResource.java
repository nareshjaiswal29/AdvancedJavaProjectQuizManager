package fr.epita.rest.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.epita.datamodel.Question;
import fr.epita.services.QuizCreationDataService;

import fr.epita.datamodel.MCQChoice;
import fr.epita.exception.CreationFailedException;
import fr.epita.services.QuizCreationDataService;
import fr.epita.services.dto.MCQChoiceDTO;
import fr.epita.services.dto.QuestionDTO;
import fr.epita.services.dto.QuizNameDTO;

@Path("/quizzes")
public class QuizResource {
	
	
	@Inject 
	QuizCreationDataService service;
	
	@POST
	@Path("/{id}/questions")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createQuestionForQuiz(@PathParam("id") String id, QuestionDTO dto) {
		if (dto == null) {
			return Response.status(400, "no question was provided").entity("bad request").build();
		}

		try {
			service.createMCQQuestion(dto);
		}catch(CreationFailedException cfe) {
			return Response.notModified().build();
		}
		
		return Response.created(URI.create(String.valueOf("quiz/"+id+"/question/"+ dto.getQuestionId()))).build();
	}
	
	@PUT
	@Path("/{id}/questions")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateQuestionForQuiz(@PathParam("id") String id, QuestionDTO dto) {
		if (dto == null) {
			return Response.status(400, "no question was provided").entity("bad request").build();
		}

		try {
			service.UpdateMCQQuestion(dto);
		}catch(CreationFailedException cfe) {
			return Response.notModified().build();
		}
		
		return Response.created(URI.create(String.valueOf("quiz/"+id+"/question/"+ dto.getQuestionId()))).build();
	}
	
	@DELETE
	@Path("/question/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteQuestionForQuiz(@PathParam("id") String id) {

		try {
			service.DeleteMCQQuestion(id);
		}catch(CreationFailedException cfe) {
			return Response.notModified().build();
		}
		return Response.ok("deleted").build();
	}
	
	
	@GET
	@Path("/questions/{questionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestion(@PathParam("questionId") String questionId) {
		QuestionDTO dto = service.getById(questionId);
		return Response.ok().entity(dto).build();
	}
	
	@GET
	@Path("/questions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestion() {
		List<QuestionDTO> dto = service.getAll();
		return Response.ok().entity(dto).build();
	}
	
	@POST
	@Path("/{id}/quiz")
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer createQuestionForQuiz(@PathParam("id") String id, QuizNameDTO dto) {
		if (dto == null) {
			return 0;
		}
		try {
			service.createQuiz(dto);
		}catch(CreationFailedException cfe) {
			return 0;
		}
		
		return dto.getId();
	}
	
	@GET
	@Path("/quizAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response quizAll() {
		List<QuizNameDTO> dto = service.getAllQuiz();
		return Response.ok().entity(dto).build();
	}
	
	@GET
	@Path("/AllQuestionsByQuizId/{quizId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AllQuestionsByQuizId(@PathParam("quizId") String quizIdId) {

		List<QuestionDTO> dto = service.getAllByQuizId(quizIdId);
		return Response.ok().entity(dto).build();
	}

}
