package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import dataObjects.Course;
import persistence.SchoolDb;

public class CourseService {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private SchoolDb mSchoolDb;
	
	//Constructor.
	public CourseService() throws ClassNotFoundException, SQLException {
		mSchoolDb = new SchoolDb();
	}
	
	//Gives all the categories
	public ArrayList<Course> getCourses() throws SQLException, ClassNotFoundException {
		
		LOGGER.fine("getCourses()");
		
		return mSchoolDb.getCourses();
	}
}
