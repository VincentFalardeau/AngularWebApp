package persistance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataObjects.*;

public class SchoolDb {
	
	private static SchoolDb instance = null; 
	
	private Connection mConnection = null;
	//private ResourcesManager mResourcesManager = null;
	    
	public static SchoolDb getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
	    	instance = new SchoolDb();
	    }
	    return instance;
	}
	    
	private SchoolDb() throws SQLException, ClassNotFoundException { 
		
		mConnection = MySqlConnection.getInstance("root", "!h4zastkR", "jdbc:mysql://localhost:3306/schooldb").getConnection();
		
		//mResourcesManager = new ResourcesManager();
	}
	
	private Connection getConnection() {
		return mConnection;
	}
	
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

		//String filename = "sql/query2.sql";
		//String query = mResourcesManager.getFileContent(filename, semester);
		
		return query;
	}
	
	private String getMarkQuery(int id) {
		String query = "select * from Mark where idMark = " + id;
		return query;
	}
	
	private String getCategoryQuery(int id) {
		String query = "select * from Category where idCategory = " + id;
		return query;
	}
	
	private String getCourseQuery(int id) {
		String query = "select * from Class where idClass = " + id;
		return query;
	}
	
	//Gives a list of mark for a semester.
	public ArrayList<Mark> getMarks(int semester) throws SQLException, IOException{
		ArrayList<Mark> marks = new ArrayList<Mark>();
		
		Connection connection = getConnection();		
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getMarksQuery(semester));
		
		//For every fetched row
		while(rs.next()) {
			
			int idMark = rs.getInt(1);
			int idClass = rs.getInt(2);
			int categoryId = rs.getInt(3);
			
			String markDescription = rs.getString(4);
			float mark = rs.getFloat(5);
			float weight = rs.getFloat(6);
			
			String categoryDescription = rs.getString(7);
			
			String classCode = rs.getString(8);
			semester = rs.getInt(9);
			String classDescription = rs.getString(10);
			float classCredits = rs.getFloat(11);
			
			//Create data objects
			Category categoryObj = new Category(categoryId, categoryDescription);
			Course courseObj = new Course(idClass, classCode, semester, classDescription, classCredits);
			Mark markObj = new Mark(idMark, markDescription, mark, weight, courseObj, categoryObj);
			
			//Add the mark data object to the array
			marks.add(markObj);
		}
		
		return marks;
	}
	
	//Creates a new mark in the database
	public void addMark(float mark, String description, float weight, int categoryId, int courseId) throws SQLException{
		Connection connection = getConnection();
		
		String sql = "insert into Mark values(null, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, courseId);
		ps.setInt(2, categoryId);
		ps.setString(3, description);
		ps.setFloat(4, mark);
		ps.setFloat(5, weight);
		
		ps.execute();
		ps.close();
	}
	
	public void updateMark(int idMark, float mark, String description, float weight, int idCategory, int idCourse) throws SQLException {
		Connection connection = getConnection();
		
		String sql = "update Mark set idClass = ?, idCategory = ?, description = ?,  mark = ?, weight = ? where idMark = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, idCourse);
		ps.setInt(2, idCategory);
		ps.setString(3, description);
		ps.setFloat(4, mark);
		ps.setFloat(5, weight);
		ps.setInt(6, idMark);
		
		ps.execute();
		ps.close();
	}
	
	public void deleteMark(int idMark) throws SQLException {
		Connection connection = getConnection();
		
		String sql = "delete from Mark where idMark = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, idMark);
		
		ps.execute();
		ps.close();
		
	}
	
	//Gives a boolean indicating whether a mark exists or not.
	//Takes id, an integer representing the mark's id.
	public boolean markExists(int id) throws SQLException {
		Connection connection = getConnection();		
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getMarkQuery(id));
		return rs.next() != false;
	}
	
	//Gives a boolean indicating whether a category exists or not.
	//Takes id, an integer representing the category's id.
	public boolean categoryExists(int id) throws SQLException {
		Connection connection = getConnection();		
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getCategoryQuery(id));
		return rs.next() != false;
	}
	
	//Gives a boolean indicating whether a course exists or not.
	//Takes id, an integer representing the course's id.
	public boolean courseExists(int id) throws SQLException {
		Connection connection = getConnection();		
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getCourseQuery(id));
		return rs.next() != false;
	}
}
