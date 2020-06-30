package httpControllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Throwables;

import dataObjects.GlobalGrade;
import dataObjects.Grade;
import dataObjects.Mark;
import services.GradeService;
import services.MarkService;
import services.SemesterService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SemesterController {
	
	private final String URL = "/semesters";
	
	// Gives all the marks for a semester.
	@GetMapping(URL + "/{semester}/marks")
	public ResponseEntity<?> getMarksCourses(@PathVariable int semester) {

		Logger log = LogManager.getLogger(CourseController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the courses.
			SemesterService semesterService = new SemesterService();
			ArrayList<Mark> marks = semesterService.getMarks(semester);

			// Generate OK response with the courses.
			responseEntity = responseEntityGenerator.generateOK(marks);

		} catch (Exception e) {

			// Log the error.
			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error.
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}
	
	// Gives all the grades for a semester.
	@GetMapping(URL + "/{semester}/grades")
	public ResponseEntity<?> grades(@PathVariable int semester) {

		Logger log = LogManager.getLogger(GradeController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the grades.
			SemesterService semesterService = new SemesterService();
			ArrayList<Grade> grades = semesterService.getGrades(semester);

			// Generate OK response with the grades.
			responseEntity = responseEntityGenerator.generateOK(grades);

		} catch (Exception e) {

			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();

		}

		return responseEntity;
	}

	// Gives the global grade for a semester.
	@GetMapping(URL + "/{semester}/global-grade")
	public ResponseEntity<?> globalGrade(@PathVariable int semester) {

		Logger log = LogManager.getLogger(GradeController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the grade.
			SemesterService semesterService = new SemesterService();
			GlobalGrade grade = semesterService.getGlobalGrade(semester);

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
