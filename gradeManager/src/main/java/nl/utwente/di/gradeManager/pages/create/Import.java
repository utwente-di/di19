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
		html.addLine("<html>");
		html.addLine("<head>");
		html.addLine("<link rel=\"stylesheet\" href=\"css/foundation.css\">");
		html.addLine("<script src=\"js/d3.js\"></script>");;
		html.addLine("<script src=\"js/d3.min.js\"></script>");
		html.addLine("<script src=\"js/import.js\"></script>");
		html.addLine("<script src=\"js/modernizr.js\"></script>");
		html.addLine("<script src=\"js/jquery.js\"></script>");
		html.addLine("<script src=\"js/fastclick.js\"></script>");
		html.addLine("<script src=\"js/foundation.js\"></script>);");
		html.addLine("</head>");
		html.addLine("<body>");
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
		html.addLine("</body>");
		html.addLine("</html>");
		
		return html.getHTML();
		
	}
	
	@POST
	public void stuurDoor(){
		iets met doorsturen
	}
		
	
}
