package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import dataObjects.*;

//Class allowing interactions with schooldb MySQL database.
public class SchoolDb {
	
	//TODO: Mettre la fermeture de connection dans un finalize.
	
	//The logger
	//private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	//Connection to the database.
	private MySqlConnection mConnection = null;
	    
	//Constructor that initializes the connection to the database.
	public SchoolDb() throws SQLException, ClassNotFoundException { 
		
		//DatabaseCredentials dc = new DatabaseCredentials();
		
		//mConnection = new MySqlConnection(dc.getUsername(), dc.getPassword(), dc.getUrl());
		
		mConnection = new MySqlConnection("root", "!h4zastkR", "jdbc:mysql://localhost:3306/schooldb");
	}
	
	//Gives the query that selects all the marks.
	private String getMarksQuery() { 
		
		//LOGGER.fine("getMarksQuery()");
		
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
		
		//LOGGER.fine("getMarksQuery("+semester+")");
		
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
		
		//LOGGER.fine("getMarkQuery("+idMark+")");
		
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
		
		//LOGGER.fine("getCategoryQuery("+id+")");
		
		String query = "select * from Category where idCategory = " + id;
		
		return query;
	}
	
	//Gives the query that selects all the categories.
	private String getCategoriesQuery() {
		
		//LOGGER.fine("getCategoriesQuery()");
		
		String query = "select * from Category";
			
		return query;
	}
	
	//Gives the query that selects the class having the specified id.
	private String getCourseQuery(int id) {
		
		//LOGGER.fine("getCourseQuery("+id+")");
		
		String query = "select * from Class where idClass = " + id;
		
		return query;
	}
	
	//Gives the query that selects all the courses.
	private String getCoursesQuery() {
		
		//LOGGER.fine("getCoursesQuery()");
		
		String query = "select * from Class";
				
		return query;
	}
	
	//Gives an array of Mark dataObjects from the given result set.
	private ArrayList<Mark> marksFrom(ResultSet rs) throws SQLException{
		
		//LOGGER.fine("marksFrom("+rs.toString()+")");
		
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
	
	//Gives an array of Course dataObjects from the given result set.
	private ArrayList<Course> coursesFrom(ResultSet rs) throws SQLException{
		
		//LOGGER.fine("coursesFrom("+rs.toString()+")");
			
		//The array.
		ArrayList<Course> courses = new ArrayList<Course>();
			
		//Make dataObjects from every row of the result set.
		while(rs.next()) {
			
				int idClass = rs.getInt(1);
				String classCode = rs.getString(2);
				int semester = rs.getInt(3);
				String classDescription = rs.getString(4);
				float classCredits = rs.getFloat(5);
				
				Course courseObj = new Course(idClass, classCode, semester, classDescription, classCredits);

				//Add the Course to the array.
				courses.add(courseObj);
			}

			return courses;
		}
	
	//Gives an array of Category dataObjects from the given result set.
	private ArrayList<Category> categoriesFrom(ResultSet rs) throws SQLException{
		
		//LOGGER.fine("categoriesFrom("+rs.toString()+")");
		
		//The array.
		ArrayList<Category> categories = new ArrayList<Category>();
		
		//Make dataObjects from every row of the result set.
		while(rs.next()) {
		
			int idCategory = rs.getInt(1);
			String categoryDescription = rs.getString(2);

			Category categoryObj = new Category(idCategory, categoryDescription);
			
			//Add the Category to the array.
			categories.add(categoryObj);
		}

		return categories;
	}
	
	//Gives all the marks.
	public ArrayList<Mark> getMarks() throws SQLException, IOException, ClassNotFoundException{
		
		//LOGGER.fine("getMarks()");
		
		//The connection
		Connection connection = mConnection.getConnection();
		
		//Fetch the query's result into a result set.
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getMarksQuery());
		
		//Make an array of dataObjects from the result set.
		ArrayList<Mark> marks = marksFrom(rs);
		 
		rs.close();
		connection.close();
		
		return marks;
				
	}
	
	//Gives the marks for a semester.
	public ArrayList<Mark> getMarks(int semester) throws SQLException, IOException, ClassNotFoundException{
		
		//LOGGER.fine("getMarks("+semester+")");
		
		//The connection
		Connection connection = mConnection.getConnection();
		
		//Fetch the query's result into a result set.
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getMarksQuery(semester));
		
		//Make an array of dataObjects from the result set.
		ArrayList<Mark> marks = marksFrom(rs);
				 
		rs.close();
		connection.close();
				
