package org.wahlzeit.location;

public interface LocationInterface {
	
	public double[] getLocation();
	
	public String getFormat();
	
	public void setLocation(double[] coord);
	
	public boolean hasLocation();
	
	public boolean isEqual(Object obj);
	
	public String asString();
	
	public void deleteLocation();
}
