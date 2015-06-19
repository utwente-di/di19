package nl.utwente.di.gradeManager.helpers;

public class HTMLGenerator {
	
	private static final String head = "<html>\n<head>\n<meta charset=\"utf-8\">\n<meta name=\"viewport\" content=\"width=device-width, initial-scale =1.0\"/>\n<title> TOSTi, your grades made delicious</title>\n";
	private String scripts;
	private static final String endhead = "</head>\n<body>\n";
	private String html;
	private static final String bottom = "<script>$(document).foundation();</script>\n</body>\n</html>\n";
	
	
	private static final String navbar = "<nav class=\"top-bar\" data-topbar role=\"navigation\" data-options=\"is_hover: false\">\n<ul class=\"title-area\">\n<li class=\"name\">\n<h1><a href=\"#\">TOSTi (NAAM)</a></h1>\n</li>\n <!-- Remove the class \"menu-icon\" to get rid of menu icon. Take out \"Menu\" to just have icon alone -->\n<li class=\"toggle-topbar menu-icon\"><a href=\"#\"><span>Menu</span></a></li>\n</ul>\n\n<section class=\"top-bar-section\">\n<!-- Right Nav Section -->\n<ul class=\"left\">\n<li class=\"has-dropdown\">\n<a href=\"#\">Jaar 1</a>\n<ul class=\"dropdown\">\n<li><a href=\"#\">Module 1</a></li>\n<li><a href=\"#\">Module 2</a></li>\n</ul>\n</li>\n<li class=\"has-dropdown\">\n<a href=\"#\">Jaar 2</a>\n<ul class=\"dropdown\">\n<li><a href=\"#\">Module 1</a>\n<li><a href=\"#\">Module 2</a>\n<li><a href=\"#\">Module 3</a>\n</ul>\n\n</section>\n<section class=\"top-bar-section\">\n<ul class=\"right\">\n<li><a href=\"#\">My account</a></li>\n<li><a href=\"#\">Settings</a></li>\n</section>\n</nav>\n";
	private final int depth;
	
	public HTMLGenerator(int depth){
		this.depth = depth;
		html = "";
		scripts = DepthGenerator.generateCSSLink(depth);
		scripts += DepthGenerator.generateJSLink(depth, "foundation.min.js");
	}
	
	public HTMLGenerator(int depth, boolean addNavbar){
		this(depth);
		if(addNavbar){
			html = navbar;
		}
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
