package org.wahlzeit.model.extension;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

/**
 * This Class extends the Photo.java class and is supposed to be part of the "design
 * by contract" architecture.
 * @author qwert
 *
 */
public class PancakePhoto extends Photo {
	public static final String RECIPE = "recipe";
	public static final String INGREDIENT1 = "ingredient1";
	public static final String INGREDIENT2 = "ingredient2";
	public static final String INGREDIENT3 = "ingredient3";
	public static final String INGREDIENT4 = "ingredient4";
	public static final String INGREDIENT5 = "ingredient5";
	
	protected String ingredient1;
	protected String ingredient2;
	protected String ingredient3;
	protected String ingredient4;
	protected String ingredient5;
	
	protected PancakeRecipe recipe = new PancakeRecipe();
	
	/**
	*
	* @methodtype constructor
	*/
	public PancakePhoto() {
		super();
	}
	/**
	*
	* @methodtype constructor
	*/
	public PancakePhoto(PhotoId myId) {
		super(myId);
	}
	/**
	*
	* @methodtype constructor
	*/
	public PancakePhoto(ResultSet rset) throws SQLException {
		readFrom(rset);
	}
	
	/**
	*
	* @methodtype initialization method
	*/
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		super.readFrom(rset);
		ingredient1 = rset.getString("ingredient1");
		ingredient2 = rset.getString("ingredient2");
		ingredient3 = rset.getString("ingredient3");
		ingredient4 = rset.getString("ingredient4");
		ingredient5 = rset.getString("ingredient5");
		recipe = new PancakeRecipe(ingredient1, ingredient2, ingredient3,
				ingredient4, ingredient5);
	}
	/**
	*
	* @methodtype command method
	*/
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		super.writeOn(rset);
		rset.updateString("ingredient1", recipe.getIngredien1());
		rset.updateString("ingredient2", recipe.getIngredien2());
		rset.updateString("ingredient3", recipe.getIngredien3());
		rset.updateString("ingredient4", recipe.getIngredien4());
		rset.updateString("ingredient5", recipe.getIngredien5());
	}
	/**
	*
	* @methodtype get method
	*/
	public PancakeRecipe getRecipe() {
		return recipe;
	}
	/**
	*
	* @methodtype set method
	*/
	public void setRecipe(String ingredient1, String ingredient2,
			String ingredient3, String ingredient4, String ingredient5) {
		this.recipe = new PancakeRecipe(ingredient1, ingredient2,
			ingredient3, ingredient4, ingredient5);
	}
	
	/**
	 * 
	 * @pre Recipe should not be null or empty
	 * @post Recipe not changed, temp not empty
	 * @methodtype get method
	 */
	public String getAsStringRecipe(){
		if(!recipe.isEmpty()) {
			String temp = recipe.asStringIngredients();
			assert(temp != null);
			return temp;
		} else 
			return "";
		
		
	}
}
