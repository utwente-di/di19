package nl.utwente.di.gradeManager.servlets;


import java.util.List;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import nl.utwente.di.gradeManager.db.LoginDB;
import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.model.*;

public class Login extends HttpServlet {
	private final String jsp_address = "Login.jsp";
	private final String student_address = "Student.jsp";
	private final String teacher_address = "Teacher.jsp";
	private final String manager_address = "Manager.jsp";
	
	public void init(ServletConfig config){
		Debug.logln("Login servlet: init called.");
		
	}
	
	/**
	 * GET for the login page, show the login form.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		Debug.logln("Login servlet: doGet.");
		
		HttpSession session = request.getSession(false);
		if (session == null){
			//user is not logged in yet.
		} else {
			//user has a session.
			Debug.logln("There exists a session for the user.");
			String sessionid = session.getId();
			Debug.logln("The id is: " + sessionid);
			LoginDB loginDB = new LoginDB();
			String personid = loginDB.getLoggedInPersonid(sessionid);
			Debug.logln("The personid is " + personid);
			if (personid != "" ){
				List<Person> persons = loginDB.getLogins();
				for(Person p : persons) {
					
					if (p.getPersonID().equals(personid)){

						Debug.logln("He is logged in as " + p.getFirstname() + " " + p.getSurname());
						int personid_asinteger = Integer.parseInt(personid.substring(1));
						Debug.logln("Permissions: student: " + loginDB.isStudent(personid_asinteger) + " teacher : " + loginDB.isTeacher(personid_asinteger) + " manager: " + loginDB.isManager(personid_asinteger));
					}
				}
			}
			
			loginDB.closeConnection();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * POST for the login page, send the information to the server.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		Debug.logln("Login servlet: doPost.");
		//log in
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		
		LoginDB loginDB = new LoginDB();
		
		//it does not take into account whether the user already has a session here.
		for(Person p : loginDB.getLogins()){
			if (p.getPersonID().toLowerCase().equals(username.toLowerCase())){
				if(p.getPassword().equals(password)){
					Debug.logln("Login servlet: login was a success.. creating session for you right now!");
					HttpSession session = request.getSession(true);
					LoginSession ls = new LoginSession(session.getId(),username);
					loginDB.addSession(ls);
					int personid_asinteger = Integer.parseInt(username.substring(1));
					boolean student = loginDB.isStudent(personid_asinteger);
					boolean teacher = loginDB.isTeacher(personid_asinteger);
					boolean manager = loginDB.isManager(personid_asinteger);
					
					Debug.logln("Permissions: student: " + student + " teacher: " + teacher + " manager: " + manager);
					RequestDispatcher dispatcher;
					if(manager){
						//redirect naar manager pagina.
						dispatcher = request.getRequestDispatcher(manager_address);
					} else if(teacher){
						//redirect naar teacher pagina.
						dispatcher = request.getRequestDispatcher(teacher_address);
					} else {
						//redirect naar student pagina.
						dispatcher = request.getRequestDispatcher(student_address);
					}
					//Redirect to the page.
					try {
						dispatcher.forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}				
			}			
		}
		
		loginDB.closeConnection();
		
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//als login succesvol is
	}
	
	public void destroy(){
		Debug.logln("Login servlet: destroy.");
	}	
	
}
