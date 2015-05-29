package nl.utwente.di.gradeManager.pages;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Request;
//import javax.ws.rs.core.UriInfo;
//import javax.ws.rs.core.Response;
//import javax.xml.bind.JAXBElement;

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
	@GET
	@Path("dologin")
	@Produces(MediaType.TEXT_HTML)
	public String ShowLogin(){
		int depth = 1;
		return "<html> <head> <meta charset=\"utf-8\"/> " 
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale =1.0\"/>"
				+ "<title> TOSTi inlog</title>" + Style.generateCSSLink(depth) 
				+ "<script src=\"js/vendor/modernizr.js\"></script></head>"
				+ "<body><div style=\"text-align: right; \">"
				+ "<button type=\"button\" style=\"background-color:transparent; border-color:transparent;\""
				+ "onclick =\"alert('Hier komt hulp voor het inloggen te staan')\">"
				+ "<img src=\"help.png\"/></button></div>"
				+ "<div style=\"text-align: center;\">"
				+ "<div style=\"box-sizing: border-box; max-width: 480px; border: 5px solid #6249BB; border-radius: 10px; margin: 200px auto auto; pdaaing: 20px; background-color: #E2C800;\">"
				+ "<form name=\"login\" action = \"try_login\" method = \"post\" accept-charset=\"utf-8\">"
				+ "<div class=\"row\"><div class=\"large-12 columns\">"
				+ "<label for=\"userid\"><b><h5>Student of medewerkernummer</b></h5</label>"
				+ "<input id=\"userid\" type=\"text\" name=\"userid\" size=\"15\" placeholder=\"s0000000\" required>"
				+ "<label for=\"password\"><b><h5>Wachtwoord</b></h5></label>"
				+ "<input id=\"password\" type=\"password\" name=\"password\" placeholder =\"Wachtwoord\" required>"
				+ "<input type=\"submit\" class=\"button expand\" value=\"Login\">"
				+ "</div></div></form></div></div>"
				+ "<br><br><br><br>"
				+ "<img src=\"UT_Logo.png\" width=\"40%\" height=\"40%\"/>"
				+ "<div class=\"NaamOnderaan\">Gemaakt door groep 19</div>"
				+ "<script src=\"js/vendor/jquery.js\"></script>"
				+ "<script src=\"js/foundation.min.js\"></script>"
				+ "<script>$(document).foundation();</script>";
				
	}
	
	@Path("try_login")
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String tryLogin(@FormParam("userid") String userid, @FormParam("password") String password){
		//uses the number of the account to log in (so only numbers)
		int depth = 2;
		String response = "";
		for(Person p : LoginDao.instance.getLogins().values()){
			if (p.getPersonID().equals(userid)){
				//there exists an account for which the user wants to log in
				if(p.getPassword().equals(password)){
					//success
					response = "Login succesvol. Welkom " + p.getFirstname() + ".";
				} else {
					//wrong password
					response = "Wachtwoord komt niet overeen met de bijbehorende gebruikersnaam.";
					System.out.println("Failed login attempt: " + userid + ":" + password);
					System.out.println("Correct would be : " + String.valueOf(p.getPersonID()) + ":" + p.getPassword());
				}
			}
		}
		if (response.equals("")){
			//Geen van de usernames in de LoginDao komt overeen met de userid die in de form meegegeven is.
			System.out.println("Failed login attempt: " + userid + ":" + password);
			response = "De gebruiker die is opgegeven is niet bekend in het systeem.";
		}
		
		return "<html> <head> " + Style.generateCSSLink(depth) + " </head> <h1> " + response + "</h1> </html>";

	}
	
	@GET
	@Path("accounts")
	@Produces(MediaType.TEXT_HTML)
	/**
	 * returns the list of logins to the user in the browser.
	 * @return the list of logins.
	 */
	public String getLoginsBrowser(){
		int depth = 2;
		String personlist = "<ul>\n";
		for (Person p : LoginDao.instance.getLogins().values()){
			personlist += "<li>" + p.getPersonID() + ": " + p.getPassword() + "</li>";
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
