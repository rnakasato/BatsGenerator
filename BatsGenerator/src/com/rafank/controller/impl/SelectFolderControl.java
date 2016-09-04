package com.rafank.controller.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import com.rafank.constant.ComparatorType;
import com.rafank.controller.SelectFolderControlBC;
import com.rafank.core.impl.Facade;
import com.rafank.domain.impl.Folder;
import com.rafank.factory.impl.FactoryComparator;
import com.rafank.util.FormUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class SelectFolderControl extends SelectFolderControlBC implements Initializable{
	// Non FXML attributes
	private static File selectedFile;
	private static List<Folder> fullFolderList;
	private static List<Folder> filteredFolderList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		facade = new Facade<Folder>();
		setFullFolderList(facade.findAll(new Folder()).getEntityList());
		refreshTable(FactoryComparator.build(ComparatorType.COMPARE_FOLDER_BY_NAME), fullFolderList);

		btnFilter.setOnAction(e -> filterAction());
		btnCancel.setOnAction(e -> close());
		btnSelect.setOnAction(e -> selectAction());
		txtSearch.setOnKeyReleased(e -> keyReleasedFilter(e));
	}

	public void keyReleasedFilter(KeyEvent event) {
		filterAction();
	}
	
	public void selectAction(){
		Folder folderSelected; 
		folderSelected = tvFolderList.getSelectionModel().getSelectedItem();
		FormUtil.setResponse(folderSelected.getPath());
		close();
	}

	public void filterAction() {
		Comparator comparator = null;
		if (fullFolderList != null && !fullFolderList.isEmpty() && txtSearch != null) {
			String filter = txtSearch.getText().toLowerCase();
			filteredFolderList = new ArrayList<>();
			for (Folder folder : fullFolderList) {
				if (folder.getFolderName().toLowerCase().indexOf(filter) >= 0) {
					filteredFolderList.add(folder);
				}
			}
		} else {
			filteredFolderList = fullFolderList;
		}
		comparator = FactoryComparator.build(ComparatorType.COMPARE_FOLDER_BY_NAME);
		filteredFolderList.sort(comparator);
		
		refreshTable(comparator, filteredFolderList);
	}

	private void refreshTable(Comparator comparator, List<Folder> folderList) {
		ObservableList<Folder> folderLines = FXCollections.observableArrayList();
		for (Folder folder : folderList) {
			folderLines.add(folder);
		}
		folderLines.sort(comparator);
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
		SelectFolderControl.selectedFile = selectedFile;
	}

	public static List<Folder> getFullFolderList() {
		return fullFolderList;
	}

	public static void setFullFolderList(List<Folder> fullFolderList) {
		SelectFolderControl.fullFolderList = fullFolderList;
	}

	public static List<Folder> getFilteredFolderList() {
		return filteredFolderList;
	}

	public static void setFilteredFolderList(List<Folder> filteredFolderList) {
		SelectFolderControl.filteredFolderList = filteredFolderList;
	}
}
