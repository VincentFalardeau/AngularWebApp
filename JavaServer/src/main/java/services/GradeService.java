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

	// Gives the global grade.
	public GlobalGrade getGlobalGrade() throws ClassNotFoundException, SQLException {

		logger.debug("getGlobalGrade()");

		ArrayList<Grade> grades = this.getGrades();

		// Calculate the global average, GPA and letter.
		float average = this.getGlobalAverage(grades);
		float gpa = this.getGlobalGpa(grades);
		String letter = this.getLetter(average);

		return new GlobalGrade(average, gpa, letter);
	}

	// Gives the global grade for a semester
	public GlobalGrade getGlobalGrade(int semester) throws ClassNotFoundException, SQLException {

		logger.debug("getGlobalGrade(" + semester + ")");

		ArrayList<Grade> grades = this.getGrades(semester);

		// Calculate the global average, GPA and letter.
		float average = this.getGlobalAverage(grades);
		float gpa = this.getGlobalGpa(grades);
		String letter = this.getLetter(average);

		return new GlobalGrade(semester, average, gpa, letter);
	}

	// Gives all the grades.
	public ArrayList<Grade> getGrades() throws SQLException, ClassNotFoundException {

		logger.debug("getGrades()");

		ArrayList<Grade> grades = new ArrayList<Grade>();
		ArrayList<Course> courses = mSchoolDb2.getCourses();

		for (Course course : courses) {
			grades.add(this.getGrade(course));
		}

		return grades;
	}

	// Gives all the grades for a semester.
	public ArrayList<Grade> getGrades(int semester) throws SQLException, ClassNotFoundException {

		logger.debug("getGrades(" + semester + ")");

		ArrayList<Grade> grades = new ArrayList<Grade>();
		ArrayList<Course> courses = mSchoolDb2.getCourses(semester);

		for (Course course : courses) {
			grades.add(this.getGrade(course));
		}

		return grades;
	}

	// Gives the grade of the course having the specified id.
	public Grade getGrade(int idCourse) throws ParameterException {

		logger.debug("getGrade(" + idCourse + ")");

		Course course= null;
		Grade grade = null;

		try {
			course = mSchoolDb2.getCourse(idCourse);

			grade = this.getGrade(course);

		} catch (PersistenceException pe) {

			if (mSchoolDb2.getCourse(idCourse) == null) {
				throw new ParameterException("idCourse not referring to an existing course", pe);
			}

		}

		return grade;
	}

	// Gives the grade for the specified course.
	private Grade getGrade(Course course) {

		logger.debug("getGrade("+course.toString()+")");

		// Get the course's marks.
		ArrayList<Mark> marks = mSchoolDb2.getCourseMarks(course.getId());

		// Calculate the average.
		float average = this.getAverage(marks);

		// Get the GPA and the letter of the average.
		float gpa = this.getGpa(average);
		String letter = this.getLetter(average);

		return new Grade(course, average, gpa, letter);
	}

	// Gives the weighted average of an array of grades.
	private float getGlobalAverage(ArrayList<Grade> grades) {

		float total = 0.0f;
		float totalWeight = 0.0f;

		for (Grade grade : grades) {

			float weight = grade.getCourse().getCredits();

			total += grade.getAverage() * weight;
			totalWeight += weight;
		}

		return total / totalWeight;
	}

	// Gives the weighted GPA of an array of grades.
	private float getGlobalGpa(ArrayList<Grade> grades) {

		float total = 0.0f;
		float totalWeight = 0.0f;

		for (Grade grade : grades) {

			float weight = grade.getCourse().getCredits();

			total += grade.getGpa() * weight;
			totalWeight += weight;
		}

		return total / totalWeight;
	}

	// Gives the weighted average of an array of marks.
	private float getAverage(ArrayList<Mark> marks) {

		float total = 0.0f;
		float totalWeight = 0.0f;

		for (Mark mark : marks) {

			float weight = mark.getWeight();

			total += mark.getMark() * weight;
			totalWeight += weight;
		}

		if (totalWeight == 0.0f) {
			totalWeight = 1.0f;
		}

		return total / totalWeight;
	}

	// Gives the GPA of a mark.
	private float getGpa(float mark) {

		if (mark >= 90) {
			return 4.3f;
		} else if (mark >= 85) {
			return 4.0f;
		} else if (mark >= 80) {
			return 3.7f;
		} else if (mark >= 77) {
			return 3.3f;
		} else if (mark >= 73) {
			return 3.0f;
		} else if (mark >= 70) {
			return 2.7f;
		} else if (mark >= 65) {
			return 2.3f;
		} else if (mark >= 60) {
			return 2.0f;
		} else if (mark >= 57) {
			return 1.7f;
		} else if (mark >= 54) {
			return 1.3f;
		} else if (mark >= 50) {
			return 1.0f;
		} else if (mark >= 35) {
			return 0.5f;
		}
		return 0.0f;
	}

	// Gives the letter of a mark.
	private String getLetter(float mark) {

		if (mark >= 90) {
			return "A+";
		} else if (mark >= 85) {
			return "A";
		} else if (mark >= 80) {
			return "A-";
		} else if (mark >= 77) {
			return "B+";
		} else if (mark >= 73) {
			return "B";
		} else if (mark >= 70) {
			return "B-";
		} else if (mark >= 65) {
			return "C+";
		} else if (mark >= 60) {
			return "C";
		} else if (mark >= 57) {
			return "C-";
		} else if (mark >= 54) {
			return "D+";
		} else if (mark >= 50) {
			return "D";
		} else if (mark >= 35) {
			return "E";
		}
		return "F";
	}

}
