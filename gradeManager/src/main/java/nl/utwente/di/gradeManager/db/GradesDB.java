package nl.utwente.di.gradeManager.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.model.Module;

/**
 * Helper class for Database connection for use for grades page.
 *
 */
public class GradesDB extends DB {

	public GradesDB(){
		super();
	}
	
	/**
	 * Gets the modules, which a student is doing.
	 * @param argStudentID The ID of the student.
	 * @return A list of modules, which the student is doing.
	 */
	public List<Module> getModulesForStudent(int argStudentID){
		List<Module> result = new ArrayList<Module>();
		
		
		String query = "SELECT Testi.module.modulecode, Testi.module.year, Testi.supermodule.name FROM Testi.module,Testi.moduleresult,Testi.supermodule WHERE Testi.moduleresult.studentid = " + 
		argStudentID + 
		" AND Testi.moduleresult.modulecode = Testi.module.modulecode AND Testi.moduleresult.year = Testi.module.year AND Testi.module.modulecode = Testi.supermodule.modulecode";
		try { 
			//execute query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int modulecode = rs.getInt("modulecode");
				int year = rs.getInt("year");
				String name = rs.getString("name");
				Module m = new Module(modulecode, year, name);
				result.add(m);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
				
		return result;
	}
	
	
	
	
}
