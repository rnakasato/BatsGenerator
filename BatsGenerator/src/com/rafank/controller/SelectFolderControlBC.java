package com.rafank.controller;

import com.rafank.domain.impl.Folder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SelectFolderControlBC extends ControllerBC {

	// Buttons
	@FXML
	protected Button btnFilter;
	@FXML
	protected Button btnSelect;
	@FXML
	protected Button btnCancel;

	// Fields
	@FXML
	protected TextField txtSearch;

	// Table
	@FXML
	protected TableView<Folder> tvFolderList;
	@FXML
	protected TableColumn<Folder, String> cnFolderName;
	@FXML
	protected TableColumn<Folder, Integer> cnBatsNumber;
	@FXML
	protected TableColumn<Folder, String> cnPath;

	public Button getBtnFilter() {
		return btnFilter;
	}

	public void setBtnFilter(Button btnFilter) {
		this.btnFilter = btnFilter;
	}

	public Button getBtnSelect() {
		return btnSelect;
	}

	public void setBtnSelect(Button btnSelect) {
		this.btnSelect = btnSelect;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	public TextField getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(TextField txtSearch) {
		this.txtSearch = txtSearch;
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
