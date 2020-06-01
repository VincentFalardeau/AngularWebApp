package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Singleton class that provides a connection to the database.
public class MySqlConnection{
	//The instance.
	private static MySqlConnection instance = null; 
	//The database connection.
	private Connection mConnection;
	    
	//Gives the instance.
	public static MySqlConnection getInstance() {
		return instance;
	}
	    
	//Gives the instance, initializes if not initialized.
	//Takes the informations required by the database connection: user, password and url.
	public static MySqlConnection getInstance(String user, String pwd, String url) throws ClassNotFoundException, SQLException {
		if(instance == null && user != null && pwd != null && url != null) {
	    	instance = new MySqlConnection(user, pwd, url);
	    }
	    return instance;
	}
	    
	//Private constructor, initiates the connection to the database.
	private MySqlConnection(String user, String pwd, String url) throws ClassNotFoundException, SQLException { 
	    connect(user, pwd, url);
	}
	     
	//Method responsible for initializing the Connection object with a connection to the database
	//Takes the informations required by the database connection: user, password and url.
	private void connect(String user, String pwd, String url) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		mConnection = DriverManager.getConnection(url,user,pwd);
	}	
		
	//Dives the database connection.
	public Connection getConnection() { 
		return mConnection;
	}
	
	//Closes the connection on object destroy
	@Override
	public void finalize(){
		if(mConnection != null) {
			try {
				mConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
