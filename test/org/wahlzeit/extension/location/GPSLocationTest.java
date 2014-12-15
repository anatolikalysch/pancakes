package org.wahlzeit.extension.location;

import junit.framework.TestCase;

public class GPSLocationTest extends TestCase {
	
	GPSLocation location1 = new GPSLocation("9.6, 9.7");
	GPSLocation location2 = new GPSLocation("9.7, 9.6");
	GPSLocation location3 = new GPSLocation("8.799984, 8.799993");
	
	/**
	*
	* @param args
	*/
	public static void main(final String[] args) {
	junit.textui.TestRunner.run(GPSLocationTest.class);
	}
	/**
	*
	* @param name
	*/
	public GPSLocationTest(final String name) {
	super(name);
	}
	/**
	*
	*/
	public void testGetFormat() {
	assertEquals(location1.getLocationFormat(), "GPS");
	assertEquals(location2.getLocationFormat(), "GPS");
	}
	/**
	*
	*/
	public void testGetLatGetLon() {
	double latitude = location1.latitude;
	double longitude = location1.longitude;
	assertTrue(latitude == 9.6);
	assertTrue(longitude == 9.7);
	}
	/**
	*
	*/
	public void testAsMapcode() {
	GPSLocation location3 = new GPSLocation("8.799984, 8.799993");
	assertEquals(location3.asMapcodeString(), "International KJ7YB.7TPB");
	}
	
	/**
	*
	*/
	public void testAsString() {
	assertSame(location1.asString().length(), ("9.6, 9.7; "+
	location1.asMapcodeString()).length());
	assertEquals(location1.toString(), location1.asString());
	}
	
	public void testGPSCoordinates() {
		double[] gps = new double[2];
		gps[0] = 9.6;
		gps[1] = 9.7;
		assertEquals(location1.asGPSCoordinates(), gps);
	}
	
	
}
