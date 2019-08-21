package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.CashierCutOffAddController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CashierCutOffAdd {
	
	private static Stage stage;
	private static CashierCutOffAddController cashierCutOffAddController;
	
	public CashierCutOffAdd() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static CashierCutOffAddController getCashierCutOffAddController() {
		return cashierCutOffAddController;
	}
	
	public static void setCashierCutOffAddController(CashierCutOffAddController controller) {
		cashierCutOffAddController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/cashierCutOffAdd.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 1000, 550);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Registro de productos");
			getStage().setResizable(false);
			getStage().initModality(Modality.APPLICATION_MODAL);
			getStage().initStyle(StageStyle.DECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CashierCutOffAddController controller = (CashierCutOffAddController) loader.getController();
		setCashierCutOffAddController(controller);
		
		
		return node;
	}
	
}
