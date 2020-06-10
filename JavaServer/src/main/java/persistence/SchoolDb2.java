package persistence;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dataObjects.Category;
import mappers.CategoriesMapper;

public class SchoolDb2 {
	
	private final String CONFIG_FILE = "src/main/resources/mybatis-config.xml";
	
	private InputStream mInputStream;
	private SqlSessionFactory mSqlSessionFactory;
	
	public SchoolDb2() throws IOException {
		
		mInputStream = Resources.getResourceAsStream(CONFIG_FILE);
		mSqlSessionFactory =  new SqlSessionFactoryBuilder().build(mInputStream);
	}
	
	public ArrayList<Category> getCategories() {
		SqlSession session = mSqlSessionFactory.openSession();
		CategoriesMapper cm = session.getMapper(CategoriesMapper.class);
		Map<Integer, Category> map = cm.selectCategories();
		
		//TODO: Convertit la map en array de categories
		
		//for(int i = 0; i < map.size(); i++) {
			//map.
		//}
		
		return null;
	}
	
	
}
