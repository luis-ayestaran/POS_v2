package com.masterdev.student.views;

import java.io.IOException;

import com.masterdev.student.views.controllers.CashCalculatorController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CashCalculator {
	private static Stage stage;
	private static CashCalculatorController cashCalculatorController;
	
	public CashCalculator() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}
	
	public static CashCalculatorController getCashCalculatorController() {
		return cashCalculatorController;
	}
	
	public static void setCashCalculatorController(CashCalculatorController controller) {
		cashCalculatorController = controller;
	}
	
	public StackPane loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/cashCalculator.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 500, 700);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().setTitle("Calculadora de efectivo");
			getStage().setResizable(false);
			getStage().initModality(Modality.APPLICATION_MODAL);
			getStage().initStyle(StageStyle.DECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CashCalculatorController controller = (CashCalculatorController) loader.getController();
		setCashCalculatorController(controller);
		
		
		return node;
	}
	
}
