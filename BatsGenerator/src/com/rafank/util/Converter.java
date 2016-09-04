package com.rafank.util;

import java.util.ArrayList;
import java.util.List;

import com.rafank.domain.impl.Folder;

public class Converter {


	public static List<String> parseFolderList(List<Folder> entityList) {
		List<String> lines = new ArrayList<>();
		List<Folder> folderList = (List<Folder>) entityList;
		for (Folder folder : folderList) {
			String line = folder.getPath();
			lines.add(line);
		}
		return lines;
	}

}
