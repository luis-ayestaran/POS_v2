package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.DashboardController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;

public class Dashboard {//extends Application{
	
	private static Stage stage;
	
	public Dashboard() {}
	
	public void launchDashboard() {
		Parent root = null;
		DashboardController controller = null;
		FXMLLoader loader = new FXMLLoader();
		try {
			root = loader.load(getClass().getResource("/fxml/dashboard.fxml"));
			controller = (DashboardController) loader.getController();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 1000, 650);
		
		//We initialise some components
		
		Stage stage = new Stage();
		setStage(stage);
		getStage().setScene(scene);
		getStage().setTitle("POS");
		//getStage().setResizable(false);
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

	    //set Stage boundaries to visible main screen bounds
	    getStage().setX(primaryScreenBounds.getMinX());
	    getStage().setY(primaryScreenBounds.getMinY());
	    getStage().setWidth(primaryScreenBounds.getWidth());
	    getStage().setHeight(primaryScreenBounds.getHeight());
	    
		getStage().show();
		
		//Ensures that when we close the app, it wont let active threads running
		getStage().setOnCloseRequest(e -> {
			Platform.exit(); //Shuts down the GUI thread
			System.exit(0); //Exit killing the JVM
		});
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
	
	
}
