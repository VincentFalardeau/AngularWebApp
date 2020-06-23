package httpControllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Throwables;

import dataObjects.Mark;
import services.MarkService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GradeController {
	
	private final String URL = "grade";
	private final String MULTIPLE_GRADES_URL= URL + "s";
	private final String ALL_GRADES_URL = MULTIPLE_GRADES_URL + "/all";

	//Gives all the grades.
	@GetMapping(ALL_GRADES_URL)
	public ResponseEntity<?> grades() {
		
		Logger log = LogManager.getLogger(GradeController.class);
		
		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;
		
		try {
			
			//Get the grades.
			MarkService markService = new MarkService();
			ArrayList<Mark> marks = markService.getMarks();
			
			//Generate OK response with the marks.
			responseEntity = responseEntityGenerator.generateOK(marks);
			
		} catch (Exception e) {
			
			log.error(Throwables.getStackTraceAsString(e));
			
			//Generate internal server error
			responseEntity = responseEntityGenerator.generateInternalServerError();
			
		}
		
		return responseEntity;
	}
}
