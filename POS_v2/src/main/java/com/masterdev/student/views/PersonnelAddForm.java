package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.PersonnelAddFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PersonnelAddForm {

	private static Stage stage;
	private static PersonnelAddFormController personnelAddFormController;
	
	public PersonnelAddForm() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static PersonnelAddFormController getPersonnelAddFormController() {
		return personnelAddFormController;
	}
	
	public static void setPersonnelAddFormController(PersonnelAddFormController controller) {
		personnelAddFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/personnelAddForm.fxml"));
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
		PersonnelAddFormController controller = (PersonnelAddFormController) loader.getController();
		setPersonnelAddFormController(controller);
		return node;
	}
}
