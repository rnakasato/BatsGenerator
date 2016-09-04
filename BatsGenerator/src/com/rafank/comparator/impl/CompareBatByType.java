package com.rafank.comparator.impl;

import java.util.Comparator;

import com.rafank.domain.impl.Bat;

public class CompareBatByType implements Comparator<Bat>{

	@Override
	public int compare(Bat o1, Bat o2) {
		int result = 0;
		if(o1.getBatType().getDescription().equals(o2.getBatType().getDescription())){
			result = 0;
		}else{
			result = o1.getBatType().getDescription().compareToIgnoreCase(o2.getBatType().getDescription());			
		}
		return result;		
	}
}
