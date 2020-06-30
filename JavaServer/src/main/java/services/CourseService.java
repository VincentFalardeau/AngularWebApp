package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tools.GradeTools;
import dataObjects.Course;
import dataObjects.Grade;
import dataObjects.Mark;
import exceptions.ParameterException;
import persistence.SchoolDb2;

public class CourseService {

	private Logger mLogger;
	private SchoolDb2 mSchoolDb2;
	private GradeTools mGradeTools;

	public CourseService() throws ClassNotFoundException, SQLException, IOException {

		mLogger = LogManager.getLogger(CourseService.class);
		mSchoolDb2 = new SchoolDb2();
		mGradeTools = new GradeTools();
	}

	// Gives all the courses
	public ArrayList<Course> getCourses() throws SQLException, ClassNotFoundException {

		mLogger.debug("getCourses()");

		return mSchoolDb2.getCourses();
	}
	
	//Gives the marks of a course.
	public ArrayList<Mark> getMarks(int id) throws NumberFormatException, SQLException, IOException, ClassNotFoundException{
			
		mLogger.debug("getMarks("+id+")");
			
		return mSchoolDb2.getCourseMarks(id);
	}
	
	// Gives the grade of the course having the specified id.
	public Grade getGrade(int id) throws ParameterException {

		mLogger.debug("getGrade(" + id + ")");

		Course course= null;
		Grade grade = null;

		try {
			course = mSchoolDb2.getCourse(id);

			grade = this.getGrade(course);

		} catch (PersistenceException pe) {

			if (mSchoolDb2.getCourse(id) == null) {
				throw new ParameterException("idCourse not referring to an existing course", pe);
			}

		}

		return grade;
	}
	
	// Gives the grade of the specified course.
	public Grade getGrade(Course course) {

		mLogger.debug("getGrade("+course.toString()+")");

		// Get the course's marks.
		ArrayList<Mark> marks = mSchoolDb2.getCourseMarks(course.getId());

		// Calculate the average.
		float average = mGradeTools.getAverage(marks);

		// Get the GPA and the letter of the average.
		float gpa = mGradeTools.getGpa(average);
		String letter = mGradeTools.getLetter(average);

		return new Grade(course, average, gpa, letter);
	}
}
