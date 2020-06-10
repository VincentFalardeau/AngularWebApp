package persistence;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SchoolDb2 {
	
	private final String CONFIG_FILE = "src/main/resources/mybatis-config.xml";
	
	private InputStream mInputStream;
	private SqlSessionFactory mSqlSessionFactory;
	
	public SchoolDb2() throws IOException {
		
		mInputStream = Resources.getResourceAsStream(CONFIG_FILE);
		mSqlSessionFactory =  new SqlSessionFactoryBuilder().build(mInputStream);
	}
	
	
}
