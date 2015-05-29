package nl.utwente.di.gradeManager.pages;

import java.sql.*;

import javax.ws.rs.Path;

import nl.utwente.di.gradeManager.style.Style;

@Path("/overzicht")
public class Overzicht {
	public static Connection conn;

	public static void main (String[] args) {
		try {
			Class.forName("org.postgresql.Driver");

			try {
				conn = DriverManager.getConnection(
					"jdbc:postgresql://farm14.ewi.utwente.nl/di19",
					"di19", "Qyp4+D9MD");
			
				try {
					Statement st = conn.createStatement();
					st.execute("set search_path to 'TESTi'");
					ResultSet rs = st.executeQuery(
						"SELECT firstname FROM \"Person\" p1, \"Student\" s1 WHERE s1.studentID = p1.PersonID "
							);
					
					int i=1;					
					while (rs.next())
					{
						System.out.print("Ik werk, dit is resultaat:");
						System.out.println(i+" : "+rs.getString(1));
						i++;
					}
					rs.close();
					st.close(); 
		  		} catch(SQLException e) {
					System.err.println("Oops: " + e.getMessage() );
					System.err.println("SQLState: " + e.getSQLState() );
		  		}
				
				
				conn.close();
			}
				catch(SQLException e) {
				System.err.println("Oops: " + e.getMessage() );
				System.err.println("SQLState: " + e.getSQLState() );
			}
		}
		catch (ClassNotFoundException e) {
			System.err.println("JDBC driver not loaded");
		}
	}
}