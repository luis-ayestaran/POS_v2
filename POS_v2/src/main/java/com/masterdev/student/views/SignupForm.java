package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.SignupFormController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class SignupForm {
	
	private static SignupFormController signupFormController;
	
	public SignupForm() {}
	
	public static SignupFormController getSignupFormController() {
		return signupFormController;
	}
	
	public static void setSignupFormController(SignupFormController controller) {
		signupFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/signupForm.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SignupFormController controller = (SignupFormController) loader.getController();
		setSignupFormController(controller);
		return node;
	}
}
