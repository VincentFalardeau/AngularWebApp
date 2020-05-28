package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dataObjects.Mark;
import exceptions.ParameterFormatException;
import exceptions.ParameterException;
import persistance.SchoolDb;

public class MarkServices {
	private SchoolDb mSchoolDb;
	
	public MarkServices() throws ClassNotFoundException, SQLException {
		mSchoolDb = SchoolDb.getInstance();
	}
	
	public ArrayList<Mark> getMarks() throws SQLException, IOException{
		return mSchoolDb.getMarks();
	}
	
	public ArrayList<Mark> getMarks(String semester) throws ParameterFormatException, SQLException, IOException{
		return this.getMarks(this.parseSemester(semester));
	}

	public ArrayList<Mark> getMarks(int semester) throws NumberFormatException, SQLException, IOException{
		return mSchoolDb.getMarks(semester);
	}
	
	public ArrayList<Mark> getMark(String idMark) throws ParameterFormatException, SQLException, IOException{
		return this.getMark(this.parseIdMark(idMark));
	}

	public ArrayList<Mark> getMark(int idMark) throws NumberFormatException, SQLException, IOException{
		return mSchoolDb.getMark(idMark);
	}
	
	public void addMark(
			String markParam, 
			String descriptionParam, 
			String weightParam, 
			String idCategoryParam, 
			String idCourseParam) throws ParameterFormatException, SQLException, ParameterException {
		
		float mark = this.parseMark(markParam);
		String description = descriptionParam;
		float weight = this.parseWeight(weightParam);
		int idCategory = this.parseIdCategory(idCategoryParam);
		int idCourse = this.parseIdCourse(idCourseParam);
		
		this.addMark(mark, description, weight, idCategory, idCourse);
	}
	
	public void addMark(
			float mark, 
			String description, 
			float weight, 
			int idCategory, 
			int idCourse) throws SQLException, ParameterException {
		
		try {
			//Try to add the mark
			mSchoolDb.addMark(mark, description, weight, idCategory, idCourse);
			
		}catch(SQLException se) {			
			//If it failed,
			//Make sure it is not because the category does not exist
			if(!mSchoolDb.categoryExists(idCategory)) {
				throw new ParameterException("Incorrect value for parameter idCategory", se, "idCategory");
			}
			//Make sure it is not because the course does not exist
			if(!mSchoolDb.courseExists(idCourse)) {
				throw new ParameterException("Incorrect value for parameter idCourse", se, "idCourse");
			}
			throw se;
		}
	}
	
	public void updateMark(
			String idMarkParam, 
			String markParam, 
			String descriptionParam, 
			String weightParam, 
			String idCategoryParam, 
			String idCourseParam) throws ParameterFormatException, SQLException, ParameterException {
		
		int idMark = this.parseIdMark(idMarkParam);
		float mark = this.parseMark(markParam);
		String description = descriptionParam;
		float weight = this.parseWeight(weightParam);
		int idCategory = this.parseIdCategory(idCategoryParam);
		int idCourse = this.parseIdCourse(idCourseParam);
		
		this.updateMark(idMark, mark, description, weight, idCategory, idCourse);
	}
	
	public void updateMark(
			int idMark,
			float mark, 
			String description, 
			float weight, 
			int idCategory, 
			int idCourse) throws SQLException, ParameterException {
		
		mSchoolDb.updateMark(idMark, mark, description, weight, idCategory, idCourse);
		
	}
	
	public void deleteMark(String idMarkParam) throws ParameterFormatException, SQLException {
		int idMark = this.parseIdMark(idMarkParam);
		this.deleteMark(idMark);
		
	}
	
	public void deleteMark(int idMark) throws SQLException {
		mSchoolDb.deleteMark(idMark);
	}
	
	private float parseMark(String mark) throws ParameterFormatException {
		return this.parseFloat(mark, "mark");
	}
	
	private float parseWeight(String weight) throws ParameterFormatException {
		return this.parseFloat(weight, "weight");
	}
	
	private int parseSemester(String semester) throws ParameterFormatException {
		return this.parseInteger(semester, "semester");
	}
	
	private int parseIdCategory(String idCategory) throws ParameterFormatException {
		return this.parseInteger(idCategory, "idCategory");
	}
	
	private int parseIdCourse(String idCourse) throws ParameterFormatException {
		return this.parseInteger(idCourse, "idCourse");
	}
	
	private int parseIdMark(String idMark) throws ParameterFormatException {
		return this.parseInteger(idMark, "idMark");
	}
	
	private int parseInteger(String numberStr, String parameterKey) throws ParameterFormatException {
		int number;
		try {
			number = Integer.parseInt(numberStr);
		} catch(NumberFormatException nfe) {
			throw new ParameterFormatException("Invalid parameter format, "+parameterKey+" expected to be an integer", nfe, parameterKey, "integer");
		}
		return number;
	}
	
	private float parseFloat(String numberStr, String parameterKey) throws ParameterFormatException {
		float number;
		try {
			number = Float.parseFloat(numberStr);
		} catch(NumberFormatException nfe) {
			throw new ParameterFormatException("Invalid parameter format, "+parameterKey+" expected to be a float", nfe, parameterKey, "float");
		}
		return number;
	}
}
