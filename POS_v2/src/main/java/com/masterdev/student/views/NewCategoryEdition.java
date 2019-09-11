package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.NewCategoryEditionController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewCategoryEdition {
	
	private static Stage stage;
	private static NewCategoryEditionController newCategoryEditionController;
	
	public NewCategoryEdition() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static NewCategoryEditionController getNewCategoryEditionController() {
		return newCategoryEditionController;
	}
	
	public static void setNewCategoryEditionController(NewCategoryEditionController controller) {
		newCategoryEditionController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/newCategoryEdition.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 500, 200);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Nueva categoría");
			getStage().setResizable(false);
			getStage().initModality(Modality.APPLICATION_MODAL);
			getStage().initStyle(StageStyle.DECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		NewCategoryEditionController controller = (NewCategoryEditionController) loader.getController();
		setNewCategoryEditionController(controller);
		return node;
	}
}
