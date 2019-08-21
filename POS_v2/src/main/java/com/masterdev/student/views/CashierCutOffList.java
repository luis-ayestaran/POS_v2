package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.CashierCutOffListController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class CashierCutOffList {
	private static StackPane node;
	private static CashierCutOffListController cashierCutOffListController;
	
	public CashierCutOffList() {}
	
	public static StackPane getNode() {
		return node;
	}
	
	public static void setNode(StackPane newNode) {
		node = newNode;
	}
	
	public static CashierCutOffListController getCashierCutOffListController() {
		return cashierCutOffListController;
	}
	
	public static void setCashierCutOffListController(CashierCutOffListController controller) {
		cashierCutOffListController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/cashierCutOffList.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setNode(node);
		CashierCutOffListController controller = (CashierCutOffListController) loader.getController();
		setCashierCutOffListController(controller);
		return node;
	}
}
