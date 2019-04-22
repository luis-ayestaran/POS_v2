package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.DepartmentAddFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DepartmentAddForm {
private static DepartmentAddFormController departmentAddFormController;
	
	public DepartmentAddForm() {}
	
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
			stage.setScene(scene);
			stage.setTitle("POS");
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DepartmentAddFormController controller = (DepartmentAddFormController) loader.getController();
		setDepartmentAddFormController(controller);
		return node;
	}
}
