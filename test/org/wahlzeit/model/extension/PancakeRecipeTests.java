package org.wahlzeit.model.extension;

import junit.framework.TestCase;

public class PancakeRecipeTests extends TestCase{
	
	private String ingredient1 = "";
	private String ingredient2 = "I am an ingredient";
	private String ingredient3 = "42";
	private String ingredient4 = "qwert";
	private String ingredient5 = "recipe";
	
	private PancakeRecipe mixedRecipe = new PancakeRecipe(ingredient1, ingredient2,
				ingredient3, ingredient4, ingredient1);
	private PancakeRecipe emptyRecipe = new PancakeRecipe(ingredient1, ingredient1,
			 	ingredient1, ingredient1, ingredient1);
	private PancakeRecipe fullRecipe = new PancakeRecipe(ingredient3, ingredient3, 
				ingredient3, ingredient3, ingredient3);

	/**
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		junit.textui.TestRunner.run(PancakeRecipeTests.class);
	}

	/**
	 * 
	 * @param name
	 */
	public PancakeRecipeTests(final String name) {
		super(name);
	}
	
	/**
	 * 
	 */
	public void testIsEmpty() {
		assertFalse(fullRecipe.isEmpty());
		assertTrue(emptyRecipe.isEmpty());
		assertFalse(mixedRecipe.isEmpty());
		assertFalse(fullRecipe.isEmpty());
		assertTrue(emptyRecipe.isEmpty());
		assertFalse(mixedRecipe.isEmpty());
		
	}
	
	/**
	 * 
	 * @return
	 */
	public void testAsStringIngredients() {
		assertEquals(emptyRecipe.asStringIngredients(), "");
		assertFalse(fullRecipe.asStringIngredients().isEmpty());
		assertFalse(fullRecipe.asStringIngredients().matches
				(mixedRecipe.asStringIngredients()));
		assertEquals(fullRecipe.asStringIngredients(), "42, 42, 42, 42, 42.");
		assertEquals(mixedRecipe.asStringIngredients(), "I am an ingredient, 42, qwert.");
	}
	
	public void testIsIngredientEmpty() {
		assertTrue(mixedRecipe.isIngredientEmpty(ingredient1));
		assertFalse(mixedRecipe.isIngredientEmpty(ingredient3));
		assertTrue(mixedRecipe.isIngredientEmpty(ingredient5));
		assertFalse(mixedRecipe.isIngredientEmpty(ingredient2));
		
	}
	
	public void testGetMethod() {
		assertEquals(fullRecipe.getIngredient(1), "42");

		assertEquals(fullRecipe.getIngredient(3), "42");

		assertEquals(fullRecipe.getIngredient(5), "42");

		assertEquals(mixedRecipe.getIngredient(3), "qwert");
	}
	
}