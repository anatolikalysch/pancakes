package org.wahlzeit.location;

import junit.framework.TestCase;

public class GPSLocationTest extends TestCase {
	
	GPSLocation location1 = new GPSLocation(9.6, 9.7);
	GPSLocation location2 = new GPSLocation(9.7, 9.6);
	GPSLocation location3 = new GPSLocation(8.799984, 8.799993);
	double latitude = 9.6;
	double longtitude = 9.7;
	
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
		assertEquals(location1.getFormat(), "GPS");
		assertEquals(location2.getFormat(), "GPS");
	}
	
	/**
	 * 
	 */
	public void testGetLatGetLon() {
		latitude = location1.getLatitude();
		longtitude = location1.getLongtitude();
		assertTrue(latitude == 9.6);
		assertTrue(longtitude == 9.7);
	}
	
	/**
	 * 
	 */
	public void testGetLocation() {
		double[] result = location1.getLocation();
		assertTrue(result[0] == 9.6);
		assertTrue(result[1] == 9.7);
	}
	
	/**
	 * 
	 */
	public void testAsMapcode() {
		GPSLocation location3 = new GPSLocation(8.799984, 8.799993);
		assertEquals(location3.asMapcode().asInternationalFullName(), "International KJ7YB.7TPB");
	}
	
	/**
	 * 
	 */
	public void testHasLocation() {
		assertTrue(location1.hasLocation);
		assertTrue(location1.hasLocation());
	}
	
	/**
	 * 
	 */
	public void testSetLocationDouble() {
		location2.setLocation(9.7, 9.6);
		assertTrue(location2.hasLocation);
		assertTrue(location2.getLatitude() == 9.7);
		assertTrue(location2.getLongtitude() == 9.6);
	}
	
	/**
	 * 
	 */
	public void testSetLocationMapcode() {
		location2 = new GPSLocation(8.799984, 8.799993);
		location2.setLocation(location2.asMapcode());
		System.out.println(location2.asMapcode().asInternationalFullName());
		MapcodeLocation n = new MapcodeLocation("AAA KJ7YB.7TPB");
		System.out.println(String.valueOf(n.getLatitude()));
		System.out.println(String.valueOf(n.getLongtitude()));
		assertTrue(location2.getLatitude() == 8.799984);
		assertTrue(location2.getLongtitude() == 8.799993);
		assertTrue(location2.hasLocation);
	}
	
	/**
	 * 
	 */
	public void testIsEqual() {
		location1 = new GPSLocation(9.6, 9.7);
		location2 = new GPSLocation(9.7, 9.6);
		location3 = new GPSLocation(8.8, 8.8);
		assertTrue(location1.isEqual(9.6, 9.7));
		assertTrue(location1.isEqual(latitude, longtitude));
		assertTrue(location1.isEqual(location1.asMapcode()));
		assertFalse(location2.isEqual(9.6, 9.7));
		assertFalse(location3.isEqual(0, 0));
		assertFalse(location2.isEqual(location1.asMapcode()));
	}
	
	/**
	 * 
	 */
	public void testAsString() {
		assertSame(location1.asString().length(), ("9.6, 9.7; "+
	location1.asMapcode().asInternationalFullName()).length());
		assertEquals(String.valueOf(location1.getLatitude()), "9.6");
		assertEquals(String.valueOf(location1.getLongtitude()), "9.7");
	}
	
	/**
	 * 
	 */
	public void testDeleteLocation() {
		location1.deleteLocation();
		assertFalse(location1.hasLocation());
		assertTrue(location1.latitude == 0);
		assertTrue(location1.longtitude == 0);
	}

}
