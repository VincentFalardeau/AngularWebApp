package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataObjects.Category;
import persistence.SchoolDb2;

public class CategoryService {
	
	private Logger mLogger;
	private SchoolDb2 mSchoolDb2;
	
	public CategoryService() throws ClassNotFoundException, SQLException, IOException {
		
		mLogger = LogManager.getLogger(CategoryService.class);
		mSchoolDb2 = new SchoolDb2();
	}
	
	//Gives all the categories
	public ArrayList<Category> getCategories() throws SQLException, ClassNotFoundException {
		
		mLogger.debug("getCategories()");
		
		return mSchoolDb2.getCategories();
	}
}
