package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.DepartmentListController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class DepartmentList {

	private static DepartmentListController departmentListController;
	
	public DepartmentList() {}
	
	public static DepartmentListController getDepartmentListController() {
		return departmentListController;
	}
	
	public static void setDepartmentListController(DepartmentListController controller) {
		departmentListController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/departmentList.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DepartmentListController controller = (DepartmentListController) loader.getController();
		setDepartmentListController(controller);
		return node;
	}
	
}
