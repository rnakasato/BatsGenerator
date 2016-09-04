package com.rafank.controller.impl;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.rafank.controller.NewFolderControlBC;
import com.rafank.core.impl.Facade;
import com.rafank.domain.impl.Folder;
import com.rafank.util.AlertBox;
import com.rafank.util.FormUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

public class NewFolderControl extends NewFolderControlBC implements Initializable {

	// Non FXML attributes
	private static File selectedFile;
	private static List<Folder> folderList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		facade = new Facade<Folder>();
		btnBrowse.setOnAction(e -> browseAction());
		btnCancel.setOnAction(e -> close());
		btnSave.setOnAction(e -> saveAction());
		refreshTable();
	}

	// Actions
	/**
	 * Method used to create a reference for a new folder for bats
	 */
	public void browseAction() {
		selectedFile = FormUtil.browseFolder();
		if (selectedFile != null) {
			Folder folder = new Folder();
			folder.setPath(selectedFile.getAbsolutePath());
			if (folderList != null && !folderList.isEmpty() && folderList.contains(folder)) {
				AlertBox.display("WARNING", "Selected folder already exists");
			} else {
				txtPath.setText(selectedFile.getAbsolutePath());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void saveAction() {

		if (selectedFile != null) {
			try {
				List<Folder> folderList = facade.findAll(new Folder()).getEntityList();
				Folder folder = new Folder();
				folder.setPath(selectedFile.getAbsolutePath());
				folderList.add(folder);
				facade.saveList(folderList);

				refreshTable();
				// AlertBox
				AlertBox.display("SAVE", "Folder was saved successfully");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			AlertBox.display("Alert", "Select a folder to continue");
		}
	}

	@SuppressWarnings("unchecked")
	private void refreshTable() {
		ObservableList<Folder> folderLines = FXCollections.observableArrayList();
		setFolderList(facade.findAll(new Folder()).getEntityList());
		for (Folder folder : folderList) {
			folderLines.add(folder);
		}
		cnFolderName.setCellValueFactory(new PropertyValueFactory<>("folderName"));
		cnBatsNumber.setCellValueFactory(new PropertyValueFactory<>("batsNumber"));
		cnPath.setCellValueFactory(new PropertyValueFactory<>("path"));
		tvFolderList.setItems(folderLines);
	}

	// Getters and Setters
	public static File getSelectedFile() {
		return selectedFile;
	}

	public static void setSelectedFile(File selectedFile) {
		NewFolderControl.selectedFile = selectedFile;
	}

	public static List<Folder> getFolderList() {
		return folderList;
	}

	public static void setFolderList(List<Folder> folderList) {
		NewFolderControl.folderList = folderList;
	}

}
