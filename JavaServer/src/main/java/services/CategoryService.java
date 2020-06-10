package services;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataObjects.Category;
import persistence.SchoolDb;
import persistence.SchoolDb2;

public class CategoryService {
	
	private Logger logger = LogManager.getLogger(CategoryService.class);
	
	private SchoolDb mSchoolDb;
	private SchoolDb2 mSchoolDb2;
	
	public CategoryService() throws ClassNotFoundException, SQLException {
		mSchoolDb = new SchoolDb();
	}
	
	//Gives all the categories
	public ArrayList<Category> getCategories() throws SQLException, ClassNotFoundException {
		
		logger.debug("getCategories()");
		
		//return mSchoolDb.getCategories();
		
		return mSchoolDb2.getCategories();
	}
}
