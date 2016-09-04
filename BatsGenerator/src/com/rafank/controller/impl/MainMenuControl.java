package com.rafank.controller.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import com.rafank.application.Result;
import com.rafank.constant.BatType;
import com.rafank.constant.ComparatorType;
import com.rafank.constant.FXMLPath;
import com.rafank.controller.MainMenuControlBC;
import com.rafank.core.impl.Facade;
import com.rafank.domain.impl.Bat;
import com.rafank.domain.impl.Folder;
import com.rafank.factory.impl.FactoryBatType;
import com.rafank.factory.impl.FactoryComparator;
import com.rafank.util.AlertBox;
import com.rafank.util.BGInitializer;
import com.rafank.util.ConfirmBox;
import com.rafank.util.FileUtil;
import com.rafank.util.FormUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controller for BatsGenerator Main Menu
 * 
 * @author rafae
 *
 */
/**
 * @author rafae
 *
 */
public class MainMenuControl extends MainMenuControlBC implements Initializable {

	// Non FXML attributes
	private String searchString;
	private File selectedFolder;
	private List<Bat> fullBatList;
	private List<Bat> filteredBatList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Form caller buttons
		btnListFolders.setOnAction(e -> listFoldersAction());
		btnNewFolder.setOnAction(e -> newFolderAction());
		btnNewBat.setOnAction(e -> newBatAction());
		btnDetail.setOnAction(e -> detailAction());
		btnSelectFolder.setOnAction(e -> selectFolderAction());

		btnDelete.setOnAction(e -> deleteAction());
		btnEdit.setOnAction(e -> editAction());
		btnOpen.setOnAction(e -> openAction());
		btnRefresh.setOnAction(e -> refreshAction());
		btnSystemEditor.setOnAction(e -> systemEditorAction());
		btnFilter.setOnAction(e -> filterAction());
		btnExit.setOnAction(e -> exitAction());
		btnExecuteBat.setOnAction(e -> testBatAction());

		rdTypeAll.setOnAction(e -> filterAction());
		rdTypeCustomScript.setOnAction(e -> filterAction());
		rdTypeExecProgram.setOnAction(e -> filterAction());
		rdTypeOpenFile.setOnAction(e -> filterAction());
		rdTypeOpenFolder.setOnAction(e -> filterAction());
		rdTypeUnknown.setOnAction(e -> filterAction());
		
		rdPoName.setOnAction(e -> filterAction());
		rdPoPath.setOnAction(e -> filterAction());
		rdPoType.setOnAction(e -> filterAction());

		facade = new Facade<Folder>();

		lblPath.setText(BGInitializer.init());

