package nl.utwente.di.gradeManager.pages;


import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.style.Style;

@Path("/hello")
/**
 * Simple hello world page.
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
		int depth = 1;
		//log the access to the debug.
		Debug.logln("Hello: A user accessed the hello world page!");
		return "<html> <head> " + Style.generateCSSLink(depth) + "</head> <h1> Hello ! </h1> </html>";
	}
	
}
