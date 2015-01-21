package org.wahlzeit.extension.domain;

import junit.framework.TestCase;

public class IngredientsTest extends TestCase {
	
	Ingredients ing;

	public IngredientsTest() {
		super();
	}
	
	public static void main(final String[] args) {
		junit.textui.TestRunner.run(IngredientsTest.class);
	}
	
	protected void testToStringArray(String ingredients){
		ing = Ingredients.getInstance("qwe, qwer, qwert, qwertz, qwertzu");
		String[] temp = new String[] {"qwe", "qwer", "qwert", "qwertz", "qwertzu"};
		assertTrue(ing.toStringArray("qwe, qwer, qwert, qwertz, qwertzu") == temp);
		assertTrue(ing.toStringArray(null) == new String[] {"n/a"});
		
	}
		
	public void testAsString() {
		ing = Ingredients.getInstance("qwe, qwer, qwert, qwertz, qwertzu");
		assertTrue(ing.asString() == "qwe, qwer, qwert, qwertz, qwertzu");
		
	}
	
	public void testAsStringArray() {
		ing = Ingredients.getInstance("qwe, qwer, qwert, qwertz, qwertzu");
		String[] temp = new String[] {"qwe", "qwer", "qwert", "qwertz", "qwertzu"};
		assertTrue(ing.asStringArray() == temp);
	}
}
