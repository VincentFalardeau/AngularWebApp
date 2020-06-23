package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataObjects.Course;
import dataObjects.Grade;
import dataObjects.Mark;
import persistence.SchoolDb2;

public class GradeService {
	
	private Logger logger;
	private SchoolDb2 mSchoolDb2;
	
	public GradeService() throws ClassNotFoundException, SQLException, IOException {
		
		logger = LogManager.getLogger(GradeService.class);
		mSchoolDb2 = new SchoolDb2();
	}
	
	//Gives all the grades
	public ArrayList<Grade> getGrades() throws SQLException, ClassNotFoundException {
		
		logger.debug("getGrades()");
		
		ArrayList<Grade> grades = new ArrayList<Grade>();
		ArrayList<Course> courses = mSchoolDb2.getCourses();
		
		for (Course course : courses) {
			grades.add(this.getGrade(course.getId()));
		}
		
		return null;
	}
	
	private Grade getGrade(int idCourse) {
		
		ArrayList<Mark> marks = mSchoolDb2.getCourseMarks(idCourse);
		
		
		
		return null;
		
	}

}
