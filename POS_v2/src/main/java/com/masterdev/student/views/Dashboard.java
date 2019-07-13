package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.DashboardController;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;

import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;

public class Dashboard {//extends Application{
	
	private static Stage stage;
	private static DashboardController DBController;
	
	public Dashboard() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
	
	public static DashboardController getDashboardController() {
		return DBController;
	}
	
	public static void setDashboardController(DashboardController controller) {
		DBController = controller;
	}
	
	public void launchDashboard() {
		Parent root = null;
		FXMLLoader loader = null;
		DashboardController controller = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
			root = loader.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		controller = (DashboardController) loader.getController();
		setDashboardController(controller);
		
		Scene scene = new Scene(root, 1000, 650); 
		
		//We initialise some components
		
		Stage stage = new Stage();
		setStage(stage);
		
		//SETTING THE SCENE IN THE STAGE
		getStage().setScene(scene);
		getStage().setTitle("POS");
		//getStage().setResizable(false);
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

	    //set Stage boundaries to visible main screen bounds
	    getStage().setX(primaryScreenBounds.getMinX());
	    getStage().setY(primaryScreenBounds.getMinY());
	    getStage().setWidth(primaryScreenBounds.getWidth());
	    getStage().setHeight(primaryScreenBounds.getHeight());
	    Image icon = new Image("/stylesheets/images/LOGO.png");
		getStage().getIcons().add(icon);
	    getStage().setMaximized(true);
	    
		getStage().show();
		
		//Initialises hotkeys
		getDashboardController().setHotkeys();
		
		//Ensures that when we close the app, it wont let active threads running
		getStage().setOnCloseRequest(e -> getDashboardController().closeRequest(e));
		
	}
}
