package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.SplashScreenController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreen extends Application{
	private static Stage stage;
	private static SplashScreenController splashScreenController;
	
	public SplashScreen() {}
	
	public void launchView(String args[]) {
		launch(SplashScreen.class,args);
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static SplashScreenController getSplashScreenController() {
		return splashScreenController;
	}
	
	public static void setSplashScreenController(SplashScreenController controller) {
		splashScreenController = controller;
	}

	
	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/splashScreen.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 750, 500);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().initStyle(StageStyle.UNDECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SplashScreenController controller = (SplashScreenController) loader.getController();
		setSplashScreenController(controller);
	}
	
	public static void forceStop() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                closeWindow();
            }
        });
    }
	
	public static void closeWindow() {
		if(Dashboard.getStage() != null) {
			 if(InventoryAddForm.getStage() != null) {
				 if(InventoryAddForm.getInventoryAddFormController().formHasInformation()) {
					 InventoryAddForm.getInventoryAddFormController().exitView();
				 }
			 }
		 }
		Platform.exit();
		System.exit(0);
	}
	
	@Override
    public void stop() throws Exception {
		 if(Dashboard.getStage() != null) {
			 if(InventoryAddForm.getStage() != null) {
				 if(InventoryAddForm.getInventoryAddFormController().formHasInformation()) {
					 InventoryAddForm.getInventoryAddFormController().exitView();
				 }
			 }
		 }
		 Platform.exit();
		 System.exit(0);
    }
}
