package org.wahlzeit.extension.location;

public class FactoryProducer {
	
	public AbstractFactory getFactory(String locationType){
		switch (locationType){
		case "GPS":
			return GPSLocationFactory.getInstance();
		case "Mapcode":
			return MapcodeLocationFactory.getInstance();
		}
		//if choice is not served
		return null;
	}
}
