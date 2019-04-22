package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.LoginFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class LoginForm {
	
	private static LoginFormController loginFormController;
	
	public LoginForm() {}
	
	public static LoginFormController getLoginFormController() {
		return loginFormController;
	}
	
	public static void setLoginFormController(LoginFormController controller) {
		loginFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/loginForm.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LoginFormController controller = (LoginFormController) loader.getController();
		setLoginFormController(controller);
		return node;
	}
}
