package org.wahlzeit.extension.model;

import java.util.HashMap;

import org.wahlzeit.utils.StringUtil;


/**
 * This is a value Object. The only way to set the components is via constructor.
 * Further methods to change its state should not exist, e.g. any mutation methods.
 * 
 * This class is part of the TypeObject collaboration.
 * @author qwert
 *
 */

public class Ingredients {
	
	public static final Ingredients EMPTY_INGREDIENTS = new Ingredients();
	/**
	 * 
	 */
	private final String[] ingredients;
	/**
	 * 
	 */
	private static final HashMap<String,Ingredients> map = new HashMap<String,Ingredients>();
	
	
	/**
	 * @methodtype constructor
	 * @methodproperty
	 * @pre ingredients is a valid String and more than 2 ing
	 * @post this.ingredients == ingredients
	 */
	private Ingredients(String[] ingredients){
		// precondition
		if(ingredients == null || (ingredients.length > 1 && ingredients.length < 3))
			throw new IllegalArgumentException("ingredients");
		
		this.ingredients = ingredients;
		
		//postcondition
		assert(this.ingredients == ingredients);
		//invariant
		assertInvariants();
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty convenience
	 * @pre
	 * @post
	 */
	private Ingredients() {
		ingredients = new String[] {"n/a"};
		assertInvariants();
	}
	
	/**
	 * @methodtype get
	 * @methodproperty composed
	 * @pre map must exist
	 * @post
	 */
	public static Ingredients getInstance(String pancakeIngredients) {
		if (StringUtil.isNullOrEmptyString(pancakeIngredients)){
			if (!map.containsKey("n/a"))
				map.put("n/a", EMPTY_INGREDIENTS);
			return EMPTY_INGREDIENTS;
		}
			
		else {
			if (map.containsKey(pancakeIngredients)) 
				return map.get(pancakeIngredients);
			else {
				String[] temp = toStringArray(pancakeIngredients);
				Ingredients result = new Ingredients(temp);
				map.put(pancakeIngredients, result);
				return result;
			}
		}
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty primitive
	 * @pre ingredients is valid String
	 * @post ingredients =! null
	 */
	protected static String[] toStringArray(String ingredients){
		//pre
		if (StringUtil.isNullOrEmptyString(ingredients))
			return new String[] {"n/a"};
		
		String[] result = ingredients.split(",");
		for (int i = 0; i < result.length-1; i++)
			result[i] = result[i].trim();
		
		assert(result != null);
		//post
		if (result == null || (result.length > 1 && result.length < 3))
			throw new AssertionError("ingredients");
		return result;
	}
	
	/**
	 * @methodtype command
	 * @methodproperty
	 * @pre  ingredient is valid String
	 * @post ingredients != null
	 */
	public Ingredients addIngredient(String ingredient) {
		if (StringUtil.isNullOrEmptyString(ingredient))
			throw new IllegalArgumentException("ingredients");
		
		int temp = ingredients.length;
		String[] result = new String[temp + 1];
		for (int i=0; i < (temp-1); i++) {
			result[i] = ingredients[i];
		}
		result[temp] = ingredient;
		assertInvariants();
		return new Ingredients(result);
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty composed
	 * @pre
	 * @post
	 */
	public String toString() {
		return asString();
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty primitive
	 * @pre ingredients != null
	 * @post Stringlength > 3
	 */
	public String asString() {
		
		//pre
		assertInvariants();
		String result = "";
		for (int i=0; i < ingredients.length; i++) {
			result = result.concat(ingredients[i]+", ");
		}
		
		//post
		if (result.length() < 3)
			throw new AssertionError("ingredients");
		else
			return result.substring(0, result.length()-2);
	}
	
	public String[] asStringArray() {
		return ingredients;
	}
	
	
	
	
	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * @invariant ingredients != null
	 */
	protected void assertInvariants() throws IllegalStateException {
		boolean isValid = (this.ingredients != null);
		if (!isValid) {
			throw new IllegalStateException("ingredients");
		}
	}
	
}
