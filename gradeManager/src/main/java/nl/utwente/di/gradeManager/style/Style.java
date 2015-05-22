package nl.utwente.di.gradeManager.style;

public class Style {
	
	public static String generateCSSLink(int depth){
		String CSS = "/css/foundation.css";
		for (int i = 0; i < depth; i++){
			CSS = "../" + CSS;
		}
		String CSSLink = "<link rel=\"stylesheet\" href=\"" + CSS + "\"/>";
		return CSSLink;
		
	}
	
}
