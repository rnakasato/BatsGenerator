package com.rafank.controller.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import com.rafank.constant.ComparatorType;
import com.rafank.controller.ListFolderControlBC;
import com.rafank.core.impl.Facade;
import com.rafank.domain.impl.Folder;
import com.rafank.factory.impl.FactoryComparator;
import com.rafank.util.AlertBox;
import com.rafank.util.ConfirmBox;
import com.rafank.util.FileUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ListFolderControl extends ListFolderControlBC implements Initializable {

	// Non FXML attributes
	private static File selectedFile;
	private static List<Folder> fullFolderList;
	private static List<Folder> filteredFolderList;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		facade = new Facade<Folder>();
		setFullFolderList(facade.findAll(new Folder()).getEntityList());
		refreshTable(FactoryComparator.build(ComparatorType.COMPARE_FOLDER_BY_NAME),fullFolderList);
		
		btnFilter.setOnAction(e -> filterAction());
		btnClose.setOnAction(e -> close());
		btnOpen.setOnAction(e -> openAction());
		btnRemove.setOnAction(e -> removeAction());
		txtSearch.setOnKeyReleased(e -> keyReleasedFilter(e));
		tvFolderList.setOnKeyPressed(e -> keyPressedTable(e));
		
		rdBatNumberAsc.setOnAction(e -> filterAction());
		rdBatNumberDsc.setOnAction(e -> filterAction());
		rdFolderName.setOnAction(e -> filterAction());
	}

	// Actions
	public void keyReleasedFilter(KeyEvent event){
		filterAction();		
	}
	
	public void keyPressedTable(KeyEvent event){
		if(event.getCode().equals(KeyCode.ENTER)){
			openAction();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public void removeAction(){
		Folder folderSelected; 
		ObservableList<Folder> allFolders;
		allFolders = tvFolderList.getItems();
		folderSelected = tvFolderList.getSelectionModel().getSelectedItem();
		if(folderSelected != null && !folderSelected.getPath().isEmpty()){
			boolean response = ConfirmBox.display("WARNING", "Are you sure you want to remove the selected folder?");
			if(response){
				allFolders.remove(folderSelected);
				facade.saveList(allFolders);
				refreshTable(FactoryComparator.build(ComparatorType.COMPARE_FOLDER_BY_NAME), allFolders);
			}						
		}else{
			AlertBox.display("WARNING", "Select a folder to remove");
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void filterAction() {
		Comparator comparator = null;
		if(fullFolderList != null && !fullFolderList.isEmpty() && txtSearch != null){
			String filter = txtSearch.getText().toLowerCase();
			filteredFolderList = new ArrayList<>();
			for(Folder folder : fullFolderList){
				if(folder.getFolderName().toLowerCase().indexOf(filter) >= 0){
					filteredFolderList.add(folder);
				}
			}			
		}else{
			filteredFolderList = fullFolderList;
		}
		if(rdBatNumberAsc.isSelected()){
			comparator = FactoryComparator.build(ComparatorType.COMPARE_FOLDER_BY_BATS_NUMBER_ASC);				
		}else if(rdBatNumberDsc.isSelected()){
			comparator = FactoryComparator.build(ComparatorType.COMPARE_FOLDER_BY_BATS_NUMBER_DSC);
		}else if(rdFolderName.isSelected()){
			comparator = FactoryComparator.build(ComparatorType.COMPARE_FOLDER_BY_NAME);
		}
		
		if(comparator != null){
			filteredFolderList.sort(comparator);
		}
		refreshTable(comparator, filteredFolderList);		
	}

	@SuppressWarnings("unused")
	public void openAction() {
		Folder folderSelected; 
		ObservableList<Folder> allFolders;
		allFolders = tvFolderList.getItems();
		folderSelected = tvFolderList.getSelectionModel().getSelectedItem();
		if(folderSelected != null && !folderSelected.getPath().isEmpty()){
			String path = folderSelected.getPath();
		 	FileUtil.openFile(path); 
		}else{
			AlertBox.display("WARNING", "Select a folder to open");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		ListFolderControl.selectedFile = selectedFile;
	}

	public static List<Folder> getFullFolderList() {
		return fullFolderList;
	}

	public static void setFullFolderList(List<Folder> fullFolderList) {
		ListFolderControl.fullFolderList = fullFolderList;
	}

	public static List<Folder> getFilteredFolderList() {
		return filteredFolderList;
	}

	public static void setFilteredFolderList(List<Folder> filteredFolderList) {
		ListFolderControl.filteredFolderList = filteredFolderList;
	}

}
