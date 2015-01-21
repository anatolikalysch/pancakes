package org.wahlzeit.extension.UIInteraction;

import org.wahlzeit.model.LanguageConfigs;
import org.wahlzeit.model.ModelConfig;
import org.wahlzeit.model.PhotoSize;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.services.Language;

public class ExtendedUserSession extends UserSession {

	protected ExtendedModelConfig extendConfig = new ExtendedEnglishModelConfig();
	
	/**
	 * 
	 * @methodtype constructor
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public ExtendedUserSession(String myName, String mySiteUrl) {
		super(myName, mySiteUrl);
	}
	
	/**
	 * 
	 * @methodtype constructor
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
	 * @methodtype command
	 * @methodproperty composed
	 * @pre
	 * @post
	 */
	@Override
	public void clear() {
		setConfiguration(LanguageConfigs.get(Language.ENGLISH));
		photoSize = PhotoSize.MEDIUM;
		clearDisplayedPhotos();
		clearPraisedPhotos();
	}
	
	/**
	 * 
	 * @methodtype get
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
	 * @methodtype set
	 * @methodproperty composed
	 * @pre conf instance of ModelConfig
	 * @post
	 */
	@Override
	public void setConfiguration(ModelConfig conf) {
		if (!(conf instanceof ModelConfig))
			doSetConfiguration(new ExtendedEnglishModelConfig());
		else
			switch (conf.getLanguage()) {
			case ENGLISH:
				doSetConfiguration(new ExtendedEnglishModelConfig());
				break;
			case GERMAN:
				doSetConfiguration(new ExtendedGermanModelConfig());
				break;
			case JAPANESE: //no extension implemented
				doSetConfiguration(new ExtendedEnglishModelConfig());
				break;
			case SPANISH: // no extension implemented
				doSetConfiguration(new ExtendedEnglishModelConfig());
				break;
			default:
				break;
			}
	}
	
	/**
	 * 
	 * @methodtype set
	 * @methodproperty primitive
	 * @pre exttendConfig instanceof ExtendedModelConfig (checked in setConfiguration())
	 * @post
	 */
	private void doSetConfiguration(ExtendedModelConfig extendConfig){
		this.extendConfig = extendConfig;
	}
	
	
	
	

}
