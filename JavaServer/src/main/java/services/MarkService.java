package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import dataObjects.Mark;
import exceptions.ParameterException;
import persistence.SchoolDb;

//Provides the service for marks
public class MarkService {
	
	//private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private SchoolDb mSchoolDb;
	
	//Constructor.
	public MarkService() throws ClassNotFoundException, SQLException {
		mSchoolDb = new SchoolDb();
	}
	
	//Gives all the marks.
	public ArrayList<Mark> getMarks() throws SQLException, IOException, ClassNotFoundException{
		
		//LOGGER.fine("getMarks()");
		
		return mSchoolDb.getMarks();
	}

	//Gives the marks for a semester.
	public ArrayList<Mark> getMarks(int semester) throws NumberFormatException, SQLException, IOException, ClassNotFoundException{
		
		//LOGGER.fine("getMarks("+semester+")");
		
		return mSchoolDb.getMarks(semester);
	}

	//Gives the mark having the specified id.
	public Mark getMark(int idMark) throws NumberFormatException, SQLException, IOException, ClassNotFoundException{
		
		//LOGGER.fine("getMark("+idMark+")");
		
		return mSchoolDb.getMark(idMark);
	}
	
	//Adds a mark.
	public void addMark(float mark, String description, float weight, int idCategory, int idCourse) throws SQLException, ParameterException, ClassNotFoundException {
		
		//LOGGER.fine("addMark("+mark+", "+description+", "+weight+", "+idCategory+", "+idCourse+")");
		
		try {
			mSchoolDb.addMark(mark, description, weight, idCategory, idCourse);
			
		}catch(SQLException se) {			
			
			//Check if SQLException is thrown because idCategory and/or idCourse refer to nothing.
			if(!mSchoolDb.categoryExists(idCategory) || !mSchoolDb.courseExists(idCourse)) {
				
				//If it is the case, throw a parameter exception.
				throw new ParameterException("ParameterException: idCategory or idCourse not refering to anything in the database.", se);
			}
			//Otherwise, throw the SQLException.
			throw se;
		}
	}
	
	//Updates a mark.
	public void updateMark(int idMark,float mark, String description, float weight, int idCategory, int idCourse) throws SQLException, ParameterException, ClassNotFoundException {
		
		//LOGGER.fine("updateMark("+idMark+", "+mark+", "+description+", "+weight+", "+idCategory+", "+idCourse+")");
		
		try {
			mSchoolDb.updateMark(idMark, mark, description, weight, idCategory, idCourse);
			
		}catch(SQLException se) {	
			
			//Check if SQLException is thrown because idCategory and/or idCourse refer to nothing.
			if(!mSchoolDb.categoryExists(idCategory) || !mSchoolDb.courseExists(idCourse)) {
				
				//If it is the case, throw a parameter exception.
				throw new ParameterException("ParameterException: idCategory or idCourse not refering to anything in the database.", se);
			}
			//Otherwise, throw the SQLException.
			throw se;
		}
		
		
	}

	//Deletes a mark.
	public void deleteMark(int idMark) throws SQLException, ClassNotFoundException {
		
		//LOGGER.fine("deleteMark("+idMark+")");
		
		mSchoolDb.deleteMark(idMark);
	}

}
