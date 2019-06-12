package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.EditCategoryFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditCategoryForm {
	private static Stage stage;
	private static EditCategoryFormController editCategoryFormController;
	
	public EditCategoryForm() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static EditCategoryFormController getEditCategoryFormController() {
		return editCategoryFormController;
	}
	
	public static void setEditCategoryFormController(EditCategoryFormController controller) {
		editCategoryFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/editCategoryForm.fxml"));
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
		EditCategoryFormController controller = (EditCategoryFormController) loader.getController();
		setEditCategoryFormController(controller);
		return node;
	}
}
