package com.masterdev.student.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
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
		stage.setScene(scene);
		stage.setTitle("QualytHome");
		stage.setResizable(false);
		stage.showAndWait();
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
	
}
