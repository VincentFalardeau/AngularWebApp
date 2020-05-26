package persistance;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.tomcat.util.json.JSONParser;

import com.fasterxml.jackson.core.JsonParser;

import dataObjects.*;
import tools.ResourcesManager;

public class SchoolDb {
	
	private static SchoolDb instance = null; 
	
	private Connection mConnection = null;
	private ResourcesManager mResourcesManager = null;
	    
	public static SchoolDb getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
	    	instance = new SchoolDb();
	    }
	    return instance;
	}
	    
	private SchoolDb() throws SQLException, ClassNotFoundException, IOException { 
		
		mConnection = MySqlConnection.getInstance("root", "!h4zastkR", "jdbc:mysql://localhost:3306/schooldb").getConnection();
		
		//String filename = "dbCredentials/credentials.json";
		//String json = mResourcesManager.getFileContent(filename);
		
		//Object obj = new JSONParser().parse(json); 
		
		
		mResourcesManager = new ResourcesManager();
	}
	
	private Connection getConnection() {
		return mConnection;
	}
	
	private String getMarksQuery(int semester) throws IOException { 
		
//		String query = "select \n" + 
//			"m.idMark as idMark, m.idClass as idClass, m.idCategory as idCategory, \n" + 
//			"m.description as markDescription, m.mark as mark, m.ponderation as ponderation, \n" + 
//			"ca.description as categoryDescription,\n" + 
//			"cl.code as classCode, cl.semester as semester, cl.description as classDescription, cl.credits as classCredits\n" + 
//			"from Mark m \n" + 
//			"inner join Category ca on ca.idCategory = m.idCategory\n" + 
//			"inner join Class cl on cl.idClass = m.idClass\n" +
//			"where semester = " + semester;

		String filename = "sql/query2.sql";
		String query = mResourcesManager.getFileContent(filename, semester);
		
		return query;
	}
	
	public ArrayList<Mark> getMarks(int semester) throws SQLException, IOException{
		ArrayList<Mark> marks = new ArrayList<Mark>();
		
		Connection connection = getConnection();		
		Statement st = connection.createStatement();
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
