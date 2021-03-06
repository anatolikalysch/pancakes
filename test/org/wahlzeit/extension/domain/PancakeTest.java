package org.wahlzeit.extension.domain;

import org.wahlzeit.extension.domain.PancakePhoto;
import org.wahlzeit.extension.domain.PancakePhotoFactory;
import org.wahlzeit.extension.domain.Recipe;

import junit.framework.TestCase;

public class PancakeTest extends TestCase {
	public PancakeTest (String name) {
		super(name);
	}
	
	public static void main(final String[] args) {
		junit.textui.TestRunner.run(PancakeTest.class);
	}
	
	public void testNullAssignmentPancakePhoto(){
		PancakePhoto photo = PancakePhotoFactory.getInstance().createPhoto();
		photo.getPancake().getType().setRecipe(null);
		assertFalse(photo.getPancake().getType().recipe == null);
		
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
