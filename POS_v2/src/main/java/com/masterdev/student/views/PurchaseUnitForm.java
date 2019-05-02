package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.PurchaseUnitFormController;

//import com.masterdev.student.views.controllers.InventoryAddFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PurchaseUnitForm {
	private static Stage stage;
	private static PurchaseUnitFormController purchaseUnitFormController;
	
	public PurchaseUnitForm() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static PurchaseUnitFormController getPurchaseUnitFormController() {
		return purchaseUnitFormController;
	}
	
	public static void setPurchaseUnitFormController(PurchaseUnitFormController controller) {
		purchaseUnitFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/purchaseUnitForm.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 450, 300);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Agregar unidad de compra");
			getStage().setResizable(false);
			getStage().initModality(Modality.APPLICATION_MODAL);
			getStage().initStyle(StageStyle.UNIFIED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PurchaseUnitFormController controller = (PurchaseUnitFormController) loader.getController();
		setPurchaseUnitFormController(controller);
		return node;
	}
}
