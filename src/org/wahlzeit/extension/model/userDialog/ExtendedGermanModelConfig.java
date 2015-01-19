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
	
	/**
	 * 
	 * @methodtype
	 * @methodproperty
	 * @pre
	 * @post
	 */
	@Override
	public String getLocationIllegalArguments(String message) {
		switch(message){
		case "latitude":
			return "Die Angabe des Latitudenwerts war fehlerhaft!";
		case "longitude":
			return "Die Angabe des Longitudenwerts war fehlerhaft!";
		case "gps":
			return "Einlesen der GPS Koordinaten schlug fehl!";
		case "mapcode":
			return "Die Angabe des Mapcodes war fehlerhaft!";
		default:
			return "Die Argumente der Ortsangabe waren fehlerhaft!";
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
			
			switch(message){
			case "ingredients":
				var = "den Zutaten";
				break;
			case "recipe":
				var = "dem Rezept";
				break;
			case "ID":
				var = "der PancakeID";
				break;
			default:
				var = "den Eingaben";
				break;
			}
		
		else
			var = "den Eingaben";
		
		result = "Bearbeitung von " + var + " schlug fehl!";
		return result;
	}
}
