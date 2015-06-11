package nl.utwente.di.gradeManager.model;

public class Course {
	private int coursecode;
	private int year; 
	private String name; //NOT NULL
	private int weight; //NOT NULL
	
	
	/**
	 * Default constructor for Course
	 * @param code  	This course's code
	 * @param name  	This course's name
	 * @param weight	This course's weight
	 * @param year 		This course's year
	 */
	public Course(int code, String name, int weight, int year){
		this.coursecode = code;
		this.name = name;
		this.weight = weight;
		this.year = year;
	}
	
	/**
	 * 
	 * @return the course code
	 */
	public int getCode(){
		return coursecode;
	}

	/**
	 * 
	 * @param code  the course code
	 */
	public void setCode(int code){
		this.coursecode = code;
	}
	

	/**
	 * 
	 * @return this course's year
	 */
	public int getYear(){
		return year;
	}
	
	/**
	 * 
	 * @param year  this course's year
	 */
	public void setYear(int year){
		this.year = year;
	}
	
	/**
	 * 
	 * @return the course name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @param name  the course name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 
	 * @return the weight of this course
	 */
	public int getWeight(){
		return weight;
	}
	
	/**
	 * 
	 * @param weight  the weight of this course
	 */
	public void setWeight(int weight){
		this.weight = weight;
	}
	
}
