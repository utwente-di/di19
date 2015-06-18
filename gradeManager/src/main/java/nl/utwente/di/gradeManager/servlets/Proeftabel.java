package nl.utwente.di.gradeManager.servlets;

public class Proeftabel extends Resulttable{
	
	public void init(){
		int id = 987654321;
		int pid = 1;
		setModule(id);
		setCourses(id);
		setAssignments();
		setResults(pid);
	}

}
