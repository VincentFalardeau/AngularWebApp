package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dataObjects.Mark;
import exceptions.InvalidParameterFormatException;
import persistance.SchoolDb;

public class MarkServices {
	
	private SchoolDb mSchoolDb;
	
	public MarkServices() throws ClassNotFoundException, SQLException {
		mSchoolDb = SchoolDb.getInstance();
	}
	
	public ArrayList<Mark> getMarks(String semester) throws InvalidParameterFormatException, SQLException{
		ArrayList<Mark> marks;
		
		try {
			int semesterInt = Integer.parseInt(semester);
			
			marks = getMarks(semesterInt);
		}catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			
			throw new InvalidParameterFormatException("Invalid parameter format, semester expected to be an integer", nfe);
		}
		return marks;
	}
	
public ArrayList<Mark> getMarks(int semester) throws NumberFormatException, SQLException{
		return  mSchoolDb.getMarks(semester);
	}
}
