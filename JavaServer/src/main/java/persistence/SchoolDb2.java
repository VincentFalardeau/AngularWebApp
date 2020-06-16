package persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataObjects.Category;
import dataObjects.Course;
import dataObjects.Mark;

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
		log.info("getCategories()");
		
		SqlSession session = mSqlSessionFactory.openSession();	

		ArrayList<Category> categories = (ArrayList)session.selectList("dataObjects.Category.getCategories");

		session.commit();
		session.close();
		
		return categories;
	}
	
	//Gives all the courses.
	public ArrayList<Course> getCourses() {
			
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.info("getCourses()");
			
		SqlSession session = mSqlSessionFactory.openSession();	

		ArrayList<Course> courses = (ArrayList)session.selectList("dataObjects.Course.getCourses");

		session.commit();
		session.close();
			
		return courses;
	}
	
	//Gives all the marks.
	public ArrayList<Mark> getMarks() {
	
		Logger log = LogManager.getLogger(SchoolDb2.class);
		log.info("getMarks()");
		
		SqlSession session = mSqlSessionFactory.openSession();		
		
		ArrayList<Mark> marks = (ArrayList)session.selectList("dataObjects.Mark.getMarks");

		session.commit();
		session.close();
		
		return marks;
	}
}
