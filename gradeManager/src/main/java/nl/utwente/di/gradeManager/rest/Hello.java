package nl.utwente.di.gradeManager.rest;


import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.helpers.*;

@Path("/hello")
/**
 * Simple hello world page.
 * Note that this is just a test and is not intended to be used, ever.
 *
 */
public class Hello {

	
	@GET
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Say hello to the user in the browser
	 * @return a html page saying Hello!
	 */
	public String sayHtmlHello() {
		//log the access to the debug.
		Debug.logln("Hello: A user accessed the hello world page!");
		HTMLGenerator html = new HTMLGenerator(1);
		html.addLine("<h1> Hello ! </h1>");
		return html.getHTML();
	}
	
}
