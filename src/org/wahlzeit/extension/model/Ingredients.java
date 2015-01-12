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
	* @pre recipe != null
	* @post this.recipe == recipe
	* @param ingredients
	*/
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
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
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	private Ingredients() {
		ingredients = new String[] {"n/a"};		
	}
	
	/**
	* @methodtype get method
	* @param pancakeIngredients
	* @return Ingredients instance
	*/
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
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
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
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
		
		return result;
	}
	
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
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
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public String[] getIngredients() {
		return ingredients;
	}
	
	/**
	* @methodtype conversion method
	* @return
	*/
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public String toString() {
		return asString();
	}
	
	/**
	* @methodtype conversion method
	* @return
	*/
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
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
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	protected void assertInvariants() throws IllegalStateException {
		boolean isValid = (this.ingredients != null);
		if (!isValid) {
			throw new IllegalStateException("class invariant violated");
		}
	}
	
}
