package persistance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import dataObjects.*;

public class SchoolDb {
	
	private static SchoolDb instance = null; 
	
	private Connection mConnection = null;
	    
	public static SchoolDb getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
	    	instance = new SchoolDb();
	    }
	    return instance;
	}
	    
	private SchoolDb() throws SQLException, ClassNotFoundException { 
		//TODO constantes pour credentials, eventuellement fichier de config avec spring
		mConnection = MySqlConnection.getInstance("root", "!h4zastkR", "jdbc:mysql://localhost:3306/schooldb").getConnection();
		
	}
	
	private Connection getConnection() {
		return mConnection;
	}
		
	//TODO: takes the semester as parameter
	//TODO: utiliser spring jdbc ou Mybatis (requetes dans des fichiers)
	private String getMarksQuery(int semester) { 
		String query = "select \n" + 
			"m.idMark as idMark, m.idClass as idClass, m.idCategory as idCategory, \n" + 
			"m.description as markDescription, m.mark as mark, m.ponderation as ponderation, \n" + 
			"ca.description as categoryDescription,\n" + 
			"cl.code as classCode, cl.semester as semester, cl.description as classDescription, cl.credits as classCredits\n" + 
			"from Mark m \n" + 
			"inner join Category ca on ca.idCategory = m.idCategory\n" + 
			"inner join Class cl on cl.idClass = m.idClass\n" +
			"where semester = " + semester;
		
		return query;
	}
	
	public ArrayList<Mark> getMarks(String semesterParam) throws NumberFormatException, SQLException, Exception{
		ArrayList<Mark> marks = new ArrayList<Mark>();
		
		Connection connection = getConnection();		
		Statement st = connection.createStatement();
		int semester = Integer.parseInt(semesterParam);
		ResultSet rs = st.executeQuery(this.getMarksQuery(semester));
		
		while(rs.next()) {
			
			int idMark = rs.getInt(1);
			int idClass = rs.getInt(2);
			int categoryId = rs.getInt(3);
			
			String markDescription = rs.getString(4);
			float mark = rs.getFloat(5);
			float ponderation = rs.getFloat(6);
			
			String categoryDescription = rs.getString(7);
			
			String classCode = rs.getString(8);
			semester = rs.getInt(9);
			String classDescription = rs.getString(10);
			float classCredits = rs.getFloat(11);
			
			
			Category categoryObj = new Category(categoryId, categoryDescription);
			Course courseObj = new Course(idClass, classCode, semester, classDescription, classCredits);
			Mark markObj = new Mark(idMark, markDescription, mark, ponderation, courseObj, categoryObj);
			
			marks.add(markObj);
		}
		
		return marks;
	}
}
