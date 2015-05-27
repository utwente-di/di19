package nl.utwente.di.gradeManager.pages;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import nl.utwente.di.gradeManager.model.Person;
import nl.utwente.di.gradeManager.pages.login.dao.LoginDao;
import nl.utwente.di.gradeManager.style.Style;

@Path("/login")
public class Login {
//	@Context
//	UriInfo uriInfo;
//	@Context
//	Request request;
//	String id;
	
	public Login(){
//		this.uriInfo = uriInfo;
//		this.request = request;
//		this.id = id;		
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	/**
	 * returns the list of logins to the user in the browser.
	 * @return the list of logins.
	 */
	public String getLoginsBrowser(){
		int depth = 1;
		String personlist = "<ul>\n";
		for (Person p : LoginDao.instance.getLogins().values()){
			personlist += "<li>" + p.getPersonID() + ":" + p.getPassword() + "</li>";
		}
		personlist+="</ul>";
		return "<html> <head> " + Style.generateCSSLink(depth) + " </head> <body> <h2> The persons are : </h2>" + personlist;
				
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_HTML)
	/**
	 * 
	 * @return The count of login accounts
	 */
	public String getCount(){
		int depth = 2;
		int count = LoginDao.instance.getLogins().size();
		return "<html> <head> " + Style.generateCSSLink(depth) + "</head> <h1> There are " + String.valueOf(count) + " login accounts.";
	}
	
	
}
