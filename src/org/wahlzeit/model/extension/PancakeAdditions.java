package org.wahlzeit.model.extension;

public class PancakeAdditions {

	private boolean feast;
	private boolean sirup;
	private boolean fruits;
	private boolean butter;
	private boolean hazelnut;
	
	public PancakeAdditions() {
		
	}
	
	/**
	*
	* @pre feast only true with PancakeNumber many or load
	* @post no null or wrong values will be set
	* @methodtype constructor
	*/
	public PancakeAdditions(boolean feast, boolean sirup, boolean fruits, boolean butter, boolean hazelnut) {
		setFeast(feast);
		setSirup(sirup);
		setFruits(fruits);
		setButter(butter);
		setHazelnut(hazelnut);
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
		//if(feast && (!number.equals(PancakeNumber.load) || !number.equals(PancakeNumber.many)))
			//throw new RuntimeException("invariant violated");
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
	*
	* @methodtype set method
	*/
	public void setSirup(boolean sirup) {
		this.sirup = sirup;
	}
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isFruits() {
		return fruits;
	}
	/**
	*
	* @methodtype set method
	*/
	public void setFruits(boolean fruits) {
		this.fruits = fruits;
	}
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isButter() {
		return butter;
	}
	/**
	*
	* @methodtype set method
	*/
	public void setButter(boolean butter) {
		this.butter = butter;
	}
	
	/**
	*
	* @methodtype boolean query method
	*/
	public boolean isHazelnut()	{
		return hazelnut;
	}
	/**
	*
	* @methodtype set method
	*/
	public void setHazelnut(boolean hazelnut) {
		this.hazelnut = hazelnut;
	}
	
}
