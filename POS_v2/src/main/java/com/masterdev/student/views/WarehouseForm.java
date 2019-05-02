package com.masterdev.student.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;

import javafx.stage.Stage;

public class WarehouseForm {
	
	private static Stage stage;
	
	public WarehouseForm() {}
	
	public void launchWarehouseForm() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/warehouseForm.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 600);
		
		//We initialise some components
		
		Stage stage = new Stage();
		setStage(stage);
		getStage().setScene(scene);
		getStage().setTitle("QualytHome");
		Image icon = new Image("/stylesheets/images/LOGO.png");
		getStage().getIcons().add(icon);
		getStage().setResizable(false);
		getStage().showAndWait();
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
	
}
