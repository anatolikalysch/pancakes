package org.wahlzeit.extension.model;

public class PancakeType {

	protected String name = "";
	protected Ingredients ingredients = Ingredients.EMPTY_INGREDIENTS;
	protected Recipe recipe = Recipe.EMPTY_RECIPE;
	
	public PancakeType(String name,Ingredients ingredients, Recipe recipe) {
		this.name = name;
		this.ingredients = ingredients;
		this.recipe = recipe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		//precondition
		if(name == null)
			throw new IllegalArgumentException();
		
		this.name = name;
	}

	public Recipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(Recipe recipe) {
		//precondition
		if(recipe == null)
			throw new IllegalArgumentException();
		
		this.recipe = recipe;
	}
	
	public Ingredients getIng() {
		return ingredients;
	}
	
	public void setIng(Ingredients ingredients) {
		if(ingredients == null)
			throw new IllegalArgumentException();
		
		this.ingredients = ingredients;
	}

}
