package nl.utwente.di.gradeManager.pages;


import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.style.Style;

@Path("/hello")
public class Hello {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		System.out.println("A user accessed the hello world page!");
		return "<html> <head> " + Style.CSSLink + "</head> <h1> Hello ! </h1> </html>";
	}
	
}
