package com.masterdev.student.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoginForm {
	
	public LoginForm() {}
	
	public Parent show() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/loginForm.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return root;
	}
}
