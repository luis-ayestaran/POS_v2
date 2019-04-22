package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.SalesHistoryController;

import javafx.fxml.FXMLLoader;

import javafx.scene.layout.StackPane;

public class SalesHistory {
	
	private static SalesHistoryController salesHistoryController;
	
	public SalesHistory() {}
	
	public static SalesHistoryController getSalesFormController() {
		return salesHistoryController;
	}
	
	public static void setSalesHistoryController(SalesHistoryController controller) {
		salesHistoryController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/salesHistory.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SalesHistoryController controller = (SalesHistoryController) loader.getController();
		setSalesHistoryController(controller);
		return node;
	}
}
