package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.LoginController;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;

import javafx.stage.Stage;

public class Login {//extends Application{
	
	private static Stage stage;
	private static LoginController loginController;
	
	public Login() {}
	
	/*public void launchLogin(String args[]) {
		launch(Login.class,args);
	}*/
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static LoginController getLoginController() {
		return loginController;
	}
	
	public static void setLoginController(LoginController controller) {
		loginController = controller;
	}
	
	//@Override
	public void loadView() {//start(Stage primaryStage) {
		FXMLLoader loader = null;
		Parent root = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
			root = loader.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
		LoginController controller = (LoginController) loader.getController();
		setLoginController(controller);
		
		Scene scene = new Scene(root, 800, 490);
		Stage stage = new Stage();
		setStage(stage);
		getStage().setScene(scene);
		getStage().setTitle("POS");
		Image icon = new Image("/stylesheets/images/LOGO.png");
		getStage().getIcons().add(icon);
		getStage().setResizable(false);
		getStage().show();
	}
	
	
}

