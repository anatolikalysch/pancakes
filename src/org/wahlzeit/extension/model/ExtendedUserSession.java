package org.wahlzeit.extension.model;

import org.wahlzeit.model.LanguageConfigs;
import org.wahlzeit.model.ModelConfig;
import org.wahlzeit.model.PhotoSize;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.services.Language;

public class ExtendedUserSession extends UserSession {

	protected ExtendedModelConfig extendConfig = new ExtendedEnglishModelConfig();
	
	public ExtendedUserSession(String myName, String mySiteUrl) {
		super(myName, mySiteUrl);
	}
	
	public ExtendedUserSession(UserSession us) {
		super(us.getName(), us.getSiteUrl());
		this.setConfiguration(us.cfg());
	}
	
	
	/**
	 * 
	 */
	@Override
	public void clear() {
		configuration = LanguageConfigs.get(Language.ENGLISH);
		photoSize = PhotoSize.MEDIUM;
		clearDisplayedPhotos();
		clearPraisedPhotos();
	}
	
	/**
	 * 
	 */
	@Override
	public ExtendedModelConfig cfg() {
		return extendConfig;
	}
	
	/**
	 * 
	 */
	@Override
	public void setConfiguration(ModelConfig conf) {
		
		switch (conf.getLanguage()) {
		case ENGLISH:
			doSetConfiguration(new ExtendedEnglishModelConfig());
			break;
		case GERMAN:
			doSetConfiguration(new ExtendedGermanModelConfig());
			break;
		case JAPANESE: //no extension implemented
			break;
		case SPANISH: // no extension implemented
			break;
		default:
			break;
		}
		
	}
	
	private void doSetConfiguration(ExtendedModelConfig extendConfig){
		this.extendConfig = extendConfig;
	}
	
	
	
	

}
