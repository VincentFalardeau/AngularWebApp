package httpControllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Throwables;

import dataObjects.GlobalGrade;
import services.GradeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GlobalGradeController {

	// Gives the global grade.
	@GetMapping("global-grade")
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
}
