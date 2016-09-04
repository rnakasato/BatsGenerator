package com.rafank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BatFormControlBC extends ControllerBC{

	@FXML
	protected BorderPane borderPane;
	@FXML
	protected HBox hbFile;
	@FXML
	protected HBox hbFolder;
	@FXML
	protected HBox hbProgram;

	@FXML
	protected VBox vbContent;

	// Label
	@FXML
	protected Label lblFolder;
	@FXML
	protected Label lblProgram;
	@FXML
	protected Label lblFile;

	// TextField
	@FXML
	protected TextField txtName;
	@FXML
	protected TextField txtFolderPath;
	@FXML
	protected TextField txtFilePath;
	@FXML
	protected TextField txtProgramPath;
	@FXML
	protected TextArea txtContent;

	// Button
	@FXML
	protected Button btnBrowseFolder;
	@FXML
	protected Button btnBrowseProgram;
	@FXML
	protected Button btnBrowseFile;
	@FXML
	protected Button btnSave;
	@FXML
	protected Button btnCancel;

	// RadioButton
	@FXML
	protected RadioButton rdOpenFolder;
	@FXML
	protected RadioButton rdExecuteProgram;
	@FXML
	protected RadioButton rdOpenFile;
	@FXML
	protected RadioButton rdCustomScript;
	@FXML
	protected RadioButton rdUnknown; // Só é habilitado quando o bat está em
										// edição

	public TextField getTxtName() {
		return txtName;
	}

	public void setTxtName(TextField txtName) {
		this.txtName = txtName;
	}

	public TextField getTxtFolderPath() {
		return txtFolderPath;
	}

	public void setTxtFolderPath(TextField txtFolderPath) {
		this.txtFolderPath = txtFolderPath;
	}

	public TextField getTxtFilePath() {
		return txtFilePath;
	}

	public void setTxtFilePath(TextField txtFilePath) {
		this.txtFilePath = txtFilePath;
	}

	public TextField getTxtProgramPath() {
		return txtProgramPath;
	}

	public void setTxtProgramPath(TextField txtProgramPath) {
		this.txtProgramPath = txtProgramPath;
	}

	public TextArea getTxtContent() {
		return txtContent;
	}

	public void setTxtContent(TextArea txtContent) {
		this.txtContent = txtContent;
	}

	public Button getBtnBrowseFolder() {
		return btnBrowseFolder;
	}

	public void setBtnBrowseFolder(Button btnBrowseFolder) {
		this.btnBrowseFolder = btnBrowseFolder;
	}

	public Button getBtnBrowseProgram() {
		return btnBrowseProgram;
	}

	public void setBtnBrowseProgram(Button btnBrowseProgram) {
		this.btnBrowseProgram = btnBrowseProgram;
	}

	public Button getBtnBrowseFile() {
		return btnBrowseFile;
	}

	public void setBtnBrowseFile(Button btnBrowseFile) {
		this.btnBrowseFile = btnBrowseFile;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	public RadioButton getRdOpenFolder() {
		return rdOpenFolder;
	}

	public void setRdOpenFolder(RadioButton rdOpenFolder) {
		this.rdOpenFolder = rdOpenFolder;
	}

	public RadioButton getRdExecuteProgram() {
		return rdExecuteProgram;
	}

	public void setRdExecuteProgram(RadioButton rdExecuteProgram) {
		this.rdExecuteProgram = rdExecuteProgram;
	}

	public RadioButton getRdOpenFile() {
		return rdOpenFile;
	}

	public void setRdOpenFile(RadioButton rdOpenFile) {
		this.rdOpenFile = rdOpenFile;
	}

	public RadioButton getRdCustomScript() {
		return rdCustomScript;
	}

	public void setRdCustomScript(RadioButton rdCustomScript) {
		this.rdCustomScript = rdCustomScript;
	}

	public RadioButton getRdUnknown() {
		return rdUnknown;
	}

	public void setRdUnknown(RadioButton rdUnknown) {
		this.rdUnknown = rdUnknown;
	}

	public HBox getHbFile() {
		return hbFile;
	}

	public void setHbFile(HBox hbFile) {
		this.hbFile = hbFile;
	}

	public HBox getHbFolder() {
		return hbFolder;
	}

	public void setHbFolder(HBox hbFolder) {
		this.hbFolder = hbFolder;
	}

	public HBox getHbProgram() {
		return hbProgram;
	}

	public void setHbProgram(HBox hbProgram) {
		this.hbProgram = hbProgram;
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}

	public void setBorderPane(BorderPane borderPane) {
		this.borderPane = borderPane;
	}

	public Label getLblFolder() {
		return lblFolder;
	}

	public void setLblFolder(Label lblFolder) {
		this.lblFolder = lblFolder;
	}

	public Label getLblProgram() {
		return lblProgram;
	}

	public void setLblProgram(Label lblProgram) {
		this.lblProgram = lblProgram;
	}

	public Label getLblFile() {
		return lblFile;
	}

	public void setLblFile(Label lblFile) {
		this.lblFile = lblFile;
	}

	public VBox getVbContent() {
		return vbContent;
	}

	public void setVbContent(VBox vbContent) {
		this.vbContent = vbContent;
	}

}
