package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.HomeController;

import javafx.fxml.FXMLLoader;

import javafx.scene.layout.StackPane;

public class Home {
	
	private static StackPane node;
	private static HomeController homeController;
	
	public Home() {} 
	
	public static StackPane getNode() {
		return node;
	}
	
	public static void setNode(StackPane newNode) {
		node = newNode;
	}
	
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
		setNode(node);
		HomeController controller = (HomeController) loader.getController();
		setHomeController(controller);
		return node;
	}
}
