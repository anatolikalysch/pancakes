package org.wahlzeit.model.extension;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {
	
	/**
	 * 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(PancakeRecipeTests.class);
		return suite;
	}

}
