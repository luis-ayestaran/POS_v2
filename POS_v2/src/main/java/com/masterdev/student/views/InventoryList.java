package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.InventoryListController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class InventoryList {
	
	private static StackPane node;
	private static InventoryListController inventoryListController;
	
	public InventoryList() {}
	
	public static StackPane getNode() {
		return node;
	}
	
	public static void setNode(StackPane newNode) {
		node = newNode;
	}
	
	public static InventoryListController getInventoryListController() {
		return inventoryListController;
	}
	
	public static void setInventoryListController(InventoryListController controller) {
		inventoryListController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/inventoryList.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setNode(node);
		InventoryListController controller = (InventoryListController) loader.getController();
		setInventoryListController(controller);
		return node;
	}
}
