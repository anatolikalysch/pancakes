package org.wahlzeit.extension.model;

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
		try {
			photo.getPancake().getType().setRecipe(null);
		} catch (IllegalArgumentException as) {
			return;
		}
		fail();
	}
	
	
	
	public void testNewRecipe() {
		Recipe recipe = Recipe.getInstance("");
		try {
			Recipe recipeOther = Recipe.getInstance(null);
		} catch (IllegalArgumentException as) {
			return;
		}
		fail();
	}
	
	public void testPancakeIsEqual() {
		Recipe recipe = Recipe.getInstance("1");
		Recipe recipeOther = Recipe.getInstance("1");
		this.assertEquals(recipe, recipeOther);
	}
}
