package com.rafank.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rafank.constant.FileNames;

public class BGInitializer {
	public static String init() {
		List<String> lines = null;
		String iniFolder = null;
		try { // Verificando se o arquivo de lista de folders já existe

			File folderFile = new File(FileNames.LAST_FOLDER);
			lines = FileUtil.getContentLinesList(folderFile);

			if (lines != null && lines.size() > 0) {
				iniFolder = lines.get(0);
			}

		} catch (Exception e) { // se entrou aqui o arquivo não existe
			FileUtil.createFolder(FileNames.LAST_FOLDER);
		}
		return iniFolder;
	}
	
	
	
	
	public static void setInitFolder(String path) throws IOException{
		File file = new File(FileNames.LAST_FOLDER);
		List<String> folderList = new ArrayList<>();
		folderList.add(path);
		FileUtil.writeList(file, folderList);		
	}
}
