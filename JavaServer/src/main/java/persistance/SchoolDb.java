package persistance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataObjects.*;

//Class allowing interactions with schooldb MySQL database.
public class SchoolDb {

	//Connection to the database.
	private Connection mConnection = null;
	    
	//Constructor that initializes the connection to the database.
	public SchoolDb() throws SQLException, ClassNotFoundException { 
		
		//TODO: Credentials from a property file with Spring JDBC.
		mConnection = MySqlConnection.getInstance("root", "!h4zastkR", "jdbc:mysql://localhost:3306/schooldb").getConnection();
	}
	
	//TODO: Get SQL queries from SQL files with Spring JDBC.
	
	//Gives the query that selects all the marks.
	private String getMarksQuery() { 
		
		String query = "select \n" + 
			"m.idMark as idMark, m.idClass as idClass, m.idCategory as idCategory, \n" + 
			"m.description as markDescription, m.mark as mark, m.weight as weight, \n" + 
			"ca.description as categoryDescription,\n" + 
			"cl.code as classCode, cl.semester as semester, cl.description as classDescription, cl.credits as classCredits\n" + 
			"from Mark m \n" + 
			"inner join Category ca on ca.idCategory = m.idCategory\n" + 
			"inner join Class cl on cl.idClass = m.idClass";
		
		return query;
	}
	
	//Gives the query that selects all the marks for a specified semester.
	private String getMarksQuery(int semester) { 
		
		String query = "select \n" + 
			"m.idMark as idMark, m.idClass as idClass, m.idCategory as idCategory, \n" + 
			"m.description as markDescription, m.mark as mark, m.weight as weight, \n" + 
			"ca.description as categoryDescription,\n" + 
			"cl.code as classCode, cl.semester as semester, cl.description as classDescription, cl.credits as classCredits\n" + 
			"from Mark m \n" + 
			"inner join Category ca on ca.idCategory = m.idCategory\n" + 
			"inner join Class cl on cl.idClass = m.idClass\n" +
			"where semester = " + semester;
		
		return query;
	}
	
	//Gives the query that selects the mark having the specified id.
	private String getMarkQuery(int idMark) { 
		
		String query = "select \n" + 
			"m.idMark as idMark, m.idClass as idClass, m.idCategory as idCategory, \n" + 
			"m.description as markDescription, m.mark as mark, m.weight as weight, \n" + 
			"ca.description as categoryDescription,\n" + 
			"cl.code as classCode, cl.semester as semester, cl.description as classDescription, cl.credits as classCredits\n" + 
			"from Mark m \n" + 
			"inner join Category ca on ca.idCategory = m.idCategory\n" + 
			"inner join Class cl on cl.idClass = m.idClass\n" +
			"where idMark = " + idMark;
		
		return query;
	}
	
	//Gives the query that selects the category having the specified id.
	private String getCategoryQuery(int id) {
		String query = "select * from Category where idCategory = " + id;
		
		return query;
	}
	
	//Gives the query that selects the class having the specified id.
	private String getCourseQuery(int id) {
		String query = "select * from Class where idClass = " + id;
		
		return query;
	}
	
	//Gives all the marks.
	public ArrayList<Mark> getMarks() throws SQLException, IOException{
		
		//Fetch the query's result into a result set.
		Statement st = mConnection.createStatement();
		ResultSet rs = st.executeQuery(this.getMarksQuery());
				
		//Make an array of dataObjects from the result set.
		return marksFrom(rs);
				
	}
	
	//Gives the marks for a semester.
	public ArrayList<Mark> getMarks(int semester) throws SQLException, IOException{
		
		//Fetch the query's result into a result set.
		Statement st = mConnection.createStatement();
		ResultSet rs = st.executeQuery(this.getMarksQuery(semester));
		
		//Make an array of dataObjects from the result set.
		return marksFrom(rs);
		
	}
	
