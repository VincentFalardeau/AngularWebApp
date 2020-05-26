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
	
	//Gives the marks of a semester
	//Takes semesterParam, a String representing the semester.
	//Returns an array of mark.
	public ArrayList<Mark> getMarks(String semesterParam) throws ParameterFormatException, SQLException, IOException{
		ArrayList<Mark> marks;
		
		try {
			//Try to convert semesterParam to an integer.
			int semester = Integer.parseInt(semesterParam);
			
			//Get the marks
			marks = getMarks(semester);
			
		}catch(NumberFormatException nfe) {
			//nfe.printStackTrace();
			
			//In case semesterParam is not an integer, throw an exception
			String errorMessage = "Invalid parameter format, semester expected to be an integer";
			String parameterKey = "semester";
			String expectedType = "integer";
			throw new ParameterFormatException(errorMessage, nfe, parameterKey, expectedType);
		}
		
		return marks;
	}

	//Gives the marks of a semester.
	//Takes semester, an integer representing the semester.
	//Returns an array of marks.
	public ArrayList<Mark> getMarks(int semester) throws NumberFormatException, SQLException, IOException{
		return mSchoolDb.getMarks(semester);
	}
	
	//Adds a new mark.
	//Takes Strings representing the values of a mark in the database.
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
		
		//Add the mark
		this.addMark(mark, description, weight, idCategory, idCourse);
	}
	
	//Adds a new mark.
	//Takes the values of a mark in the database
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
			
			//se.printStackTrace();
			
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
		
		//Update the mark
		this.updateMark(idMark, mark, description, weight, idCategory, idCourse);
	}
	
	public void updateMark(
			int idMark,
			float mark, 
			String description, 
			float weight, 
			int idCategory, 
			int idCourse) throws SQLException, ParameterException {
		
		try {
			//Try to update the mark
			mSchoolDb.updateMark(idMark, mark, description, weight, idCategory, idCourse);
			
		}catch(SQLException se) {				
			//se.printStackTrace();
			throw se;
		}
		
	}
	
	
	private float parseMark(String markParam) throws ParameterFormatException {
		//Try to convert the mark to a float.
		float mark;
		try {
			mark = Float.parseFloat(markParam);
			
		}catch(NumberFormatException nfe) {
			//nfe.printStackTrace();
			
			String errorMessage = "Invalid parameter format, mark expected to be a float";
			String parameterKey = "mark";
			String expectedType = "float";
			throw new ParameterFormatException(errorMessage, nfe, parameterKey, expectedType);
		}
		return mark;
	}
	
	private float parseWeight(String weightParam) throws ParameterFormatException {
		//Try to convert the weight to a float.
		float weight;
		try {
			weight = Float.parseFloat(weightParam);
			
		}catch(NumberFormatException nfe) {
			//nfe.printStackTrace();
			
			String errorMessage = "Invalid parameter format, weight expected to be a float";
			String parameterKey = "weight";
			String expectedType = "float";
			throw new ParameterFormatException(errorMessage, nfe, parameterKey, expectedType);
		}
		return weight;
	}
	
	private int parseIdCategory(String idCategoryStr) throws ParameterFormatException {
		//Try to convert the category id to an integer.
		int idCategory;
		try {
			idCategory = Integer.parseInt(idCategoryStr);
			
		}catch(NumberFormatException nfe) {
			//nfe.printStackTrace();
			
			String errorMessage = "Invalid parameter format, idCategory expected to be an integer";
			String parameterKey = "idCategory";
			String expectedType = "integer";
			throw new ParameterFormatException(errorMessage, nfe, parameterKey, expectedType);
		}
		return idCategory;
	}
	
	private int parseIdCourse(String idCourseStr) throws ParameterFormatException {
		//Try to convert the course id to an integer.
		int idCourse;
		try {
			idCourse = Integer.parseInt(idCourseStr);
			
		}catch(NumberFormatException nfe) {
			//nfe.printStackTrace();
			
			String errorMessage = "Invalid parameter format, idCourse expected to be an integer";
			String parameterKey = "idCourse";
			String expectedType = "integer";
			throw new ParameterFormatException(errorMessage, nfe, parameterKey, expectedType);
		}
		return idCourse;
	}
	
	private int parseIdMark(String idMarkStr) throws ParameterFormatException {
		//Try to convert the mark id to an integer.
		int idMark;
		try {
			idMark = Integer.parseInt(idMarkStr);
			
		}catch(NumberFormatException nfe) {
			//nfe.printStackTrace();
			
			String errorMessage = "Invalid parameter format, idMark expected to be an integer";
			String parameterKey = "idMark";
			String expectedType = "integer";
			throw new ParameterFormatException(errorMessage, nfe, parameterKey, expectedType);
		}
		return idMark;
	}
}
