package com.rafank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class BatEditFormControlBC extends BatFormControlBC {
	@FXML
	protected TextArea txtOldContent;

	@FXML
	protected Button btnReload;

	public TextArea getTxtOldContent() {
		return txtOldContent;
	}

	public void setTxtOldContent(TextArea txtOldContent) {
		this.txtOldContent = txtOldContent;
	}

	public Button getBtnReload() {
		return btnReload;
	}

	public void setBtnReload(Button btnReload) {
		this.btnReload = btnReload;
	}

}
