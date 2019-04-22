package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.WarehouseListController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class WarehouseList {
private static WarehouseListController warehouseListController;
	
	public WarehouseList() {}
	
	public static WarehouseListController getWarehouseListController() {
		return warehouseListController;
	}
	
	public static void setWarehouseListController(WarehouseListController controller) {
		warehouseListController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/warehouseList.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		WarehouseListController controller = (WarehouseListController) loader.getController();
		setWarehouseListController(controller);
		return node;
	}
}
