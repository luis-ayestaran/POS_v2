package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.DepartmentAddFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DepartmentAddForm {
	
	private static Stage stage;
	private static DepartmentAddFormController departmentAddFormController;
	
	public DepartmentAddForm() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static DepartmentAddFormController getDepartmentAddFormController() {
		return departmentAddFormController;
	}
	
	public static void setDepartmentAddFormController(DepartmentAddFormController controller) {
		departmentAddFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/departmentAddForm.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 800, 600);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("POS");
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().setResizable(false);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DepartmentAddFormController controller = (DepartmentAddFormController) loader.getController();
		setDepartmentAddFormController(controller);
		return node;
	}
}
