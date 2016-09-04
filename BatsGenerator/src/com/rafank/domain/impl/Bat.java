package com.rafank.domain.impl;

import java.util.List;

import com.rafank.constant.BatType;
import com.rafank.domain.DomainEntity;

/**
 * Represents a .bat file
 * 
 * @author rafae
 *
 */
public class Bat extends DomainEntity {
	private String name;
	private String path;
	private BatType batType;
	private List<String> contentLines;
	private String parentPath;
	private String oldPath;

	/**
	 * target path defined for the BatTypes: Open File, Open Folder, Execute
	 * Program
	 */
	private String targetPath;

	public String getName() {
		return name;
	}

	public String getNoExtensionName() {
		return name.replace(".bat", "");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BatType getBatType() {
		return batType;
	}

	public void setType(BatType type) {
		this.batType = type;
	}

	public List<String> getContentLines() {
		return contentLines;
	}

	public void setContentLines(List<String> contentLines) {
		this.contentLines = contentLines;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public String getOldPath() {
		return oldPath;
	}

	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}

	public void setBatType(BatType batType) {
		this.batType = batType;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[NAME] ");
		sb.append(getName());
		sb.append("\n");

		sb.append("[TYPE] ");
		sb.append("\n");
		sb.append("\t");
		sb.append(getBatType() != null ? getBatType().getType() : null);
		sb.append("\n");
		sb.append("\t");
		sb.append(getBatType() != null ? getBatType().getDescription() : null);
		sb.append("\n");
		sb.append("[END_TYPE] ");
		sb.append("\n");

		sb.append("[PATH] ");
		sb.append(getPath());
		sb.append("\n");

		sb.append("[PARENT] ");
		sb.append(getParentPath());
		sb.append("\n");

		sb.append("[CONTENT] ");
		sb.append("\n");
		if (getContentLines() != null) {
			for (String s : getContentLines()) {
				sb.append("\t");
				sb.append(s);
				sb.append("\n");
			}
		} else {
			sb.append("null");
		}

		sb.append("\n");
		sb.append("[END_CONTENT] ");
		return sb.toString();
	}

}
