package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataObjects.Course;
import dataObjects.GlobalGrade;
import dataObjects.Grade;
import dataObjects.Mark;
import exceptions.ParameterException;
import persistence.SchoolDb2;

public class GradeService {

	private Logger logger;
	private SchoolDb2 mSchoolDb2;

	public GradeService() throws ClassNotFoundException, SQLException, IOException {

		logger = LogManager.getLogger(GradeService.class);
		mSchoolDb2 = new SchoolDb2();
	}



	// Gives all the grades.
	public ArrayList<Grade> getGrades() throws SQLException, ClassNotFoundException, IOException {

		logger.debug("getGrades()");

		ArrayList<Grade> grades = new ArrayList<Grade>();
		ArrayList<Course> courses = mSchoolDb2.getCourses();
		
		CourseService courseService = new CourseService();

		for (Course course : courses) {
			Grade grade = courseService.getGrade(course);
			//To avoid classes with no marks to be counted.
			if(grade.getAverage() > 0.0f) {
				grades.add(grade);
			}
			
		}

		return grades;
	}



}
