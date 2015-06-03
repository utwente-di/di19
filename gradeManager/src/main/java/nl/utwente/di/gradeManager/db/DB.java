package nl.utwente.di.gradeManager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import nl.utwente.di.gradeManager.debug.Debug;

/**
 * Database helper tool
 *
 */
public class DB {

	//Initial variables for connection to the database.
	static final String url = "jdbc:postgresql://farm14.ewi.utwente.nl/di19";
	public static final String username = "di19";
	public static final String password = "Qyp4+D9MD";
	public Connection conn;

	public DB(){
		try { 
			Class.forName("org.postgresql.Driver"); 
			try {
				Debug.logln("DB: Setting up connection");
				conn = DriverManager.getConnection(url,username,password);
				conn.setAutoCommit(true);
				conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			} catch (SQLException e) {
				Debug.logln("DB: Oops: " + e.getMessage() );
				Debug.logln("DB: SQLState: " + e.getSQLState() );
			}
		
			
		}	catch (ClassNotFoundException e) {
			Debug.logln("DB: JDBC driver not loaded.");
		}
	}
	
	public void closeConnection(){
		Debug.logln("DB: Closing connection");
		try {
			conn.close();
		} catch (SQLException e) {
			Debug.logln("DB: Oops: " + e.getMessage() );
			Debug.logln("DB: SQLState: " + e.getSQLState() );
		}
	}
}
