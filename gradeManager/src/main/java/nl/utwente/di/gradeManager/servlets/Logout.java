package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.utwente.di.gradeManager.debug.Debug;

public class Logout extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
	Debug.logln("Login servlet: doGet.");
	
	HttpSession session = request.getSession(false);

	if(session != null){
	    session.invalidate();
	}
	RequestDispatcher rd = request.getRequestDispatcher("login");
	try { rd.forward(request, response);
	} catch (ServletException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}   
	
	}
}
