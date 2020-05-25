package httpControllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import persistance.*;
import services.MarkServices;
import dataObjects.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MarksController {
	
	//TODO faire un package services avec une ou plusieurs classes
	//Le code ci dessous se trouverait dans une fonction dans services
	//TODO package persistance
	//Contiendrait le query dans une classe db, mysqlconnection
	//TODO Creer mes exceptions, exemple: semester invalide, string, 0, negatif, ca retournera un bad request.
	//Je dois créer des exceptions customs pour des fautes du client, une erreur qui survient par un truc comme
	//la connexion SQL qui donne une SQLException est une erreur du système et non de l'usager
	//Dans un tel cas, cest un internal error, pas a cause de l'usager
	
	@GetMapping("/marks")//enlever le defaultValue, le faire dans le client
	public ResponseEntity<Object> marks(@RequestParam(value = "semester") String semester) {
		
		ResponseEntity responseEntity;
		
		try {
			MarkServices ms = new MarkServices();
			ArrayList<Mark> marks = ms.getMarks(1);
			responseEntity = new ResponseEntity<ArrayList<Mark>>(marks, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseEntity = new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		//TODO constantes pour credentials, eventuellement fichier de config avec spring
		//MySqlConnection mySqlConnection = MySqlConnection.getInstance("root", "!h4zastkR", "jdbc:mysql://localhost:3306/schooldb");
		
		//SchoolDb schoolDb= SchoolDb.getInstance();
		//String query = schoolDb.getMarksQuery();
		
		
//		String query = "select \n" + 
//				"m.idMark as idMark, m.idClass as idClass, m.idCategory as idCategory, \n" + 
//				"m.description as markDescription, m.mark as mark, m.ponderation as ponderation, \n" + 
//				"ca.description as categoryDescription,\n" + 
//				"cl.code as classCode, cl.semester as semester, cl.description as classDescription, cl.credits as classCredits\n" + 
//				"from Mark m \n" + 
//				"inner join Category ca on ca.idCategory = m.idCategory\n" + 
//				"inner join Class cl on cl.idClass = m.idClass\n" +
//				"where semester = " + paramSemester;
		
//		ArrayList<Mark> marks = new ArrayList<Mark>();
//		
//		//Dans la classe database
//		try {
//			Statement st = mySqlConnection.getConnection().createStatement();
//			ResultSet rs = st.executeQuery(query);
//			
//			while(rs.next()) {
//				
//				int idMark = rs.getInt(1);
//				int idClass = rs.getInt(2);
//				int categoryId = rs.getInt(3);
//				
//				String markDescription = rs.getString(4);
//				float mark = rs.getFloat(5);
//				float ponderation = rs.getFloat(6);
//				
//				String categoryDescription = rs.getString(7);
//				
//				String classCode = rs.getString(8);
//				int semester = rs.getInt(9);
//				String classDescription = rs.getString(10);
//				float classCredits = rs.getFloat(11);
//				
//				
//				Category categoryObj = new Category(categoryId, categoryDescription);
//				Course courseObj = new Course(idClass, classCode, semester, classDescription, classCredits);
//				Mark markObj = new Mark(idMark, markDescription, mark, ponderation, courseObj, categoryObj);
//				
//				marks.add(markObj);
//			}
//			
//			//propager les erreurs jusqu ici
//			//faire le  bad request si le semestre est invalide, ou not found
//			responseEntity = new ResponseEntity<ArrayList<Mark>>(marks, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			responseEntity = new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		
		return responseEntity;
	}
}