		txtSearch.setOnKeyReleased(e -> keyReleasedFilter(e));
		tvBats.setOnKeyReleased(e -> keyPressedTable(e));
		refreshTable(null, lblPath.getText());
	}

	// ButtonActions
	public void keyReleasedFilter(KeyEvent event) {
		filterAction();
	}

	public void keyPressedTable(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			detailAction();
		}
	}
	
	public void newBatAction() {
		try {
			if (lblPath.getText() != null && !lblPath.getText().isEmpty()) {
				Parent root;
				root = FXMLLoader.load(getClass().getResource(FXMLPath.BAT_SAVE_FORM));
				BatSaveFormControl.setParentPath(lblPath.getText());
				FormUtil.openForm(root,false);
				refreshAction();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	public void testBatAction() {
		Bat selectedBat = tvBats.getSelectionModel().getSelectedItem();
		if (selectedBat != null && selectedBat.getPath() != null) {
			FileUtil.openFile(selectedBat.getPath());
		} else {
			AlertBox.display("WARNING", "Select a bat to execute");
		}
	}

	@SuppressWarnings("unchecked")
	public void filterAction() {
		Comparator<Bat> comparator = null;
		if (fullBatList != null && !fullBatList.isEmpty() && txtSearch != null) {
			String filter = txtSearch.getText().toLowerCase();
			filteredBatList = new ArrayList<>();
			RadioButton rd = (RadioButton) rdTypeAll.getToggleGroup().getSelectedToggle();
			BatType batType = FactoryBatType.build(rd.getText());
			for (Bat bat : fullBatList) {
				if (bat.getName().toLowerCase().indexOf(filter) >= 0
						&& (batType.getType().equals(BatType.ALL) || bat.getBatType().equals(batType))) {
					filteredBatList.add(bat);
				}
			}
		} else {
			filteredBatList = fullBatList;
		}
		if (rdPoName.isSelected()) {
			comparator = FactoryComparator.build(ComparatorType.COMPARE_BAT_BY_NAME);
		} else if (rdPoPath.isSelected()) {
			comparator = FactoryComparator.build(ComparatorType.COMPARE_BAT_BY_PATH);
		} else if (rdPoType.isSelected()) {
			comparator = FactoryComparator.build(ComparatorType.COMPARE_BAT_BY_TYPE);
		}

		refreshTable(comparator, filteredBatList);
	}

	@SuppressWarnings("unchecked")
	public void refreshAction() {
		Comparator<Bat> comparator = null;
		if (rdPoName.isSelected()) {
			comparator = FactoryComparator.build(ComparatorType.COMPARE_BAT_BY_NAME);
		} else if (rdPoPath.isSelected()) {
			comparator = FactoryComparator.build(ComparatorType.COMPARE_BAT_BY_PATH);
		} else if (rdPoType.isSelected()) {
			comparator = FactoryComparator.build(ComparatorType.COMPARE_BAT_BY_TYPE);
		}
		refreshTable(comparator, lblPath.getText());
		if (filteredBatList != null) {
			filterAction();
		}
	}

	public void systemEditorAction() {
		Bat selectedBat = tvBats.getSelectionModel().getSelectedItem();
		if (selectedBat != null && selectedBat.getPath() != null) {
			FileUtil.editFile(selectedBat.getPath());
		} else {
			AlertBox.display("WARNING", "Select a bat to open with system editor");
		}
	}

	public void newFolderAction() {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource(FXMLPath.NEW_FOLDER));
			FormUtil.openForm(root,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void selectFolderAction() {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource(FXMLPath.SELECT_FOLDER));
			String path = FormUtil.openFormWithResponse(root,false);
			if (path != null) {
				lblPath.setText(path);
				refreshTable(FactoryComparator.build(ComparatorType.COMPARE_BAT_BY_NAME), lblPath.getText());
				BGInitializer.setInitFolder(path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listFoldersAction() {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource(FXMLPath.LIST_FOLDERS));
			FormUtil.openForm(root,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void detailAction() {
		Bat bat = tvBats.getSelectionModel().getSelectedItem();
		if (bat != null) {
			try {
				BatDetailControl.setBat(bat);
				Parent root;
				root = FXMLLoader.load(getClass().getResource(FXMLPath.BAT_DETAIL));
				FormUtil.openForm(root,false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			AlertBox.display("WARNING", "Select a bat to see details");
		}
	}

	public void openAction() {
		if (lblPath != null && !lblPath.getText().equals("")) {
			String path = lblPath.getText();
			FileUtil.openFile(path);
		} else {
			AlertBox.display("WARNING", "Select a folder to open");
		}
	};

	public void editAction() {
		Bat bat = tvBats.getSelectionModel().getSelectedItem();
		try {
			if (bat != null) {
				BatEditFormControl.defineBat(bat);
				BatDetailControl.setBat(bat);
				Parent root;
				root = FXMLLoader.load(getClass().getResource(FXMLPath.BAT_EDIT_FORM));
				FormUtil.openForm(root,false);
				refreshAction();
			} else {
				AlertBox.display("WARNING", "Select a bat to edit");
			}
		} catch (SecurityException | IOException e) {
			AlertBox.display("WARNING", "System Error");
		}
	};

	public void deleteAction() {
		boolean response = false;
		Bat bat = tvBats.getSelectionModel().getSelectedItem();
		try {
			if (bat != null) {
				response = ConfirmBox.display("Confirm", "Are you sure to delete the selected bat?");
				if (response) {
					File file = new File(bat.getPath());
					file.delete();
					refreshAction();
				}
			} else {
				AlertBox.display("WARNING", "Select a bat to delete");
			}
		} catch (SecurityException e) {
			AlertBox.display("WARNING", "System Error");
		}
	}

	public void exitAction() {
		boolean confirm = false;
		confirm = ConfirmBox.display("WARNING", "Are you sure to exit BatsGenerator?");
		if (confirm) {
			System.exit(0);
		}

	}

	// Non action Methods
	@SuppressWarnings("unchecked")
	private void refreshTable(Comparator<Bat> comparator, String parentBatsFolder) {
		if (parentBatsFolder != null && !parentBatsFolder.isEmpty()) {
			ObservableList<Bat> batLines = FXCollections.observableArrayList();
			Bat bat = new Bat();
			bat.setParentPath(parentBatsFolder);
			Result<Bat> result = facade.find(bat);
			if (result.getEntityList() != null) {
				setBatList(result.getEntityList());
				for (Bat b : fullBatList) {
					batLines.add(b);
				}
				if (comparator != null) {
					batLines.sort(comparator);
				}
				cnName.setCellValueFactory(new PropertyValueFactory<>("name"));
				cnPath.setCellValueFactory(new PropertyValueFactory<>("path"));
				cnType.setCellValueFactory(new PropertyValueFactory<>("batType"));
				cnTargetPath.setCellValueFactory(new PropertyValueFactory<>("targetPath"));
				tvBats.setItems(batLines);
			}
		}
	}

	private void refreshTable(Comparator<Bat> comparator, List<Bat> batList) {
		if (batList != null) {
			ObservableList<Bat> batLines = FXCollections.observableArrayList();
			for (Bat b : batList) {
				batLines.add(b);
			}
			if (comparator != null) {
				batLines.sort(comparator);
			}
			cnName.setCellValueFactory(new PropertyValueFactory<>("name"));
			cnPath.setCellValueFactory(new PropertyValueFactory<>("path"));
			cnType.setCellValueFactory(new PropertyValueFactory<>("batType"));
			tvBats.setItems(batLines);
		}
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public File getSelectedFolder() {
		return selectedFolder;
	}

	public void setSelectedFolder(File selectedFolder) {
		this.selectedFolder = selectedFolder;
	}

	public List<Bat> getBatList() {
		return fullBatList;
	}

	public void setBatList(List<Bat> batList) {
		this.fullBatList = batList;
	}

}
