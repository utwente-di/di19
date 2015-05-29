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
						"SELECT firstname, c1.name FROM \"Person\" p1, \"Student\" s1, \"ModuleResult\" mr1, \"Module\" m1, \"SuperModule\" sm1, \"hasCourses\" hc1, \"Course\" c1 WHERE "
						+ "s1.studentID = p1.personID AND "
						+ "s1.studentID = mr1.studentID AND "
						+ "m1.moduleCode = mr1.ModuleCode AND "
						+ "m1.year = mr1.year AND "
						+ "sm1.moduleCode = hc1.moduleCode AND "
						+ "hc1.courseCode = c1.courseCode AND "
						+ "hc1.courseYear = c1.Year"
						
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
