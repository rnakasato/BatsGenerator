package com.rafank.controller;

import java.util.List;

import com.rafank.domain.impl.Bat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public abstract class MainMenuControlBC extends ControllerBC {
	// Buttons
	@FXML
	protected Button btnNewBat;
	@FXML
	protected Button btnNewFolder;
	@FXML
	protected Button btnSelectFolder;
	@FXML
	protected Button btnListFolders;
	@FXML
	protected Button btnOpen;
	@FXML
	protected Button btnEdit;
	@FXML
	protected Button btnDelete;
	@FXML
	protected Button btnSystemEditor;
	@FXML
	protected Button btnRefresh;
	@FXML
	protected Button btnFilter;
	@FXML
	protected Button btnDetail;
	@FXML
	protected Button btnExecuteBat;
	@FXML
	protected Button btnExit;

	// TextField
	@FXML
	protected TextField txtSearch;

	// Label
	@FXML
	protected Label lblPath;

	// Bat type Radio Button
	@FXML
	protected RadioButton rdTypeAll;
	@FXML
	protected RadioButton rdTypeOpenFolder;
	@FXML
	protected RadioButton rdTypeExecProgram;
	@FXML
	protected RadioButton rdTypeOpenFile;
	@FXML
	protected RadioButton rdTypeCustomScript;
	@FXML
	protected RadioButton rdTypeUnknown;

	// Priority Order Radio Button
	@FXML
	protected RadioButton rdPoName;
	@FXML
	protected RadioButton rdPoPath;
	@FXML
	protected RadioButton rdPoContent;
	@FXML
	protected RadioButton rdPoType;

	// Table
	@FXML
	protected TableView<Bat> tvBats;
	@FXML
	protected TableColumn<Bat, String> cnName;
	@FXML
	protected TableColumn<Bat, String> cnPath;
	@FXML
	protected TableColumn<Bat, Integer> cnType;
	@FXML
	protected TableColumn<Bat, String> cnTargetPath;

	public Button getBtnNewBat() {
		return btnNewBat;
	}

	public void setBtnNewBat(Button btnNewBat) {
		this.btnNewBat = btnNewBat;
	}

	public Button getBtnNewFolder() {
		return btnNewFolder;
	}

	public void setBtnNewFolder(Button btnNewFolder) {
		this.btnNewFolder = btnNewFolder;
	}

	public Button getBtnSelectFolder() {
		return btnSelectFolder;
	}

	public void setBtnSelectFolder(Button btnSelectFolder) {
		this.btnSelectFolder = btnSelectFolder;
	}

	public Button getBtnListFolders() {
		return btnListFolders;
	}

	public void setBtnListFolders(Button btnListFolders) {
		this.btnListFolders = btnListFolders;
	}

	public Button getBtnOpen() {
		return btnOpen;
	}

	public void setBtnOpen(Button btnOpen) {
		this.btnOpen = btnOpen;
	}

	public Button getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(Button btnEdit) {
		this.btnEdit = btnEdit;
	}

	public Button getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(Button btnDelete) {
		this.btnDelete = btnDelete;
	}

	public TextField getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(TextField txtSearch) {
		this.txtSearch = txtSearch;
	}

	public RadioButton getRdTypeAll() {
		return rdTypeAll;
	}

	public void setRdTypeAll(RadioButton rdTypeAll) {
		this.rdTypeAll = rdTypeAll;
	}

	public RadioButton getRdTypeOpenFolder() {
		return rdTypeOpenFolder;
	}

	public void setRdTypeOpenFolder(RadioButton rdTypeOpenFolder) {
		this.rdTypeOpenFolder = rdTypeOpenFolder;
	}

	public RadioButton getRdTypeExecProgram() {
		return rdTypeExecProgram;
	}

	public void setRdTypeExecProgram(RadioButton rdTypeExecProgram) {
		this.rdTypeExecProgram = rdTypeExecProgram;
	}

	public RadioButton getRdTypeOpenFile() {
		return rdTypeOpenFile;
	}

	public void setRdTypeOpenFile(RadioButton rdTypeOpenFile) {
		this.rdTypeOpenFile = rdTypeOpenFile;
	}

	public RadioButton getRdTypeCustomScript() {
		return rdTypeCustomScript;
	}

	public void setRdTypeCustomScript(RadioButton rdTypeCustomScript) {
		this.rdTypeCustomScript = rdTypeCustomScript;
	}

	public RadioButton getRdPoName() {
		return rdPoName;
	}

	public void setRdPoName(RadioButton rdPoName) {
		this.rdPoName = rdPoName;
	}

	public RadioButton getRdPoPath() {
		return rdPoPath;
	}

	public void setRdPoPath(RadioButton rdPoPath) {
		this.rdPoPath = rdPoPath;
	}

	public RadioButton getRdPoContent() {
		return rdPoContent;
	}

	public void setRdPoContent(RadioButton rdPoContent) {
		this.rdPoContent = rdPoContent;
	}

	public RadioButton getRdPoType() {
		return rdPoType;
	}

	public void setRdPoType(RadioButton rdPoType) {
		this.rdPoType = rdPoType;
	}

	public TableView<Bat> getTvBats() {
		return tvBats;
	}

	public void setTvBats(TableView<Bat> tvBats) {
		this.tvBats = tvBats;
	}

	public TableColumn<Bat, String> getCnName() {
		return cnName;
	}

	public void setCnName(TableColumn<Bat, String> cnName) {
		this.cnName = cnName;
	}

	public TableColumn<Bat, String> getCnPath() {
		return cnPath;
	}

	public void setCnPath(TableColumn<Bat, String> cnPath) {
		this.cnPath = cnPath;
	}

	public TableColumn<Bat, Integer> getCnType() {
		return cnType;
	}

	public void setCnType(TableColumn<Bat, Integer> cnType) {
		this.cnType = cnType;
	}

	public Label getLblPath() {
		return lblPath;
	}

	public void setLblPath(Label lblPath) {
		this.lblPath = lblPath;
	}

	public Button getBtnSystemEditor() {
		return btnSystemEditor;
	}

	public void setBtnSystemEditor(Button btnSystemEditor) {
		this.btnSystemEditor = btnSystemEditor;
	}

	public Button getBtnRefresh() {
		return btnRefresh;
	}

	public void setBtnRefresh(Button btnRefresh) {
		this.btnRefresh = btnRefresh;
	}

	public Button getBtnFilter() {
		return btnFilter;
	}

	public void setBtnFilter(Button btnFilter) {
		this.btnFilter = btnFilter;
	}

	public Button getBtnDetail() {
		return btnDetail;
	}

	public void setBtnDetail(Button btnDetail) {
		this.btnDetail = btnDetail;
	}

	public Button getBtnExecuteBat() {
		return btnExecuteBat;
	}

	public void setBtnExecuteBat(Button btnExecuteBat) {
		this.btnExecuteBat = btnExecuteBat;
	}

	public Button getBtnExit() {
		return btnExit;
	}

	public void setBtnExit(Button btnExit) {
		this.btnExit = btnExit;
	}

	public RadioButton getRdTypeUnknown() {
		return rdTypeUnknown;
	}

	public void setRdTypeUnknown(RadioButton rdTypeUnknown) {
		this.rdTypeUnknown = rdTypeUnknown;
	}

	public TableColumn<Bat, String> getCnTargetPath() {
		return cnTargetPath;
	}

	public void setCnTargetPath(TableColumn<Bat, String> cnTargetPath) {
		this.cnTargetPath = cnTargetPath;
	}

}
