package com.rafank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class BatDetailControlBC extends ControllerBC {

	// Buttons
	@FXML
	protected Button btnClose;
	@FXML
	protected Button btnOpen;

	// Labels
	@FXML
	protected Label lblName;
	@FXML
	protected Label lblPath;
	@FXML
	protected Label lblType;
	@FXML
	protected TextArea txtContent;
	@FXML
	protected Label lblFolder;

	public Button getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(Button btnClose) {
		this.btnClose = btnClose;
	}

	public Label getLblName() {
		return lblName;
	}

	public void setLblName(Label lblName) {
		this.lblName = lblName;
	}

	public Label getLblPath() {
		return lblPath;
	}

	public void setLblPath(Label lblPath) {
		this.lblPath = lblPath;
	}

	public Label getLblType() {
		return lblType;
	}

	public void setLblType(Label lblType) {
		this.lblType = lblType;
	}

	public Button getBtnOpen() {
		return btnOpen;
	}

	public void setBtnOpen(Button btnOpen) {
		this.btnOpen = btnOpen;
	}

	public Label getLblFolder() {
		return lblFolder;
	}

	public void setLblFolder(Label lblFolder) {
		this.lblFolder = lblFolder;
	}

	public TextArea getTxtContent() {
		return txtContent;
	}

	public void setTxtContent(TextArea txtContent) {
		this.txtContent = txtContent;
	}

}
