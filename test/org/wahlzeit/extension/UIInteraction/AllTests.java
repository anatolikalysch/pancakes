package org.wahlzeit.extension.UIInteraction;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {

	/**
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		
		suite.addTestSuite(ExtendedUserSessionTest.class);
		
		return suite;
	}
}
