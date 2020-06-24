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

	private final String URL = "mark";
	private final String MULTIPLE_MARKS_URL = URL + "s";
	private final String COURSE_MARKS_URL = MULTIPLE_MARKS_URL + "/course";
	private final String ALL_MARKS_URL = MULTIPLE_MARKS_URL + "/all";

	// Gives all the marks.
	@GetMapping(ALL_MARKS_URL)
	public ResponseEntity<?> marks() {

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

	// Gives all the marks for a specific semester.
	@GetMapping(MULTIPLE_MARKS_URL)
	public ResponseEntity<?> marks(@RequestParam(value = "semester") int semester) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the marks.
			MarkService markService = new MarkService();
			ArrayList<Mark> marks = markService.getMarks(semester);

			// Generate OK response with the marks.
			responseEntity = responseEntityGenerator.generateOK(marks);

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}
	
	// Gives all the marks for a specific course.
	@GetMapping(COURSE_MARKS_URL)
	public ResponseEntity<?> courseMarks(@RequestParam(value = "id") int idCourse) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the marks.
			MarkService markService = new MarkService();
			ArrayList<Mark> marks = markService.getCourseMarks(idCourse);

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
	@GetMapping(URL)
	public ResponseEntity<?> mark(@RequestParam(value = "id") int idMark) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the mark.
			MarkService markService = new MarkService();
			Mark mark = markService.getMark(idMark);

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
	public ResponseEntity<?> addMark(@RequestParam(value = "mark") float mark,
			@RequestParam(value = "description") String description, @RequestParam(value = "weight") float weight,
			@RequestParam(value = "idCategory") int idCategory, @RequestParam(value = "idCourse") int idCourse) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Add the mark.
			MarkService markService = new MarkService();
			markService.addMark(mark, description, weight, idCategory, idCourse);

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
	@PatchMapping(URL)
	public ResponseEntity<?> updateMark(@RequestParam(value = "idMark") int idMark,
			@RequestParam(value = "mark") float mark, @RequestParam(value = "description") String description,
			@RequestParam(value = "weight") float weight, @RequestParam(value = "idCategory") int idCategory,
			@RequestParam(value = "idCourse") int idCourse) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Update the mark.
			MarkService markService = new MarkService();
			markService.updateMark(idMark, mark, description, weight, idCategory, idCourse);

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
	@DeleteMapping(URL)
	public ResponseEntity<?> deleteMark(@RequestParam(value = "idMark") int idMark) {

		Logger log = LogManager.getLogger(MarkController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {
			// Delete the mark.
			MarkService markService = new MarkService();
			markService.deleteMark(idMark);

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
