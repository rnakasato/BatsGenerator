package com.rafank.util;

import java.io.File;
import java.util.List;

import com.rafank.domain.impl.Folder;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormUtil {
	private static String response;

	public static void openForm(Parent root) {

		Stage window = new Stage();
		Scene scene = new Scene(root);
		window.setScene(scene);
		// impede que haja interações entre as outras janelas
		// até que esta seja fechada
		window.initModality(Modality.APPLICATION_MODAL);
		// mostra a janela e antes de voltar para outro stage precisa ser
		// fechada
		StageCarrier.setStage(window);
		window.showAndWait();

	}
	
	public static void openForm(Parent root, boolean resizable){
		Stage window = new Stage();
		Scene scene = new Scene(root);
		if(!resizable){
			window.setResizable(false);
		}
		window.setScene(scene);
		// impede que haja interações entre as outras janelas
		// até que esta seja fechada
		window.initModality(Modality.APPLICATION_MODAL);
		// mostra a janela e antes de voltar para outro stage precisa ser
		// fechada
		StageCarrier.setStage(window);
		window.showAndWait();
	}

	public static String openFormWithResponse(Parent root) {
		Stage window = new Stage();
		Scene scene = new Scene(root);
		window.setScene(scene);
		// impede que haja interações entre as outras janelas
		// até que esta seja fechada
		window.initModality(Modality.APPLICATION_MODAL);
		// mostra a janela e antes de voltar para outro stage precisa ser
		// fechada
		StageCarrier.setStage(window);
		window.showAndWait();
		// Executa ações antes de abrir a form
		return response;
	}
	
	public static String openFormWithResponse(Parent root, boolean resizable) {
		Stage window = new Stage();
		Scene scene = new Scene(root);
		if(!resizable){
			window.setResizable(false);
		}
		window.setScene(scene);
		// impede que haja interações entre as outras janelas
		// até que esta seja fechada
		window.initModality(Modality.APPLICATION_MODAL);
		// mostra a janela e antes de voltar para outro stage precisa ser
		// fechada
		StageCarrier.setStage(window);
		window.showAndWait();
		// Executa ações antes de abrir a form
		return response;
	}
	
	public static File browseFolder() {
		Stage window = StageCarrier.getStage();
		File file = ChooserUtil.chooseFolder(window);
		return file;
	}
	
	public static File browseFolder(String startPath){
		Stage window = StageCarrier.getStage();
		File file = ChooserUtil.chooseFolder(window,startPath);
		return file;
	}
	
	public static File browseFile() {
		Stage window = StageCarrier.getStage();
		File file = ChooserUtil.chooseFile(window);
		return file;
	}
	
	public static File browseFile(String startPath) {
		Stage window = StageCarrier.getStage();
		File file = ChooserUtil.chooseFile(window,startPath);
		return file;
	}
	/**
	 * Returns the response of the form and cleans value
	 * @return
	 */
	public static String getResponse() {
		String msg = response;
		response = null;
		return msg;
	}

	public static void setResponse(String response) {
		FormUtil.response = response;
	}

}
