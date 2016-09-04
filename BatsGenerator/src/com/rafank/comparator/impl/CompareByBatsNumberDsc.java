package com.rafank.comparator.impl;

import java.util.Comparator;

import com.rafank.domain.impl.Folder;

public class CompareByBatsNumberDsc implements Comparator<Folder>{

	@Override
	public int compare(Folder o1, Folder o2) {
		int result = 0;
		if(o1.getBatsNumber() != null && o2.getBatsNumber() != null){
			if(o1.getBatsNumber() > o2.getBatsNumber()){
				result = -1;
			}else if(o1.getBatsNumber() < o2.getBatsNumber()){
				result = 1;
			}else if(o1.getBatsNumber().equals(o2.getBatsNumber())){
				result = 0;
			}
		}else if(o1.getBatsNumber() != null && o2.getBatsNumber() == null){
			result = -1;
		}else if(o1.getBatsNumber() == null && o2.getBatsNumber() != null){
			result = 1;
		}
		return result;
	}

}
