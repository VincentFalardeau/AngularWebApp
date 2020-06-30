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

import dataObjects.Course;
import dataObjects.Grade;
import dataObjects.Mark;
import services.CourseService;
import services.GradeService;
import services.MarkService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CourseController {

	private final String URL = "/courses";

	// Gives all the courses.
	@GetMapping(URL)
	public ResponseEntity<?> getCourses() {

		Logger log = LogManager.getLogger(CourseController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the courses.
			CourseService courseService = new CourseService();
			ArrayList<Course> courses = courseService.getCourses();

			// Generate OK response with the courses.
			responseEntity = responseEntityGenerator.generateOK(courses);

		} catch (Exception e) {

			// Log the error.
			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error.
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}
	
	// Gives all the marks for a course.
	@GetMapping(URL + "/{id}/marks")
	public ResponseEntity<?> getCourseMarks(@PathVariable int id) {

		Logger log = LogManager.getLogger(CourseController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the courses.
			CourseService courseService = new CourseService();
			ArrayList<Mark> marks = courseService.getMarks(id);

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
	
	// Gives all the marks for a course.
	@GetMapping(URL + "/{id}/grade")
	public ResponseEntity<?> getCourseGrade(@PathVariable int id) {

		Logger log = LogManager.getLogger(GradeController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the grade.
			CourseService courseService = new CourseService();
			Grade grade = courseService.getGrade(id);

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
