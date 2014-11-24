package org.wahlzeit.location;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;



public class GPSLocation extends AbstractLocation {
	protected double latitude;
	protected double longtitude;
	

	public GPSLocation(double latitude, double longtitude) {
		this.latitude = latitude;
		this.longtitude = longtitude;
		hasLocation = true;
	}
	
	@Override
	public double[] getLocation() {
		double[] location = new double[2];
		location[0] = latitude;
		location[1] = longtitude;
		return location;
	}
	
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
	
	public double getLatitude() {
		return latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}
	
	@Override
	public String getFormat() {
		return "GPS";
	}

	@Override
	public void setLocation(double latitude, double longtitude) {
		this.latitude = latitude;
		this.longtitude = longtitude;
		hasLocation = true;
	}
	
	public void setLocation(Mapcode mapcode) {
		Point point;
		try {
			point = MapcodeCodec.decode(mapcode.asInternationalFullName());
			latitude = point.getLatDeg();
			longtitude = point.getLonDeg();
			hasLocation = true;
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			e.printStackTrace();
			deleteLocation();
		}
	}

	@Override
	public boolean isEqual(Mapcode mapcode) {
		return (mapcode.asInternationalFullName().equalsIgnoreCase(this.asMapcode().asInternationalFullName()));
	}

	@Override
	public boolean isEqual(double lat, double lon) {
		return (latitude == lat && longtitude == lon);
	}

	@Override
	public boolean hasLocation() {
		return hasLocation;
	}

	@Override
	public String asString() {
		return String.valueOf(latitude)+", "+String.valueOf(longtitude)+"; "+asMapcode().asInternationalFullName();
	}
	
	public Mapcode asMapcode() {
		Mapcode mapcode = MapcodeCodec.encodeToInternational(latitude, longtitude);
		return mapcode;
	}
	
	@Override
	public void deleteLocation() {
		super.deleteLocation();
		latitude = 0;
		longtitude = 0;
	}
}
