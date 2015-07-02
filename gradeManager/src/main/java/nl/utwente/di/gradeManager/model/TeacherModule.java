package nl.utwente.di.gradeManager.model;

/**
 * Combination class for a teacher and a module.
 *
 */
public class TeacherModule {
	
	private Teacher teacher;
	private Module module;
	private String type;
	
	/**
	 * Create a combination between a teacher and a module
	 * @param argTeacher The teacher that teaches the module
	 * @param argModule The module that the teacher teaches.
	 * @param type The type of teacher (Studentassistent, Hulpmedewerker, Docent)
	 */
	public TeacherModule(Teacher argTeacher, Module argModule, String type){
		this.teacher = argTeacher;
		this.module = argModule;
		this.type = type;
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
	
	/**
	 * 
	 * @param argtype The association the teacher has with this module
	 */
	public void setType(String argtype){
		this.type = argtype;
	}
	
	/**
	 * 
	 * @return The association the teacher has with this module
	 */
	public String getType(){
		return type;
	}
	
}
