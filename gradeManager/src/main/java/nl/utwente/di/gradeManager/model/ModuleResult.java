package nl.utwente.di.gradeManager.model;

import java.math.BigDecimal;

/**
 * Class for the result of a module
 *
 */

public class ModuleResult {

	private int studentID;
	private int moduleCode;
	private int year;
	private BigDecimal result;

	/**
	 * Constructs a module result
	 * @param argStudentID The ID of the student who got the result.
	 * @param argModuleCode The module code for which this result is applicable.
	 * @param argYear The year on which the result was achieved.
	 * @param argResult The result.
	 */
	public ModuleResult(int argStudentID, int argModuleCode, int argYear, BigDecimal argResult){
		this.studentID = argStudentID;
		this.moduleCode = argModuleCode;
		this.year = argYear;
		this.result = argResult;	
	}

	/**
	 * Gets the student ID for the module result.
	 * @return The student ID for the module result.
	 */
	public int getStudentID(){
		return this.studentID;
	}
	
	/**
	 * Sets the student ID for the module result.
	 * @param argStudentID The student ID for the module result.
	 */
	public void setStudentID(int argStudentID){
		this.studentID = argStudentID;
	}
	
	/**
	 * Gets the module code for the module result.
	 * @return The module code for the module result.
	 */
	public int getModuleCode(){
		return this.moduleCode;
	}
	
	/**
	 * Sets the module code for the module result.
	 * @param argModuleCode The module code for the module result.
	 */
	public void setModuleCode(int argModuleCode){
		this.moduleCode = argModuleCode;
	}
	
	/**
	 * Gets the year of the module result.
	 * @return The year of the module result.
	 */
	public int getYear(){
		return this.year;
	}
	
	/**
	 * Sets the year of the module result.
	 * @param argYear The year of the module result.
	 */
	public void setYear(int argYear){
		this.year = argYear;
	}
	
	/**
	 * Gets the result of the module.
	 * @return The result of the module.
	 */
	public BigDecimal getResult(){
		return this.result;
	}
	
	/**
	 * Sets the result of the module.
	 * @param argResult The result of the module.
	 */
	public void setResult(BigDecimal argResult){
		this.result = argResult;
	}
	
}
