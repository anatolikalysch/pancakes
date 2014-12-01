package org.wahlzeit.location;

import junit.framework.TestCase;

public class GPSLocationTest extends TestCase { 
	
	double[] gps1 = new double[2];
	//this.gps1[0] = 0;
	//gps1[1] = 0;
	
	double[] gps2 = new double[2];
	//gps2[0] = 9.77;
	//gps2[1] = 5.44;
	
	double[] gps3 = new double[2];
	//gps3[0] = 8.799984;
	//gps3[1] = 8.799993;	
	
	final GPSLocation location1 = new GPSLocation(gps1);
	final GPSLocation location2 = new GPSLocation(gps2);
	final GPSLocation location3 = new GPSLocation(gps3);
	
	
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
		double latitude = location1.getLatitude();
		double longtitude = location1.getLongtitude();
		assertTrue(latitude == 0);
		assertTrue(longtitude == 0);
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
		GPSLocation location3 = new GPSLocation(gps3);
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
	public void testIsEqual() {
		assertTrue(location1.isEqual(gps1));
		assertFalse(location2.isEqual(gps1));
		assertFalse(location2.isEqual(location2.asMapcode()));
	}
	
	/**
	 * 
	 */
	public void testAsString() {
		assertSame(location1.asString().length(), ("0, 0; "+
	location1.asMapcode().asInternationalFullName()).length());
		assertEquals(String.valueOf(location1.getLatitude()), "0");
		assertEquals(String.valueOf(location2.getLongtitude()), "5.44");
	}
	
	/**
	 * 
	 */
	public void testDeleteLocation() {
		location2.deleteLocation();
		assertFalse(location2.hasLocation());
		assertTrue(location2.getLatitude() == 0);
		assertFalse(location3.getLongtitude() == 0);
	}

}
