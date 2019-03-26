package com.masterdev.student.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

public class Home {
	
	public Home() {}
	
	public Parent showHome() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return root;
	}
}
