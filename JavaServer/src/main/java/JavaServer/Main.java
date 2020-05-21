package JavaServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		
//		Connection connection = MySqlConnection.getInstance("root", "!h4zastkR", "jdbc:mysql://localhost:3306/schooldb").getConnection();
//		
//		String query = "select * from Category";
//		Statement st;
//		try {
//			st = connection.createStatement();
//			ResultSet rs = st.executeQuery(query);
//			System.out.println("allo");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
}

