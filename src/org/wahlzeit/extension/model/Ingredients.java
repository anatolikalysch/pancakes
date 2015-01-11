package org.wahlzeit.extension.model;

import java.util.HashMap;


/**
 * This is a value Object. The only way to set the components is via constructor.
 * Further methods to change its state should not exist, e.g. any mutation methods.
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
	* @pre recipe != null
	* @post this.recipe == recipe
	* @param ingredients
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
	 * @ convenience
	 */
	private Ingredients() {
		ingredients = new String[] {"n/a"};		
	}
	
	/**
	* @methodtype get method
	* @param pancakeIngredients
	* @return Ingredients instance
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
	
	protected static String[] asStringArray(String ingredients){
		String[] result = ingredients.split(",");
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
		
		//post
		if (result == null)
			result = new String[] {"n/a"};
		return result;
	}
	
	public Ingredients addIngredient(String ingredient) {
		int temp = ingredients.length;
		String[] result = new String[temp + 1];
		for (int i=0; i < (temp-1); i++) {
			result[i] = ingredients[i];
		}
		result[temp] = ingredient;
		
		return new Ingredients(result);
	}
	
	/**
	* @methodtype get method
	* @return recipe
	*/
	public String[] getIngredients() {
		return ingredients;
	}
	
	/**
	* @methodtype conversion method
	* @return
	*/
	public String toString() {
		return asString();
	}
	
	/**
	* @methodtype conversion method
	* @return
	*/
	public String asString() {
		String result = "";
		for (int i=0; i < ingredients.length; i++) {
			result = result.concat(ingredients[i]+", ");
		}
		
		return result.substring(0, result.length()-2);
	}
	
	/**
	* @methodtype assertion method
	* @throws IllegalStateException
	*/
	protected void assertInvariants() throws IllegalStateException {
		boolean isValid = (this.ingredients != null);
		if (!isValid) {
			throw new IllegalStateException("class invariant violated");
		}
	}
	
}
