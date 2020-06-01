package services;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import dataObjects.Category;
import persistence.SchoolDb;

public class CategoryService {
	
	private SchoolDb mSchoolDb;
	
	//Constructor.
	public CategoryService() throws ClassNotFoundException, SQLException {
		mSchoolDb = new SchoolDb();
	}
	
	//Gives all the categories
	public ArrayList<Category> getCategories() throws SQLException, ClassNotFoundException {
		return mSchoolDb.getCategories();
	}
}
