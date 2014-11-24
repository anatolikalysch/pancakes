package org.wahlzeit.location;

import com.mapcode.Mapcode;



public abstract class AbstractLocation implements InterfaceLocation {

	protected boolean hasLocation = false;
	protected double lat;
	protected double lon;

	
	@Override
	public String getFormat() {
		return "Abstract";
	}

	@Override
	public void setLocation(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	@Override
	public boolean isEqual(Mapcode mapcode) {
		return false;
	}

	@Override
	public boolean isEqual(double lat, double lon) {
		return false;
	}

	@Override
	public boolean hasLocation() {
		return hasLocation;
	}

	@Override
	public String asString() {
		return null;
	}
	
	@Override
	public void deleteLocation() {
		hasLocation = false;
	}

	public double getLatitude() {
		return lat;
	}

	public double getLongtitude() {
		return lon;
	}
		
}
