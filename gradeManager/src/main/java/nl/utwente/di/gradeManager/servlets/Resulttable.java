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
import nl.utwente.di.gradeManager.model.Assignment;
import nl.utwente.di.gradeManager.model.AssignmentOccasion;
import nl.utwente.di.gradeManager.model.AssignmentResult;
import nl.utwente.di.gradeManager.model.Course;
import nl.utwente.di.gradeManager.model.Module;
import nl.utwente.di.gradeManager.model.ModuleResult;
import nl.utwente.di.gradeManager.model.Student;
import nl.utwente.di.gradeManager.db.LoginDB;


public class Resulttable extends HttpServlet {
	
	//Alle benodiggde variablen aanmaken
	private final String jsp_address = "Student.jsp";
	private List<Course> courses;
	private List<Assignment> assignments;
	private List<AssignmentResult> occasions;
	private List<Module> studentmodules;
	private Module module;
	private ModuleResult moduleResult;
	private Student student;
	private StudentModules modules;
	
	protected void setInfo(int personID, int moduleID, int moduleYear){
		
		//Database connectie opzetten
		GradesDB gradesDB = new GradesDB();
		
		//Alle database operaties uitvoeren in try-finally zodat de connectie ook wordt afgesloten mocht er iets fout gaan in de queries
		try{
			
			//De modules van de ingelogde student opzoeken
			if(studentmodules == null){
				studentmodules = gradesDB.getModulesForStudent(personID);
			}
			
			//Alle studentinformatie van de huidig ingelogde student opvragen
			student = gradesDB.getStudent(personID);
			
			//Alle modules die de ingelogde student volgt opzoeken
			modules = new StudentModules(personID, studentmodules);
			
			//De module opzoeken die op het scherm moet verschijnen
			module = gradesDB.getModule(moduleID, moduleYear);
			
			//Alle vakken van de module opzoeken
			courses = gradesDB.getCoursesForModule(moduleID);
			
			//Het resultaat van de gehele module opzoeken
			moduleResult = gradesDB.getModuleResult(personID, module.getModulecode(), module.getYear());
			
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
			 * Daarna weer tijdelijke lijst terugzetten naar de officiele 'occasions' AssignmentResult lijst
			 */
			List<AssignmentResult> resultList = new ArrayList<AssignmentResult>();
			//Alle assignments doorlopen
			//Alle assignments van alle vakken staan al in de lijst, dus hoeven geen vak te specificeren
			for(int i = 0; i < assignments.size(); i++){
				//Alle resultaten van een assignment opvragen
				occasions = gradesDB.getResultsForAssignmentAndStudent(personID, assignments.get(i).getAssignmentID());
				//Als er resultaten zijn, deze toevoegen aan de 'resultList'
				if (occasions != null){
					//Alle resultaten doorlopen
					for (int j = 0; j < occasions.size(); j++) {
						//Resultaat toevoegen aan de tijdelijke lijst
						if(occasions.get(j) != null){
							resultList.add(occasions.get(j));
						}
					}
				}
			}
			//Tijdelijke lijst weer omzetten naar de definitieve lijst
			occasions = resultList;
			
		}finally{
			//Database connectie afsluiten
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
		HttpSession session = request.getSession(false);
		//Het ID van de sessie opvragen
		String sessionid = session.getId();
		//Database connectie aanmaken
		LoginDB logindb = new LoginDB();
		try{
			//Het studentnummer verbonden aan de huidige sessie in de variabele 'SID' zetten
			SID = Integer.parseInt(logindb.getLoggedInPersonid(sessionid).substring(1));
		}finally{
			//Database connectie afsluiten
			logindb.closeConnection();
		}
		
		
		
		//Als er geen moduleid en moduleyear in de URL wordt gespecificeerd, de meest recente module van de student opzoeken en die tonen
		if (request.getParameter("moduleid") == null || request.getParameter("moduleyear") == null){
			//Database connectie aanmaken
			GradesDB gradesDB = new GradesDB();
			try{
				//Alle modules van de gebruiker opvragen
				studentmodules = gradesDB.getModulesForStudent(SID);
				//getModulecode sorteert automatisch op de meest recente module, dus eerste resultaat is de module die getoont moet worden
				moduleid = studentmodules.get(0).getModulecode();
				moduleyear = studentmodules.get(0).getYear();
			}finally{
				//Database connectie afsluiten
				gradesDB.closeConnection();
			}
		}else{
			//Als er wel een module-id&jaar wordt gespecificeerd, deze uit de URL halen en de locale variabelen stoppen
			moduleid = Integer.parseInt(request.getParameter("moduleid"));
			moduleyear = Integer.parseInt(request.getParameter("moduleyear"));
		}
		
		//Alle informatie is nu bekend, dus nu kan 'setInfo' aangeroepen worden.
		setInfo(SID, moduleid, moduleyear);
		
		//De informatie van de student meegeven aan de JSP pagina
		request.setAttribute("student", student);
		
		//De lijst van alle modules meegeven aan de JSP pagina
		request.setAttribute("studentModules", modules);
		
		//De informatie van de module meegeveen aan de JSP pagina
		request.setAttribute("moduletoShow", module);
		
		//Het uiteindelijke moduleresulaat meegeven aan de JSP pagina
		request.setAttribute("moduleresulttoShow", moduleResult);
		
		//Bean aanmaken met de lijst van vakken die de student volgt
		StudentCourses beanSC = new StudentCourses(SID, courses);
		//De bean meegeven aan de JSP pagina
		request.setAttribute("coursestoShow", beanSC);

		//Bean aanmaken met de lijst van alle assignments van alle vakken van de module
		CourseAssignments beanCA = new CourseAssignments("Dit is een vak", assignments);
		//De bean meegeven voor de JSP pagina
		request.setAttribute("assignmentstoShow", beanCA);
		
		//Bean aanmaken van alle behaalde resultaten van de student
		StudentAssignments beanSA = new StudentAssignments("Dit is een resultaat", occasions);
		//Bean meegeven aan de JSP pagina
		request.setAttribute("resultstoShow", beanSA);
		
		//Juiste JSP pagina specificeren om naar door te sturen
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
		//Daadwerkelijk alles doorsturen
		dispatcher.forward(request, response);
			
		}
	}
