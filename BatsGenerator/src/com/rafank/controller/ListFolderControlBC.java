package com.rafank.controller;

import com.rafank.domain.impl.Folder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public abstract class ListFolderControlBC extends ControllerBC {
	// Buttons
	@FXML
	protected Button btnFilter;
	@FXML
	protected Button btnOpen;
	@FXML
	protected Button btnClose;
	@FXML
	protected Button btnRemove;

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

	// Radio Button
	@FXML
	protected RadioButton rdFolderName;
	@FXML
	protected RadioButton rdBatNumberAsc;
	@FXML
	protected RadioButton rdBatNumberDsc;

	public Button getBtnFilter() {
		return btnFilter;
	}

	public void setBtnFilter(Button btnFilter) {
		this.btnFilter = btnFilter;
	}

	public Button getBtnOpen() {
		return btnOpen;
	}

	public void setBtnOpen(Button btnOpen) {
		this.btnOpen = btnOpen;
	}

	public Button getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(Button btnClose) {
		this.btnClose = btnClose;
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

	public RadioButton getRdFolderName() {
		return rdFolderName;
	}

	public void setRdFolderName(RadioButton rdFolderName) {
		this.rdFolderName = rdFolderName;
	}

	public RadioButton getRdBatNumberAsc() {
		return rdBatNumberAsc;
	}

	public void setRdBatNumberAsc(RadioButton rdBatNumberAsc) {
		this.rdBatNumberAsc = rdBatNumberAsc;
	}

	public RadioButton getRdBatNumberDsc() {
		return rdBatNumberDsc;
	}

	public void setRdBatNumberDsc(RadioButton rdBatNumberDsc) {
		this.rdBatNumberDsc = rdBatNumberDsc;
	}

	public TextField getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(TextField txtSearch) {
		this.txtSearch = txtSearch;
	}

	public Button getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(Button btnRemove) {
		this.btnRemove = btnRemove;
	}

}
