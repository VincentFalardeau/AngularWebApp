package httpControllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import services.MarkServices;
import dataObjects.*;
import exceptions.ParameterFormatException;

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
			
			responseEntity = new ResponseEntity<ParameterFormatExceptionDataObject>(pfe.toDataObject(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			String errorMessage = "Internal server error";
			responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
}
