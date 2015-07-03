package nl.utwente.di.gradeManager.servlets;


import java.util.List;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import nl.utwente.di.gradeManager.db.LoginDB;
import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.helpers.Security;
import nl.utwente.di.gradeManager.model.*;

public class Login extends HttpServlet {
	private final String jsp_address = "Login.jsp";
	private final String student_address = "Resulttabel";
	private final String teacher_address = "DocentTabel";
	private final String manager_address = "Manager";
	
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
			try{
				String personid = loginDB.getLoggedInPersonid(sessionid);
				Debug.logln("The personid is " + personid);
				if (personid != "" ){
					List<Person> persons = loginDB.getLogins();
					for(Person p : persons) {
						
						if (p.getPersonID().equals(personid)){
	
							Debug.logln("He is logged in as " + p.getFirstname() + " " + p.getSurname());
							int personid_asinteger = Integer.parseInt(personid.substring(1));
							boolean student = loginDB.isStudent(personid_asinteger);
							boolean teacher = loginDB.isTeacher(personid_asinteger);
							boolean manager = loginDB.isManager(personid_asinteger);
							
							Debug.logln("Permissions: student: " + student + " teacher: " + teacher + " manager: " + manager);
							if(manager){
								//redirect naar manager pagina.
								Debug.logln("Login: Redirecting to manager page!");
								try {
									response.sendRedirect(manager_address);
								} catch (IOException e) {
									e.printStackTrace();
								}
								return;
							} 
							if(teacher){
								//redirect naar teacher pagina.
								Debug.logln("Login: Redirecting to teacher page!");
								try {
									response.sendRedirect(teacher_address);
								} catch (IOException e) {
									e.printStackTrace();
								}
								return;
							} else {
								//redirect naar student pagina.
	
								Debug.logln("Login: Redirecting to student page!");
								try {
									response.sendRedirect(student_address);
								} catch (IOException e) {
									e.printStackTrace();
								}
								return;
							}
						
						
						}
					}
				}
			}finally{	
				loginDB.closeConnection();
			}
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
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginals = request.getParameter("loginals");
		
		LoginDB loginDB = new LoginDB();
		
		//it does not take into account whether the user already has a session here.
		try{
			for(Person p : loginDB.getLogins()){
				if (p.getPersonID().toLowerCase().equals(username.toLowerCase())){
					int personid_asinteger = Integer.parseInt(username.substring(1));
					if(loginDB.checkPassword(personid_asinteger, password)){
						Debug.logln("Login servlet: login was a success.. creating session for you right now!");
						HttpSession session = request.getSession(true);
						LoginSession ls = new LoginSession(session.getId(),username);
						loginDB.addSession(ls);
						boolean student = loginDB.isStudent(personid_asinteger);
						boolean teacher = loginDB.isTeacher(personid_asinteger);
						boolean manager = loginDB.isManager(personid_asinteger);
						
						Debug.logln("Permissions: student: " + student + " teacher: " + teacher + " manager: " + manager);
						Debug.logln("Gekozen:" + loginals);
						if(manager && loginals.equals("Manager")){
							//redirect naar manager pagina.
							Debug.logln("Login: Redirecting to manager page!");
							try {
								response.sendRedirect(manager_address);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return;
						} 
						if(teacher && loginals.equals("Docent")){
							//redirect naar teacher pagina.
							Debug.logln("Login: Redirecting to teacher page!");
							try {
								response.sendRedirect(teacher_address);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return;
						} 
						if(student && loginals.equals("Student")){
							//redirect naar teacher pagina.
							Debug.logln("Login: Redirecting to teacher page!");
							try {
								response.sendRedirect(student_address);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return;
						}
						else {
							//redirect naar student pagina.
	
							Debug.logln("Login: Verkeerde combinatie!");
							try {
								response.sendRedirect(jsp_address);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return;
						}
					}				
				}			
			}
		}finally{
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
		
		
		//als login succesvol is
	}
	
	public void destroy(){
		Debug.logln("Login servlet: destroy.");
	}	
	
}
