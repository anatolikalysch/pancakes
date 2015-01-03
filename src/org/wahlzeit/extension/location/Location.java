package org.wahlzeit.extension.location;

public interface Location {
	public String toString();
	public double[] asGPSCoordinates();
	public String getLocationFormat();
	public AbstractLocation getLocation();
}