		return marks;
		
	}
	
	//Gives the mark having idMark as id.
	public Mark getMark(int idMark) throws SQLException, IOException, ClassNotFoundException{
		
		//LOGGER.fine("getMark("+idMark+")");
			
		//The connection
		Connection connection = mConnection.getConnection();
		
		//Fetch the query's result into a result set.
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getMarkQuery(idMark));
		
		//Make an array of dataObjects from the result set.
		ArrayList<Mark> marks = marksFrom(rs);
		
		//Initialize a Mark to the first and only (in theory) element of marks, if marks is not empty.
		Mark mark = null;
		if(marks.size() > 0) {
			mark = marks.get(0);
		}
		
		rs.close();
		connection.close();
		
		//Return that Mark.			
		return mark;
					
	}
	
	//Creates a new mark in the database.
	public void addMark(float mark, String description, float weight, int idCategory, int idCourse) throws SQLException, ClassNotFoundException{
		
		//LOGGER.fine("addMark("+mark+", "+description+", "+weight+", "+idCategory+", "+idCourse+")");
		
		//The connection
		Connection connection = mConnection.getConnection();
		
		//Prepare the insertion statement.
		String sql = "insert into Mark values(null, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		//Set the statement's parameters.
		ps.setInt(1, idCourse);
		ps.setInt(2, idCategory);
		ps.setString(3, description);
		ps.setFloat(4, mark);
		ps.setFloat(5, weight);
		
		//Execute the statement.
		ps.execute();
		ps.close();
		
		connection.close();
	}
	
	//Updates a mark.
	public void updateMark(int idMark, float mark, String description, float weight, int idCategory, int idCourse) throws SQLException, ClassNotFoundException {
		
		//LOGGER.fine("updateMark("+idMark+", "+mark+", "+description+", "+weight+", "+idCategory+", "+idCourse+")");
		
		//The connection
		Connection connection = mConnection.getConnection();
		
		//Prepare the update statement.
		String sql = "update Mark set idClass = ?, idCategory = ?, description = ?,  mark = ?, weight = ? where idMark = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
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
		
		connection.close();
	}
	
	//Deletes a mark.
	public void deleteMark(int idMark) throws SQLException, ClassNotFoundException {
		
		//LOGGER.fine("deleteMark("+idMark+")");
		
		//The connection
		Connection connection = mConnection.getConnection();
		
		//Prepare the delete statement.
		String sql = "delete from Mark where idMark = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		//Set the statement's parameter.
		ps.setInt(1, idMark);
		
		//Execute the statement.
		ps.execute();
		ps.close();
		
		connection.close();
		
	}
	
	//Gives a boolean indicating whether a category exists or not.
	//Takes id, an integer representing the category's id.
	public boolean categoryExists(int id) throws SQLException, ClassNotFoundException {
		
		//LOGGER.fine("categoryExists("+id+")");
		
		//The connection
		Connection connection = mConnection.getConnection();
		
		//Fetch the query's result into a result set.
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getCategoryQuery(id));
		
		//Check if the id matches a Category.
		boolean exists = rs.next() != false;
		
		rs.close();
		connection.close();
		
		return exists;
	}
	
	//Gives all the categories.
	public ArrayList<Category> getCategories() throws SQLException, ClassNotFoundException{
		
		//LOGGER.fine("getCategories()");
		
		//The connection
		Connection connection = mConnection.getConnection();
		
		//Fetch the query's result into a result set.
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getCategoriesQuery());
		
		//Make an array of dataObjects from the result set.
		ArrayList<Category> categories = categoriesFrom(rs);
		
		rs.close();
		connection.close();
						
		return categories;
	}
	
	//Gives a boolean indicating whether a course exists or not.
	//Takes id, an integer representing the course's id.
	public boolean courseExists(int id) throws SQLException, ClassNotFoundException {	
		
		//LOGGER.fine("courseExists("+id+")");
		
		//The connection
		Connection connection = mConnection.getConnection();
		
		//Fetch the query's result into a result set.
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getCourseQuery(id));
		
		//Check if the id matches a Course.
		boolean exists = rs.next() != false;
				
		rs.close();
		connection.close();
				
		return exists;
	}
	
	//Gives all the courses.
	public ArrayList<Course> getCourses() throws SQLException, ClassNotFoundException{
		
		//LOGGER.fine("getCourses()");
		
		//The connection
		Connection connection = mConnection.getConnection();
				
		//Fetch the query's result into a result set.
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(this.getCoursesQuery());
		
		//Make an array of dataObjects from the result set.
		ArrayList<Course> courses = coursesFrom(rs);
		
		rs.close();
		connection.close();
						
		return courses;
	}
}


