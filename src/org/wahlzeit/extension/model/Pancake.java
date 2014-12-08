package org.wahlzeit.extension.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

public class Pancake extends DataObject {
	
	/**
	 * 
	 */
	protected Integer id;
	
	/**
	 * 
	 */
	protected String name;
	
	/**
	 * 
	 */
	protected Recipe recipe = Recipe.getInstance("");
	
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
		this.readFrom(rset);
	}
	
	/**
	 * 
	 * @methodtype get method
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 
	 * @methodtype get method
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @methodtype set method
	 */
	public void setName(String name) {
		//precondition
		if(name == null)
			throw new IllegalArgumentException();
		
		this.name = name;
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype get method
	 */
	public Recipe getRecipe() {
		return recipe;
	}
	
	/**
	 * 
	 * @methodtype set method
	 */
	public void setRecipe(Recipe recipe) {
		//precondition
		if(recipe == null)
			throw new IllegalArgumentException();
	
		this.recipe = recipe;
		incWriteCount();
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
		return "ID: " + this.id + ", Recipe: " + this.recipe.asString();
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
		this.id = rset.getInt("id");
		this.name = rset.getString("name");
		this.recipe = Recipe.getInstance(rset.getString("recipe"));
	}
	
	/**
	 * @methodtype command method
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", this.id);
		rset.updateString("name", this.name);
		rset.updateString("recipe", this.recipe.asString());
	}
	
	/**
	 * 
	 */
	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, this.id);
	}

}
