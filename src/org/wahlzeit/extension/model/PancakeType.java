package org.wahlzeit.extension.model;


/**
 * This class is a TypeObject and also part of the TypeObject collaboration.
 * @author qwert
 *
 */
public class PancakeType {

	protected String name;
	protected Ingredients ingredients;
	protected Recipe recipe;
	
	/**
	 * @methodtype constructor
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	public PancakeType(String name,Ingredients ingredients, Recipe recipe) {
		this.name = name;
		this.ingredients = ingredients;
		this.recipe = recipe;
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	public PancakeType() {
		name = "";
		ingredients = Ingredients.EMPTY_INGREDIENTS;
		recipe = Recipe.EMPTY_RECIPE;
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre name valid String
	 * @post
	 */
	public String getName() {
		if (name == null)
			throw new IllegalArgumentException();
		return name;
	}

	/**
	 * @methodtype set
	 * @methodproperty composed
	 * @pre
	 * @post class invariant not violated
	 */
	public void setName(String name) {
		//precondition
		if(name == null)
			throw new IllegalArgumentException();
		
		this.name = name;
		assertInvariants();
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre recipe != null
	 * @post
	 */
	public Recipe getRecipe() {
		if (name == null)
			throw new IllegalArgumentException();
		return recipe;
	}

	/**
	 * @methodtype set
	 * @methodproperty composed
	 * @pre
	 * @post class invariant not violated
	 */
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		assertInvariants();
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre ingredients =! null;
	 * @post
	 */
	public Ingredients getIng() {
		if(ingredients == null)
			throw new IllegalArgumentException();
		
		return ingredients;
	}

	/**
	 * @methodtype set
	 * @methodproperty composed
	 * @pre
	 * @post class invariant not violated
	 */
	public void setIng(Ingredients ingredients) {
		this.ingredients = ingredients;
		assertInvariants();
	}
	
	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * @invariant PancakeType.fields are not null
	 */
	protected void assertInvariants() throws IllegalStateException {
		boolean isValid = (name != null && ingredients != null && recipe != null);
		if (!isValid) {
			throw new IllegalStateException("class invariant violated");
		}
	}
}
