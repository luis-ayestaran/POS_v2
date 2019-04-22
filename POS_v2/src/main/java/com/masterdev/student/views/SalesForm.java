package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.SalesFormController;

import javafx.fxml.FXMLLoader;

import javafx.scene.layout.StackPane;

public class SalesForm {
	
	private static SalesFormController salesFormController;
	
	public SalesForm() {}
	
	public static SalesFormController getSalesFormController() {
		return salesFormController;
	}
	
	public static void setSalesFormController(SalesFormController controller) {
		salesFormController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/salesForm.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SalesFormController controller = (SalesFormController) loader.getController();
		setSalesFormController(controller);
		return node;
	}
}