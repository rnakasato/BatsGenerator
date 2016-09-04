package com.rafank.util;

import javafx.stage.Stage;

public class StageCarrier {
	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		StageCarrier.stage = stage;
	}

}
