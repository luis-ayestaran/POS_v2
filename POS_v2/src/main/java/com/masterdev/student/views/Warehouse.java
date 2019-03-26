package com.masterdev.student.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

public class Warehouse {
	
	public Warehouse() {}
	
	public Parent showResources() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/warehouse.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return root;
	}
	
}
