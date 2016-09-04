package com.rafank.comparator.impl;

import java.util.Comparator;

import com.rafank.domain.impl.Bat;

public class CompareBatByName implements Comparator<Bat>{

	@Override
	public int compare(Bat o1, Bat o2) {
		int result = 0;
		if(o1.getName().equals(o2.getName())){
			result = 0;
		}else{
			result = o1.getName().compareToIgnoreCase(o2.getName());			
		}
		return result;		
	}
}
