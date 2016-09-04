package com.rafank.constant;

import java.util.Random;

public class BatType {

	public static final String ALL = "::[ALL]";
	public static final String OPEN_FOLDER = "::[OPEN_FOLDER]";
	public static final String EXECUTE_PROGRAM = "::[EXECUTE_PROGRAM]";
	public static final String OPEN_FILE = "::[OPEN_FILE]";
	public static final String CUSTOM_SCRIPT = "::[CUSTOM_SCRIPT]";
	public static final String UNKNOWN = "::[UNKNOWN]";

	public static final String ALL_DSC = "All";
	public static final String OPEN_FOLDER_DSC = "Open Folder";
	public static final String EXECUTE_PROGRAM_DSC = "Execute Program";
	public static final String OPEN_FILE_DSC = "Open File";
	public static final String CUSTOM_SCRIPT_DSC = "Custom Script";
	public static final String UNKNOWN_DSC = "Unknown";

	private String type;
	private String description;
	
	public BatType(String type, String description){
		this.type = type;
		this.description = description;		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		BatType type = (BatType) obj;
		if(this.getType().equals(type.getType())){
			isEqual = true;
		}
		return isEqual;
	}
	
	
	
	
}
