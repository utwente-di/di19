package nl.utwente.di.gradeManager.model;

public class Module extends SuperModule{
	
	private int year;
	private Teacher coordinator;
	
	/**
	 * Default constructor for module
	 * @param name  the name of this module
	 * @param code  the code of this module
	 */
	public Module(String name, String code) {
		super(name, code);
			}

	/**
	 * 
	 * @return the year of this module
	 */
	public int getYear(){
		return year;
	}
	/**
	 * 
	 * @param year  the year of this module
	 */
	public void setYear(int year){
		this.year = year; 
	}
	
	/**
	 * 
	 * @return	The coordinator of this module
	 */
	public Teacher getCoordinator(){
		return coordinator;
	}
	
	/**
	 * 
	 * @param teacher	The coordinator of this module
	 */
	public void setCoordinator(Teacher teacher){
		this.coordinator = teacher;
	}
}
