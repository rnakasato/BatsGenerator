package com.rafank.converter.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rafae
 *
 */
public class BatContentToTargetPathConverter {
	
	public static String convert(List<String> contentLines){
		String path = null;
		if(contentLines != null && !contentLines.isEmpty() && contentLines.size() > 1){
			for(String content: contentLines){
				if(content.startsWith("start \"\"")){
					String edit = content.replace("start","");
					edit = edit.replace("\"", "");
					edit = edit.trim();
					path = edit;
					break;
				}
			}
		}
		return path;
	}
	
	public static void main(String[] args) {
		List<String> contentLines = new ArrayList<>();
		contentLines.add("::[OPEN_FOLDER]");
		contentLines.add("start \"\" \"C:\\Meus Documentos\\Ajuda\" ");
		System.out.println(convert(contentLines));
	}

}
