package org.wahlzeit.model.extension;

/**
 * This is a value Object. The only way to set the components is via constructor.
 * Further methods to change its state should not exist.
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
	public PancakeRecipe() {
		this.ingredient1 = "";
		this.ingredient2 = "";
		this.ingredient3 = "";
		this.ingredient4 = "";
		this.ingredient5 = "";
	}
	
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
	 * 
	 * @return
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
	 * @post if the objects are equal, their hashCode should be too
	 */
	public boolean equals(Object obj) {
		if (this.equals(obj)) {
			assert(this.hashCode() == obj.hashCode());
			return this.equals(obj);	
		} else 
			return false;
			
	}
	
	/**
	 * Values don’t have identity, so it is important to properly implement anything
	 * that has to do with equality. In Java this means the Methods equals and hashCode.
	 */
	public int hashCode() {
		return this.hashCode();
	}
	
	/**
	 * @pre ingredients should not be empty
	 * @post temp should not be empty
	 * @return
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
				temp = temp.substring(0, temp.length());		//removes the ","
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
	 * @param ingredient
	 * @return
	 */
	public boolean isIngredientEmpty(String ingredient){
		return this.doEvaluateIsIngredientEmpty(ingredient);
	}
	
	/**
	 * @methodtype assertion method
	 */
	protected boolean doEvaluateIsIngredientEmpty(String ingredient){
		if (ingredient == null || ingredient.isEmpty())
			return true;
		else 
			return false;
	}
	
	/**
	 * @methodtype get method
	 * @return
	 */
	public String getIngredient1() {
		return ingredient1;
	}

	/**
	 * @methodtype get method
	 * @return
	 */
	public String getIngredient2() {
		return ingredient2;
	}

	/**
	 * @methodtype get method
	 * @return
	 */
	public String getIngredient3() {
		return ingredient3;
	}

	/**
	 * @methodtype get method
	 * @return
	 */
	public String getIngredient4() {
		return ingredient4;
	}

	/**
	 * @methodtype get method
	 * @return
	 */
	public String getIngredient5() {
		return ingredient5;
	}
}
