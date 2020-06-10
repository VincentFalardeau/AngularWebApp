package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import httpControllers.MarkController;

public class MySqlConnection {
	
	//The logger
	//private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	//The database connection.
	private Connection mConnection;
	
	//The credentials.
	private String mUser;
	private String mPwd;
	private String mUrl;
	
	
	//Constructor
	public MySqlConnection(String user, String pwd, String url) throws ClassNotFoundException, SQLException{
		
		this.setUser(user);
		this.setPwd(pwd);
		this.setUrl(url);
		connect();
	}
	
	//Gives the database connection, connects if not connected.
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		//LOGGER.fine("getConnection()");
		
		if(mConnection == null || mConnection.isClosed()) {
			connect();
		}
		return mConnection;
	}
	
	private void connect() throws ClassNotFoundException, SQLException {
		
		//LOGGER.fine("connect()");
		
		Class.forName("com.mysql.jdbc.Driver");
		mConnection = DriverManager.getConnection(mUrl,mUser,mPwd);
	}

	public String getUser() {
		return mUser;
	}

	public void setUser(String mUser) {
		this.mUser = mUser;
	}

	public String getPwd() {
		return mPwd;
	}

	public void setPwd(String mPwd) {
		this.mPwd = mPwd;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String mUrl) {
		this.mUrl = mUrl;
	}
}
