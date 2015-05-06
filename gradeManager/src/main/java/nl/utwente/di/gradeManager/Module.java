package nl.utwente.di.gradeManager;

import java.util.List;

/**
 * Module class 
 *
 */
public class Module {

	private List<RequiredResult> requiredResults;
	private Teacher coordinator;
	
	/**
	 * Constructs the Module object.
	 * @param argCoordinator the coordinator of this module (the main teacher) 
	 * 
	 */
	public Module(Teacher argCoordinator){
		this.coordinator = argCoordinator;
	}
	
	/**
	 * 
	 * @param argRequiredResult
	 */
	public void addRequiredResult(RequiredResult argRequiredResult){
		this.requiredResults.add(argRequiredResult);
	}
	
	/**
	 * 
	 * @return the required results for passing this module.
	 */
	public List<RequiredResult> getRequiredResults(){
		return requiredResults;
	}
	
	/**
	 * 
	 * @return the teacher of the module.
	 */
	public Teacher getCoordinator(){
		return coordinator;
	}
	
}
