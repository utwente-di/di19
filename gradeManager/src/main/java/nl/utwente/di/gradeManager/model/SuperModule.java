package nl.utwente.di.gradeManager.model;


/**
 * Class for super modules.
 */
public class SuperModule {
	private int modulecode;
	private String name;
	
	/**
	 * Constructs a supermodule
	 * @param argModulecode The module code of the supermodule.
	 * @param argName The name for the supermodule.
	 */
	public SuperModule(int argModulecode, String argName){
		this.modulecode = argModulecode;
		this.name = argName;
		
	}
	
	/**
	 * Gets the modulecode for this supermodule.
	 * @return The modulecode for the supermodule.
	 */
	public int getModulecode(){
		return this.modulecode;
	}
	
	/**
	 * Sets the modulecode for this supermodule
	 * @param argModulecode The module code for the supermodule.
	 */
	public void setModulecode(int argModulecode){
		this.modulecode = argModulecode;
	}
	
	/**
	 * Gets the name of the supermodule.
	 * @return The name of the supermodule.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the name for the supermodule.
	 * @param argName The new name for the supermodule.
	 */
	public void setName(String argName){
		this.name = argName;
	}
	
	
	
}
