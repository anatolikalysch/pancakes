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
	 * @methodproperty
	 * @pre recipe is a valid String
	 * @post this.recipe == recipe
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
	 * @methodtype constructor
	 * @methodproperty convenience
	 * @pre
	 * @post recipe != null
	 */
	private Recipe(){
		recipe = "";
		assertInvariants();
	}
	
	
	/**
	 * @methodtype get
	 * @methodproperty 
	 * @pre map exists
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
	 * @methodtype conversion
	 * @methodproperty composed
	 * @pre
	 * @post
	 */
	public String toString() {
		return asString();
	}
	
	
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre recipe != null
	 * @post
	 */
	public String asString() {
		assertInvariants();
		return recipe;
	}
	

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * @invariant
	 */
	protected void assertInvariants() throws IllegalStateException {
		boolean isValid = (this.recipe != null);
		if (!isValid) {
			throw new IllegalStateException("class invariant violated");
		}
	}
	
}
