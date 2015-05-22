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
	@Produces(MediaType.TEXT_XML)
	/**
	 * returns the list of logins to the user in the browser.
	 * @return
	 */
	public List<Person> getLoginsBrowser(){
		List<Person> persons = new ArrayList<Person>();
		persons.addAll(LoginDao.instance.getMap().values());
		return persons;
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_HTML)
	/**
	 * 
	 * @return The count of login accounts
	 */
	public String getCount(){
		int count = LoginDao.instance.getMap().size();
		return "<html> <head> " + Style.CSSLink + "</head> <h1> There are " + String.valueOf(count) + " login accounts.";
	}
	
	
}
