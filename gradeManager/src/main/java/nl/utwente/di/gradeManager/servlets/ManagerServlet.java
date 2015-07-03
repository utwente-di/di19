package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.utwente.di.gradeManager.db.LoginDB;

public class ManagerServlet extends HttpServlet{
	
	private final String jsp_address = "Manager.jsp";	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	//protect this page from people who are not managers. 
	HttpSession session = request.getSession(false);
	System.out.println("manager servlet: doGet");
	if(session == null){
		//there is no session, so redirect to login page.
		response.sendRedirect("Login.jsp");
		return;
	} else {
		LoginDB loginDB = new LoginDB();
		String loggedinpersonid = "";
		try{
		loggedinpersonid = loginDB.getLoggedInPersonid(session.getId());
		} finally {
			loginDB.closeConnection();
		}
		if (loggedinpersonid.equals("") ){
			response.sendRedirect("Login.jsp");
			return;
		} else {
			if(!loginDB.isManager(Integer.parseInt(loggedinpersonid.substring(1)))){
				response.sendRedirect("Login.jsp");
				return;
			}
		}
		
	}
	
	
	RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
	dispatcher.forward(request, response);
	}
}
