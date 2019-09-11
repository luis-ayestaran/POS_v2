package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.InventoryAddFormController;
import com.masterdev.student.views.controllers.InventoryEditFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InventoryEditForm {
	
	private static Stage stage;
	private static InventoryEditFormController inventoryEditFormController;
	
	public InventoryEditForm() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static InventoryEditFormController getInventoryEditFormController() {
		return inventoryEditFormController;
	}
	
	public static void setInventoryEditFormController(InventoryEditFormController controller) {
		inventoryEditFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/inventoryEditForm.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 1000, 680);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Edición de productos");
			getStage().setResizable(false);
			getStage().initModality(Modality.APPLICATION_MODAL);
			getStage().initStyle(StageStyle.DECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InventoryEditFormController controller = (InventoryEditFormController) loader.getController();
		setInventoryEditFormController(controller);
		
		
		return node;
	}
}
