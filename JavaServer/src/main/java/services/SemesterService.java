package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tools.GradeTools;
import dataObjects.Course;
import dataObjects.GlobalGrade;
import dataObjects.Grade;
import dataObjects.Mark;
import persistence.SchoolDb2;

public class SemesterService {

	private Logger mLogger;
	private SchoolDb2 mSchoolDb2;
	private GradeTools mGradeTools;

	public SemesterService() throws ClassNotFoundException, SQLException, IOException {

		mLogger = LogManager.getLogger(CourseService.class);
		mSchoolDb2 = new SchoolDb2();
		mGradeTools = new GradeTools();
	}
	
	//Gives the marks for a semester.
	public ArrayList<Mark> getMarks(int semester) throws NumberFormatException, SQLException, IOException, ClassNotFoundException{
		
		mLogger.debug("getMarks("+semester+")");
		
		return mSchoolDb2.getMarks(semester);
	}
	
	// Gives the global grade for a semester
	public GlobalGrade getGlobalGrade(int semester) throws ClassNotFoundException, SQLException, IOException {

		mLogger.debug("getGlobalGrade(" + semester + ")");

		ArrayList<Grade> grades = this.getGrades(semester);

		// Calculate the global average, GPA and letter.
		float average = mGradeTools.getGlobalAverage(grades);
		float gpa = mGradeTools.getGlobalGpa(grades);
		String letter = mGradeTools.getLetter(average);

		return new GlobalGrade(semester, average, gpa, letter);
	}
	
	// Gives all the grades for a semester.
	public ArrayList<Grade> getGrades(int semester) throws SQLException, ClassNotFoundException, IOException {

		mLogger.debug("getGrades(" + semester + ")");

		ArrayList<Grade> grades = new ArrayList<Grade>();
		ArrayList<Course> courses = mSchoolDb2.getCourses(semester);
		
		CourseService courseService = new CourseService();

		for (Course course : courses) {
			grades.add(courseService.getGrade(course));
		}

		return grades;
	}
}
