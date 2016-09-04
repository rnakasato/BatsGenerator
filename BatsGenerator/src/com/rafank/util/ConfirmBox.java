package com.rafank.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	static boolean answer;
	
	public static boolean display(String title, String message){
		Stage window = new Stage();
		
		// impede que haja interações entre as outras janelas
		// até que esta seja fechada
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(350);
		
		
		Label label = new Label();
		label.setText(message);
		
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		yesButton.setOnAction(e ->{
			answer = true;
			window.close();
		});
		
		noButton.setOnAction(e ->{
			answer = false;
			window.close();
		});
		
		VBox layout = new VBox(10);
		HBox buttonLayout = new HBox();
		
		buttonLayout.setPadding(new Insets(10,10,10,10));
		buttonLayout.getChildren().addAll(yesButton,noButton);
		buttonLayout.setMargin(yesButton, new Insets(0,10,0,0));
		buttonLayout.setAlignment(Pos.CENTER);
		
		layout.setPadding(new Insets(10,10,10,10));
		layout.getChildren().addAll(label, buttonLayout);		
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		
		// mostra a janela e antes de voltar para outro stage precisa ser fechada
		window.showAndWait();
		
		return answer;
	}

}
