/**
 * 
 */
package org.wahlzeit.extension.model;

import org.wahlzeit.model.GermanModelConfig;

/**
 * @author qwert
 *
 */
public class ExtendedGermanModelConfig extends GermanModelConfig implements ExtendedModelConfig{

	/**
	 * 
	 */
	public ExtendedGermanModelConfig() {
		super();
	}
	
	public String getLocationIllegalArguments(String message) {
		switch(message){
		case "latitude":
			return "Die Angabe des Latitudenwerts war fehlerhaft!";
		case "longitude":
			return "Die Angabe des Longitudenwerts war fehlerhaft!";
		case "mapcode":
			return "Die Angabe des Mapcodes war fehlerhaft!";
		default:
			return "Die Argumente der Ortsangabe waren fehlerhaft!";
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
