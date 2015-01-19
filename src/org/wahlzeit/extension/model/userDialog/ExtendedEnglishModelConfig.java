/**
 * 
 */
package org.wahlzeit.extension.model.userDialog;

import org.wahlzeit.model.EnglishModelConfig;

/**
 * @author qwert
 *
 */
public class ExtendedEnglishModelConfig extends EnglishModelConfig implements ExtendedModelConfig{

	/**
	 * 
	 */
	public ExtendedEnglishModelConfig() {
		super();
	}
	
	
	/**
	 * 
	 * @methodtype
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public String getLocationIllegalArguments(String message) {
		switch(message){
		case "latitude":
			return "Error occured while reading the latitude!";
		case "longitude":
			return "Error occured while reading the longitude!";
		case "gps":
			return "Error occured while reading the GPS coordinates!";
		case "mapcode":
			return "Error occured while reading the mapcode!";
		default:
			return "Error occured while reading the location values!";
		}
	}

	
	/**
	 * 
	 * @methodtype
	 * @methodproperty
	 * @pre
	 * @post
	 */
	@Override
	public String getPancakeIllegalArguments(String message) {
		switch(message){
		case "ingredients":
			return "Error occured while reading the Ingredients!";
		case "recipe":
			return "Error occured while reading the Recipe!";
		case "type":
			return "Error occured while reading the type of the PancakeObject!";
		case "ID":
			return "Error occured while reading the id!";
		default:
			return "Error occured while reading the Pancake values!";
		}
	}

	
	/**
	 * 
	 * @methodtype
	 * @methodproperty
	 * @pre
	 * @post
	 */
	@Override
	public String getPancakePostViolation(String message) {
		String result;
		String var;
		if (message != null)
			var = message;
		else
			var = "input";
		
		result = "Processing of " + var + " failed!";
		return result;
	}

}
