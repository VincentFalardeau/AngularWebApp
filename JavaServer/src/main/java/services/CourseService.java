package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dataObjects.Course;
import persistence.SchoolDb;

public class CourseService {
private SchoolDb mSchoolDb;
	
	//Constructor.
	public CourseService() throws ClassNotFoundException, SQLException {
		mSchoolDb = new SchoolDb();
	}
	
	//Gives all the categories
	public ArrayList<Course> getCourses() throws SQLException, ClassNotFoundException {
		return mSchoolDb.getCourses();
	}
}
