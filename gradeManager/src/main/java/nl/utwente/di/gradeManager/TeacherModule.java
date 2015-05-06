package nl.utwente.di.gradeManager;

/**
 * Combination class for a teacher and a module.
 *
 */
public class TeacherModule {
	
	private Teacher teacher;
	private Module module;
	
	/**
	 * Create a combination between a teacher and a module
	 * @param argTeacher The teacher that teaches the module
	 * @param argModule The module that the teacher teaches.
	 */
	public TeacherModule(Teacher argTeacher, Module argModule){
		this.teacher = argTeacher;
		this.module = argModule;
	}
	
	/**
	 * sets the teacher that teaches this module.
	 * @param argTeacher the teacher of this module.
	 */
	public void setTeacher(Teacher argTeacher){
		this.teacher = argTeacher;
	}
	
	/**
	 * gets the teacher of this module.
	 * @return the teacher of this combination
	 */
	public Teacher getTeacher(){
		return teacher;
	}
	
	/**
	 * 
	 * @param argModule sets the module of this combination
	 */
	public void setModule(Module argModule){
		this.module = argModule;
	}
	
	/**
	 * gets that this teacher teaches.
	 * @return The module of this combination class
	 */
	public Module getModule(){
		return module;
	}
	
}
