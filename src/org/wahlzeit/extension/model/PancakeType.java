package org.wahlzeit.extension.model;

public class PancakeType {

	protected String name = "";
	protected Recipe recipe = Recipe.getInstance("");
	
	public PancakeType(String name, Recipe recipe) {
		this.name = name;
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

}
