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
import fr.epita.services.QuizCreationDataService;
import fr.epita.services.StudentCreationDataService;
import fr.epita.services.dto.QuestionDTO;
import fr.epita.services.dto.StudentDTO;

@Path("/students")
public class StudendResource {

	@Inject 
	StudentCreationDataService service;
	
	@POST
	@Path("/{id}/student")
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer createQuestionForQuiz(@PathParam("id") String id, StudentDTO dto) {
		if (dto == null) {
			return 0;
		}

		try {
			service.createStudent(dto);
		}catch(CreationFailedException cfe) {
			return 0;
		}
		
		return dto.getId();
	}
	@GET
	@Path("/students/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudent(@PathParam("studentId") String studentId) {
		List<StudentDTO> dto = service.getAll();
		return Response.ok().entity(dto).build();
		//return "Naresh";
	}
}
