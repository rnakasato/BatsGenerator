package com.rafank.converter.impl;

import java.util.List;

public class ContentListToStringConverter {
	public static String convert(List<String> lines){
		String converted = null;
		StringBuilder sb = new StringBuilder();
		for(String l: lines){
			sb.append(l).append("\n");
		}
		converted = sb.toString();
		return converted;
	}
}
