package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tools.GradeTools;
import dataObjects.GlobalGrade;
import dataObjects.Grade;
import persistence.SchoolDb2;

public class GlobalGradeService {
	
	private Logger mLogger;
	private SchoolDb2 mSchoolDb2;
	private GradeTools mGradeTools;

	public GlobalGradeService() throws ClassNotFoundException, SQLException, IOException {

		mLogger = LogManager.getLogger(GradeService.class);
		mSchoolDb2 = new SchoolDb2();
		mGradeTools = new GradeTools();
	}
	
	// Gives the global grade.
	public GlobalGrade getGlobalGrade() throws ClassNotFoundException, SQLException, IOException {

		mLogger.debug("getGlobalGrade()");

		GradeService gradeService = new GradeService();
		ArrayList<Grade> grades = gradeService.getGrades();

		// Calculate the global average, GPA and letter.
		float average = mGradeTools.getGlobalAverage(grades);
		float gpa = mGradeTools.getGlobalGpa(grades);
		String letter = mGradeTools.getLetter(average);

		return new GlobalGrade(average, gpa, letter);
	}
}
