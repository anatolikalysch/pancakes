package org.wahlzeit.extension.UIInteraction;

import org.wahlzeit.extension.UIInteraction.ExtendedEnglishModelConfig;
import org.wahlzeit.extension.UIInteraction.ExtendedGermanModelConfig;
import org.wahlzeit.extension.UIInteraction.ExtendedUserSession;
import org.wahlzeit.extension.domain.PancakePhoto;
import org.wahlzeit.extension.domain.PancakePhotoFactory;
import org.wahlzeit.extension.domain.PancakeTest;
import org.wahlzeit.extension.domain.Recipe;
import org.wahlzeit.model.EnglishModelConfig;
import org.wahlzeit.model.GermanModelConfig;
import org.wahlzeit.model.ModelConfig;
import org.wahlzeit.model.PhotoSize;

import junit.framework.TestCase;

public class ExtendedUserSessionTest extends TestCase {
	
	ExtendedUserSession eus;

	public ExtendedUserSessionTest() {
		super();
		
	}
	
	public static void main(final String[] args) {
		junit.textui.TestRunner.run(ExtendedUserSessionTest.class);
	}
	
	public void testNewEUS() {
		eus = new ExtendedUserSession("example", "example");
		assertFalse(eus.cfg() instanceof ExtendedGermanModelConfig);
		assertTrue(eus.cfg() instanceof ExtendedEnglishModelConfig);
		assertTrue(eus.getName().matches("example"));
	}
	
	public void testClear() {
		eus.clear();
		assertFalse(eus.cfg() instanceof ExtendedGermanModelConfig);
		assertTrue(eus.cfg() instanceof ExtendedEnglishModelConfig);
		assertTrue(eus.getPhotoSize() == PhotoSize.MEDIUM);
	}

	public void testSetConfiguration(){
		ModelConfig eConf = new EnglishModelConfig();
		ModelConfig dConf = new GermanModelConfig();
		eus.setConfiguration(eConf);
		assertFalse(eus.cfg() instanceof ExtendedGermanModelConfig);
		assertEquals(eus.cfg().getPancakeIllegalArguments("ID"), "Error occured while reading the id!");
		assertEquals(eus.cfg().getLocationIllegalArguments("gps"), "Error occured while reading the GPS coordinates!");
		eus.setConfiguration(dConf);
		assertTrue(eus.cfg() instanceof ExtendedGermanModelConfig);
		assertEquals(eus.cfg().getPancakePostViolation("recipe"), "Bearbeitung von dem Rezept schlug fehl!");
		
	}
}
