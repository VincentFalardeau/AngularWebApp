package httpControllers;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.http.HttpStatus;
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
import logging.MyLogger;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MarkController {
	
	//TODO:
	//SEVERE: pour les exceptions
	//INFO: pour les calls http
	//FINE: getMarks(1) // on indique les méthodes appelées au début de chacune d'entres elles avec les paramètres qui leur sont passés.
	
	//Constants
	private final String INTERNAL_SERVER_ERROR_MESSSAGE = "Internal server error";
	private final String OK_MESSAGE = "Success";
	
	//The logger
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//Generates an Internal Server Error ResponseEntity.
	private ResponseEntity<String> generateInternalServerError() {
		return new ResponseEntity<String>(INTERNAL_SERVER_ERROR_MESSSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Generates a Bad Request ResponseEntity containing the specified string.
	private ResponseEntity<String> generateBadRequest(String message){
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	//Generates an OK ResponseEntity.
	private ResponseEntity<String> generateOK(){
		return new ResponseEntity<String>(OK_MESSAGE, HttpStatus.OK);
	}
	
	//Generates an OK ResponseEntity containing the specified mark.
	private ResponseEntity<Mark> generateOK(Mark mark){
		return new ResponseEntity<Mark>(mark, HttpStatus.OK);
	}
	
	//Generates an OK ResponseEntity containing the specified array of mark.
	private ResponseEntity<ArrayList<Mark>> generateOK(ArrayList<Mark> marks){
		return new ResponseEntity<ArrayList<Mark>>(marks, HttpStatus.OK);
	}
	
//	//Gives the given array of stack trace element as a string which can be logged.
//	private String getStackString(StackTraceElement[] elements) {
//    	String stackString = "";
//    	for (int i = 1; i < elements.length; i++) {
//    	     StackTraceElement s = elements[i];
//    	     stackString += "\tat " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":" + s.getLineNumber() + ")";
//    	}
//    	return stackString;
//    	
//    }
	
	//Gives all the marks.
	@GetMapping("/marks/all")
	public ResponseEntity<?> marks() {
		ResponseEntity<?> responseEntity;
		
		//Log current http call
		LOGGER.info("GET - /marks/all");
		
		try {
			//Retrieve the marks.
			MarkService markService = new MarkService();
			ArrayList<Mark> marks = markService.getMarks();
			
			//Generate OK response with the marks.
			responseEntity = generateOK(marks);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if exception thrown.
			responseEntity = generateInternalServerError();
			
			//Log the error
			LOGGER.severe(Throwables.getStackTraceAsString(e));
		}
		return responseEntity;
	}
	
	//Gives all the marks for a specified semester.
	@GetMapping("/marks")
	public ResponseEntity<?> marks(@RequestParam(value = "semester") int semester) {
		ResponseEntity<?> responseEntity;
		
		//Log current http call
		LOGGER.info("GET - /marks?semester=" + semester);
		
		try {
			//Retrieve the marks.
			MarkService markService = new MarkService();
			ArrayList<Mark> marks = markService.getMarks(semester);
			
			//Generate OK response with the marks.
			responseEntity = generateOK(marks);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if exception thrown.
			responseEntity = generateInternalServerError();
			
			//Log the error
			LOGGER.severe(Throwables.getStackTraceAsString(e));
		}
		
		return responseEntity;
	}
	
	//Gives the mark having the specified id.
	@GetMapping("/mark")
	public ResponseEntity<?> mark(@RequestParam(value = "idMark") int idMark) {
		ResponseEntity<?> responseEntity;
		
		//Log current http call
		LOGGER.info("GET - /mark?idMark=" + idMark);
		
		try {
			//Retrieve the mark.
			MarkService markService = new MarkService();
			Mark mark = markService.getMark(idMark);
			
			//Generate OK response with the mark.
			responseEntity = generateOK(mark);

		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if exception thrown.
			responseEntity = generateInternalServerError();
			
			//Log the error
			LOGGER.severe(Throwables.getStackTraceAsString(e));
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
		LOGGER.info("POST - /mark?mark="+mark+"&description="+description+"&weight="+weight+"&idCategory="+idCategory+"&idCourse="+idCourse);
		
		try {
			//Add the mark.
			MarkService markService = new MarkService();
			markService.addMark(mark, description, weight, idCategory, idCourse);
			
			//Generate OK response.
			responseEntity = generateOK();
			
		} catch (ParameterException pe) {
			pe.printStackTrace();
			
			//ParameterExceptions will occur when idCategory and/or idCourse refer to nothing in the database.
			responseEntity = generateBadRequest(pe.getGenericErrorMessage());
			
			//Log the error
			LOGGER.severe(Throwables.getStackTraceAsString(pe));
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if any other exception thrown.
			responseEntity = generateInternalServerError();
			
			//Log the error
			LOGGER.severe(Throwables.getStackTraceAsString(e));
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
		LOGGER.info("PATCH - /mark?idMark="+idMark+"&mark="+mark+"&description="+description+"&weight="+weight+"&idCategory="+idCategory+"&idCourse="+idCourse);
		
		try {
			//Update the mark.
			MarkService markService = new MarkService();
			markService.updateMark(idMark, mark, description, weight, idCategory, idCourse);
			
			//Generate OK response.
			responseEntity = generateOK();
			
		} catch (ParameterException pe) {
			pe.printStackTrace();
			
			//ParameterExceptions will occur when idCategory and/or idCourse refer to nothing in the database.
			responseEntity = generateBadRequest(pe.getGenericErrorMessage());
			
			//Log the error
			LOGGER.severe(Throwables.getStackTraceAsString(pe));
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if any other exception thrown.
			responseEntity = generateInternalServerError();
			
			//Log the error
			LOGGER.severe(Throwables.getStackTraceAsString(e));
		}
		
		return responseEntity;
	}
	
	//Deletes a mark.
	@DeleteMapping("/mark")
	public ResponseEntity<?> deleteMark(@RequestParam(value = "idMark") int idMark) {
		ResponseEntity<?> responseEntity;
		
		//Log current http call
		LOGGER.info("DELETE - /mark?idMark="+idMark);
		
		try {
			//Delete the mark.
			MarkService markService = new MarkService();
			markService.deleteMark(idMark);
			
			//Generate OK response.
			responseEntity = generateOK();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Internal server error if exception thrown.
			responseEntity = generateInternalServerError();
			
			//Log the error
			LOGGER.severe(Throwables.getStackTraceAsString(e));
		}
		
		return responseEntity;
	}
}
