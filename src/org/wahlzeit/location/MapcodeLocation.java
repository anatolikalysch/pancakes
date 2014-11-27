package org.wahlzeit.location;

import com.mapcode.Mapcode;



public class MapcodeLocation extends AbstractLocation {
	protected Mapcode mapcode;

	public MapcodeLocation(Mapcode mapcode) {
		this.mapcode = mapcode;
		hasLocation = true;
	}
	
	public Mapcode getMapcode() {
		return mapcode;
	}
	
	@Override
	public double[] getLocation() {
		LocationTranslater trans = new LocationTranslater(mapcode);
		trans.translateToGPS();
		return trans.gps;
	}
	
	@Override
	public double getLatitude(){
		LocationTranslater trans = new LocationTranslater(mapcode);
		trans.translateToGPS();
		return trans.gps[0];
	}
	
	@Override
	public double getLongtitude() {
		LocationTranslater trans = new LocationTranslater(mapcode);
		trans.translateToGPS();
		return trans.gps[0];
	}
	
	@Override
	public String getFormat() {
		return "Mapcode";
	}
	
	@Override
	public String asString() {
		return String.valueOf(getLatitude())+", "+String.valueOf(getLongtitude())+"; "+mapcode.asInternationalFullName();
	}
	
	@Override
	public void deleteLocation() {
		super.deleteLocation();
		mapcode = null;
	}

	@Override
	public boolean isEqual(Object obj) {
		if (obj instanceof MapcodeLocation)
			return this.equals(obj);
		else
			return false;
	}

}
