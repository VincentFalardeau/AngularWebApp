package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import dataObjects.Category;
import persistence.SchoolDb;

public class CategoryService {
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private SchoolDb mSchoolDb;
	
	//Constructor.
	public CategoryService() throws ClassNotFoundException, SQLException {
		mSchoolDb = new SchoolDb();
	}
	
	//Gives all the categories
	public ArrayList<Category> getCategories() throws SQLException, ClassNotFoundException {
		
		LOGGER.fine("getCategories()");
		
		return mSchoolDb.getCategories();
	}
}
