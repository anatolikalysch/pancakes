package org.wahlzeit.extension.model;

/**
 * This class is a TypeObject and also part of the TypeObject collaboration.
 * @author qwert
 *
 */
public class PancakeType {

	protected String name = "";
	protected Ingredients ingredients = Ingredients.EMPTY_INGREDIENTS;
	protected Recipe recipe = Recipe.EMPTY_RECIPE;
	
	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public PancakeType(String name,Ingredients ingredients, Recipe recipe) {
		this.name = name;
		this.ingredients = ingredients;
		this.recipe = recipe;
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public String getName() {
		return name;
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void setName(String name) {
		//precondition
		if(name == null)
			throw new IllegalArgumentException();
		
		this.name = name;
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Recipe getRecipe() {
		return recipe;
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void setRecipe(Recipe recipe) {
		//precondition
		if(recipe == null)
			throw new IllegalArgumentException();
		
		this.recipe = recipe;
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Ingredients getIng() {
		return ingredients;
	}

	/**
	 * @methodtype 
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void setIng(Ingredients ingredients) {
		if(ingredients == null)
			throw new IllegalArgumentException();
		
		this.ingredients = ingredients;
	}
}
