package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.ServicesListController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class ServicesList {
	private static StackPane node;
	private static ServicesListController servicesListController;
	
	public ServicesList() {}
	
	/*public void launchLogin(String args[]) {
		launch(Login.class,args);
	}*/
	
	public static StackPane getNode() {
		return node;
	}
	
	public static void setNode(StackPane newNode) {
		node = newNode;
	}
	
	public static ServicesListController getServicesListController() {
		return servicesListController;
	}
	
	public static void setServicesListController(ServicesListController controller) {
		servicesListController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/servicesList.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setNode(node);
		ServicesListController controller = (ServicesListController) loader.getController();
		setServicesListController(controller);
		return node;
	}
}
