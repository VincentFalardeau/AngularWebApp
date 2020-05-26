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

import services.MarkServices;
import dataObjects.*;
import exceptions.ParameterFormatException;
import exceptions.ParameterException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MarksController {
	
	@GetMapping("/marks")
	public ResponseEntity<Object> marks(@RequestParam(value = "semester") String semester) {
		ResponseEntity responseEntity;
		
		try {
			MarkServices markServices = new MarkServices();
			ArrayList<Mark> marks = markServices.getMarks(semester);
			
			responseEntity = new ResponseEntity<ArrayList<Mark>>(marks, HttpStatus.OK);
			
		} catch (ParameterFormatException pfe) {
			pfe.printStackTrace();
			
			responseEntity = new ResponseEntity<ParameterFormatExceptionDataObject>(pfe.toParameterFormatExceptionDataObject(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			String errorMessage = "Internal server error";
			responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@PostMapping("/mark")
	public ResponseEntity<Object> mark(
			@RequestParam(value = "mark") String mark,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "weight") String weight,
			@RequestParam(value = "idCategory") String idCategory,
			@RequestParam(value = "idCourse") String idCourse) {
		ResponseEntity responseEntity;
		
		try {
			MarkServices markServices = new MarkServices();
			markServices.addMark(mark, description, weight, idCategory, idCourse);
			
			responseEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
			
		} catch (ParameterFormatException pfe) {
			pfe.printStackTrace();
			
			responseEntity = new ResponseEntity<ParameterFormatExceptionDataObject>(pfe.toParameterFormatExceptionDataObject(), HttpStatus.BAD_REQUEST);
			
		} catch (ParameterException pe) {
			pe.printStackTrace();
			
			responseEntity = new ResponseEntity<ParameterExceptionDataObject>(pe.toParameterExceptionDataObject(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			String errorMessage = "Internal server error";
			responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@PatchMapping("/mark")
	public ResponseEntity<Object> mark(
			@RequestParam(value = "idMark") String idMark,
			@RequestParam(value = "mark") String mark,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "weight") String weight,
			@RequestParam(value = "idCategory") String idCategory,
			@RequestParam(value = "idCourse") String idCourse) {
		ResponseEntity responseEntity;
		
		try {
			MarkServices markServices = new MarkServices();
			markServices.updateMark(idMark, mark, description, weight, idCategory, idCourse);
			
			responseEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
			
		} catch (ParameterFormatException pfe) {
			pfe.printStackTrace();
			
			responseEntity = new ResponseEntity<ParameterFormatExceptionDataObject>(pfe.toParameterFormatExceptionDataObject(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			String errorMessage = "Internal server error";
			responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@DeleteMapping("/mark")
	public ResponseEntity<Object> mark(@RequestParam(value = "idMark") String idMark) {
		ResponseEntity responseEntity;
		
		try {
			MarkServices markServices = new MarkServices();
			markServices.deleteMark(idMark);
			
			responseEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
			
		} catch (ParameterFormatException pfe) {
			pfe.printStackTrace();
			
			responseEntity = new ResponseEntity<ParameterFormatExceptionDataObject>(pfe.toParameterFormatExceptionDataObject(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			String errorMessage = "Internal server error";
			responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
}
