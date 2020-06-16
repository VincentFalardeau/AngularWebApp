package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataObjects.Course;
import persistence.SchoolDb;
import persistence.SchoolDb2;

public class CourseService {

	private Logger logger;
	private SchoolDb2 mSchoolDb2;
	
	public CourseService() throws ClassNotFoundException, SQLException, IOException {
		
		logger = LogManager.getLogger(CourseService.class);
		mSchoolDb2 = new SchoolDb2();
	}
	
	//Gives all the courses
	public ArrayList<Course> getCourses() throws SQLException, ClassNotFoundException {
		
		logger.debug("getCourses()");
		
		return mSchoolDb2.getCourses();
	}
}
