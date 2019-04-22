package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.HomeController;

import javafx.fxml.FXMLLoader;

import javafx.scene.layout.StackPane;

public class Home {
	
	private static HomeController homeController;
	
	public Home() {}
	
	public static HomeController getHomeController() {
		return homeController;
	}
	
	public static void setHomeController(HomeController controller) {
		homeController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HomeController controller = (HomeController) loader.getController();
		setHomeController(controller);
		return node;
	}
}
