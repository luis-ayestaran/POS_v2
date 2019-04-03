package com.masterdev.student.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class SignupForm {
	public SignupForm() {}
	
	public Parent show() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/signupForm.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return root;
	}
}
