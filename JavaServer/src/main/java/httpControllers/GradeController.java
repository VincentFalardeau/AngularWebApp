package httpControllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Throwables;

import dataObjects.GlobalGrade;
import dataObjects.Grade;
import dataObjects.Mark;
import services.GradeService;
import services.MarkService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GradeController {

	private final String URL = "grade";
	private final String URLs = URL + "s";

	// Gives all the grades.
	@GetMapping(URLs)
	public ResponseEntity<?> grades() {

		Logger log = LogManager.getLogger(GradeController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the grades.
			GradeService gradeService = new GradeService();
			ArrayList<Grade> grades = gradeService.getGrades();

			// Generate OK response with the grades.
			responseEntity = responseEntityGenerator.generateOK(grades);

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();

		}

		return responseEntity;
	}

	// Gives all the grades for a semester.
	@GetMapping(URLs + "/{semester}")
	public ResponseEntity<?> grades(@PathVariable int semester) {

		Logger log = LogManager.getLogger(GradeController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the grades.
			GradeService gradeService = new GradeService();
			ArrayList<Grade> grades = gradeService.getGrades(semester);

			// Generate OK response with the grades.
			responseEntity = responseEntityGenerator.generateOK(grades);

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();

		}

		return responseEntity;
	}

	// Gives the grade of the course having the specified id.
	@GetMapping(URL)
	public ResponseEntity<?> grade(@RequestParam(value = "id") int idCourse) {

		Logger log = LogManager.getLogger(GradeController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the grade.
			GradeService gradeService = new GradeService();
			Grade grade = gradeService.getGrade(idCourse);

			// Generate OK response with the grade.
			responseEntity = responseEntityGenerator.generateOK(grade);

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}

	// Gives the global grade.
	@GetMapping(URL + "/global")
	public ResponseEntity<?> globalGrade() {

		Logger log = LogManager.getLogger(GradeController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the grade.
			GradeService gradeService = new GradeService();
			GlobalGrade grade = gradeService.getGlobalGrade();

			// Generate OK response with the grade.
			responseEntity = responseEntityGenerator.generateOK(grade);

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}

	// Gives the global grade for a semester.
	@GetMapping(URL + "global/{semester}")
	public ResponseEntity<?> globalGrade(@PathVariable int semester) {

		Logger log = LogManager.getLogger(GradeController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the grade.
			GradeService gradeService = new GradeService();
			GlobalGrade grade = gradeService.getGlobalGrade(semester);

			// Generate OK response with the grade.
			responseEntity = responseEntityGenerator.generateOK(grade);

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}
}
