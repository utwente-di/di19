package nl.utwente.di.gradeManager.pages;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.LoginDB;
import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.helpers.*;
import nl.utwente.di.gradeManager.model.Person;
import nl.utwente.di.gradeManager.model.Teacher;

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
		HTMLGenerator html = new HTMLGenerator(2);
		html.addLine("<div style=\"text-align: center;\">");
		html.addLine("<div class=\"loginbox\">");
		html.addLine("<form name=\"login\" action = \"try_login\" method = \"post\" accept-charset=\"utf-8\">");
		html.addLine("<div class=\"row\"><div class=\"large-12 columns\">");
		html.addLine("<label for=\"userid\"><h3>Student of medewerkernummer</h3></label>");
		html.addLine("<input id=\"userid\" type=\"text\" name=\"userid\" size=\"15\" placeholder=\"s0000000\" required autofocus>");
		html.addLine("<label for=\"password\"><h3>Wachtwoord</h3></label>");
		html.addLine("<input id=\"password\" type=\"password\" name=\"password\" placeholder =\"Wachtwoord\" required>");
		html.addLine("<input type=\"submit\" class=\"button expand\" value=\"Login\">");
		html.addLine("</div></div></form></div></div>");
		html.addLine("<br><br><br><br>");
		html.addImage("<img src=\"", "UT_Logo.png", "\" width=\"40%\" height=\"40%\"/>");
		html.addLine("<div class=\"rechtsonder\">Gemaakt door groep 19</div>");
		return html.getHTML();				
	}
	
	@Path("try_login")
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String tryLogin(@FormParam("userid") String userid, @FormParam("password") String password){
		//uses the number of the account to log in (so only numbers)
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
			//Geen van de usernames in de database komt overeen met de userid die in de form meegegeven is.
			Debug.logln("Login: Failed login attempt: " + userid + ":" + password);
			response = "De gebruiker die is opgegeven is niet bekend in het systeem.";
		}
		
		loginDB.closeConnection();
		
		HTMLGenerator html = new HTMLGenerator(2);
		html.addLine(response);
		return html.getHTML();
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
		HTMLGenerator html = new HTMLGenerator(2);
		html.addLine("<h2> The persons are : </h2>");
		html.addLine(personlist);
		return html.getHTML();	
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_HTML)
	/**
	 * 
	 * @return The count of login accounts
	 */
	public String getCount(){
		LoginDB loginDB = new LoginDB(); 
		int count = loginDB.getLoginCount();
		
		loginDB.closeConnection();
		HTMLGenerator html = new HTMLGenerator(2);
		html.addLine("<h1> There are " + String.valueOf(count) + " login accounts.");
		return html.getHTML();
	}
	
	
}
