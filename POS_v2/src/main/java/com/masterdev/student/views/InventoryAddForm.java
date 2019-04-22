package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.InventoryAddFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class InventoryAddForm {
	
	private static InventoryAddFormController inventoryAddFormController;
	
	public InventoryAddForm() {}
	
	public static InventoryAddFormController getInventoryAddFormController() {
		return inventoryAddFormController;
	}
	
	public static void setInventoryAddFormController(InventoryAddFormController controller) {
		inventoryAddFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/inventoryAddForm.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InventoryAddFormController controller = (InventoryAddFormController) loader.getController();
		setInventoryAddFormController(controller);
		return node;
	}
}
