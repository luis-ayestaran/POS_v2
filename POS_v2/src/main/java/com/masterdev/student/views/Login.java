package com.masterdev.student.views;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.stage.Stage;

public class Login extends Application{
	
	private static Stage stage;
	
	public Login() {}
	
	public void launchLogin(String args[]) {
		launch(Login.class,args);
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
	
	@Override
	public void start(Stage primaryStage) {
		setStage(primaryStage);
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root, 800, 500);
		
		getStage().setScene(scene);
		getStage().setTitle("POS");
		getStage().setResizable(false);
		getStage().show();
	}
}

