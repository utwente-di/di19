package nl.utwente.di.gradeManager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nl.utwente.di.gradeManager.debug.Debug;

/**
 * Database helper tool
 *
 */
public class DB {

	//Initial variables for connection to the database.
	static final String url = "jdbc:postgresql://farm14.ewi.utwente.nl/di19";
	static final String username = "di19";
	static final String password = "Qyp4+D9MD";
	static Connection conn;

	/**
	 * Returns a SQL Result set for a given query executed on the di19 database
	 * @param query The query to be executed
	 * @return The corresponding result set, after the query is executed.
	 */
	public static ResultSet executeQuery(String query){
		ResultSet result = null;
		Debug.logln("DB: Attempting to Execute query: " + query + " on database...");
		try { 
			Class.forName("org.postgresql.Driver");
			try {
				conn = DriverManager.getConnection(url,username,password);
				try{ 
					Statement st = conn.createStatement();
					st.execute("set search_path to 'TESTi'");
					result =  st.executeQuery(query);
					
				}	catch(SQLException e) {
					Debug.logln("DB: Error: Oops: " + e.getMessage() );
					Debug.logln("DB: Error: SQLState: " + e.getSQLState() );
		  		}
				conn.close();
			}	catch(SQLException e) {
				Debug.logln("DB: Error: Oops: " + e.getMessage() );
				Debug.logln("DB: Error: SQLState: " + e.getSQLState() );
			}
		}	catch (ClassNotFoundException e) {
			Debug.logln("DB: Error: JDBC driver not loaded.");
		}
		if (result == null){
			Debug.logln("DB: The query : " + query + " did not return any results.");
		}
		return result;
	}	
}
