package com.rafank.factory.impl;

import java.util.HashMap;
import java.util.Map;

import com.rafank.constant.BatType;

public class FactoryBatType {
	
	private static Map<String,BatType> batTypeMap;
	
	public static BatType build(String type) {
		if(batTypeMap == null){
			initialize();
		}
		BatType batType = batTypeMap.get(type);
		if(batType == null){
			batType = batTypeMap.get(BatType.UNKNOWN);
		}
		return batType;
	}

	private static void initialize(){
		if(batTypeMap == null){
			batTypeMap = new HashMap<>();
			BatType all = new BatType(BatType.ALL, BatType.ALL_DSC);
			BatType openFolder = new BatType(BatType.OPEN_FOLDER, BatType.OPEN_FOLDER_DSC);
			BatType executeProgram = new BatType(BatType.EXECUTE_PROGRAM, BatType.EXECUTE_PROGRAM_DSC);
			BatType openFile = new BatType(BatType.OPEN_FILE, BatType.OPEN_FILE_DSC);
			BatType customScript = new BatType(BatType.CUSTOM_SCRIPT, BatType.CUSTOM_SCRIPT_DSC);
			BatType unknown = new BatType(BatType.UNKNOWN, BatType.UNKNOWN_DSC);
			
			batTypeMap.put(BatType.ALL, all);
			batTypeMap.put(BatType.OPEN_FOLDER, openFolder);
			batTypeMap.put(BatType.EXECUTE_PROGRAM, executeProgram);
			batTypeMap.put(BatType.OPEN_FILE, openFile);
			batTypeMap.put(BatType.CUSTOM_SCRIPT, customScript);
			batTypeMap.put(BatType.UNKNOWN, unknown);			
			
			batTypeMap.put(BatType.ALL_DSC, all);
			batTypeMap.put(BatType.OPEN_FOLDER_DSC, openFolder);
			batTypeMap.put(BatType.EXECUTE_PROGRAM_DSC, executeProgram);
			batTypeMap.put(BatType.OPEN_FILE_DSC, openFile);
			batTypeMap.put(BatType.CUSTOM_SCRIPT_DSC, customScript);
			batTypeMap.put(BatType.UNKNOWN_DSC, unknown);
			
		}
		
	}
}
