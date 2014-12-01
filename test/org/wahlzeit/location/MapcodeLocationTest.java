package org.wahlzeit.location;

import com.mapcode.Mapcode;

import junit.framework.TestCase;

public class MapcodeLocationTest extends TestCase {
	
	Mapcode mapcode1 = new Mapcode("KJ7YB.7TPB", null);
	Mapcode mapcode2 = new Mapcode("KJ9QR.CQC1", null);
	
	final MapcodeLocation location1 = new MapcodeLocation(mapcode1); //8.799984, 8.799993
	final MapcodeLocation location2 = new MapcodeLocation(mapcode2); //9.599988, 9.600021
	
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
		double latitude = location1.getLatitude();
		double longtitude = location1.getLongtitude();
		assertTrue(latitude == 8.799984);
		assertTrue(longtitude == 8.799993);
	}
	
	/**
	 * 
	 */
	public void testGetLocation() {
		double[] result = location1.getLocation();
		assertFalse(result[0] == 8.8);
		assertTrue(result[0] == 9.599988);
	}
	
	/**
	 * 
	 */
	public void testAsMapcode() {
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
		assertTrue(location2.hasLocation);
		assertTrue(location2.getLatitude() == 9.599988);
		assertTrue(location2.getLongtitude() == 9.600021);
	}
	
	/**
	 * 
	 */
	public void testSetLocationMapcode() {
		assertTrue(location2.hasLocation());
		assertTrue(location2.getLatitude() == 9.599988);
		assertTrue(location2.getLongtitude() == 9.600021);
	}
	
	/**
	 * 
	 */
	public void testIsEqual() {
		System.out.println(location2.asString());
		System.out.println(location1.asString());
		assertFalse(location1.isEqual(location2));
		assertTrue(location2.isEqual(location2));
		assertTrue(location1.isEqual(location1.getMapcode()));
		assertFalse(location2.isEqual(location1.getMapcode()));
	}
	
	/**
	 * 
	 */
	public void testAsString() {
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
