package com.rafank.comparator.impl;

import java.util.Comparator;

import com.rafank.domain.impl.Folder;

public class CompareFolderByPath implements Comparator<Folder>{
	@Override
	public int compare(Folder o1, Folder o2) {
		int result = 0;
		if(o1.getPath().equals(o2.getPath())){
			result = 0;
		}else{
			result = o1.getPath().compareToIgnoreCase(o2.getPath());			
		}
		return result;		
	}
}
