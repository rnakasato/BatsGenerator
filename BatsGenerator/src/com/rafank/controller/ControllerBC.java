package com.rafank.controller;

import com.rafank.core.IFacade;
import com.rafank.util.StageCarrier;

import javafx.stage.Stage;

public abstract class ControllerBC {
	protected IFacade facade;
	
	public void close() {
		Stage window = StageCarrier.getStage();
		window.close();
	}
	
	
	public IFacade getFacade() {
		return facade;
	}

	public void setFacade(IFacade facade) {
		this.facade = facade;
	}

}
