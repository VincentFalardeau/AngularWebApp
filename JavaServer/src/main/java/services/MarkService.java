package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataObjects.Mark;
import dataObjects.MarkData;
import dataObjects.MarkDataPrimitive;
import exceptions.ParameterException;
import persistence.SchoolDb2;

public class MarkService {
	
	private Logger mLogger;
	private SchoolDb2 mSchoolDb2;
	
	public MarkService() throws ClassNotFoundException, SQLException, IOException {
		
		mLogger = LogManager.getLogger(MarkService.class);
		mSchoolDb2 = new SchoolDb2();
	}
	
	//Gives all the marks.
	public ArrayList<Mark> getMarks() throws SQLException, IOException, ClassNotFoundException{
		
		mLogger.debug("getMarks()");
		
		return mSchoolDb2.getMarks();
	}

	//Gives the mark having the specified id.
	public Mark getMark(int idMark) throws NumberFormatException, SQLException, IOException, ClassNotFoundException{
		
		mLogger.debug("getMark("+idMark+")");
		
		return mSchoolDb2.getMark(idMark);
	}
	
	//Adds a mark.
	public void addMark(MarkDataPrimitive markDataPrimitive) throws Exception {
		
		mLogger.debug("addMark("+markDataPrimitive.toString()+")");
		
		try {
			mSchoolDb2.addMark(new MarkData(markDataPrimitive));
			
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
	public void updateMark(MarkDataPrimitive markDataPrimitive) throws Exception {
		
		mLogger.debug("updateMark("+markDataPrimitive.toString()+")");
		
		try {
			mSchoolDb2.updateMark(new MarkData(markDataPrimitive));
			
		}catch(PersistenceException pe) {
			
			if(mSchoolDb2.getMark(markDataPrimitive.getId()) == null) {
				throw new ParameterException("idCourse not referring to an existing course", pe);
			}
			
			if(mSchoolDb2.getCourse(markDataPrimitive.getIdCourse()) == null) {
				throw new ParameterException("idCourse not referring to an existing course", pe);
			}
			
			if(mSchoolDb2.getCategory(markDataPrimitive.getIdCategory()) == null) {
				throw new ParameterException("idCategory not referring to an existing category", pe);
			}
			
			throw pe;
		}
		
		
	}
	
	//Updates an array of mark.
	public void updateMark(MarkDataPrimitive[] marks) throws Exception {
		
		mLogger.debug("updateMark("+marks.toString()+")");
		
		try {
			
			ArrayList<MarkData> list = new ArrayList<MarkData>();
			
			for(MarkDataPrimitive mark : marks) {
				list.add(new MarkData(mark));
			}
			
			mSchoolDb2.updateMark(list);
			
		}catch(PersistenceException pe) {	
			
			for(MarkDataPrimitive mark : marks) {
				
				if(mSchoolDb2.getMark(mark.getId()) == null) {
					throw new ParameterException("idCourse not referring to an existing course", pe);
				}
				
				if(mSchoolDb2.getCourse(mark.getIdCourse()) == null) {
					throw new ParameterException("idCourse not referring to an existing course", pe);
				}
				
				if(mSchoolDb2.getCategory(mark.getIdCategory()) == null) {
					throw new ParameterException("idCategory not referring to an existing category", pe);
				}
			}
			
			throw pe;
		}
		
		
	}
	
	

	//Deletes a mark.
	public void deleteMark(int idMark) throws SQLException, ClassNotFoundException {
		
		mSchoolDb2.deleteMark(idMark);
	}
}
