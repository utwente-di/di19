package nl.utwente.di.gradeManager.pages.create;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.helpers.HTMLGenerator;

@Path("import")
public class Import{
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String showImport(){
		HTMLGenerator html = new HTMLGenerator(2);
		html.addScript("import.js");
		html.addLine("<div id=\"dvImportSegments\" class=\"fileupload \">");
		html.addLine("<fieldset>");
		html.addLine("<legend>Importeer uw bestand</legend>");
		html.addLine("<input type=\"file\" name=\"File Upload\" id=\"txtFileUpload\" accept=\".csv, .xls\" />");
		html.addLine("<select id=\"tableSelector\">");
		html.addLine("<option value=\"Persons\">Personen</option>");
		html.addLine("<option value=\"Grades\">Cijfers</option>");
		html.addLine("<option value=\"Modules\">Modules</option>");
		html.addLine("</select> ");
		html.addLine("</fieldset>");
		html.addLine("</div>");
		html.addLine("<div id=\"showHash\">");
		html.addLine("</div>");
		html.addLine("<form id=\"importData\">");
		html.addLine("</form>");
		html.addLine("<script>");
		html.addLine("<$(document).ready(addListeners());");
		html.addLine("</script>");
		
		return html.getHTML();
		
	}
	
	@POST
	public void stuurDoor(){
		//iets met doorsturen
	}
		
	
}
