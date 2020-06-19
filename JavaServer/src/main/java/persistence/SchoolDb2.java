package persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dataObjects.Category;
import dataObjects.Course;
import dataObjects.Mark;
import dataObjects.MarkData;

public class SchoolDb2 {			
	
	private final String CONFIG_FILE = "mybatis-config.xml";
	
	private InputStream mInputStream;
	private SqlSessionFactory mSqlSessionFactory;
	
	public SchoolDb2() throws IOException {
		
		mInputStream = Resources.getResourceAsStream(CONFIG_FILE);
		mSqlSessionFactory =  new SqlSessionFactoryBuilder().build(mInputStream);
	}
	
	//Gives all the categories.
	public ArrayList<Category> getCategories() {
		
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.debug("getCategories()");
		
		ArrayList<Category> categories = null;
		
		try (SqlSession session = mSqlSessionFactory.openSession()){
			
			categories = (ArrayList)session.selectList("dataObjects.Category.getCategories");

			session.commit();
		}
		
		return categories;
	}
	
	//Gives a single category specified by an id.
	public Category getCategory(int id) {
					
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.debug("getCategory("+id+")");
		
		ArrayList<Category> categories = null;
		Category category = null;
					
		try (SqlSession session = mSqlSessionFactory.openSession()){
			
			categories = (ArrayList)session.selectList("dataObjects.Category.getCategory", id);
			
			//Get the category
			if(!categories.isEmpty()) {
				category = categories.get(0);
			}
			
			session.commit();
		}
					
		return category;
	}
	
	//Gives all the courses.
	public ArrayList<Course> getCourses() {
			
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.debug("getCourses()");
		
		ArrayList<Course> courses = null;
			
		try(SqlSession session = mSqlSessionFactory.openSession()){
			courses = (ArrayList)session.selectList("dataObjects.Course.getCourses");

			session.commit();
		}
			
		return courses;
	}
	
	//Gives a single course specified by an id.
	public Course getCourse(int id) {
				
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.debug("getCourse("+id+")");
		
		ArrayList<Course> courses = null;
		Course course = null;
				
		try (SqlSession session = mSqlSessionFactory.openSession()){

			courses = (ArrayList)session.selectList("dataObjects.Course.getCourse", id);
			
			//Get the course.
			if(!courses.isEmpty()) {
				course = courses.get(0);
			}
	
			session.commit();
		}
				
		return course;
	}
	
	//Gives all the marks.
	public ArrayList<Mark> getMarks() {
	
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.debug("getMarks()");
		
		SqlSession session = mSqlSessionFactory.openSession();		
		
		ArrayList<Mark> marks = (ArrayList)session.selectList("dataObjects.Mark.getMarks");

		session.commit();
		session.close();
		
		return marks;
	}
	
	//Gives all the marks for a semester.
	public ArrayList<Mark> getMarks(int semester) {
		
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.debug("getMarks("+semester+")");
		
		ArrayList<Mark> marks = null;
		
		try (SqlSession session = mSqlSessionFactory.openSession()){
			marks = (ArrayList)session.selectList("dataObjects.Mark.getMarksForSemester", semester);

			session.commit();
		}
			
		return marks;
	}
	
	//Gives a single mark specified by an id.
	public Mark getMark(int idMark) {
			
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.debug("getMark("+idMark+")");
		
		ArrayList<Mark> marks = null;
		Mark mark = null;
				
		try (SqlSession session = mSqlSessionFactory.openSession()){
			
			marks = (ArrayList)session.selectList("dataObjects.Mark.getMark", idMark);
			
			//Get the mark.
			if(!marks.isEmpty()) {
				mark = marks.get(0);
			}
			
			session.commit();
		}
				
		return mark;
	}

	//
	public void addMark(float mark, String description, float weight, int idCategory, int idCourse) throws PersistenceException{
		
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.debug("addMark("+mark+", "+description+", "+weight+", "+idCategory+", "+idCourse+")");
				
		try (SqlSession session = mSqlSessionFactory.openSession()){
			
			MarkData markData = new MarkData(null, description, mark, weight, idCourse, idCategory);
			
			session.insert("dataObjects.Mark.insert", markData);
			
			session.commit();
		}
		
	}
	
	public void updateMark(int idMark, float mark, String description, float weight, int idCategory, int idCourse) throws PersistenceException{
		
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.debug("updateMark("+idMark+", "+mark+", "+description+", "+weight+", "+idCategory+", "+idCourse+")");
				
		try (SqlSession session = mSqlSessionFactory.openSession()){
			
			MarkData markData = new MarkData(idMark, description, mark, weight, idCourse, idCategory);
			
			session.update("dataObjects.Mark.update", markData);
			
			session.commit();
		}
		
	}
	
	public void deleteMark(int idMark) {
		
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.debug("deleteMark("+idMark+")");
				
		try (SqlSession session = mSqlSessionFactory.openSession()){
			
			session.delete("dataObjects.Mark.delete", idMark);
			
			session.commit();
		}
	}
}
