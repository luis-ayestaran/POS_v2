package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.SalesUnitFormController;

//import com.masterdev.student.views.controllers.InventoryAddFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SalesUnitForm {
	private static Stage stage;
	private static SalesUnitFormController salesUnitFormController;
	
	public SalesUnitForm() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static SalesUnitFormController getSalesUnitFormController() {
		return salesUnitFormController;
	}
	
	public static void setSalesUnitFormController(SalesUnitFormController controller) {
		salesUnitFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/salesUnitForm.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 800, 350);
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
		SalesUnitFormController controller = (SalesUnitFormController) loader.getController();
		setSalesUnitFormController(controller);
		return node;
	}
}
