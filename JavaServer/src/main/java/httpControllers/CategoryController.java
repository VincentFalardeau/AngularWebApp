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

	private final String URL = "/categories";
	private final String ALL_CATEGORIES_URL = URL + "/all";

	// Gives all the categories.
	@GetMapping(ALL_CATEGORIES_URL)
	public ResponseEntity<?> categories() {

		Logger log = LogManager.getLogger(CategoryController.class);

		ResponseEntityGenerator responseEntityGenerator = new ResponseEntityGenerator();
		ResponseEntity<?> responseEntity;

		try {

			// Get the categories.
			CategoryService categoryService = new CategoryService();
			ArrayList<Category> categories = categoryService.getCategories();

			// Generate OK response with the categories.
			responseEntity = responseEntityGenerator.generateOK(categories);

		} catch (Exception e) {

			// Log the error.
			log.error(Throwables.getStackTraceAsString(e));

			// Generate internal server error.
			responseEntity = responseEntityGenerator.generateInternalServerError();
		}

		return responseEntity;
	}

}
