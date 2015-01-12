package org.wahlzeit.extension.location;

import org.wahlzeit.utils.StringUtil;

/**
 * This class is part of the Location and the Abstract Factory collaborations.
 * @author qwert
 *
 */
public abstract class AbstractLocation implements Location {
	
	boolean hasLocation = false;
	
	/**
	 * @methodtype conversion
	 * @methodproperty composed
	 * @pre has a location
	 * @post
	 */
	@Override
	public String toString() {
		return asString();
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty template
	 * @pre has a location
	 * @post 
	 */
	public String asString() {
		if (hasLocation)
			return doLocationAsString();
		else
			return "";
	}
	
	/**
	 * @methodtype set
	 * @methodproperty template
	 * @pre location is valid String
	 * @post hasLocation is set
	 */
	protected void setLocation(String location){
		if (StringUtil.isNullOrEmptyString(location)) {
			doSetLocation(location);
			hasLocation = true;
		}
	}
	
	/**
	 * @methodtype get
	 * @methodproperty template
	 * @pre has a location
	 * @post
	 */
	@Override
	public AbstractLocation getLocation(){
		if (hasLocation)
			return doGetLocation();
		else
			return null;
	}
	
	/**
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	@Override
	public String getLocationFormat() {
		return "Abstract";
	}
	
	protected abstract void doSetLocation(String location);
	
	protected abstract String doLocationAsString();
	
	protected abstract AbstractLocation doGetLocation();

}
