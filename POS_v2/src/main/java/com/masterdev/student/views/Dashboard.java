package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.DashboardController;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;

import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;

public class Dashboard {//extends Application{
	
	private static Stage stage;
	private DashboardController DBController;
	
	public Dashboard() {}
	
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
		setDBController(controller);
		
		Scene scene = new Scene(root, 1000, 650);
		
		//We initialise some components
		
		Stage stage = new Stage();
		setStage(stage);
		
		//Adding hotkeys (keyboard shortcuts) to the app 
		final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.V, KeyCombination.ALT_DOWN);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (keyComb1.match(event)) {
					setMnemonic();
				}
			}
		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			 	public void handle(final KeyEvent keyEvent) {
			 		if (keyEvent.getCode() == KeyCode.F5) {
			 			//controller.loadSalesFormView();
			 			//Stop letting it do anything else
			 			keyEvent.consume();
			   	}
			 }
		});
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
	
	public DashboardController getDBController() {
		return DBController;
	}
	
	public void setDBController(DashboardController DBController) {
		this.DBController = DBController;
	}
	
	public void setMnemonic() {
		this.getDBController().getHomeController().animationWithoutSubmenu();
	}
}
