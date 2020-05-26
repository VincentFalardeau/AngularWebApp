package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dataObjects.Mark;
import exceptions.ParameterFormatException;
import persistance.SchoolDb;

public class MarkServices {
	
	private SchoolDb mSchoolDb;
	
	public MarkServices() throws ClassNotFoundException, SQLException {
		mSchoolDb = SchoolDb.getInstance();
	}
	
	public ArrayList<Mark> getMarks(String semester) throws ParameterFormatException, SQLException, IOException{
		ArrayList<Mark> marks;
		
		try {
			int semesterInt = Integer.parseInt(semester);
			
			marks = getMarks(semesterInt);
			
		}catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			
			String errorMessage = "Invalid parameter format, semester expected to be an integer";
			String parameterKey = "semester";
			String expectedType = "integer";
			throw new ParameterFormatException(errorMessage, nfe, parameterKey, expectedType);
		}
		return marks;
	}
	
	public ArrayList<Mark> getMarks(int semester) throws NumberFormatException, SQLException, IOException{
		return mSchoolDb.getMarks(semester);
	}
}
