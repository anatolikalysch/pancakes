package org.wahlzeit.extension.utils;

import java.util.Collection;

import org.wahlzeit.extension.domain.Pancake;
import org.wahlzeit.extension.domain.PancakeManager;
import org.wahlzeit.utils.HtmlUtil;

public class ExtendedHtmlUtil extends HtmlUtil {

	public static String asPancakeSelection() {
		StringBuffer buffer = new StringBuffer();
		Collection<Pancake> list = PancakeManager.getInstance().loadPancakes();
		for (Pancake pancake : list) {
			buffer.append("<option ");
			buffer.append("value=\""+pancake.getId()+"\"");
			if(pancake.getId().equals(-1)) 
				buffer.append(" selected");
		
			buffer.append(">");
			buffer.append(pancake.getType().getName());
			buffer.append("</option>");
		}
		
		return buffer.toString();
	}
	
	public static String asIngredientTable(Pancake pancake) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<table>");
		String[] ingredients = pancake.getType().getIng().asStringArray();
		for (int i = 0; i < ingredients.length - 1; i++) {
			buffer.append("<tr><td>");
			buffer.append(ingredients[i]);
			buffer.append("</td></tr>");
		}
		buffer.append("</table>");
		return buffer.toString();
	}

}
