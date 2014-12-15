package org.wahlzeit.extension.location;

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
		assertEquals(location1.getLocationFormat(), "Mapcode");
		assertEquals(location2.getLocationFormat(), "Mapcode");
	}
	/**
	*
	*/
	public void testGetLocation() {	
		location1 = new MapcodeLocation("AAA KJ9QR.CQC1");
		double[] result = location1.asGPSCoordinates();
		assertFalse(result[0] == 8.8);
		assertTrue(result[0] == 9.599988);
	}
	/**
	*
	*/
	public void testAsMapcode() {
		location1 = new MapcodeLocation("KJ7YB.7TPB");
		assertEquals(location2.mapcode, "International KJ9QR.CQC1");
		assertFalse(location1.mapcode == "International KJ9QR.CQC1");
	}
	/**
	*
	*/
	public void testIsEqual() {
		location1 = new MapcodeLocation("AAA KJ7YB.7TPB"); //8.8, 8.8
		location2 = new MapcodeLocation("KJ9QR.CQC1"); //9.6, 9.6
		System.out.println(location2.asString());
		System.out.println(location1.asString());
		assertFalse(location1.equals(location2));
		assertTrue(location2.equals(new MapcodeLocation("KJ9QR.CQC1")));
	}	
}
