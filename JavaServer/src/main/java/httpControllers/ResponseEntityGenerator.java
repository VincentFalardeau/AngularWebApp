package httpControllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dataObjects.Category;
import dataObjects.Grade;
import dataObjects.Mark;

public class ResponseEntityGenerator {

	// Constants
	private final String INTERNAL_SERVER_ERROR_MESSSAGE = "Internal server error";
	private final String OK_MESSAGE = "Success";

	// Generates an Internal Server Error ResponseEntity.
	public ResponseEntity<String> generateInternalServerError() {
		return new ResponseEntity<String>(INTERNAL_SERVER_ERROR_MESSSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Generates a Bad Request ResponseEntity containing the specified string.
	public ResponseEntity<String> generateBadRequest(String message) {
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}

	// Generates an OK ResponseEntity.
	public ResponseEntity<String> generateOK() {
		return new ResponseEntity<String>(OK_MESSAGE, HttpStatus.OK);
	}

	// Generates an OK ResponseEntity containing the specified object.
	public ResponseEntity<Object> generateOK(Object obj) {
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}

	// Generates an OK ResponseEntity containing the specified array of objects.
	public ResponseEntity<ArrayList<?>> generateOK(ArrayList<?> array) {
		return new ResponseEntity<ArrayList<?>>(array, HttpStatus.OK);
	}
}