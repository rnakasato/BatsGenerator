package com.rafank.converter.impl;

import java.util.Arrays;
import java.util.List;

public class TextToContentLinesConverter {
	public static List<String> convert(String text){
		List<String> list = null;
		if(text != null){
			String[] vec = text.split("\n");
			list = Arrays.asList(vec);
			list = Arrays.asList(vec);
		}		
		return list;
	}
	
	public static void main(String[] args) {
		List<String> list = convert("TEXTO BLABLABLA \nHAHAHA \nIOLO");
		if(list != null){
			for (String string : list) {
				System.out.println(string);
			}
			
		}
		
		System.out.println("--------------------");
		System.out.println("TEXTO BLABLABLA \nHAHAHA \nIOLO");
	}
}
