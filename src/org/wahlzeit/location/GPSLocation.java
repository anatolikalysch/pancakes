package org.wahlzeit.location;

import com.mapcode.Mapcode;



public class GPSLocation extends AbstractLocation {
	
	protected double[] gps = new double[2];

	public GPSLocation(double[] coord) {
		this.gps[0] = 0;
		this.gps[1] = 0;
		this.coord = gps;
		hasLocation = true;
	}
	
	/**
	 * @methodtype get method
	 */
	@Override
	public String getFormat() {
		return "GPS";
	}
	
	/**
	 * @methodtype conversion method
	 */
	@Override
	public String asString() {
		return String.valueOf(gps[0])+", "+String.valueOf(gps[1])+"; "+asMapcode().asInternationalFullName();
	}
	
	/**
	 * @methodtype conversion method
	 * @return
	 */
	public Mapcode asMapcode() {
		LocationTranslater trans = new LocationTranslater(gps);
		return trans.translateToMapcode();
	}
	
	/**
	 * @methodtype command method
	 */
	@Override
	public void deleteLocation() {
		super.deleteLocation();
		gps[0] = 0;
		gps[1] = 0;
	}
	
	/**
	 * @methodtype query method
	 */
	@Override
	public boolean isEqual(Object obj) {
		if (obj instanceof GPSLocation)
			return this.equals(obj);
		else
			return false;
	}
	
	/**
	 * @methodtype assertion method
	 * @param location
	 * @throws AssertionError
	 */
	protected void assertIsValidLocation(String location) throws AssertionError {
		if (location != null){
			String[] components = location.split(",");
			for (String component : components) {
				component.trim();
			}
			assert(components.length == 2);
			double x = Double.parseDouble(components[0]);
			double y = Double.parseDouble(components[1]);
			assert ((x <= 90 && x >= -90) && (y <= 180 && y >= -180));
		}
	}
}
