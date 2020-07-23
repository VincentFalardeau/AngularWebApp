package httpControllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Throwables;

import services.MarkService;
import dataObjects.*;
import exceptions.ParameterException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MarkController {

	private final String URL = "marks";

	// Gives all the marks.
	@GetMapping(URL)
	public ResponseEntity<?> getMarks(@RequestParam(value = "startIndex", defaultValue = "0") int startIndex,
			@RequestParam(value = "size", defaultValue = "-1") int size) {
		
		//TODO: Use startIndex and size.

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the marks.
			MarkService markService = new MarkService();
			ArrayList<Mark> marks = markService.getMarks();

			// Generate OK response with the marks.
			responseEntity = responseEntityGenerator.generateOK(marks);

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();

		}

		return responseEntity;
	}

	// Gives the mark having the specified id.
	@GetMapping(URL + "/{id}")
	public ResponseEntity<?> getMark(@PathVariable int id) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the mark.
			MarkService markService = new MarkService();
			Mark mark = markService.getMark(id);

			// Generate OK response with the mark.
			responseEntity = responseEntityGenerator.generateOK(mark);

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}

	// Adds a mark in the database.
	@PostMapping(URL)
	public ResponseEntity<?> addMark(@RequestBody MarkWrapper mark) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Add the mark.
			MarkService markService = new MarkService();
			markService.addMark(mark);

			// Generate OK response.
			responseEntity = responseEntityGenerator.generateOK();

		} catch (ParameterException pe) {

			log.error(Throwables.getStackTraceAsString(pe));

			// Generate bad request
			responseEntity = responseEntityGenerator.generateBadRequest(pe.getGenericErrorMessage());


		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}

	// Updates a mark.
	@PutMapping(URL + "/{id}")
	public ResponseEntity<?> updateMark(@PathVariable int id, @RequestBody MarkWrapper mark) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			mark.setId(id);

			// Update the mark.
			MarkService markService = new MarkService();
			markService.updateMark(mark);

			// Generate OK response.
			responseEntity = responseEntityGenerator.generateOK();

		} catch (ParameterException pe) {

			log.error(Throwables.getStackTraceAsString(pe));

			// Generate bad request
			responseEntity = responseEntityGenerator.generateBadRequest(pe.getGenericErrorMessage());

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}
	
	// Updates a batch of mark.
	@PutMapping(URL)
	public ResponseEntity<?> updateMark(@RequestBody MarkWrapper[] marks) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Update the marks.
			MarkService markService = new MarkService();
			markService.updateMark(marks);

			// Generate OK response.
			responseEntity = responseEntityGenerator.generateOK();

		} catch (ParameterException pe) {

			log.error(Throwables.getStackTraceAsString(pe));

			// Generate bad request
			responseEntity = responseEntityGenerator.generateBadRequest(pe.getGenericErrorMessage());

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}

	// Deletes a mark.
	@DeleteMapping(URL + "/{id}")
	public ResponseEntity<?> deleteMark(@PathVariable int id) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {
			// Delete the mark.
			MarkService markService = new MarkService();
			markService.deleteMark(id);

			// Generate OK response.
			responseEntity = responseEntityGenerator.generateOK();

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}
	
	// Deletes a batch of mark.
	@DeleteMapping(URL)
	public ResponseEntity<?> deleteMark(@RequestBody int[] ids) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {
			// Delete the mark.
			MarkService markService = new MarkService();
			markService.deleteMark(ids);

			// Generate OK response.
			responseEntity = responseEntityGenerator.generateOK();

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}
}
