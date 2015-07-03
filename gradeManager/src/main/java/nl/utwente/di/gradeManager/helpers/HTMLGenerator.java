package nl.utwente.di.gradeManager.helpers;

public class HTMLGenerator {
	
	private static final String head = "<html>\n<head>\n<meta charset=\"utf-8\">\n<meta name=\"viewport\" content=\"width=device-width, initial-scale =1.0\"/>\n<title> TOSTi, your grades made delicious</title>\n";
	private String scripts;
	private static final String endhead = "</head>\n<body>\n";
	private String html;
	private static final String bottom = "<script>$(document).foundation();</script>\n</body>\n</html>\n";
	
	
	private static final String navbar = "<nav class=\"top-bar\" data-topbar role=\"navigation\" data-options=\"is_hover: false\"><ul class=\"title-area\"><li class=\"name\"><h1><a href=\"#\">TOSTi</a></h1></li> <!-- Remove the class \"menu-icon\" to get rid of menu icon. Take out \"Menu\" to just have icon alone --><li class=\"toggle-topbar menu-icon\"><a href=\"#\"><span>Menu</span></a></li></ul><section class=\"top-bar-section\"><!-- Right Nav Section --><ul class=\"left\"><li class=\"has-dropdown\"><a href=\"#\">Studenten</a><ul class=\"dropdown\"><li> <a href=\"/gradeManager/rest/view/students\">Zien</a></li><li> <a href=\"/gradeManager/rest/create/students\">Nieuw</a></li></ul></li><li class=\"has-dropdown\"><a href=\"#\">Docenten</a><ul class=\"dropdown\"><li> <a href=\"/gradeManager/rest/view/teachers\">Zien</a></li><li> <a href=\"/gradeManager/rest/create/teachers\">Nieuw</a></li></ul></li><li><a href=\"/gradeManager/Manager\" class=\"\">Bestand importeren</a></li></ul></section><section class=\"top-bar-section\"><ul class=\"right\"><li><a href=\"#\">My account</a></li><li><a href=\"#\">Settings</a></li><li><a href=\"Logout\">Logout</a></li></ul></section></nav>";
	private final int depth;
	
	public HTMLGenerator(int depth){
		this.depth = depth;
		html = "";
		scripts = DepthGenerator.generateCSSLink(depth);
		addScript("vendor/jquery.js");
		addScript("vendor/modernizr.js");
		addScript("vendor/fastclick.js");
		addScript("foundation/foundation.js");
		addScript("foundation.min.js");
	}
	
	public HTMLGenerator(int depth, boolean addNavbar){
		this(depth);
		if(addNavbar){
			html = navbar;
		}
	}
	
	public void addScript(String name){
		scripts += DepthGenerator.generateJSLink(depth, name);
	}
	
	public String getHTML(){
		return head + scripts + endhead + html + bottom;
	}
	
	public void addLine(String line){
		html += line + "\n";
	}
	
	public void addImage(String firstLine, String imagename, String secondLine){
		html += firstLine + DepthGenerator.generateLogoLink(depth, imagename) + secondLine + "\n";
	}
}
