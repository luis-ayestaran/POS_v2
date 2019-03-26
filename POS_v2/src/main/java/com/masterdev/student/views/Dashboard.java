package com.masterdev.student.views;

import java.io.IOException;

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
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
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

	    //set Stage boundaries to visible bounds of the main screen
	    getStage().setX(primaryScreenBounds.getMinX());
	    getStage().setY(primaryScreenBounds.getMinY());
	    getStage().setWidth(primaryScreenBounds.getWidth());
	    getStage().setHeight(primaryScreenBounds.getHeight());
	    
		getStage().show();
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
	
	/*
	 * @Override
	 * public void start(Stage primaryStage) {
		setStage(primaryStage);
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 480);
		
		getStage().setScene(scene);
		getStage().setTitle("POS");
		getStage().setResizable(false);
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

	    //set Stage boundaries to visible bounds of the main screen
	    getStage().setX(primaryScreenBounds.getMinX());
	    getStage().setY(primaryScreenBounds.getMinY());
	    getStage().setWidth(primaryScreenBounds.getWidth());
	    getStage().setHeight(primaryScreenBounds.getHeight());
	    
		getStage().show();
	}
	 */
}
