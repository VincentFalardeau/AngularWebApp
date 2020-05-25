package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dataObjects.Mark;
import persistance.SchoolDb;

public class MarkServices {
	
	private SchoolDb mSchoolDb;
	
	public MarkServices() throws ClassNotFoundException, SQLException {
		mSchoolDb = SchoolDb.getInstance();
	}
	
	public ArrayList<Mark> getMarks(String semester){
		
		//TODO: Convert the semester to a valid integer, throw custom errors (cause its user's fault) if not a valid integer
		
		//return mSchoolDb.getMarks(semester);
		return null;
	}
}
