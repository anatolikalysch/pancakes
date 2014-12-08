package org.wahlzeit.extension.model;

import junit.framework.TestCase;

public class DomainPhotoTest extends TestCase {
	public DomainPhotoTest (String name) {
		super(name);
	}
	
	public static void main(final String[] args) {
		junit.textui.TestRunner.run(DomainPhotoTest.class);
	}
	
	public void testNullAssignmentPancakePhoto(){
		PancakePhoto photo = (PancakePhoto) PancakeFactory.getInstance().createPhoto();
		try {
			photo.getPancake().setRecipe(null);
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
	
	public void testGuitarManufacturerEquality() {
		Recipe recipe = Recipe.getInstance("1");
		Recipe recipeOther = Recipe.getInstance("1");
		this.assertEquals(recipe, recipeOther);
	}
}