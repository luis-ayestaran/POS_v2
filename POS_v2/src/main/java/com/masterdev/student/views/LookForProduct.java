package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.LookForProductController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LookForProduct {
	private static Stage stage;
	private static LookForProductController LookForProductController;
	
	public LookForProduct() {} 
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static LookForProductController getLookForProductController() {
		return LookForProductController;
	}
	
	public static void setLookForProductController(LookForProductController controller) {
		LookForProductController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/lookForProduct.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 500, 350);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Buscar precios");
			getStage().setResizable(false);
			getStage().initModality(Modality.APPLICATION_MODAL);
			getStage().initStyle(StageStyle.DECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LookForProductController controller = (LookForProductController) loader.getController();
		setLookForProductController(controller);
		return node;
	}
}
