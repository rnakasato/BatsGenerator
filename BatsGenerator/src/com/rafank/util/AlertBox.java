package com.rafank.util;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox{
	
	public static void display(String title, String message){
		Stage window = new Stage();
		
		// impede que haja interações entre as outras janelas
		// até que esta seja fechada
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
				
		Button closeButton = new Button();
		closeButton.setText("Close");
		closeButton.setOnAction(e -> window.close());
				
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		layout.setMargin(label, new Insets(10,10,0,10));
		layout.setMargin(closeButton, new Insets(10,10,10,10));
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		
		// mostra a janela e antes de voltar para outro stage precisa ser fechada
		window.showAndWait();
	}
	
}
