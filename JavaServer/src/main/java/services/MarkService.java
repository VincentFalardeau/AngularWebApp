package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataObjects.Mark;
import dataObjects.MarkData;
import dataObjects.MarkDataPrimitive;
import exceptions.ParameterException;
import persistence.SchoolDb2;

public class MarkService {
	
	private Logger logger;
	private SchoolDb2 mSchoolDb2;
	
	public MarkService() throws ClassNotFoundException, SQLException, IOException {
		
		logger = LogManager.getLogger(MarkService.class);
		mSchoolDb2 = new SchoolDb2();
	}
	
	//Gives all the marks.
	public ArrayList<Mark> getMarks() throws SQLException, IOException, ClassNotFoundException{
		
		logger.debug("getMarks()");
		
		return mSchoolDb2.getMarks();
	}

	//Gives the marks for a semester.
	public ArrayList<Mark> getMarks(int semester) throws NumberFormatException, SQLException, IOException, ClassNotFoundException{
		
		logger.debug("getMarks("+semester+")");
		
		return mSchoolDb2.getMarks(semester);
	}
	
	//Gives the marks of a course.
	public ArrayList<Mark> getCourseMarks(int idCourse) throws NumberFormatException, SQLException, IOException, ClassNotFoundException{
			
		logger.debug("getCourseMarks("+idCourse+")");
			
		return mSchoolDb2.getCourseMarks(idCourse);
	}

	//Gives the mark having the specified id.
	public Mark getMark(int idMark) throws NumberFormatException, SQLException, IOException, ClassNotFoundException{
		
		logger.debug("getMark("+idMark+")");
		
		return mSchoolDb2.getMark(idMark);
	}
	
	//Adds a mark.
	public void addMark(MarkDataPrimitive markDataPrimitive) throws ParameterException {
		
		logger.debug("addMark("+markDataPrimitive.toString()+")");
		
		try {
			mSchoolDb2.addMark(markDataPrimitive);
			
		}catch(PersistenceException pe) {
			
			if(mSchoolDb2.getCourse(markDataPrimitive.getIdCourse()) == null) {
				throw new ParameterException("idCourse not referring to an existing course", pe);
			}
			
			if(mSchoolDb2.getCategory(markDataPrimitive.getIdCategory()) == null) {
				throw new ParameterException("idCategory not referring to an existing category", pe);
			}
			
			throw pe;
		
		}
		
	}

	
	//Updates a mark.
	public void updateMark(MarkDataPrimitive markDataPrimitive) throws SQLException, ParameterException, ClassNotFoundException {
		
		logger.debug("updateMark("+markDataPrimitive.toString()+")");
		
		try {
			mSchoolDb2.updateMark(markDataPrimitive);
			
		}catch(PersistenceException pe) {	
			
			if(mSchoolDb2.getCourse(markDataPrimitive.getIdCourse()) == null) {
				throw new ParameterException("idCourse not referring to an existing course", pe);
			}
			
			if(mSchoolDb2.getCategory(markDataPrimitive.getIdCategory()) == null) {
				throw new ParameterException("idCategory not referring to an existing category", pe);
			}
			
			throw pe;
		}
		
		
	}

	//Deletes a mark.
	public void deleteMark(int idMark) throws SQLException, ClassNotFoundException {
		
		mSchoolDb2.deleteMark(idMark);
	}
}
