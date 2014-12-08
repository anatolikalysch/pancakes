package org.wahlzeit.extension.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

public class Pancake extends DataObject {

	protected Integer id;
	protected String name;
	protected Recipe recipe = Recipe.getInstance("");
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		//precondition
		if(name == null)
			throw new IllegalArgumentException();
		
		this.name = name;
		incWriteCount();
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(Recipe recipe) {
		//precondition
		if(recipe == null)
			throw new IllegalArgumentException();
	
		this.recipe = recipe;
		incWriteCount();
	}
	
	public Pancake(Integer id) {
		this.id = id;
		incWriteCount();
	}
	
	public Pancake(ResultSet rset) throws SQLException{
		this.readFrom(rset);
	}
	
	public String asString() {
		return "ID: " + this.id + ", Recipe: " + this.recipe.asString();
	}
	
	@Override
	public String getIdAsString() {
		return String.valueOf(this.id);
	}
	
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		this.id = rset.getInt("id");
		this.name = rset.getString("name");
		this.recipe = Recipe.getInstance(rset.getString("recipe"));
	}
	
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", this.id);
		rset.updateString("name", this.name);
		rset.updateString("recipe", this.recipe.asString());
	}
	
	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, this.id);
	}

}
