package org.wahlzeit.extension.location;

public interface Location {

	public static final Location EMPTY_LOCATION = GPSLocation.EMPTY_LOCATION;
	public String asString();
	public double[] asGPSCoordinates();
	public void setLocation(String location);
	public String getLocationFormat();
	
}
