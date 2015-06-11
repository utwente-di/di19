package nl.utwente.di.gradeManager.model;

/**
 * Class for a module.
 *
 */
public class Module extends SuperModule{
	private int year;

	/**
	 * Constructs a module.
	 * @param argModulecode The module code of this module.
	 * @param argYear The year in which this module is given.
	 * @param argName The name of the module.
	 */
	public Module(int argModulecode, int argYear, String argName){
		super(argModulecode, argName);
	}
	/**
	 * Gets the year of this module.
	 * @return The year of this module.
	 */
	public int getYear(){
		return this.year;
	}
	
	/**
	 * Sets the year of this module.
	 * @param argYear The year of this module.
	 */
	public void setYear(int argYear){
		this.year = argYear;
	}
	
	
	
}

