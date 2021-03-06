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

	private final String URL = "grades";

	// Gives all the grades.
	@GetMapping(URL)
	public ResponseEntity<?> getGrades() {

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
}
