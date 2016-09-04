package com.rafank.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.rafank.constant.FileNames;
import com.rafank.domain.impl.Folder;

public class ListLoaderUtil {

	// retorna lista de folders do arquivo ou cria um novo arquivo
	public static List<Folder> getFolderList() {
		List<String> lines = null;
		List<Folder> folderList = null;
		try { // Verificando se o arquivo de lista de folders já existe
			lines = new ArrayList<>();
			folderList = new ArrayList<>();
			
			FileReader fr = new FileReader("folderPath.txt");
			BufferedReader br = new BufferedReader(fr);
			Boolean exists = true;
			while (exists) {
				String batsFolderPath = br.readLine();
				if (batsFolderPath == null) {
					exists = false;
				} else {
					lines.add(batsFolderPath);
				}
			}
			br.close();

			for (String path : lines) {
				File file = new File(path);
				Folder folder = new Folder();
				folder.setFolderName(file.getName());
				folder.setPath(file.getAbsolutePath());
				String[] strList = file.list();
				if (strList != null) {
					folder.setBatsNumber(strList.length);
				} else {
					folder.setBatsNumber(0);
				}
				folderList.add(folder);
			}

		} catch (Exception e) { // se entrou aqui o arquivo não existe
			createFolderList();
		}
		return folderList;
	}

	/**
	 * Cria arquivo para conter folders de bat
	 */
	private static void createFolderList() {
		try { // criar arquivo e gravar path
			FileWriter fw;
			BufferedWriter bw;

			try {
				File fPath = new File(FileNames.BATS_FOLDER);
				fPath.createNewFile();
			} catch (Exception exc) {
				exc.printStackTrace();
				System.out.println("Erro no Sistema");
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}
	
	
	public static void main(String[] args) {
		List<Folder> folderList = ListLoaderUtil.getFolderList();
		for(Folder folder : folderList){
			System.out.println(folder.getPath());
		}
		System.out.println("FIM");
	}
}
