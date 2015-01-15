package org.wahlzeit.extension.location;

/**
 * This Interface is part of the Location collaboration.
 * @author qwert
 *
 */
public interface Location {
	public String toString();
	public double[] asGPSCoordinates();
	public String getLocationFormat();
}
