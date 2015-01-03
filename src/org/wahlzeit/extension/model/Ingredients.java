package org.wahlzeit.extension.model;

import java.util.HashMap;

import org.wahlzeit.model.Tags;


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
	private static final HashMap<String[],Ingredients> map = new HashMap<String[],Ingredients>();
	
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
		ingredients = new String[1];		
	}
	
	/**
	* @methodtype get method
	* @param ingredients
	* @return Ingredients instance
	*/
	public static Ingredients getInstance(String[] ingredients) {
		if (map.containsKey(ingredients)) 
			return map.get(ingredients);
		else {
			Ingredients result = new Ingredients(ingredients);
			map.put(ingredients, result);
			return result;
		}
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
		return result;
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
