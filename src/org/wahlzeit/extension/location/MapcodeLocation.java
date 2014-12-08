package org.wahlzeit.extension.location;

import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;

public class MapcodeLocation extends AbstractLocation {

	protected String mapcode;
	
	public MapcodeLocation(String location) {
		this.setLocation(location);
	}
	
	@Override
	public void doSetLocation(String location) {
		this.mapcode = location;
	}
	
	@Override
	protected void assertIsValidLocation(String location) throws AssertionError {
		try {
			MapcodeCodec.decode(location);
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			throw new AssertionError();
		}
	}
	
	public String asGPSString() {
		try {
			Point point = MapcodeCodec.decode(this.mapcode);
			return point.getLatDeg()+", "+point.getLonDeg();
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			return "None";
		}
	}
	
	@Override
	public String getLocationFormat() {
		return "Mapcode";
	}
	
	@Override
	public double[] asGPSCoordinates() {
		double[] result = new double[2];
		try {
			Point point = MapcodeCodec.decode(this.mapcode);
			result[0] = point.getLatDeg();
			result[1] = point.getLonDeg();
		} catch (IllegalArgumentException | UnknownMapcodeException e) {
			
		}
		return result;
	}
	
	@Override
	protected String doLocationAsString() {
		return mapcode;
	}

}
