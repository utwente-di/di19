package nl.utwente.di.gradeManager.model;

import java.util.List;

/**
 * Module class 
 *
 */
public abstract class SuperModule {

	private List<RequiredResult> requiredResults;
	
	private String moduleCode;
	private String moduleName;
	
	/**
	 *  
	 * @return the code of this module
	 */
	public String getCode(){
		return moduleCode;
	}
	/**
	 * 
	 * @param code  the code of this module
	 */
	public void setCode(String code){
		this.moduleCode = code;
	}
	/**
	 * 
	 * @return the name of this module
	 */
	public String getName(){
		return moduleName;
	}
	/**
	 * 
	 * @param name  the name of this module
	 */
	public void setName(String name){
		this.moduleName = name;
	}
	/**
	 * Default constructor for SuperModule
	 * @param name  the name of the module
	 * @param code  the code corresponding to the module
	 */
	public SuperModule(String name, String code){
		moduleName = name;
		moduleCode = code;
	}
	
	/**
	 * add this RequiredResult to the list of RequiredResults
	 * @param argRequiredResult  the RequiredResult to be added
	 */
	public void addRequiredResult(RequiredResult argRequiredResult){
		this.requiredResults.add(argRequiredResult);
	}
	
	/**
	 * Gets the Required Results for passign this module
	 * @return the required results for passing this module.
	 */
	public List<RequiredResult> getRequiredResults(){
		return requiredResults;
	}	
}
