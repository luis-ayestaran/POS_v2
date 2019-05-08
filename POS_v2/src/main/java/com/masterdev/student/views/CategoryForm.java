package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.CategoryFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CategoryForm {
	
	private static Stage stage;
	private static CategoryFormController categoryFormController;
	
	public CategoryForm() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static CategoryFormController getCategoryFormController() {
		return categoryFormController;
	}
	
	public static void setCategoryFormController(CategoryFormController controller) {
		categoryFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/categoryForm.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 800, 500);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Manejo de categorías de productos");
			getStage().setResizable(false);
			getStage().initModality(Modality.APPLICATION_MODAL);
			getStage().initStyle(StageStyle.DECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CategoryFormController controller = (CategoryFormController) loader.getController();
		setCategoryFormController(controller);
		return node;
	}
}
