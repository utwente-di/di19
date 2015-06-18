package nl.utwente.di.gradeManager.servlets;

public class Proeftabel extends Resulttable{
	
	public void init(){
		int id = 1234567890;
		int pid = 2;
		setModule(id);
		setCourses(id);
		setAssignments();
		setResults(pid);
	}

}
