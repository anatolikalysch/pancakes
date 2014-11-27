package org.wahlzeit.location;

import com.mapcode.Mapcode;



public class GPSLocation extends AbstractLocation {

	public GPSLocation(double[] coord) {
		this.coord[0] = coord[0];
		this.coord[1] = coord[1];
		hasLocation = true;
	}
	
	@Override
	public String getFormat() {
		return "GPS";
	}

	@Override
	public String asString() {
		return String.valueOf(coord[0])+", "+String.valueOf(coord[1])+"; "+asMapcode().asInternationalFullName();
	}
	
	public Mapcode asMapcode() {
		LocationTranslater trans = new LocationTranslater(coord);
		return trans.translateToMapcode();
	}
	
	@Override
	public void deleteLocation() {
		super.deleteLocation();
		coord[0] = 0;
		coord[1] = 0;
	}

	@Override
	public boolean isEqual(Object obj) {
		if (obj instanceof GPSLocation)
			return this.equals(obj);
		else
			return false;
	}
}
