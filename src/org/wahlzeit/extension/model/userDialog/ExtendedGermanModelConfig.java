/**
 * 
 */
package org.wahlzeit.extension.model.userDialog;

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
	
	@Override
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
	public String getPancakeIllegalArguments(String message) {
		switch(message){
		case "ingredients":
			return "Die Angabe der Zutaten war fehlerhaft!";
		case "recipe":
			return "Die Angabe des Rezepts war fehlerhaft!!";
		case "type":
			return "Fehler beim einlesen des Typs im PancakeObjekt!";
		case "ID":
			return "Fehler beim einlesen der Id des PancakeObjekts!";
		default:
			return "Fehler beim Lesen des PancakeObjektes!";
		}
	}

}
