package httpControllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Throwables;

import dataObjects.Category;
import services.CategoryService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CategoryController {
	
	private final String URL= "/categories";
	private final String ALL_CATEGORIES_URL = URL + "/all";
	
	//Gives all the categories.
	@GetMapping(ALL_CATEGORIES_URL)
	public ResponseEntity<?> marks() {
		
		Logger log = LogManager.getLogger(MarkController.class);
		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;
		
		log.info("GET \""+ALL_CATEGORIES_URL+"\", parameters={}");
		
		try {

			CategoryService categoryService = new CategoryService();
			ArrayList<Category> categories = categoryService.getCategories();
			
			responseEntity = responseEntityGenerator.generateOK(categories);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			log.error(Throwables.getStackTraceAsString(e));
			
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}
		return responseEntity;
	}
}
