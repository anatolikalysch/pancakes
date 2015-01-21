package org.wahlzeit.extension.domain;

import org.wahlzeit.extension.domain.Recipe;

import junit.framework.TestCase;

public class PancakeTypeTest extends TestCase {
	
	PancakeType pt;

	public PancakeTypeTest() {
		super();
	}
	
	public static void main(final String[] args) {
		junit.textui.TestRunner.run(PancakeTypeTest.class);
	}

	public void getSetName(String name) {
		pt = new PancakeType();
		pt.setName("testPancake");
		assertTrue(pt.getName() == "testPancake");
	}

	public void getSetRecipe(Recipe recipe) {
		pt = new PancakeType();
		pt.setRecipe(Recipe.getInstance("This is a test Recipe and should be discarded."));
		assertTrue(pt.getRecipe() == Recipe.getInstance("This is a test Recipe and should be discarded."));
	}

	public void getSetIng(Ingredients ingredients) {
		pt = new PancakeType();
		pt.setIng(Ingredients.getInstance("Milk, flour, sugar, salt, baking powder, eggs"));
		assertTrue(pt.getIng() == Ingredients.getInstance("Milk, flour, sugar, salt, baking powder, eggs"));
	}

}
