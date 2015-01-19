package org.wahlzeit.extension.model.userDialog;

import org.wahlzeit.model.LanguageConfigs;
import org.wahlzeit.model.ModelConfig;
import org.wahlzeit.model.PhotoSize;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.services.Language;

public class ExtendedUserSession extends UserSession {

	protected ExtendedModelConfig extendConfig = new ExtendedEnglishModelConfig();
	
	/**
	 * 
	 * @methodtype
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public ExtendedUserSession(String myName, String mySiteUrl) {
		super(myName, mySiteUrl);
	}
	
	/**
	 * 
	 * @methodtype
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public ExtendedUserSession(UserSession us) {
		super(us.getName(), us.getSiteUrl());
		this.setConfiguration(us.cfg());
	}
	
	
	/**
	 * 
	 * @methodtype
	 * @methodproperty
	 * @pre
	 * @post
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
	 * @methodtype
	 * @methodproperty
	 * @pre
	 * @post
	 */
	@Override
	public ExtendedModelConfig cfg() {
		return extendConfig;
	}
	
	/**
	 * 
	 * @methodtype
	 * @methodproperty
	 * @pre
	 * @post
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
	
	/**
	 * 
	 * @methodtype
	 * @methodproperty
	 * @pre
	 * @post
	 */
	private void doSetConfiguration(ExtendedModelConfig extendConfig){
		this.extendConfig = extendConfig;
	}
	
	
	
	

}
