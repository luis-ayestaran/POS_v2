package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.EditCategoryEditionController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditCategoryEdition {
	private static Stage stage;
	private static EditCategoryEditionController editCategoryEditionController;
	
	public EditCategoryEdition() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static EditCategoryEditionController getEditCategoryEditionController() {
		return editCategoryEditionController;
	}
	
	public static void setEditCategoryEditionController(EditCategoryEditionController controller) {
		editCategoryEditionController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/editCategoryEdition.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 500, 250);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Editar categoría");
			getStage().setResizable(false);
			getStage().initModality(Modality.APPLICATION_MODAL);
			getStage().initStyle(StageStyle.DECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditCategoryEditionController controller = (EditCategoryEditionController) loader.getController();
		setEditCategoryEditionController(controller);
		return node;
	}
}
