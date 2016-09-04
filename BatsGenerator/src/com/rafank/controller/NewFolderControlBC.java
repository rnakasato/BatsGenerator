package com.rafank.controller;

import com.rafank.domain.impl.Folder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public abstract class NewFolderControlBC extends ControllerBC {
	@FXML
	protected TextField txtPath;

	// Buttons
	@FXML
	protected Button btnBrowse;
	@FXML
	protected Button btnSave;
	@FXML
	protected Button btnCancel;

	// Table
	@FXML
	protected TableView<Folder> tvFolderList;
	@FXML
	protected TableColumn<Folder, String> cnFolderName;
	@FXML
	protected TableColumn<Folder, Integer> cnBatsNumber;
	@FXML
	protected TableColumn<Folder, String> cnPath;

	public TextField getTxtPath() {
		return txtPath;
	}

	public void setTxtPath(TextField txtPath) {
		this.txtPath = txtPath;
	}

	public Button getBtnBrowse() {
		return btnBrowse;
	}

	public void setBtnBrowse(Button btnBrowse) {
		this.btnBrowse = btnBrowse;
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

	public TableView<Folder> getTvFolderList() {
		return tvFolderList;
	}

	public void setTvFolderList(TableView<Folder> tvFolderList) {
		this.tvFolderList = tvFolderList;
	}

	public TableColumn<Folder, String> getCnFolderName() {
		return cnFolderName;
	}

	public void setCnFolderName(TableColumn<Folder, String> cnFolderName) {
		this.cnFolderName = cnFolderName;
	}

	public TableColumn<Folder, Integer> getCnBatsNumber() {
		return cnBatsNumber;
	}

	public void setCnBatsNumber(TableColumn<Folder, Integer> cnBatsNumber) {
		this.cnBatsNumber = cnBatsNumber;
	}

	public TableColumn<Folder, String> getCnPath() {
		return cnPath;
	}

	public void setCnPath(TableColumn<Folder, String> cnPath) {
		this.cnPath = cnPath;
	}

}
