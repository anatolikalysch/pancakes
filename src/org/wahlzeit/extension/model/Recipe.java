package org.wahlzeit.extension.model;

import java.util.HashMap;

/**
 * This is a value Object. The only way to set the components is via constructor.
 * Further methods to change its state should not exist, e.g. any mutation methods.
 * @author qwert
 *
 */
public class Recipe {
	
	private final String recipe;
	private static final HashMap<String,Recipe> map = new HashMap<String,Recipe>();
	
	/**
	* @methodtype get method
	* @return recipe
	*/
	public String getRecipe() {
		return recipe;
	}
	
	/**
	* @methodtype assertion method
	* @throws IllegalStateException
	*/
	protected void assertInvariants() throws IllegalStateException {
		boolean isValid = (this.recipe != null);
		if (!isValid) {
			throw new IllegalStateException("class invariant violated");
		}
	}
	
	/**
	* @methodtype constructor
	* @pre recipe != null
	* @post this.recipe == recipe
	* @param recipe
	*/
	private Recipe(String recipe){
		// precondition
		if(recipe == null)
			throw new IllegalArgumentException();
		
		this.recipe = recipe;
		
		//postcondition
		assert(this.recipe == recipe);
		//invariant
		assertInvariants();
	}
	
	/**
	* @methodtype get method
	* @param recipe
	* @return Recipe instance
	*/
	public static Recipe getInstance(String recipe) {
		if (map.containsKey(recipe)) 
			return map.get(recipe);
		else {
			Recipe result = new Recipe(recipe);
			map.put(recipe, result);
			return result;
		}
	}
	
	/**
	* @methodtype conversion method
	* @return
	*/
	public String asString() {
		return recipe;
	}
	
	/**
	* @methodtype conversion method
	* @return
	*/
	public String toString() {
		return asString();
	}
	
}
