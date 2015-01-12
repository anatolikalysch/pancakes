package org.wahlzeit.extension.location;

import org.wahlzeit.utils.StringUtil;

/**
 * This class is part of the AbstractFactory collaboration and the PancakePhoto / Location collaboration.
 * @author qwert
 *
 */
public class FactoryProducer {
	
	/**
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre locationType is valid String
	 * @post
	 */
	public AbstractLocationFactory getFactory(String locationType){
		if (!StringUtil.isNullOrEmptyString(locationType))
			switch (locationType){
			case "GPS":
				return GPSLocationFactory.getInstance();
			case "Mapcode":
				return MapcodeLocationFactory.getInstance();
			default:
				return null;
			}
		else
			return null;
	}
}
