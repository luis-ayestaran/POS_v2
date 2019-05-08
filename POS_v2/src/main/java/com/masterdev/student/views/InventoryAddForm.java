package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.InventoryAddFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InventoryAddForm {
	
	private static Stage stage;
	private static InventoryAddFormController inventoryAddFormController;
	
	public InventoryAddForm() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
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
			Scene scene = new Scene(node, 1000, 680);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Registro de productos");
			getStage().initStyle(StageStyle.DECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().setResizable(true);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InventoryAddFormController controller = (InventoryAddFormController) loader.getController();
		setInventoryAddFormController(controller);
		
		return node;
	}
}
