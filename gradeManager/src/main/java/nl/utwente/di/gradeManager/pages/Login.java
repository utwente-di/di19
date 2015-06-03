package nl.utwente.di.gradeManager.pages;


import java.sql.ResultSet;

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






import nl.utwente.di.gradeManager.db.LoginDB;
import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.model.Person;
import nl.utwente.di.gradeManager.model.Teacher;
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
		int depth = 2;
		return "<html> <head> <meta charset=\"utf-8\"/> " 
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale =1.0\"/>"
				+ "<title> TOSTi inlog</title>" + Style.generateCSSLink(depth) 
				+ "<script src=\"js/vendor/modernizr.js\"></script></head>"
				+ "<body><div style=\"text-align: right; \">"
				+ "<button type=\"button\" style=\"background-color:transparent; border-color:transparent;\""
				+ "onclick =\"alert('Hier komt hulp voor het inloggen te staan')\">"
				+ "<img src=\"" + Style.generateLogoLink(depth, "help.png") + "\"/></button></div>"
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
				+ "<img src=\"" + Style.generateLogoLink(depth, "UT_Logo.png") +"\" width=\"40%\" height=\"40%\"/>"
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
		LoginDB loginDB = new LoginDB();
		
		for(Person p :  loginDB.getLogins()){
			if (p.getPersonID().toLowerCase().equals(userid.toLowerCase())){
				//there exists an account for which the user wants to log in
				if(p.getPassword().equals(password)){
					//success
					response = "Login succesvol. Welkom " + p.getFirstname() + ".";
					if (p instanceof Teacher){
						if (((Teacher) p).isManager()){
							//p is an admin
							response = "God mode activated.";
						}
					}
				} else {
					//wrong password
					response = "Wachtwoord komt niet overeen met de bijbehorende gebruikersnaam.";
					Debug.logln("Login: Failed login attempt: " + userid + ":" + password);
					Debug.logln("Login: Correct would be : " + String.valueOf(p.getPersonID()) + ":" + p.getPassword());
				}
			}
		}
		if (response.equals("")){
			//Geen van de usernames in de LoginDao komt overeen met de userid die in de form meegegeven is.
			Debug.logln("Login: Failed login attempt: " + userid + ":" + password);
			response = "De gebruiker die is opgegeven is niet bekend in het systeem.";
		}
		
		loginDB.closeConnection();
		
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
		LoginDB loginDB = new LoginDB();
		for (Person p : loginDB.getLogins()){
			personlist += "<li>" + p.getPersonID() + ": " + p.getPassword() + "</li>";
		}
		personlist+="</ul>";
		loginDB.closeConnection();
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
		LoginDB loginDB = new LoginDB(); 
		int count = loginDB.getLoginCount();
		
		loginDB.closeConnection();
		
		return "<html> <head> " + Style.generateCSSLink(depth) + "</head> <h1> There are " + String.valueOf(count) + " login accounts.";
	}
	
	
}
