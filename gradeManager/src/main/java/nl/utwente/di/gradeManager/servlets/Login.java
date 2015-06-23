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
	
	public void init(ServletConfig config){
		Debug.logln("Login servlet: init called.");
		
	}
	
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
					}
				}
			}
			
			loginDB.closeConnection();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
				}				
			}			
		}
		
		loginDB.closeConnection();
		
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//als login succesvol is
	}
	
	public void destroy(){
		Debug.logln("Login servlet: destroy.");
	}
	
	
}
