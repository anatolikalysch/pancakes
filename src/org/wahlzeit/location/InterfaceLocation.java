package org.wahlzeit.location;

import com.mapcode.Mapcode;

public interface InterfaceLocation {
	
	public double[] getLocation();
	public String getFormat();
	
	void setLocation(double lat, double lon);
	void setLocation(Mapcode mapcode);
	
	public boolean hasLocation();
	public boolean isEqual(Mapcode mapcode);
	public boolean isEqual(double lat, double lon);
	
	public String asString();
	void deleteLocation();
	
}
