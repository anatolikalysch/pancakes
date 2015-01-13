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
	 * @pre ingredients is a valid String
	 * @post this.ingredients == ingredients
	 */
	private Ingredients(String[] ingredients){
		// precondition
		if(ingredients == null)
			throw new IllegalArgumentException();
		
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
	}
	
	/**
	 * @methodtype get
	 * @methodproperty composed
	 * @pre map must exist
	 * @post
	 */
	public static Ingredients getInstance(String pancakeIngredients) {
		if (map.containsKey(pancakeIngredients)) 
			return map.get(pancakeIngredients);
		else {
			String[] temp = asStringArray(pancakeIngredients);
			Ingredients result = new Ingredients(temp);
			map.put(pancakeIngredients, result);
			return result;
		}
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty primitive
	 * @pre ingredients is valid String
	 * @post ingredients =! null
	 */
	protected static String[] asStringArray(String ingredients){
		//pre
		if (!StringUtil.isNullOrEmptyString(ingredients))
			return new String[] {"n/a"};
		
		String[] result = ingredients.split(",");
		for (int i = 0; i < result.length-1; i++)
			result[i] = result[i].trim();
		/*char separator = ',';
		
		int n = 0; //Counter for Ingredients
		int j = 0;
		for (int i = 0; i < ingredients.length(); i = j) {
			for (; ((i < ingredients.length()) && (ingredients.charAt(i) == separator));) {
				i++;
			}

			for (j = i; ((j < ingredients.length()) && (ingredients.charAt(j) != separator));) {
				j++;
			}

			if (i != j) {
				String ing = ingredients.substring(i, j).trim();
				if (!StringUtil.isNullOrEmptyString(ing)) {
					n = n + 1; // one more ingredient found
					if (n > 1) {
						String[] temp = new String[n];
						for (int a = 0; a < (n-2); a++)
							temp[a] = result[a];
						temp[n-1] = ing;
						result = temp;
					} else 
						result = new String[] {ing};
				}
			}
		}*/
		
		assert(result != null);
		return result;
	}
	
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre  ingredient is valid String
	 * @post ingredients != null
	 */
	public Ingredients addIngredient(String ingredient) {
		if (!StringUtil.isNullOrEmptyString(ingredient))
			throw new AssertionError("Trying to add empty ingredient!");
		
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
			return "n/a";
		else
			return result.substring(0, result.length()-2);
	}
	
	
	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * @invariant ingredients != null
	 */
	protected void assertInvariants() throws IllegalStateException {
		boolean isValid = (this.ingredients != null);
		if (!isValid) {
			throw new IllegalStateException("class invariant violated");
		}
	}
	
}
