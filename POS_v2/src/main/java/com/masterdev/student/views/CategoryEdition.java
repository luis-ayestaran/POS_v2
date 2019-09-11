package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.CategoryEditionController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CategoryEdition {

	private static Stage stage;
	private static CategoryEditionController categoryEditionController;
	
	public CategoryEdition() {} 
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static CategoryEditionController getCategoryEditionController() {
		return categoryEditionController;
	}
	
	public static void setCategoryEditionController(CategoryEditionController controller) {
		categoryEditionController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/categoryEdition.fxml"));
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
		CategoryEditionController controller = (CategoryEditionController) loader.getController();
		setCategoryEditionController(controller);
		return node;
	}
	
}
