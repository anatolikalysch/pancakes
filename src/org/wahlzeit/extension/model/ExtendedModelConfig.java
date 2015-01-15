package org.wahlzeit.extension.model;

import org.wahlzeit.model.ModelConfig;

public interface ExtendedModelConfig extends ModelConfig {

	
	//Location Errors
	String getLocationIllegalArguments(String message);
	
	//Domain data Errors
	String getTypeObjectIllegalArguments(String message);
	String getPancakeIllegalArguments(String message);
}
