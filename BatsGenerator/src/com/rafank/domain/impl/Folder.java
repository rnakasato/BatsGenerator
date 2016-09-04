package com.rafank.domain.impl;

import com.rafank.domain.DomainEntity;

public class Folder extends DomainEntity{

	private String path;
	private String folderName;
	private Integer batsNumber;
	
	
	
	@Override
	public int hashCode() {
		int hashCode = 15;
		hashCode = 7 * (path != null ? path.hashCode() : 0);
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Folder){
			Folder folder = (Folder) obj;
			result = this.getPath().equals(folder.getPath());
		}
		return result;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public Integer getBatsNumber() {
		return batsNumber;
	}

	public void setBatsNumber(Integer batsNumber) {
		this.batsNumber = batsNumber;
	}

}
