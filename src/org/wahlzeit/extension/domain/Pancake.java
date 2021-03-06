package org.wahlzeit.extension.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

/**
 * This class is part of the PancakePhoto/Pancake, PancakeManager, Serializer and Type Object collaborations. See collaborating
 * methods for more information.
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
	protected PancakeType type = new PancakeType();
	
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
	 * @collaboration manager, PancakeManager
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre id != null
	 * @post
	 */
	public Integer getId() {
		assertInvariants();
		return id;
	}
	
	/**
	 * @collaboration manager, PancakeManager
	 * @methodtype command
	 * @methodproperty hook
	 * @pre
	 * @post
	 */
	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, this.id);
	}
	
	/**
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre type != null
	 * @post
	 */
	public PancakeType getType() {
		return type;
	}
	
	
	/**
	 * @collaboration typeObject, PancakeType
	 * @methodtype set
	 * @methodproperty primitive
	 * @pre type != null
	 * @post invariants
	 */
	public void setType(PancakeType type) {
		if(type == null)
			throw new IllegalArgumentException("pancake");
		this.type = type;
		incWriteCount();
	}
	
	/**
	 * @collaboration typeObject, PancakeType
	 * @methodtype set
	 * @methodproperty primitive
	 * @pre name != null
	 * @post
	 */
	public void setName(String name) {
		//precondition
		if(name == null)
			throw new IllegalArgumentException("pancake");
		
		type.setName(name);
		assertInvariants();
		incWriteCount();
	}
	
	/**
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre ing != null
	 * @post
	 */
	public Ingredients getIng(){
		assertInvariants();
		return type.getIng();
	}
	
	/**
	 * @collaboration typeObject, PancakeType
	 * @methodtype set
	 * @methodproperty primitive
	 * @pre ingredients =! null
	 * @post invariants
	 */
	public void setIng(Ingredients ingredients){
		if (ingredients == null)
			throw new IllegalArgumentException("pancake");
		this.type.setIng(ingredients);
		incWriteCount();
		assertInvariants();
	}
	
	/**
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre invariants
	 * @post
	 */
	public Recipe getRecipe(){
		assertInvariants();
		return this.type.getRecipe();
	}
	
	/**
	 * @collaboration typeObject, PancakeType
	 * @methodtype set
	 * @methodproperty primitive
	 * @pre recipe != null
	 * @post invariants
	 */
	public void setRecipe(Recipe recipe){
		if (recipe == null)
			throw new IllegalArgumentException("pancake");
		this.type.setRecipe(recipe);
		incWriteCount();
		assertInvariants();
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
	 * @pre invariants
	 * @post
	 */
	public String asString() {
		assertInvariants();
		return "ID: " + id + ", Ingredients:" + getType().getIng().toString() +  ", Recipe: " + getType().getRecipe().asString();
	}
	
	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * @invariant
	 */
	protected void assertInvariants() {
		boolean isValid = (id != null && type.name != null && type.ingredients != null && type.recipe != null);
		if (!isValid)
			throw new IllegalStateException("pancake");
	}
	
	/**
	 * @collaboration manager, PancakeManager
	 * @methodtype get
	 * @methodproperty primitive
	 * @pre id != null
	 * @post
	 */
	@Override
	public String getIdAsString() {
		assertInvariants();
		return String.valueOf(this.id);
	}
	
	/**
	 * @collaboration manager, PancakeManager
	 * @methodtype command
	 * @methodproperty hook
	 * @pre rset != null
	 * @post changed fields != null
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		if (rset == null)
			throw new IllegalArgumentException();
		id = rset.getInt("id");
		type.setName(rset.getString("name"));
		type.setIng(Ingredients.getInstance(rset.getString("ingredients")));
		type.setRecipe(Recipe.getInstance(rset.getString("recipe")));
		assertInvariants();
	}
	
	/**
	 * @collaboration manager, PancakeManager
	 * @methodtype command
	 * @methodproperty hook
	 * @pre fields != null
	 * @post
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		assertInvariants();
		rset.updateInt("id", this.id);
		rset.updateString("name", type.getName());
		rset.updateString("ingredients", type.getIng().toString());
		rset.updateString("recipe", type.getRecipe().toString());
	}
}
