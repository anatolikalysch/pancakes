package org.wahlzeit.extension.domain;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {

	/**
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		
		suite.addTestSuite(IngredientsTest.class);
		suite.addTestSuite(PancakeTypeTest.class);
		suite.addTestSuite(PancakeTest.class);
		
		return suite;
	}

}
