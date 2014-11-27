package org.wahlzeit.location;


public abstract class AbstractLocation implements LocationInterface {

	protected boolean hasLocation = false;
	protected double[] coord;
	
	@Override
	public void setLocation(double[] coord) {
		this.coord = coord;
	}

	@Override
	public double[] getLocation() {
		return coord;
	}
	
	@Override
	public String getFormat() {
		return "Abstract";
	}

	@Override
	public boolean hasLocation() {
		return hasLocation;
	}
	
	@Override
	public void deleteLocation() {
		hasLocation = false;
	}

	public double getLatitude() {
		return coord[0];
	}

	public double getLongtitude() {
		return coord[1];
	}
		
}
