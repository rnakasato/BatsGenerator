package com.rafank.controller.impl;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.rafank.application.Result;
import com.rafank.constant.BatType;
import com.rafank.controller.BatEditFormControlBC;
import com.rafank.converter.impl.BatContentToTargetPathConverter;
import com.rafank.converter.impl.ContentListToStringConverter;
import com.rafank.converter.impl.TextToContentLinesConverter;
import com.rafank.core.impl.Facade;
import com.rafank.domain.impl.Bat;
import com.rafank.util.AlertBox;
import com.rafank.util.FormUtil;

import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BatEditFormControl extends BatEditFormControlBC implements Initializable {

	private File selectedFile;
	private static Bat bat;
	private BatType type;

	private HBox tempHbFile;
	private HBox tempHbFolder;
	private HBox tempHbProgram;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(bat != null && !initValidation()){
			facade = new Facade<Bat>();
			rdOpenFolder.setOnAction(e -> openFolderAction());
			txtFolderPath.setOnKeyReleased(e -> openFolderAction());

			rdCustomScript.setOnAction(e -> customScriptAction());
			rdExecuteProgram.setOnAction(e -> executeProgramAction());
			txtProgramPath.setOnKeyReleased(e -> executeProgramAction());

			rdOpenFile.setOnAction(e -> openFileAction());
			txtFilePath.setOnKeyReleased(e -> openFileAction());		
			
			rdUnknown.setOnAction(e -> unknownAction());
			
			btnSave.setOnAction(e -> saveChangesAction());
			btnCancel.setOnAction(e -> close());
			btnBrowseFile.setOnAction(e -> browseFileAction());
			btnBrowseFolder.setOnAction(e -> browseFolderAction());
			btnBrowseProgram.setOnAction(e -> browseProgramAction());
			btnReload.setOnAction(e -> reloadAction());			
			
			txtName.setText(bat.getNoExtensionName());
			setSelectedRadio(bat.getBatType().getDescription());
			setSelectedFile(new File(bat.getPath()));
			txtOldContent.setText(ContentListToStringConverter.convert(bat.getContentLines()));
			txtContent.setText(ContentListToStringConverter.convert(bat.getContentLines()));
		}else{
			AlertBox.display("WARNING", "No valid bat was selected to edit");
			close();
		}
	}
	
	// Button actions
	public void reloadAction(){
		txtName.setText(bat.getNoExtensionName());
		setSelectedRadio(bat.getBatType().getDescription());
		txtContent.setText(ContentListToStringConverter.convert(bat.getContentLines()));
		
	}
	
	public void openFolderAction() {
		VBox vbox = (VBox) borderPane.getCenter();
		if (!vbox.getChildren().contains(hbFolder)) {
			vbox.getChildren().add(2, hbFolder);
		}
		vbox.getChildren().removeAll(hbFile, hbProgram);
		txtContent.setEditable(false);
		String folderPath = txtFolderPath.getText();
		StringBuilder script = new StringBuilder();
		script.append(BatType.OPEN_FOLDER).append("\n");
		script.append("start \"\"  \"").append(folderPath).append("\"");
		txtContent.setText(script.toString());

	}

	public void openFileAction() {
		VBox vbox = (VBox) borderPane.getCenter();
		if (!vbox.getChildren().contains(hbFile)) {
			vbox.getChildren().add(2, hbFile);
		}
		vbox.getChildren().removeAll(hbFolder, hbProgram);
		txtContent.setEditable(false);
		String folderPath = txtFilePath.getText();
		StringBuilder script = new StringBuilder();
		script.append(BatType.OPEN_FILE).append("\n");
		script.append("start \"\"  \"").append(folderPath).append("\"");
		txtContent.setText(script.toString());

	}

	public void executeProgramAction() {
		VBox vbox = (VBox) borderPane.getCenter();
		if (!vbox.getChildren().contains(hbProgram)) {
			vbox.getChildren().add(2, hbProgram);
		}
		vbox.getChildren().removeAll(hbFile, hbFolder);
		txtContent.setEditable(false);

		String programPath = txtProgramPath.getText();
		StringBuilder script = new StringBuilder();
		script.append(BatType.EXECUTE_PROGRAM).append("\n");
		script.append("start \"\"  \"").append(programPath).append("\"");
		txtContent.setText(script.toString());

	}

	public void customScriptAction() {
		VBox vbox = (VBox) borderPane.getCenter();
		vbox.getChildren().removeAll(hbFolder, hbFile, hbProgram);
		txtContent.setEditable(true);
		StringBuilder script = new StringBuilder();
		script.append(BatType.CUSTOM_SCRIPT).append("\n");		
		txtContent.setText(script.toString());
	}
	
	public void unknownAction(){
		VBox vbox = (VBox) borderPane.getCenter();
		vbox.getChildren().removeAll(hbFolder, hbFile, hbProgram);
		txtContent.setEditable(true);		
	}

	// TODO refazer com validações
	public void saveChangesAction() {
		String msg = validateForm();
		if (msg != null && !msg.isEmpty()) {
			AlertBox.display("WARNING", msg);
		} else {
			bat.setName(txtName.getText());
			bat.setContentLines(TextToContentLinesConverter.convert(txtContent.getText()));		
			bat.setTargetPath(BatContentToTargetPathConverter.convert(bat.getContentLines()));
			Result result = facade.update(bat);
			if (result != null && result.getMsg() != null && !result.getMsg().isEmpty()) {
				AlertBox.display("WARNING", result.getMsg());
			} else {
				AlertBox.display("Success", "The new Bat was saved successfully");
				close();
			}
		}
	}

	public void browseFolderAction() {
		selectedFile = FormUtil.browseFolder(bat.getTargetPath());
		if(selectedFile != null){			
			clearBrowsePaths();
			txtFolderPath.setText(selectedFile.getAbsolutePath());
			openFolderAction();	
		}			
	}

	public void browseFileAction() {
		selectedFile = FormUtil.browseFile(bat.getTargetPath());
		if (selectedFile != null) {
			clearBrowsePaths();
			txtFilePath.setText(selectedFile.getAbsolutePath());
			openFileAction();
		}
	}

	public void browseProgramAction() {
		selectedFile = FormUtil.browseFile(bat.getTargetPath());
		if (selectedFile != null) {
			clearBrowsePaths();
			txtProgramPath.setText(selectedFile.getAbsolutePath());
			executeProgramAction();
		}
	}

	// Non Action Methods
	private void setSelectedRadio(String type) {
		switch (type) {
		case BatType.OPEN_FOLDER_DSC:
			txtFolderPath.setText(bat.getTargetPath());
			openFolderAction();
			rdOpenFolder.setSelected(true);						
			break;
		case BatType.OPEN_FILE_DSC:
			txtFilePath.setText(bat.getTargetPath());
			openFileAction();
			rdOpenFile.setSelected(true);			
			break;
		case BatType.EXECUTE_PROGRAM_DSC:
			txtProgramPath.setText(bat.getTargetPath());
			executeProgramAction();
			rdExecuteProgram.setSelected(true);			
			break;
		case BatType.CUSTOM_SCRIPT_DSC:
			txtContent.setText(ContentListToStringConverter.convert(bat.getContentLines()));
			customScriptAction();
			rdCustomScript.setSelected(true);			
			break;
		case BatType.UNKNOWN_DSC:
			txtContent.setText(ContentListToStringConverter.convert(bat.getContentLines()));
			unknownAction();
			rdUnknown.setSelected(true);
			break;
		default:
			break;
		}
	}
	

	// Non Action Methods
	private String validateForm() {
		String msg = null;
		if (rdOpenFile.isSelected() && (txtFilePath.getText() == null || txtFilePath.getText().isEmpty())) {
			msg = "Select a file to create the script";
		}

		if (rdOpenFolder.isSelected() && (txtFolderPath.getText() == null || txtFolderPath.getText().isEmpty())) {
			msg = "Select a folder to create the script";
		}

		if (rdExecuteProgram.isSelected() && (txtProgramPath.getText() == null || txtProgramPath.getText().isEmpty())) {
			msg = "Select a program to create the script";
		}

		if (rdCustomScript.isSelected() && (txtContent.getText() == null || txtContent.getText().isEmpty())) {
			msg = "Write the bat content";
		}
		return msg;
	}

	private void setContentPath(String type, String path) {
		switch (type) {
		case BatType.OPEN_FOLDER_DSC:
			txtFolderPath.setText(path);
			break;
		case BatType.OPEN_FILE_DSC:
			txtFilePath.setText(path);
			break;
		case BatType.EXECUTE_PROGRAM_DSC:
			txtProgramPath.setText(path);
			break;
		default:
			break;
		}
	}
	
	private void clearBrowsePaths(){	
		txtFilePath.setText("");
		txtFolderPath.setText("");
		txtProgramPath.setText("");				
	}
	
	
	// Validate Initialization
	public boolean initValidation(){
		boolean hasError = false;
		boolean validType= bat != null && bat.getBatType() != null;
		boolean validName = bat != null && bat.getNoExtensionName() != null;
		boolean validParentPath = bat != null && bat.getParentPath() != null && !bat.getParentPath().isEmpty();
		boolean validContent = bat != null && bat.getContentLines() != null && !bat.getContentLines().isEmpty();
		boolean validPath = bat != null && bat.getPath() != null && !bat.getPath().isEmpty();
		
		if(!validType || !validName || !validParentPath || !validContent || !validPath){
			hasError = true;
		}
		return hasError;
	}
	
	
	
	
	// Getters and Setters
	public HBox getTempHbFile() {
		return tempHbFile;
	}

	public void setTempHbFile(HBox tempHbFile) {
		this.tempHbFile = tempHbFile;
	}

	public HBox getTempHbFolder() {
		return tempHbFolder;
	}

	public void setTempHbFolder(HBox tempHbFolder) {
		this.tempHbFolder = tempHbFolder;
	}

	public HBox getTempHbProgram() {
		return tempHbProgram;
	}

	public void setTempHbProgram(HBox tempHbProgram) {
		this.tempHbProgram = tempHbProgram;
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

	public static void defineBat(Bat bat) {
		BatEditFormControl.bat = bat;
	}

	public BatType getType() {
		return type;
	}

	public void setType(BatType type) {
		this.type = type;
	}
}
