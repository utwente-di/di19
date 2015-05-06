package nl.utwente.di.gradeManager;


/**
 * Combination class for Students and Modules
 * @author Frank
 *
 */
public class StudentModule {

	private Student student;
	private Module module;
	
	/**
	 * 
	 * @param argStudent the student of the combination
	 * @param argModule the module of the combination
	 */
	public StudentModule(Student argStudent, Module argModule){
		this.student = argStudent;
		this.module = argModule;
	}
	
	/**
	 * 
	 * @return the module of the combination
	 */
	public Module getModule(){
		return module;
	}
	
	/**
	 * 
	 * @return the student of the combination
	 */
	public Student getStudent(){
		return student;
	}
}


