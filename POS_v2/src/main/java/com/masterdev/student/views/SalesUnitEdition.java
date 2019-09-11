package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.SalesUnitEditionController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SalesUnitEdition {
	private static Stage stage;
	private static SalesUnitEditionController salesUnitEditionController;
	
	public SalesUnitEdition() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static SalesUnitEditionController getSalesUnitEditionController() {
		return salesUnitEditionController;
	}
	
	public static void setSalesUnitEditionController(SalesUnitEditionController controller) {
		salesUnitEditionController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/salesUnitEdition.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 900, 350);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Editar unidad de compra");
			getStage().setResizable(false);
			getStage().initModality(Modality.APPLICATION_MODAL);
			getStage().initStyle(StageStyle.DECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SalesUnitEditionController controller = (SalesUnitEditionController) loader.getController();
		setSalesUnitEditionController(controller);
		
		getStage().setOnCloseRequest(e -> {
			getSalesUnitEditionController().cancel();
		});
		return node;
	}
}
