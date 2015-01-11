package org.wahlzeit.extension.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

public class Pancake extends DataObject {
	
	/**
	 * meta-inf
	 */
	protected Integer id;
	
	/**
	 * typeObject
	 */
	protected PancakeType type = new PancakeType("", Ingredients.EMPTY_INGREDIENTS, Recipe.getInstance(""));
	/**
	 * 
	 * @methodtype constructor
	 */
	public Pancake(Integer id) {
		this.id = id;
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public Pancake(ResultSet rset) throws SQLException{
		readFrom(rset);
	}
	
	/**
	 * 
	 * @methodtype get method
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @methodtype get method
	 */
	public PancakeType getType() {
		return type;
	}
	
	/**
	 * @methodtype set method
	 */
	public void setType(PancakeType type) {
		if(type == null)
			throw new IllegalArgumentException();
		this.type = type;
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype set method
	 */
	public void setName(String name) {
		//precondition
		if(name == null)
			throw new IllegalArgumentException();
		
		this.type.setName(name);
		incWriteCount();
	}
	
	public Ingredients getIng(){
		return this.type.getIng();
	}
	
	public void setIng(Ingredients ingredients){
		this.type.setIng(ingredients);
	}
	
	public Recipe getRecipe(){
		return this.type.getRecipe();
	}
	
	public void setRecipe(Recipe recipe){
		this.type.setRecipe(recipe);
	}
	
	/**
	 * @methodtype conversion method
	 */
	@Override
	public String toString() {
		return asString();
	}
	
	/**
	 * 
	 * @methodtype conversion method
	 */
	public String asString() {
		return "ID: " + id + ", Ingredients:" + getType().getIng().toString() +  ", Recipe: " + getType().getRecipe().asString();
	}
	
	/**
	 * 
	 */
	@Override
	public String getIdAsString() {
		return String.valueOf(this.id);
	}
	
	/**
	 * @methotype command method
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		id = rset.getInt("id");
		type.setName(rset.getString("name"));
		type.setIng(Ingredients.getInstance(rset.getString("ingredients")));
		type.setRecipe(Recipe.getInstance(rset.getString("recipe")));
	}
	
	/**
	 * @methodtype command method
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", this.id);
		rset.updateString("name", type.getName());
		rset.updateString("ingredients", type.getIng().toString());
		rset.updateString("recipe", type.getRecipe().toString());
	}
	
	/**
	 * 
	 */
	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, this.id);
	}

}
