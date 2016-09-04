package com.rafank.util;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	// Extensions constants for FileUtil can be added or used different ones
	public static final String DOT_BAT = ".bat";
	
	
	public static void writeList(File file, List<String> lines) throws IOException {
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		boolean doNewLine = false;
		for (String line : lines) {
			if (doNewLine) {
				bw.newLine();
			}
			bw.write(line);
			doNewLine = true;
		}
		bw.flush();
		bw.close();

	}

	/**
	 * Verifies if the file for the given path exists
	 * 
	 * @param path
	 * @return
	 */
	public static boolean fileExists(String path) {
		boolean exists = false;
		try {
			File f = new File(path);
			if (f.exists() && f.isDirectory()) {
				exists = true;
			}
		} catch (Exception e) {
			exists = false;
		}
		return exists;
	}

	/**
	 * Cria arquivo para conter folders de bat
	 */
	public static void createFolder(String path) {
		try { // criar arquivo e gravar path
			try {
				File fPath = new File(path);
				fPath.createNewFile();
			} catch (Exception exc) {
				exc.printStackTrace();
				System.out.println("Erro no Sistema");
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public static void openFile(String path) {
		try {
			File file = new File(path);
			Desktop.getDesktop().open(file);
		} catch (Exception e) {
			AlertBox.display("ERROR", "Invalid path was selected");
		}
	}
	
	public static void editFile(String path){
		try {
			File file = new File(path);
			Desktop.getDesktop().edit(file);
		} catch (Exception e) {
			AlertBox.display("ERROR", "Invalid path was selected");
		}
	}

	public static void deleteFile(String path) {
		File file = new File(path);
		if (file != null && fileExists(path) && file.isFile()) {
			file.delete();
		}
	}

	/**
	 * Verifies if the given path is valid for an existing file/directory
	 * 
	 * @param path
	 * @return
	 * 
	 */
	public static boolean isValidFile(String path) {
		boolean isValid = false;
		File file = new File(path);
		if (file.exists() && (file.isDirectory() || file.isFile())) {
			isValid = true;
		}
		return isValid;
	}
	
	public static boolean isValidFolder(String path){
		boolean isValid = false;
		File file = new File(path);
		if (file.exists() && (file.isDirectory())) {
			isValid = true;
		}
		return isValid;
	}

	public static List<String> getFilePathListByExtension(String parentPath, String extension) {
		List<String> filePathList = null;
		File parentFile = new File(parentPath);
		if (parentFile.isDirectory()) {
			StringBuilder sb;
			String[] pathVec = parentFile.list();
			filePathList = new ArrayList<>();
			for (String path : pathVec) {
				if (path.endsWith(extension)) {
					sb = new StringBuilder();
					sb.append(parentPath);
					sb.append("\\");
					sb.append(path);
					filePathList.add(sb.toString());
				}
			}
		}

		return filePathList;
	}
	
	public static List<File> getFileListByExtension(String parentPath, String extension) {
		List<File> fileList = null;
		File parentFile = new File(parentPath);
		if (parentFile.isDirectory()) {
			File[] fileVec = parentFile.listFiles();
			fileList = new ArrayList<>();
			for (File file : fileVec) {
				if (file.getName().endsWith(extension)) {
					fileList.add(file);
				}
			}
		}

		return fileList;
	}
	
	
	/**
	 * Method used to return lines of selected file, if the content is not a valid folder it will return null
	 * @param file
	 * @return
	 */
	public static List<String> getContentLinesList(File file) throws IOException{
		List<String> lineList = null;
		if(file != null && file.isFile()){
			lineList = new ArrayList<>();
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			Boolean exists = true;
			while (exists) {				
				String batsFolderPath = br.readLine();
				if (batsFolderPath == null) {
					exists = false;
				} else {
					lineList.add(batsFolderPath);
				}
			}
			br.close();
		}
		return lineList;
	}
	
	public static void main(String[] args) {
		List<String> lista = getFilePathListByExtension("C:\\Meus Documentos\\Projects\\BatsUteis", ".bat");
		for(String s:lista){
			System.out.println(s);
		}
	}
	
	

}
