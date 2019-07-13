package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.SalesWaitingListController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SalesWaitingList {
	
	private static Stage stage;
	private static SalesWaitingListController salesWaitingListController;
	
	public SalesWaitingList() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static SalesWaitingListController getSalesWaitingListController() {
		return salesWaitingListController;
	}
	
	public static void setSalesWaitingListController(SalesWaitingListController controller) {
		salesWaitingListController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/salesWaitingList.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 800, 500);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Ventas en espera");
			getStage().setResizable(false);
			getStage().initModality(Modality.APPLICATION_MODAL);
			getStage().initStyle(StageStyle.DECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SalesWaitingListController controller = (SalesWaitingListController) loader.getController();
		setSalesWaitingListController(controller);
		return node;
	}
}