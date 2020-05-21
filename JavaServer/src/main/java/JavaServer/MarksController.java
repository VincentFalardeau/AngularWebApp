package JavaServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MarksController {
	
	@GetMapping("/marks")
	public Object[] marks(@RequestParam(value = "semester", defaultValue = "1") String paramSemester) {
		
		MySqlConnection mySqlConnection = MySqlConnection.getInstance("root", "!h4zastkR", "jdbc:mysql://localhost:3306/schooldb");
		String query = "select \n" + 
				"m.idMark as idMark, m.idClass as idClass, m.idCategory as idCategory, \n" + 
				"m.description as markDescription, m.mark as mark, m.ponderation as ponderation, \n" + 
				"ca.description as categoryDescription,\n" + 
				"cl.code as classCode, cl.semester as semester, cl.description as classDescription, cl.credits as classCredits\n" + 
				"from Mark m \n" + 
				"inner join Category ca on ca.idCategory = m.idCategory\n" + 
				"inner join Class cl on cl.idClass = m.idClass\n" +
				"where semester = " + paramSemester;
		
		ArrayList<Mark> marks = new ArrayList<Mark>();
		
		try {
			Statement st = mySqlConnection.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				
				int idMark = rs.getInt(1);
				int idClass = rs.getInt(2);
				int categoryId = rs.getInt(3);
				
				String markDescription = rs.getString(4);
				float mark = rs.getFloat(5);
				float ponderation = rs.getFloat(6);
				
				String categoryDescription = rs.getString(7);
				
				String classCode = rs.getString(8);
				int semester = rs.getInt(9);
				String classDescription = rs.getString(10);
				float classCredits = rs.getFloat(11);
				
				
				Category categoryObj = new Category(categoryId, categoryDescription);
				Course classeObj = new Course(idClass, classCode, semester, classDescription, classCredits);
				Mark markObj = new Mark(idMark, markDescription, mark, ponderation, classeObj, categoryObj);
				
				marks.add(markObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return marks.toArray();
	}
}
