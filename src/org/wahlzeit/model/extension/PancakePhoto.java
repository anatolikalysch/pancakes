package org.wahlzeit.model.extension;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

public class PancakePhoto extends Photo {
	public static final String ADDITIONS = "none";
	public static final String FEAST = "feast";
	public static final String SIRUP = "sirup";
	public static final String FRUITS = "fruits";
	public static final String BUTTER = "butter";
	public static final String HAZELNUT = "hazelnut";
	
	public static final String RECIPE = "recipe";
	public static final String FLOUR = "flour";
	public static final String EGGS = "eggs";
	public static final String MILK = "milk";
	public static final String SOJMILK = "sojmilk";
	public static final String SALT = "salt";
	
	public PancakeAdditions additions = new PancakeAdditions();
	public PancakeRecipe recipe = new PancakeRecipe();
	
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
		boolean feast = rset.getBoolean(FEAST);
		boolean sirup = rset.getBoolean(SIRUP);
		boolean fruits = rset.getBoolean(FRUITS);
		boolean butter = rset.getBoolean(BUTTER);
		boolean hazelnut = rset.getBoolean(HAZELNUT);
		this.additions = new PancakeAdditions(feast, sirup, fruits, butter, hazelnut);
		
		boolean flour = rset.getBoolean(FLOUR);
		boolean eggs = rset.getBoolean(EGGS);
		boolean milk = rset.getBoolean(MILK);
		boolean sojmilk = rset.getBoolean(SOJMILK);
		boolean salt = rset.getBoolean(SALT);
		this.recipe = new PancakeRecipe(flour, eggs, milk, sojmilk, salt);
	}
	/**
	*
	* @methodtype command method
	*/
	@Override
	public void writeOn(ResultSet rset) throws SQLException
	{
		super.writeOn(rset);
		rset.updateBoolean(FEAST, additions.isFeast());
		rset.updateBoolean(SIRUP, additions.isSirup());
		rset.updateBoolean(FRUITS, additions.isFruits());
		rset.updateBoolean(BUTTER, additions.isButter());
		rset.updateBoolean(HAZELNUT, additions.isHazelnut());
		rset.updateBoolean(FLOUR, recipe.isFlour());
		rset.updateBoolean(EGGS, recipe.isEggs());
		rset.updateBoolean(MILK, recipe.isMilk());
		rset.updateBoolean(SOJMILK, recipe.isSojmilk());
		rset.updateBoolean(SALT, recipe.isSalt());
	}
	/**
	*
	* @methodtype get method
	*/
	public PancakeAdditions getAdditions() {
		return additions;
	}
	/**
	*
	* @methodtype set method
	*/
	public void setAdditions(PancakeAdditions technique) {
		this.additions = technique;
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
	public void setRecipe(PancakeRecipe recipe) {
		this.recipe = recipe;
	}
	/**
	 * @pre booleans should not be null
	 * @post temp should not be null
	 * @methodtype get method
	 */
	public String getAsStringAdditions(){
		String temp = "";
		if(additions.isFeast())
			temp += "It's a feast: ";
		if(additions.isSirup())
			temp += "sirup, ";
		if(additions.isButter())
			temp += "butter, ";
		if(additions.isFruits())
			temp += "fruits, ";
		if(additions.isHazelnut())
			temp += "hazelnuts, ";
		temp = temp.substring(0, temp.length()); // deletes last comma
		temp += "!";
		assert(temp != null);
		return temp;
	}
	/**
	 * 
	 * @pre booleans should not be null
	 * @post temp should not be null
	 * @methodtype get method
	 */
	public String getAsStringRecipe(){
		String temp ="";
		temp += "Pancakes require ";
		if(recipe.isFlour())
			temp += "flour, ";
		if(recipe.isEggs())
			temp += "eggs, ";
		if(recipe.isMilk())
			temp += "milk, ";
		if(recipe.isSojmilk())
			temp += "sojmilk, ";
		if(recipe.isSalt())
			temp += "salt, ";
		temp = temp.substring(0, temp.length()); // deletes last comma
		assert(temp != null);
		temp += "!";
		return temp;
	}
}
