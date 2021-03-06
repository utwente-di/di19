package nl.utwente.di.gradeManager.helpers;

public class DepthGenerator {
	
	/**
	 * Generates a link to a JS files
	 * @param argDepth the depth of the page in which the file is used
	 * @param filename the name of the js file
	 * @return the link to the javascript file including tags
	 */
	public static String generateJSLink(int argDepth, String filename){
		String JS = "js/" + filename;
		for (int i = 0; i < argDepth; i++){
			JS = "../" + JS;
		}
		return "<script src=\"" + JS + "\"></script>\n";
	}
	
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
		return "<link rel=\"stylesheet\" href=\"" + CSS + "\"/>\n";
		
	}
	
	/**
	 * Generates a link to the specified image
	 *	@param int argDepth the depth
	 *	@param string name of the image
	 *	@return the link to the image with proper depth
	 */
	public static String generateLogoLink(int argDepth, String name){
		String link = "images/" + name;
		for(int i = 0; i < argDepth; i++){
			link = "../" + link;
		}
		return link;
	}
	
	
}
