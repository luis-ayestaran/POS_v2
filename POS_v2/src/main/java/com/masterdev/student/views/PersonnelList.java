package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.PersonnelListController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class PersonnelList {
	
	private static PersonnelListController personnelListController;
	
	public PersonnelList() {}
	
	public static PersonnelListController getPersonnelListController() {
		return personnelListController;
	}
	
	public static void setPersonnelListController(PersonnelListController controller) {
		personnelListController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/personnelList.fxml"));
			node = (StackPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PersonnelListController controller = (PersonnelListController) loader.getController();
		setPersonnelListController(controller);
		return node;
	}
}
