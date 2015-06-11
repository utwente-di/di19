package nl.utwente.di.gradeManager.db;

import java.sql.*;

import nl.utwente.di.gradeManager.debug.Debug;

/**
 * Database helper tool
 * 
 */
public abstract class DB {
	//The idea of this class is to extend it, and make use of the generated connection.
	
	//Initial variables for connection to the database.	
	static final String url = "jdbc:postgresql://farm14.ewi.utwente.nl/di19";
	public static final String username = "di19";
	public static final String password = "Qyp4+D9MD";
	public Connection conn;

	/**
	 * Constructs the DB object.
	 */
	public DB(){
		
		try {
			//set up jdbc driver
			Class.forName("org.postgresql.Driver"); 
			try {
				//set up connection
				Debug.logln("DB: Setting up connection");
				conn = DriverManager.getConnection(url,username,password);
				conn.setAutoCommit(true);
				conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			} catch (SQLException e) {
				//something went wrong with setting up connection
				Debug.logln("DB: Oops: " + e.getMessage() );
				Debug.logln("DB: SQLState: " + e.getSQLState() );
			}

			
		}	catch (ClassNotFoundException e) {
			//something went wrong with loading the jdbc driver.
			Debug.logln("DB: JDBC driver not loaded.");
		}
	}
	
	/**
	 * Close the connection to the database.
	 * Do not forget to call this method after making use of this class!
	 */
	public void closeConnection(){
		Debug.logln("DB: Closing connection");
		try {
			//close the connection
			conn.close();
		} catch (SQLException e) {
			//something went wrong with closing the connection.
			Debug.logln("DB: Oops: " + e.getMessage() );
			Debug.logln("DB: SQLState: " + e.getSQLState() );
		}
	}
}
