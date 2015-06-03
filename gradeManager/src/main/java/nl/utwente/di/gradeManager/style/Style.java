package nl.utwente.di.gradeManager.style;

public class Style {
	
	/**
	 * Generates a link to the css file we use in certain pages
	 * @param argDepth the depth of the page in which the file is used
	 * @return the link to the css file
	 */
	public static String generateCSSLink(int argDepth){
		String CSS = "css/foundation.css";
		for (int i = 0; i < argDepth; i++){
			CSS = "../" + CSS;
		}
		String CSSLink = "<link rel=\"stylesheet\" href=\"" + CSS + "\"/>";
		return CSSLink;
		
	}
	
	/**
	 * Generates a link to the logo of the university, given a certain depth
	 *	@param int argDepth the depth
	 *	@return the link to the logo
	 */
	public static String generateLogoLink(int argDepth){
		String logoLink = "images/UT_Logo.png";
		for(int i = 0; i < argDepth; i++){
			logoLink = "../" + logoLink;
		}
		return logoLink;
	}
	
	public static String generateLogoLink2(int argDepth){
		String logoLink = "images/help.png";
		for(int i = 0; i < argDepth; i++){
			logoLink = "../" + logoLink;
		}
		return logoLink;
	}
}
