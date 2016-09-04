package com.rafank.factory.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.rafank.comparator.impl.CompareBatByName;
import com.rafank.comparator.impl.CompareBatByPath;
import com.rafank.comparator.impl.CompareBatByType;
import com.rafank.comparator.impl.CompareByBatsNumberAsc;
import com.rafank.comparator.impl.CompareByBatsNumberDsc;
import com.rafank.comparator.impl.CompareFolderByName;
import com.rafank.comparator.impl.CompareFolderByPath;
import com.rafank.constant.ComparatorType;

public class FactoryComparator {
private static Map<String,Comparator> comparatorMap;
	
	private static void initMap(){
		if(comparatorMap == null){
			comparatorMap = new HashMap<>();
			// Folder comparators
			comparatorMap.put(ComparatorType.COMPARE_FOLDER_BY_BATS_NUMBER_ASC, new CompareByBatsNumberAsc());
			comparatorMap.put(ComparatorType.COMPARE_FOLDER_BY_BATS_NUMBER_DSC, new CompareByBatsNumberDsc());
			comparatorMap.put(ComparatorType.COMPARE_FOLDER_BY_NAME, new CompareFolderByName());
			comparatorMap.put(ComparatorType.COMPARE_FOLDER_BY_PATH, new CompareFolderByPath());
			
			// Bat comparators
			comparatorMap.put(ComparatorType.COMPARE_BAT_BY_NAME, new CompareBatByName());
			comparatorMap.put(ComparatorType.COMPARE_BAT_BY_PATH, new CompareBatByPath());
			comparatorMap.put(ComparatorType.COMPARE_BAT_BY_TYPE, new CompareBatByType());
		}
	}
	
	public static Comparator build(String comparatorType){
		initMap();
		Comparator c = comparatorMap.get(comparatorType);
		return c;
	}
}
