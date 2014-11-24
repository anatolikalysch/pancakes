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
	 * 
	 * @methodtype get method
	 */
	public String getAsStringAdditions(){
		String temp = "";
		if(this.getAdditions().isFeast())
			temp += "It's a feast: ";
		if(this.getAdditions().isSirup())
			temp += "sirup, ";
		if(this.getAdditions().isButter())
			temp += "butter, ";
		if(this.getAdditions().isFruits())
			temp += "fruits, ";
		if(this.getAdditions().isHazelnut())
			temp += "hazelnuts, ";
		temp = temp.substring(0, temp.length()); // deletes last comma
		temp += "!";
		return temp;
	}
	/**
	 * 
	 * @methodtype get method
	 */
	public String getAsStringRecipe(){
		String temp ="";
		temp += "Those require ";
		if(this.getRecipe().isFlour())
			temp += "flour, ";
		if(this.getRecipe().isEggs())
			temp += "eggs, ";
		if(this.getRecipe().isMilk())
			temp += "milk, ";
		if(this.getRecipe().isSojmilk())
			temp += "sojmilk, ";
		if(this.getRecipe().isSalt())
			temp += "salt, ";
		temp = temp.substring(0, temp.length()); // deletes last comma
		temp += "!";
		return temp;
	}
}