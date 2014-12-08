package org.wahlzeit.extension.location;

import com.mapcode.MapcodeCodec;

public class GPSLocation extends AbstractLocation {

	protected double latitude;
	protected double longitude;
	
	public static final GPSLocation EMPTY_LOCATION = new GPSLocation();
	
	public GPSLocation() {
		setLocation("0, 0");
	}
	
	public GPSLocation(String location) {
		setLocation(location);
	}
	
	@Override
	protected void assertIsValidLocation(String location) throws AssertionError {
		if (location != null) {
			String[] components = location.split(",");
			for (String component : components) {
				component.trim();
			}
			assert(components.length == 2);
			double x = Double.parseDouble(components[0]);
			double y = Double.parseDouble(components[1]);
			assert ((x <= 90.0 && x >= -90.0) && (y <= 180.0 && y >= -180.0));
		}
	}
	
	
	protected void doSetLocation(String location) {
		String[] components = location.split(",");
		for (String component : components) {
			component.trim();
		}
		this.latitude = Double.parseDouble(components[0]);
		this.longitude = Double.parseDouble(components[1]);
	}
	
	@Override
	public String getLocationFormat() {
		return "GPS";
	}
	
	public String asMapcodeString() {
		return MapcodeCodec.encodeToShortest(latitude, longitude).asInternationalISO();
	}
	
	@Override
	public double[] asGPSCoordinates() {
		double [] result = {this.latitude,this.longitude};
		return result;
	}

	@Override
	protected String doLocationAsString() {
		return latitude+", "+longitude;
		
	}
}
