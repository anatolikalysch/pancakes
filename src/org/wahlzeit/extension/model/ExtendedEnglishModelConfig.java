/**
 * 
 */
package org.wahlzeit.extension.model;

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
	
	public String getLocationIllegalArguments(String message) {
		switch(message){
		case "latitude":
			return "Error occured while reading the latitude!";
		case "longitude":
			return "Error occured while reading the longitude!";
		case "mapcode":
			return "Error occured while reading the mapcode!";
		default:
			return "Error occured while reading the location values!";
		}
	}

	@Override
	public String getTypeObjectIllegalArguments(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPancakeIllegalArguments(String message) {
		// TODO Auto-generated method stub
		return null;
	}

}
