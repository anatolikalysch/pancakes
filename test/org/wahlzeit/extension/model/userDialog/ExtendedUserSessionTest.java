package org.wahlzeit.extension.model.userDialog;

import org.wahlzeit.extension.model.PancakePhoto;
import org.wahlzeit.extension.model.PancakePhotoFactory;
import org.wahlzeit.extension.model.PancakeTest;
import org.wahlzeit.extension.model.Recipe;

import junit.framework.TestCase;

public class ExtendedUserSessionTest extends TestCase {

	public ExtendedUserSessionTest() {
		super();
	}
	
	public static void main(final String[] args) {
		junit.textui.TestRunner.run(PancakeTest.class);
	}
	
	public void testNullAssignmentPancakePhoto(){
		PancakePhoto photo = PancakePhotoFactory.getInstance().createPhoto();
		photo.getPancake().getType().setRecipe(null);
		assertFalse(photo.getPancake().getType() == null);
		
	}
	
	
	
	public void testNewRecipe() {
		Recipe recipe = Recipe.getInstance("");
		Recipe recipeOther = Recipe.getInstance(null);
		assertEquals(recipe, recipeOther);
		
	}
	
	public void testPancakeIsEqual() {
		Recipe recipe = Recipe.getInstance("1");
		Recipe recipeOther = Recipe.getInstance("1");
		assertEquals(recipe, recipeOther);
	}

}
