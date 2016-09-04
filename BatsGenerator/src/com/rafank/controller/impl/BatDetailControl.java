package com.rafank.controller.impl;

import java.net.URL;
import java.util.ResourceBundle;

import com.rafank.controller.BatDetailControlBC;
import com.rafank.domain.impl.Bat;
import com.rafank.util.FileUtil;

import javafx.fxml.Initializable;

public class BatDetailControl extends BatDetailControlBC implements Initializable{
	private static Bat bat;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(bat != null){
			lblName.setText(bat.getName());
			lblPath.setText(bat.getPath());
			lblFolder.setText(bat.getParentPath());
			lblType.setText(bat.getBatType().getDescription());
			StringBuilder sb = new StringBuilder();
			for(String s:bat.getContentLines()){
				sb.append(s).append("\n");
			}
			txtContent.setText(sb.toString());			
		}
		btnOpen.setOnAction(e -> openAction());
		btnClose.setOnAction(e -> close());		
	}
	
	public void openAction(){
		String path = lblFolder.getText(); 
		if( path != null && FileUtil.isValidFile(path)){
			FileUtil.openFile(path);
		}
	}
	
	public static Bat getBat() {
		return bat;
	}

	public static void setBat(Bat bat) {
		BatDetailControl.bat = bat;
	}

}
