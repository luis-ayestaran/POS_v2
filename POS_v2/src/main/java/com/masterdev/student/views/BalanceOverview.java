package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.BalanceOverviewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class BalanceOverview {
	
	private static StackPane node;
	private static BalanceOverviewController balanceOverviewController;
	
	public BalanceOverview() {}
	
	public static StackPane getNode() {
		return node;
	}
	
	public static void setNode(StackPane newNode) {
		node = newNode;
	}
	
	public static BalanceOverviewController getBalanceOverviewController() {
		return balanceOverviewController;
	}
	
	public static void setBalanceOverviewController(BalanceOverviewController controller) {
		balanceOverviewController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/balanceOverview.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setNode(node);
		BalanceOverviewController controller = (BalanceOverviewController) loader.getController();
		setBalanceOverviewController(controller);
		return node;
	}
}
