package httpControllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import services.MarkService;
import dataObjects.*;
import exceptions.ParameterException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MarksController {
	
	//Constants
	//TODO: Look for a way to put them in a config file, with something like Spring JDBC.
	private final String INTERNAL_SERVER_ERROR_MESSSAGE = "Internal server error";
	private final String OK_MESSAGE = "Success";
	
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
	
	//Generates an OK ResponseEntity containing the specified array of mark.
	private ResponseEntity<ArrayList<Mark>> generateOK(ArrayList<Mark> marks){
		return new ResponseEntity<ArrayList<Mark>>(marks, HttpStatus.OK);
	}
	
	//Generates an OK ResponseEntity containing the specified mark.
	private ResponseEntity<Mark> generateOK(Mark mark){
		return new ResponseEntity<Mark>(mark, HttpStatus.OK);
	}
	
	//Gives all the marks.
	@GetMapping("/marks/all")
	public ResponseEntity<?> marks() {
		ResponseEntity<?> responseEntity;
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
		}
		return responseEntity;
	}
	
	//Gives all the marks for a specified semester.
	@GetMapping("/marks")
	public ResponseEntity<?> marks(@RequestParam(value = "semester") int semester) {
		ResponseEntity<?> responseEntity;
		
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
		}
		
		return responseEntity;
	}
	
	//Gives the mark having the specified id.
	@GetMapping("/mark")
	public ResponseEntity<?> mark(@RequestParam(value = "idMark") int idMark) {
		ResponseEntity<?> responseEntity;
		
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
		
		try {
			//Add the mark.
			MarkService markService = new MarkService();
			markService.addMark(mark, description, weight, idCategory, idCourse);
			
			//Generate OK response.
			responseEntity = generateOK();
			
		} catch (ParameterException pe) {
			pe.printStackTrace();
			//ParameterExceptions will occur when idCategory and/or idCourse refer to nothing in the database.
			responseEntity = generateBadRequest(pe.getErrorMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			//Internal server error if any other exception thrown.
			responseEntity = generateInternalServerError();
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
		
		try {
			//Update the mark.
			MarkService markService = new MarkService();
			markService.updateMark(idMark, mark, description, weight, idCategory, idCourse);
			
			//Generate OK response.
			responseEntity = generateOK();
			
		} catch (ParameterException pe) {
			pe.printStackTrace();
			//ParameterExceptions will occur when idCategory and/or idCourse refer to nothing in the database.
			responseEntity = generateBadRequest(pe.getErrorMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			//Internal server error if any other exception thrown.
			responseEntity = generateInternalServerError();
		}
		
		return responseEntity;
	}
	
	//Deletes a mark.
	@DeleteMapping("/mark")
	public ResponseEntity<?> deleteMark(@RequestParam(value = "idMark") int idMark) {
		ResponseEntity<?> responseEntity;
		
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
		}
		
		return responseEntity;
	}
}
