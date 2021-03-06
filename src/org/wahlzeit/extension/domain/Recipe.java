package org.wahlzeit.extension.domain;

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
	 * @collaboration
	 * @methodtype constructor
	 * @methodproperty
	 * @pre recipe is a valid String
	 * @post this.recipe == recipe && recipe is a real text
	 */
	private Recipe(String recipe){
		// precondition
		if(recipe == null)
			throw new IllegalArgumentException("recipe");
		
		this.recipe = recipe;
		
		//postcondition
		assert(this.recipe == recipe);
		if (recipe.length() > 3 && recipe.length() < 10)
			throw new AssertionError("recipe");
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
	 * @methodproperty composed
	 * @pre map exists
	 * @post
	 */
	public static Recipe getInstance(String recipe) {
		if (StringUtil.isNullOrEmptyString(recipe)) {
			if (!map.containsKey(""))
				map.put("", EMPTY_RECIPE);
			return EMPTY_RECIPE;
		} else {
			if (map.containsKey(recipe)) 
				return map.get(recipe);
			else {
				Recipe result = new Recipe(recipe);
				map.put(recipe, result);
				return result;
			}
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
	 * @collaboration typeObject, pancake
	 * @methodtype conversion
	 * @methodproperty primitive
	 * @pre recipe != null && recipe is a real text.
	 * @post
	 */
	protected String asString() {
		if (recipe.length() > 3 && recipe.length() < 10)
			throw new AssertionError("recipe");
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
			throw new IllegalStateException("recipe");
		}
	}
	
}
