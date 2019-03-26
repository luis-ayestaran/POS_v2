package com.masterdev.student.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

public class Personal {
	
	public Personal() {}
	
	public Parent showPersonal() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/personal.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return root;
	}
}
