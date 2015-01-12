package org.wahlzeit.extension.model;

import java.util.HashMap;

/**
 * This is a value Object. The only way to set the components is via constructor.
 * Further methods to change its state should not exist, e.g. any mutation methods.
 * 
 * This class is part of the TypeObject collaboration.
 * @author qwert
 *
 */
public class Recipe {
	
	public static final Recipe EMPTY_RECIPE = new Recipe();
	/**
	 * 
	 */
	private final String recipe;
	/**
	 * 
	 */
	private static final HashMap<String,Recipe> map = new HashMap<String,Recipe>();
	
	/**
	* @methodtype constructor
	* @pre recipe != null
	* @post this.recipe == recipe
	* @param recipe
	*/
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
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
	 * @ convenience
	 */
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	private Recipe(){
		recipe = "";
	}
	
	/**
	* @methodtype get method
	* @param recipe
	* @return Recipe instance
	*/
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
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
	* @methodtype get method
	* @return recipe
	*/
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public String getRecipe() {
		return recipe;
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
		return recipe;
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
		boolean isValid = (this.recipe != null);
		if (!isValid) {
			throw new IllegalStateException("class invariant violated");
		}
	}
	
}
