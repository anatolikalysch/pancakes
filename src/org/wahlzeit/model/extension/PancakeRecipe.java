package org.wahlzeit.model.extension;

/**
 * This is a value Object. The only way to set the components is via constructor.
 * Further methods to change its state should not exist.
 * @author qwert
 *
 */
public class PancakeRecipe {
	private String ingredient1;
	private String ingredient2;
	private String ingredient3;
	private String ingredient4;
	private String ingredient5;
	/**
	* @methodtype constructor
	*/
	public PancakeRecipe() {}
	
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
		return this.doEvaluateIsEmpty();
	}
	
	/**
	 * 
	 * @return
	 */
	protected boolean doEvaluateIsEmpty(){
		if (ingredient1.length() + ingredient2.length() + ingredient3.length() + 
				ingredient4.length() + ingredient5.length() != 0)
			return true;
		else 
			return false;
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
			if (!this.doEvaluateIsIngredientEmpty(ingredient1))
				temp += ingredient1 + ", ";
			if (!this.doEvaluateIsIngredientEmpty(ingredient2))
				temp += ingredient2 + ", ";
			if (!this.doEvaluateIsIngredientEmpty(ingredient3))
				temp += ingredient3 + ", ";
			if (!this.doEvaluateIsIngredientEmpty(ingredient4))
				temp += ingredient4 + ", ";
			if (temp.length() > 1)
				temp = temp.substring(0, temp.length());		//removes the ","
			if (!this.doEvaluateIsIngredientEmpty(ingredient5))
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
