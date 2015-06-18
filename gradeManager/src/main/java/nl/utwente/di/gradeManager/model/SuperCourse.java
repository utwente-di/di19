package nl.utwente.di.gradeManager.model;
/**
 * 
 * Class for supercourses, which a course has to instantiate.
 */
public abstract class SuperCourse {

	private int coursecode;
	private String name;
	private int weight;
	
	/**
	 * Constructs the supercourse.
	 * @param argCoursecode The code of the supercourse.
	 * @param argName The name of the supercourse.
	 * @param argWeight The weight in the course in a module.
	 */
	public SuperCourse(int argCoursecode, String argName, int argWeight){
		this.coursecode = argCoursecode;
		this.name = argName;
		this.weight = argWeight;
	}
	
	/**
	 * Gets the coursecode of this supercourse.
	 * @return The coursecode of the supercourse.
	 */
	public int getCourseCode(){
		return coursecode;
	}
	
	/**
	 * Sets the coursecode of this supercourse.
	 * @param argCoursecode The code of the supercourse.
	 */
	public void setCourseCode(int argCoursecode){
		this.coursecode = argCoursecode;
	}
	
	
	/**
	 * Gets the name of the supercourse.
	 * @return The name of the supercourse.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the name of the supercourse.
	 * @param name The new name of the supercourse.
	 */
	public void setName(String argName){
		this.name = argName;
	}
	
	/**
	 * Gets the weight of this supercourse
	 * @return the weight of this supercourse
	 */
	public int getWeight(){
		return weight;
	}
	
	/**
	 * Sets the weight of the supercourse;
	 * @param weight  the weight of this supercourse
	 */
	public void setWeight(int argWeight){
		this.weight = argWeight;
	}
	

}
