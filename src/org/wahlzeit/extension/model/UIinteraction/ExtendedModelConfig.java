package org.wahlzeit.extension.model.UIinteraction;

import org.wahlzeit.model.ModelConfig;

public interface ExtendedModelConfig extends ModelConfig {

	
	//Location Errors
	String getLocationIllegalArguments(String message);
	
	//Domain data Errors
	String getPancakeIllegalArguments(String message);
	String getPancakePostViolation(String message);
}
