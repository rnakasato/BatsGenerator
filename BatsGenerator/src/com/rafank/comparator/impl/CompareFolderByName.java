package com.rafank.comparator.impl;

import java.util.Comparator;

import com.rafank.domain.impl.Folder;

public class CompareFolderByName implements Comparator<Folder>{

	@Override
	public int compare(Folder o1, Folder o2) {
		int result = 0;
		if(o1.getFolderName().equals(o2.getFolderName())){
			result = 0;
		}else{
			result = o1.getFolderName().compareToIgnoreCase(o2.getFolderName());			
		}
		return result;		
	}
}
