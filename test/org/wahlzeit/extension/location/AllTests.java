package org.wahlzeit.extension.location;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {

	/**
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		
		suite.addTestSuite(GPSLocationTest.class);
		suite.addTestSuite(MapcodeLocationTest.class);
		
		return suite;
	}
}
