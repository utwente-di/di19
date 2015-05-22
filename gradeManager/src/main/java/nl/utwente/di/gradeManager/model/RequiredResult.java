package nl.utwente.di.gradeManager.model;

/**
 * Combination class for a subject and a 
 * required result
 *
 */
public class RequiredResult {

	private Course subject;
	private String requiredResult;
	
	/**
	 * Builds the class
	 * @param argSubject The subject
	 * @param argRequiredResult The required result of this subject.
	 */
	public RequiredResult(Course argSubject, String argRequiredResult){
		this.subject = argSubject;
		this.requiredResult = argRequiredResult;
	}
	
	/**
	 * 
	 * @return The subject.
	 */
	public Course getSubject(){
		return subject;
	}
	
	/**
	 * 
	 * @param argSubject the new subject.
	 */
	public void setSubject(Course argSubject){
		this.subject = argSubject;
	}
	
	/**
	 * 
	 * @return the required result.
	 */
	public String getRequiredResult(){
		return requiredResult;
	}
	
	/**
	 * 
	 * @param argRequiredResult the new required result.
	 */
	public void setRequiredResult(String argRequiredResult){
		this.requiredResult = argRequiredResult;
	}
	
}
