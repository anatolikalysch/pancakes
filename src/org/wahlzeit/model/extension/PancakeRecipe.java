package org.wahlzeit.model.extension;

public class PancakeRecipe {
	private boolean flour;
	private boolean eggs;
	private boolean milk;
	private boolean sojmilk;
	private boolean salt;
	/**
	* @methodtype constructor
	*/
	public PancakeRecipe() {
		
	}
	/**
	* @pre milk can not be combined with sojmilk
	* @post correct values will be set
	* @methodtype constructor
	*/
	public PancakeRecipe(boolean flour, boolean eggs, boolean milk, boolean sojmilk, boolean salt) {
		setFlour(flour);
		setEggs(eggs);
		setMilk(milk);
		setSojmilk(sojmilk);
		setSalt(salt);
	}
	
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isFlour() {
		return flour;
	}
	/**
	* @post correct values will be set
	* @methodtype set method
	*/
	public void setFlour(boolean flour) {
		this.flour = flour;
		assert(this.flour == flour);
	}
	/**
	*
	* @methodtype boolean query method
	*/ 
	public boolean isEggs() {
		return eggs;
	}
	/**
	* @post correct values will be set
	* @methodtype set method
	*/
	public void setEggs(boolean eggs) {
		this.eggs = eggs;
		assert(this.eggs == eggs);
	}
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isMilk() {
		return milk;
	}
	/**
	* @pre milk can not be combined with sojmilk
	* @post correct values will be set
	* @methodtype set method
	*/
	public void setMilk(boolean milk) {
		if(sojmilk)
			throw new RuntimeException("invariant violated");
		this.milk = milk;
		assert(this.milk == milk);
	}
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isSojmilk() {
		return sojmilk;
	}
	/**
	* @pre sojmilk can not be combined with milk
	* @post correct values will be set
	* @methodtype set method
	*/
	public void setSojmilk(boolean sojmilk) {
		if(milk) throw new RuntimeException("invariant violated");
		this.sojmilk = sojmilk;
		assert(this.sojmilk == sojmilk);
	}
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isSalt() {
		return salt;
	}
	/**
	* @post correct values will be set
	* @methodtype set method
	*/
	public void setSalt(boolean salt) {
		this.salt = salt;
		assert(this.salt == salt);
	}
}
