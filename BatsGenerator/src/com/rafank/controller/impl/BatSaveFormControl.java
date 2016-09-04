package com.rafank.controller.impl;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.rafank.application.Result;
import com.rafank.constant.BatType;
import com.rafank.controller.BatFormControlBC;
import com.rafank.controller.BatSaveFormControlBC;
import com.rafank.converter.impl.BatContentToTargetPathConverter;
import com.rafank.converter.impl.ContentListToStringConverter;
import com.rafank.converter.impl.TextToContentLinesConverter;
import com.rafank.core.impl.Facade;
import com.rafank.domain.impl.Bat;
import com.rafank.util.AlertBox;
import com.rafank.util.FileUtil;
import com.rafank.util.FormUtil;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BatSaveFormControl extends BatSaveFormControlBC implements Initializable {

	private File selectedFile;
	private static String parentPath;
	private BatType type;

	private HBox tempHbFile;
	private HBox tempHbFolder;
	private HBox tempHbProgram;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		facade = new Facade<Bat>();
		Boolean render = false;
		rdOpenFolder.setOnAction(e -> openFolderAction());
		txtFolderPath.setOnKeyReleased(e -> openFolderAction());

		rdCustomScript.setOnAction(e -> customScriptAction());

		rdExecuteProgram.setOnAction(e -> executeProgramAction());
		txtProgramPath.setOnKeyReleased(e -> executeProgramAction());

		rdOpenFile.setOnAction(e -> openFileAction());
		txtFilePath.setOnKeyReleased(e -> openFileAction());

		openFolderAction();

		btnSave.setOnAction(e -> saveAction());
		btnCancel.setOnAction(e -> close());
		btnBrowseFile.setOnAction(e -> browseFileAction());
		btnBrowseFolder.setOnAction(e -> browseFolderAction());
		btnBrowseProgram.setOnAction(e -> browseProgramAction());

	}

	public void openFolderAction() {
		VBox vbox = (VBox) borderPane.getCenter();
		if (!vbox.getChildren().contains(hbFolder)) {
			vbox.getChildren().add(3, hbFolder);
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
			vbox.getChildren().add(3, hbFile);
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
			vbox.getChildren().add(3, hbProgram);
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

		String folderPath = txtFolderPath.getText();
		StringBuilder script = new StringBuilder();
		script.append(BatType.CUSTOM_SCRIPT).append("\n");
		txtContent.setText(script.toString());

	}

	// TODO refazer com validações
	public void saveAction() {
		String msg = validateForm();
		if (msg != null && !msg.isEmpty()) {
			AlertBox.display("WARNING", msg);
		} else {
			Bat bat = new Bat();
			bat.setName(txtName.getText());
			bat.setParentPath(parentPath);
			bat.setContentLines(TextToContentLinesConverter.convert(txtContent.getText()));
			bat.setTargetPath(BatContentToTargetPathConverter.convert(bat.getContentLines()));
			Result result = facade.save(bat);
			if (result != null && result.getMsg() != null && !result.getMsg().isEmpty()) {
				AlertBox.display("WARNING", result.getMsg());
			} else {
				AlertBox.display("Success", "The new Bat was saved successfully");
				close();
			}
		}
	}

	public void browseFolderAction() {
		selectedFile = FormUtil.browseFolder();
		if (selectedFile != null) {
			clearBrowsePaths();
			txtFolderPath.setText(selectedFile.getAbsolutePath());
			openFolderAction();
		}
	}

	public void browseFileAction() {
		selectedFile = FormUtil.browseFile();
		if (selectedFile != null) {
			clearBrowsePaths();
			txtFilePath.setText(selectedFile.getAbsolutePath());
			openFileAction();
		}
	}

	public void browseProgramAction() {
		selectedFile = FormUtil.browseFile();
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
			rdOpenFolder.setSelected(true);
			break;
		case BatType.OPEN_FILE_DSC:
			rdOpenFile.setSelected(true);
			break;
		case BatType.EXECUTE_PROGRAM_DSC:
			rdExecuteProgram.setSelected(true);
			break;
		case BatType.CUSTOM_SCRIPT_DSC:
			rdCustomScript.setSelected(true);
			break;
		case BatType.UNKNOWN_DSC:
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

	public static String getParentPath() {
		return parentPath;
	}

	public static void setParentPath(String parentPath) {
		BatSaveFormControl.parentPath = parentPath;
	}

}
