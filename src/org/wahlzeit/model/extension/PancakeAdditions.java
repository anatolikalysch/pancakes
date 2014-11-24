package org.wahlzeit.model.extension;

public class PancakeAdditions {

	private boolean feast;
	private boolean sirup;
	private boolean fruits;
	private boolean butter;
	private boolean hazelnut;
	/**
	 * 
	 * @methodtype constructor
	 */
	public PancakeAdditions() {
		
	}
	
	/**
	*
	* @pre feast only true with all enabled
	* @post no null or wrong values will be set
	* @methodtype constructor
	*/
	public PancakeAdditions(boolean feast, boolean sirup, boolean fruits, boolean butter, boolean hazelnut) {
		setSirup(sirup);
		setFruits(fruits);
		setButter(butter);
		setHazelnut(hazelnut);
		setFeast(feast);
	}
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isFeast() {
		return feast;
	}
	/**
	*
	* @pre feast only true with PancakeNumber many or load
	* @post no null value will be set
	* @methodtype set method
	*/
	public void setFeast(boolean feast) throws RuntimeException {
		if(feast && !fruits && !butter && !hazelnut && !sirup)
			throw new RuntimeException("invariant violated");
		this.feast = feast;
		assert(feast == this.feast);
	}
	
	/**
	*
	* @methodtype get method
	*/
	public boolean isSirup() {
		return sirup;
	}
	/**
	* @post no null value will be set
	* @methodtype set method
	*/
	public void setSirup(boolean sirup) {
		this.sirup = sirup;
		assert(sirup == this.sirup);
	}
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isFruits() {
		return fruits;
	}
	/**
	*@post no null value will be set
	* @methodtype set method
	*/
	public void setFruits(boolean fruits) {
		this.fruits = fruits;
		assert(fruits == this.fruits);
	}
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isButter() {
		return butter;
	}
	/**
	*@post no null value will be set
	* @methodtype set method
	*/
	public void setButter(boolean butter) {
		this.butter = butter;
		assert(butter == this.butter);
	}
	
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isHazelnut()	{
		return hazelnut;
	}
	/**
	*@post no null value will be set
	* @methodtype set method
	*/
	public void setHazelnut(boolean hazelnut) {
		this.hazelnut = hazelnut;
		assert(hazelnut == this.hazelnut);
	}
	
}
