package httpControllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Throwables;

import services.MarkService;
import dataObjects.*;
import exceptions.ParameterException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MarkController {
	
	//private Logger log = LogManager.getLogger(MarkController.class);

	private ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
	
	//Gives all the marks.
	@GetMapping("/marks/all")
	public ResponseEntity<?> marks() {
		ResponseEntity<?> responseEntity;
		
		//Log current http call
		//LOGGER.info("GET - /marks/all");
		//log.info("GET \"/marks/all\", parameters={}");
		
		try {
			//Retrieve the marks.
			MarkService markService = new MarkService();
			ArrayList<Mark> marks = markService.getMarks();
			
			//Generate OK response with the marks.
			responseEntity = responseEntityGenerator.generateOK(marks);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if exception thrown.
			responseEntity = responseEntityGenerator.generateInternalServerError();
			
			//Log the error
			//LOGGER.severe(Throwables.getStackTraceAsString(e));
			//log.error(Throwables.getStackTraceAsString(e));
		}
		return responseEntity;
	}
	
	//Gives all the marks for a specified semester.
	@GetMapping("/marks")
	public ResponseEntity<?> marks(@RequestParam(value = "semester") int semester) {
		ResponseEntity<?> responseEntity;
		
		//Log current http call
		//LOGGER.info("GET - /marks?semester=" + semester);
		
		try {
			//Retrieve the marks.
			MarkService markService = new MarkService();
			ArrayList<Mark> marks = markService.getMarks(semester);
			
			//Generate OK response with the marks.
			responseEntity = responseEntityGenerator.generateOK(marks);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if exception thrown.
			responseEntity = responseEntityGenerator.generateInternalServerError();
			
			//Log the error
			//LOGGER.severe(Throwables.getStackTraceAsString(e));
		}
		
		return responseEntity;
	}
	
	//Gives the mark having the specified id.
	@GetMapping("/mark")
	public ResponseEntity<?> mark(@RequestParam(value = "idMark") int idMark) {
		ResponseEntity<?> responseEntity;
		
		//Log current http call
		//LOGGER.info("GET - /mark?idMark=" + idMark);
		
		try {
			//Retrieve the mark.
			MarkService markService = new MarkService();
			Mark mark = markService.getMark(idMark);
			
			//Generate OK response with the mark.
			responseEntity = responseEntityGenerator.generateOK(mark);

		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if exception thrown.
			responseEntity = responseEntityGenerator.generateInternalServerError();
			
			//Log the error
			//LOGGER.severe(Throwables.getStackTraceAsString(e));
		}
		
		return responseEntity;
	}
	
	//Adds a mark in the database.
	@PostMapping("/mark")
	public ResponseEntity<?> addMark(
			@RequestParam(value = "mark") float mark,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "weight") float weight,
			@RequestParam(value = "idCategory") int idCategory,
			@RequestParam(value = "idCourse") int idCourse) {
		ResponseEntity<?> responseEntity;
		
		//Log current http call
		//LOGGER.info("POST - /mark?mark="+mark+"&description="+description+"&weight="+weight+"&idCategory="+idCategory+"&idCourse="+idCourse);
		
		try {
			//Add the mark.
			MarkService markService = new MarkService();
			markService.addMark(mark, description, weight, idCategory, idCourse);
			
			//Generate OK response.
			responseEntity = responseEntityGenerator.generateOK();
			
		} catch (ParameterException pe) {
			pe.printStackTrace();
			
			//ParameterExceptions will occur when idCategory and/or idCourse refer to nothing in the database.
			responseEntity = responseEntityGenerator.generateBadRequest(pe.getGenericErrorMessage());
			
			//Log the error
			//LOGGER.severe(Throwables.getStackTraceAsString(pe));
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if any other exception thrown.
			responseEntity = responseEntityGenerator.generateInternalServerError();
			
			//Log the error
			//LOGGER.severe(Throwables.getStackTraceAsString(e));
		}
		
		return responseEntity;
	}
	
	//Updates a mark.
	@PatchMapping("/mark")
	public ResponseEntity<?> updateMark(
			@RequestParam(value = "idMark") int idMark,
			@RequestParam(value = "mark") float mark,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "weight") float weight,
			@RequestParam(value = "idCategory") int idCategory,
			@RequestParam(value = "idCourse") int idCourse) {
		ResponseEntity<?> responseEntity;
		
		//Log current http call
		//LOGGER.info("PATCH - /mark?idMark="+idMark+"&mark="+mark+"&description="+description+"&weight="+weight+"&idCategory="+idCategory+"&idCourse="+idCourse);
		
		try {
			//Update the mark.
			MarkService markService = new MarkService();
			markService.updateMark(idMark, mark, description, weight, idCategory, idCourse);
			
			//Generate OK response.
			responseEntity = responseEntityGenerator.generateOK();
			
		} catch (ParameterException pe) {
			pe.printStackTrace();
			
			//ParameterExceptions will occur when idCategory and/or idCourse refer to nothing in the database.
			responseEntity = responseEntityGenerator.generateBadRequest(pe.getGenericErrorMessage());
			
			//Log the error
			//LOGGER.severe(Throwables.getStackTraceAsString(pe));
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if any other exception thrown.
			responseEntity = responseEntityGenerator.generateInternalServerError();
			
			//Log the error
			//LOGGER.severe(Throwables.getStackTraceAsString(e));
		}
		
		return responseEntity;
	}
	
	//Deletes a mark.
	@DeleteMapping("/mark")
	public ResponseEntity<?> deleteMark(@RequestParam(value = "idMark") int idMark) {
		ResponseEntity<?> responseEntity;
		
		//Log current http call
		//LOGGER.info("DELETE - /mark?idMark="+idMark);
		
		try {
			//Delete the mark.
			MarkService markService = new MarkService();
			markService.deleteMark(idMark);
			
			//Generate OK response.
			responseEntity = responseEntityGenerator.generateOK();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if exception thrown.
			responseEntity = responseEntityGenerator.generateInternalServerError();
			
			//Log the error
			//LOGGER.severe(Throwables.getStackTraceAsString(e));
		}
		
		return responseEntity;
	}
}