	//Gives the mark having idMark as id.
	public Mark getMark(int idMark) throws SQLException, IOException{
					
		//Fetch the query's result into a result set.
		Statement st = mConnection.createStatement();
		ResultSet rs = st.executeQuery(this.getMarkQuery(idMark));
		
		//Make an array of dataObjects from the result set.
		ArrayList<Mark> marks = marksFrom(rs);
		
		//Initialize a Mark to the first and only (in theory) element of marks, if marks is not empty.
		Mark mark = null;
		if(marks.size() > 0) {
			mark = marks.get(0);
		}
		
		//Return that Mark.			
		return mark;
					
	}
	
	//Gives an array of Mark dataObjects from the given result set.
	private ArrayList<Mark> marksFrom(ResultSet rs) throws SQLException{
		
		//The array.
		ArrayList<Mark> marks = new ArrayList<Mark>();
		
		//Make dataObjects from every row of the result set.
		while(rs.next()) {
			
			//Id
			int idMark = rs.getInt(1);
			int idClass = rs.getInt(2);
			int idCategory = rs.getInt(3);
			
			//Mark
			String markDescription = rs.getString(4);
			float mark = rs.getFloat(5);
			float weight = rs.getFloat(6);
			
			//Category
			String categoryDescription = rs.getString(7);
			
			//Course
			String classCode = rs.getString(8);
			int semester = rs.getInt(9);
			String classDescription = rs.getString(10);
			float classCredits = rs.getFloat(11);
			
			//Make the objects.
			Category categoryObj = new Category(idCategory, categoryDescription);
			Course courseObj = new Course(idClass, classCode, semester, classDescription, classCredits);
			Mark markObj = new Mark(idMark, markDescription, mark, weight, courseObj, categoryObj);
			
			//Add the Mark to the array.
			marks.add(markObj);
		}

		return marks;
	}
	
	//Creates a new mark in the database.
	public void addMark(float mark, String description, float weight, int categoryId, int courseId) throws SQLException{
		
		//Prepare the insertion statement.
		String sql = "insert into Mark values(null, ?, ?, ?, ?, ?)";
		PreparedStatement ps = mConnection.prepareStatement(sql);
		
		//Set the statement's parameters.
		ps.setInt(1, courseId);
		ps.setInt(2, categoryId);
		ps.setString(3, description);
		ps.setFloat(4, mark);
		ps.setFloat(5, weight);
		
		//Execute the statement.
		ps.execute();
		ps.close();
	}
	
	//Updates a mark.
	public void updateMark(int idMark, float mark, String description, float weight, int idCategory, int idCourse) throws SQLException {
		
		//Prepare the update statement.
		String sql = "update Mark set idClass = ?, idCategory = ?, description = ?,  mark = ?, weight = ? where idMark = ?";
		PreparedStatement ps = mConnection.prepareStatement(sql);
		
		//Set the statement's parameters.
		ps.setInt(1, idCourse);
		ps.setInt(2, idCategory);
		ps.setString(3, description);
		ps.setFloat(4, mark);
		ps.setFloat(5, weight);
		ps.setInt(6, idMark);
		
		//Execute the statement.
		ps.execute();
		ps.close();
	}
	
	//Deletes a mark.
	public void deleteMark(int idMark) throws SQLException {
		
		//Prepare the delete statement.
		String sql = "delete from Mark where idMark = ?";
		PreparedStatement ps = mConnection.prepareStatement(sql);
		
		//Set the statement's parameter.
		ps.setInt(1, idMark);
		
		//Execute the statement.
		ps.execute();
		ps.close();
		
	}
	
	//Gives a boolean indicating whether a category exists or not.
	//Takes id, an integer representing the category's id.
	public boolean categoryExists(int id) throws SQLException {
		
		//Fetch the query's result into a result set.
		Statement st = mConnection.createStatement();
		ResultSet rs = st.executeQuery(this.getCategoryQuery(id));
		
		//Check if the id matches a Category.
		return rs.next() != false;
	}
	
	//Gives a boolean indicating whether a course exists or not.
	//Takes id, an integer representing the course's id.
	public boolean courseExists(int id) throws SQLException {	
		
		//Fetch the query's result into a result set.
		Statement st = mConnection.createStatement();
		ResultSet rs = st.executeQuery(this.getCourseQuery(id));
		
		//Check if the id matches a Category.
		return rs.next() != false;
	}
}


