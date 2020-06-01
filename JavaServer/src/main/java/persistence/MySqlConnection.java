package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

	//The database connection.
	private Connection mConnection;
	private String mUser;
	private String mPwd;
	private String mUrl;
	
	
	//Constructors that initiates the connection.
	public MySqlConnection(String user, String pwd, String url) throws ClassNotFoundException{
		this.setUser(user);
		this.setPwd(pwd);
		this.setUrl(url);
	}
	
	//Gives the database connection.
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if(mConnection == null || mConnection.isClosed()) {
			Class.forName("com.mysql.jdbc.Driver");
			mConnection = DriverManager.getConnection(mUrl,mUser,mPwd);
		}
		return mConnection;
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
