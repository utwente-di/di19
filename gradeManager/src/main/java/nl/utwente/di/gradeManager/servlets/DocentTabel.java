package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.db.LoginDB;
import nl.utwente.di.gradeManager.model.Assignment;
import nl.utwente.di.gradeManager.model.AssignmentResult;
import nl.utwente.di.gradeManager.model.Course;
import nl.utwente.di.gradeManager.model.Module;
import nl.utwente.di.gradeManager.model.Student;
import nl.utwente.di.gradeManager.model.Teacher;

public class DocentTabel extends HttpServlet {
	
	//Variabelen aanmaken die gebruikt worden in deze klasse
	private final String jsp_address = "Docent.jsp";
	private List<AssignmentResult> results;
	private List<Assignment> assignments;
	private List<Course> courses;
	private List<Module> docentmodules;
	private StudentModules modules;
	private List<Student> students;
	private Module module;
	private Teacher docent;
	
	//Alle informatie uit de database halen en in de locale variablen zetten zodat ze doorgestuurd kunnen worden
	protected void setInfo(int personID, int moduleID, int moduleYear){
		
		//Database connectie aanmaken
		GradesDB gradesDB = new GradesDB();
		
		try{
			//Alle modules van de docent opzoeken voor navigatiebalk
			modules = new StudentModules(personID, docentmodules);
			
			//Informatie van de huidig ingelogde docent opzoeken
			docent = gradesDB.getTeacher(personID);
			
			//Informatie van de module in de database opzoeken
			module = gradesDB.getModule(moduleID, moduleYear);
			
			//Alle vakken die bij de module horen opzoeken
			courses = gradesDB.getCoursesForModule(moduleID);
			
			//Alle studenten uit de database halen, zodat de studentID`s omgezet kunnen worden naar namen
			students = gradesDB.getStudents();
			
			/**Tijdelijke Assingment lijst aanmaken.
			*Moet een arraylist zijn zodat we assignments aan de lijst kunnen toevoegen.
			*De variable 'assignments' is een List<Assignment>, hier kunnen we geen extra assignments aan toevoegen nadat we de database-query
			*voor 1 vak hebben uitgevoerd
			*Daarom hebben de assignmemtList, we lopen elk vak van de module door, en voegen alle assignments van die vakken toe aan de lijst.
			*/
			List<Assignment> assignmentList = new ArrayList<Assignment>();
			//For-loop voor alle vakken van de module
			for(int i = 0; i < courses.size(); i++){
				//Vraagt alle assignments van 1 vak op
				assignments = gradesDB.getAssignmentsForCourse(courses.get(i).getCourseCode(), courses.get(i).getYear());
				//Als er assignments zijn, toevoegen aan assignmentlist
				if (assignments != null){
					//For-loop voor alle assignments in de lijst
					for (int j = 0; j < assignments.size(); j++) {
						//Assignment toevoegen aan de tijdelijke lijst
						if(assignments.get(j) != null){
							assignmentList.add(assignments.get(j));
						}
					}
				}
			}
			//Tijdelijke assignmentlist omzetten naar de uiteindelijke 'assignment' lijst
			assignments = assignmentList;
			
			/**
			 * Tijdelijke AssignmentResult lijst aanmaken
			 * 'resultList' is een arraylist zodat er element aan toegevoegd kunnen worden
			 * Dan alle resultaten van alle assignments toevoegen aan de tijdelijke lijst
			 * Daarna weer tijdelijke lijst terugzetten naar de officiele 'results' AssignmentResult lijst
			 */
			List<AssignmentResult> resultList = new ArrayList<AssignmentResult>();
			//Alle assignments doorlopen
			//Alle assignments van alle vakken staan al in de lijst, dus hoeven geen vak te specificeren
			for(int i = 0; i < assignments.size(); i++){
				//Alle resultaten van een assignment opvragen
				results = gradesDB.getResultsForAssignment( assignments.get(i).getAssignmentID());
				//Als er resultaten zijn, deze toevoegen aan de 'resultList'
				if (results != null){
					//Alle resultaten doorlopen
					for (int j = 0; j < results.size(); j++) {
						//Resultaat toevoegen aan de tijdelijke lijst
						if(results.get(j) != null){
							resultList.add(results.get(j));
						}
					}
				}
			}
			//Tijdelijke lijst weer omzetten naar de definitieve lijst
			results = resultList;
			
		}finally{
			//Database connectie sluiten
			gradesDB.closeConnection();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//Alle variabelen aanmaken die nodig zijn om de 'setInfo' methode aan te roepen
			Integer moduleid;
			Integer moduleyear;
			Integer SID;
				
			//De sessie van de gebruiker opvragen. Als er geen sessie bestaat, deze niet aanmaken
			//HttpSession session = request.getSession(false);
			//Het ID van de sessie opvragen
			//String sessionid = session.getId();
			//Database connectie aanmaken
			//LoginDB logindb = new LoginDB();
			//try{
				//Het studentnummer verbonden aan de huidige sessie in de variabele 'SID' zetten
				//SID = Integer.parseInt(logindb.getLoggedInPersonid(sessionid).substring(1));
			//}finally{
				//Database connectie afsluiten
				//logindb.closeConnection();
			//}
		
			//Tijdelijk het docentnummer op 9876 zetten omdat docent nog niet kunnen inloggen
			SID = 9876;
		
			//Als er geen moduleid en moduleyear in de URL wordt gespecificeerd, de meest recente module van de docent opzoeken en die tonen
				if (request.getParameter("moduleid") == null || request.getParameter("moduleyear") == null){
					//Database connectie aanmaken
					GradesDB gradesDB = new GradesDB();
					try{
						//Alle modules van de gebruiker opvragen
						docentmodules = gradesDB.getModulesForDocent(SID);
						//getModulecode sorteert automatisch op de meest recente module, dus eerste resultaat is de module die getoont moet worden
						moduleid = docentmodules.get(0).getModulecode();
						moduleyear = docentmodules.get(0).getYear();
					}finally{
						//Database connectie afsluiten
						gradesDB.closeConnection();
					}
				}else{
					//Als er wel een module-id&jaar wordt gespecificeerd, deze uit de URL halen en de locale variabelen stoppen
					moduleid = Integer.parseInt(request.getParameter("moduleid"));
					moduleyear = Integer.parseInt(request.getParameter("moduleyear"));
				}
		
			//SetInfo aanroepen zodat alle gegevens uit de database getrokken worden en de beans gevuld kunnen worden
			setInfo(SID, moduleid, moduleyear);
		
			if(docent!= null && modules != null && module != null  && courses != null && assignments != null && results != null){
				//Informatie van de module meegeven voor de JSP pagina
				request.setAttribute("moduletoShow", module);
			
				//Informatie over de docent meegeven aan de JSP pagina
				request.setAttribute("docent", docent);
			
				//Alle modules van de docent meegeven aan de JSP pagina  voor de navigatiebalk
				request.setAttribute("docentModules", modules);
			
				//Bean aanmaken met de lijst van vakken die de docent kan zien
				StudentCourses beanSC = new StudentCourses(SID, courses);
				//De bean meegeven aan de JSP pagina
				request.setAttribute("coursestoShow", beanSC);
		
				//Bean aanmaken met alle assignments van alle vakken van de module
				CourseAssignments beanCA = new CourseAssignments("Dit is een vak", assignments);
				//De bean meegeven aan de JSP pagina
				request.setAttribute("assignmentstoShow", beanCA);
				
				request.setAttribute("docentModules", modules);//Bean aanmaken voor alle resultaten die behaald zijn
				StudentAssignments beanSA = new StudentAssignments("Dit is een resultaat", results);
				//De bean meegeven aan de JSP pagina
				request.setAttribute("resultstoShow", beanSA);
				
				//Alle studenten die het vak volgen in een bean stoppen
				AllStudents beanCS = new AllStudents("Dit is een student", students);
				//De bean meegeven aan de JSP pagina
				request.setAttribute("studentstoShow", beanCS);
		
				//Juiste JSP pagina specificeren om naar door te sturen
				RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
				//Daadwerkelijk alles doorsturen
				dispatcher.forward(request, response);
			}else{
				//Juiste JSP pagina specificeren om naar door te sturen
				RequestDispatcher dispatcher = request.getRequestDispatcher("login");
				//Daadwerkelijk alles doorsturen
				dispatcher.forward(request, response);
			}
	}
}
