package org.wahlzeit.model.extension;

/**
 * This is a value Object. The only way to set the components is via constructor.
 * Further methods to change its state should not exist, e.g. any mutation methods.
 * @author qwert
 *
 */
public class PancakeRecipe {
	private final String ingredient1;
	private final String ingredient2;
	private final String ingredient3;
	private final String ingredient4;
	private final String ingredient5;
	
	
	/**
	* @methodtype constructor
	*/
	public PancakeRecipe(String ingredient1, String ingredient2,
			String ingredient3, String ingredient4, String ingredient5) {
		this.ingredient1 = ingredient1;
		this.ingredient2 = ingredient2;
		this.ingredient3 = ingredient3;
		this.ingredient4 = ingredient4;
		this.ingredient5 = ingredient5;
	}
	
	/**
	 * @pre ingredients should not be empty
	 * @post recipe should not be changed
	 * @methodtype query method
	 * @return
	 */
	public boolean isEmpty(){
		return this.EvaluateIsEmpty();
	}
	
	/**
	 * @pre ingredients should not be empty
	 * @post recipe should not be changed
	 * @methodtype query method 
	 */
	protected boolean EvaluateIsEmpty(){
		if (!this.isIngredientEmpty(ingredient1) && !this.isIngredientEmpty(ingredient2) && !this.isIngredientEmpty(ingredient3) &&
				!this.isIngredientEmpty(ingredient4) && !this.isIngredientEmpty(ingredient5))
			return true;
		else 
			return false;
	}
	
	/**
	 * Values don’t have identity, so it is important to properly implement anything
	 *  that has to do with equality. In Java this means the Methods equals and hashCode.
	 * @pre recipe not null
	 * @post if the objects are equal, their hashCode should be too
	 * @methodtype comparison method
	 */
	public boolean equals(PancakeRecipe recipe) {
		assert(recipe != null);
		if (this.equals(recipe)) {
			assert(this.hashCode() == recipe.hashCode());
			return this.equals(recipe);	
		} else 
			return false;
	}
	
	/**
	 * Values don’t have identity, so it is important to properly implement anything
	 * that has to do with equality. In Java this means the Methods equals and hashCode.
	 * @methodtype conversion method
	 */
	public int hashCode() {
		return this.hashCode();
	}
	
	/**
	 * This makes it possible for System.out.print to print the Object
	 * @methodtype conversion method
	 */
	public String toString() {
		return this.asStringIngredients();
	}
	
	/**
	 * @pre ingredients should not be empty
	 * @post temp should not be empty
	 * @methodtype conversion method
	 */
	public String asStringIngredients() {
		//pre
		if (this.isEmpty())
			return "";
		else {
			String temp = "";
			if (!this.isIngredientEmpty(ingredient1))
				temp += ingredient1 + ", ";
			if (!this.isIngredientEmpty(ingredient2))
				temp += ingredient2 + ", ";
			if (!this.isIngredientEmpty(ingredient3))
				temp += ingredient3 + ", ";
			if (!this.isIngredientEmpty(ingredient4))
				temp += ingredient4 + ", ";
			if (temp.length() > 1)
				temp = temp.substring(0, temp.length()-1);		//removes the ","
			if (!this.isIngredientEmpty(ingredient5))
				temp += ingredient5;
			temp += ".";
			//post
			assert(temp.length() > 1);
			return temp;
		}
	}
	
	/**
	 * 
	 * @methodtype query method
	 * @property composed
	 */
	public boolean isIngredientEmpty(String ingredient){
		return this.doEvaluateIsIngredientEmpty(ingredient);
	}
	
	/**
	 * @methodtype query method
	 * @property primitive
	 */
	protected boolean doEvaluateIsIngredientEmpty(String ingredient){
		if (ingredient == null || ingredient == "")
			return true;
		else 
			return false;
	}
	
	/**
	 * @methodtype get method
	 */
	public String getIngredient(int i){
		switch (i) {
		case 1: 
			return ingredient1;
		case 2:
			return ingredient2;
		case 3:
			return ingredient3;
		case 4:
			return ingredient4;
		case 5:
			return ingredient5;
		default:
			return "";
		}
	}
}
