package com.masterdev.student.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.stage.Stage;


public class DepartmentForm {
	public DepartmentForm() {}
	
	public void launchDepartmentForm() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/departmentForm.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 600);
		
		//We initialise some components
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("QualytHome");
		stage.setResizable(false);
		stage.showAndWait();
	}
}
