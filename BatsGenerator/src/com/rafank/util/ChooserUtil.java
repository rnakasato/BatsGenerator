package com.rafank.util;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ChooserUtil {
	public static File chooseFolder(Stage ownerWindow){
		File file;
		DirectoryChooser chooser = new DirectoryChooser();
		file = chooser.showDialog(ownerWindow);
		return file;
	}
	
	public static File chooseFile(Stage ownerWindow){
		File file;
		FileChooser chooser = new FileChooser();
		file = chooser.showOpenDialog(ownerWindow);				
		return file;
	}
	
	public static File chooseFolder(Stage ownerWindow, String startPath){
		File file;
		DirectoryChooser chooser = new DirectoryChooser();
		if(startPath != null && !startPath.isEmpty()){
			File initialDirectory = new File(startPath);
			if(initialDirectory.exists() && initialDirectory.isDirectory()){			
				chooser.setInitialDirectory(initialDirectory);
			}		
		}		
		file = chooser.showDialog(ownerWindow);
		return file;
	}
	
	public static File chooseFile(Stage ownerWindow, String startPath){
		File file;
		FileChooser chooser = new FileChooser();
		if(startPath != null && !startPath.isEmpty()){
			File initialDirectory = new File(startPath);
			if(initialDirectory.exists() && initialDirectory.isDirectory()){			
				chooser.setInitialDirectory(initialDirectory);
			}else if(initialDirectory.exists() && initialDirectory.isFile()){
				String parentPath = initialDirectory.getAbsolutePath();
				int indexOfFile = parentPath.lastIndexOf('\\');
				chooser.setInitialDirectory(new File(parentPath.substring(0, indexOfFile)));
			}
		}		
		file = chooser.showOpenDialog(ownerWindow);				
		return file;
	}
	
}
