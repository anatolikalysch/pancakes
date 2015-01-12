package org.wahlzeit.extension.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

/**
 * This class is part of the PancakePhoto/Pancake, PancakeManager, Serializer and Type Object collaborations.
 * @author qwert
 *
 */
public class Pancake extends DataObject {
	
	/**
	 * 
	 */
	protected Integer id;
	
	/**
	 * 
	 */
	protected PancakeType type = new PancakeType("", Ingredients.EMPTY_INGREDIENTS, Recipe.EMPTY_RECIPE);
	
	/**
	 * @methodtype constructor
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Pancake(Integer id) {
		this.id = id;
		incWriteCount();
	}
	
	/**
	 * @methodtype constructor
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Pancake(ResultSet rset) throws SQLException{
		readFrom(rset);
	}
	
	
	/**
	 * @methodtype get
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * ---------- Type Object Collaboration ----------
	 */
	
	/**
	 * @methodtype get
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public PancakeType getType() {
		return type;
	}
	
	
	/**
	 * @methodtype set
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void setType(PancakeType type) {
		if(type == null)
			throw new IllegalArgumentException();
		this.type = type;
		incWriteCount();
	}
	
	
	/**
	 * @methodtype set
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void setName(String name) {
		//precondition
		if(name == null)
			throw new IllegalArgumentException();
		
		this.type.setName(name);
		incWriteCount();
	}
	
	/**
	 * @methodtype get
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Ingredients getIng(){
		return this.type.getIng();
	}
	
	/**
	 * @methodtype set
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void setIng(Ingredients ingredients){
		this.type.setIng(ingredients);
	}
	
	/**
	 * @methodtype get
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public Recipe getRecipe(){
		return this.type.getRecipe();
	}
	
	/**
	 * @methodtype set
	 * @methodproperty
	 * @pre
	 * @post
	 */
	public void setRecipe(Recipe recipe){
		this.type.setRecipe(recipe);
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty composed
	 * @pre
	 * @post
	 */
	@Override
	public String toString() {
		return asString();
	}
	
	/**
	 * @methodtype conversion
	 * @methodproperty primitive
	 * @pre
	 * @post
	 */
	public String asString() {
		return "ID: " + id + ", Ingredients:" + getType().getIng().toString() +  ", Recipe: " + getType().getRecipe().asString();
	}
	
	/**
	 * @methodtype get
	 * @methodproperty
	 * @pre
	 * @post
	 */
	@Override
	public String getIdAsString() {
		return String.valueOf(this.id);
	}
	
	
	/**
	 * ---------- Serializer collaboration ---------- 
	 */
	
	/**
	 * @methodtype command
	 * @methodproperty hook
	 * @pre
	 * @post
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		id = rset.getInt("id");
		type.setName(rset.getString("name"));
		type.setIng(Ingredients.getInstance(rset.getString("ingredients")));
		type.setRecipe(Recipe.getInstance(rset.getString("recipe")));
	}
	
	/**
	 * @methodtype command
	 * @methodproperty hook
	 * @pre
	 * @post
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", this.id);
		rset.updateString("name", type.getName());
		rset.updateString("ingredients", type.getIng().toString());
		rset.updateString("recipe", type.getRecipe().toString());
	}
	
	/**
	 * @methodtype command
	 * @methodproperty hook
	 * @pre
	 * @post
	 */
	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, this.id);
	}

}
