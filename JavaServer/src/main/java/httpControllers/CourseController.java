package httpControllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Throwables;

import dataObjects.Course;
import services.CourseService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CourseController {

	private final String URL = "/courses";
	private final String ALL_COURSES_URL = URL + "/all";

	// Gives all the courses.
	@GetMapping(ALL_COURSES_URL)
	public ResponseEntity<?> courses() {

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
}
