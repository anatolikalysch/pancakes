package org.wahlzeit.location;

import junit.framework.TestCase;

public class MapcodeLocationTest extends TestCase {
	
	MapcodeLocation location1 = new MapcodeLocation("KJ7YB.7TPB"); //8.8, 8.8
	MapcodeLocation location2 = new MapcodeLocation("KJ9QR.CQC1"); //9.6, 9.6
	
	/**
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		junit.textui.TestRunner.run(MapcodeLocationTest.class);
	}

	/**
	 * 
	 * @param name
	 */
	public MapcodeLocationTest(final String name) {
		super(name);
	}
	
	/**
	 * 
	 */
	public void testGetFormat() {
		assertEquals(location1.getFormat(), "Mapcode");
		assertEquals(location2.getFormat(), "Mapcode");
	}
	
	/**
	 * 
	 */
	public void testGetLatGetLon() {
		location1 = new MapcodeLocation("KJ7YB.7TPB");
		double latitude = location1.getLatitude();
		double longtitude = location1.getLongtitude();
		assertTrue(latitude == 8.799984);
		assertTrue(longtitude == 8.799993);
	}
	
	/**
	 * 
	 */
	public void testGetLocation() {
		location1 = new MapcodeLocation("AAA KJ9QR.CQC1");
		double[] result = location1.getLocation();
		assertFalse(result[0] == 8.8);
		assertTrue(result[0] == 9.599988);
	}
	
	/**
	 * 
	 */
	public void testAsMapcode() {
		location1 = new MapcodeLocation("KJ7YB.7TPB");
		assertEquals(location2.getMapcode().asInternationalFullName(), "International KJ9QR.CQC1");
		assertFalse(location1.getMapcode().asInternationalFullName() == "International KJ9QR.CQC1");
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
		location2 = new MapcodeLocation("KJ9QR.CQC1"); //9.6, 9.6
		location2.setLocation(9.7, 9.6);
		assertTrue(location2.hasLocation);
		assertTrue(location2.getLatitude() == 9.599988);
		assertTrue(location2.getLongtitude() == 9.600021);
	}
	
	/**
	 * 
	 */
	public void testSetLocationMapcode() {
		location2 = new MapcodeLocation("KJ9QR.CQC1");
		location2.setLocation(location2.getMapcode());
		assertTrue(location2.hasLocation());
		assertTrue(location2.getLatitude() == 9.599988);
		assertTrue(location2.getLongtitude() == 9.600021);
	}
	
	/**
	 * 
	 */
	public void testIsEqual() {
		location1 = new MapcodeLocation("AAA KJ7YB.7TPB"); //8.8, 8.8
		location2 = new MapcodeLocation("KJ9QR.CQC1"); //9.6, 9.6
		System.out.println(location2.asString());
		System.out.println(location1.asString());
		assertFalse(location1.isEqual(9.6, 9.7));
		assertTrue(location2.isEqual(9.599988, 9.600021));
		assertTrue(location1.isEqual(location1.getMapcode()));
		assertFalse(location2.isEqual(9.6, 9.7));
		assertFalse(location2.isEqual(0, 0));
		assertFalse(location2.isEqual(location1.getMapcode()));
	}
	
	/**
	 * 
	 */
	public void testAsString() {
		location1 = new MapcodeLocation("KJ7YB.7TPB");
		assertSame(location1.asString().length(), ("8.799984, 8.799993; "+location1.mapcode.asInternationalFullName()).length());
		assertEquals(String.valueOf(location1.getLatitude()), "8.799984");
		assertEquals(String.valueOf(location1.getLongtitude()), "8.799993");
	}
	
	/**
	 * 
	 */
	public void testDeleteLocation() {
		location1.deleteLocation();
		assertFalse(location1.hasLocation());
		assertTrue(location1.mapcode == null);
	}
}
