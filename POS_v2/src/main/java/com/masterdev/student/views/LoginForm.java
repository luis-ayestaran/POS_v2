package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.LoginFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class LoginForm {
	
	private static StackPane node;
	private static LoginFormController loginFormController;
	
	public LoginForm() {}
	
	public static StackPane getNode() {
		return node;
	}
	
	public static void setNode(StackPane newNode) {
		node = newNode;
	}
	
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
		setNode(node);
		LoginFormController controller = (LoginFormController) loader.getController();
		setLoginFormController(controller);
		return node;
	}
}
