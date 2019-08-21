package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.views.CashCalculator;
import com.masterdev.student.views.CashierCutOffAdd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class CashCalculatorController implements Initializable {
	
	@FXML TextField txtQuantity1000;
	@FXML Button btnPlus1000;
	@FXML Button btnMinus1000;
	
	@FXML TextField txtQuantity500;
	@FXML Button btnPlus500;
	@FXML Button btnMinus500;
	
	@FXML TextField txtQuantity200;
	@FXML Button btnPlus200;
	@FXML Button btnMinus200;
	
	@FXML TextField txtQuantity100;
	@FXML Button btnPlus100;
	@FXML Button btnMinus100;
	
	@FXML TextField txtQuantity50;
	@FXML Button btnPlus50;
	@FXML Button btnMinus50;
	
	@FXML TextField txtQuantity20;
	@FXML Button btnPlus20;
	@FXML Button btnMinus20;
	
	@FXML TextField txtQuantity10;
	@FXML Button btnPlus10;
	@FXML Button btnMinus10;
	
	@FXML TextField txtQuantity5;
	@FXML Button btnPlus5;
	@FXML Button btnMinus5;
	
	@FXML TextField txtQuantity2;
	@FXML Button btnPlus2;
	@FXML Button btnMinus2;

	@FXML TextField txtQuantity1;
	@FXML Button btnPlus1;
	@FXML Button btnMinus1;
	
	@FXML TextField txtQuantity_50;
	@FXML Button btnPlus_50;
	@FXML Button btnMinus_50;
	
	@FXML TextField txtCalculatedTotal;
	
	@FXML Button btnCancel;
	@FXML Button btnAccept;
	
	private Float amount1000 = 0.0f;
	private Float amount500 = 0.0f;
	private Float amount200 = 0.0f;
	private Float amount100 = 0.0f;
	private Float amount50 = 0.0f;
	private Float amount20 = 0.0f;
	private Float amount10 = 0.0f;
	private Float amount5 = 0.0f;
	private Float amount2 = 0.0f;
	private Float amount1 = 0.0f;
	private Float amount_50 = 0.0f;
	private Float total = 0.0f;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		initialiseListeners();
	}
	
	public void initialiseListeners() {
		txtQuantity1000.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					amount1000 = Float.parseFloat(newValue);
				} else {
					amount1000 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity1000);
				txtQuantity1000.setText(oldValue);
			}
		});
		
		txtQuantity500.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					amount500 = Float.parseFloat(newValue);
				} else {
					amount500 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity500);
				txtQuantity500.setText(oldValue);
			}
		});
		
		txtQuantity200.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					amount200 = Float.parseFloat(newValue);
				} else {
					amount200 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity200);
				txtQuantity200.setText(oldValue);
			}
		});
		
		txtQuantity100.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(newValue.trim().equals("")) {
					amount100 = Float.parseFloat(newValue);
				} else {
					amount100 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity100);
				txtQuantity100.setText(oldValue);
			}
		});
		
		txtQuantity50.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					amount50 = Float.parseFloat(newValue);
				} else {
					amount50 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity50);
				txtQuantity50.setText(oldValue);
			}
		});
		
		txtQuantity20.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					amount20 = Float.parseFloat(newValue);
				} else {
					amount20 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity20);
				txtQuantity20.setText(oldValue);
			}
		});
		
		txtQuantity10.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					amount10 = Float.parseFloat(newValue);
				} else {
					amount10 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity10);
				txtQuantity10.setText(oldValue);
			}
		});
		
		txtQuantity5.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					amount5 = Float.parseFloat(newValue);
				} else {
					amount5 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity5);
				txtQuantity5.setText(oldValue);
			}
		});
		
		txtQuantity2.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					amount2 = Float.parseFloat(newValue);
				} else {
					amount2 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity2);
				txtQuantity2.setText(oldValue);
			}
		});
		
		txtQuantity1.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					amount1 = Float.parseFloat(newValue);
				} else {
					amount1 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity1);
				txtQuantity1.setText(oldValue);
			}
		});
		
		txtQuantity_50.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					amount_50 = Float.parseFloat(newValue);
				} else {
					amount_50 = 0.0f;
				}
				setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)CashCalculator.getStage().getScene().getRoot(), txtQuantity_50);
				txtQuantity_50.setText(oldValue);
			}
		});
	}
	
	private Float calculateTotal() {
		return (float)((amount1000 * 1000) + (amount500 * 500) + (amount200 * 200) + (amount100 * 100) + (amount50 * 50) + (amount20 * 20) + (amount10 * 10) + (amount5 * 5) + (amount2 * 2) + (amount1 * 1) + (amount_50 * 0.50));
	}
	
	private void setTotal() {
		total = calculateTotal();
		txtCalculatedTotal.setText(String.format("%.2f", total));
	}
	
	@FXML
	protected void cancelTransaction() {
		cancel();
	}
	
	public void cancel() {
		closeStageCompletely();
	}
	
	@FXML
	protected void acceptTransaction() {
		accept();
	}
	
	public void accept() {
		CashierCutOffAdd.getCashierCutOffAddController().setAccountedCash(total);
		CashCalculator.getStage().close();
	}
	
	public void closeStageCompletely() {
		if(CashCalculator.getStage() != null) {
			CashCalculator.getStage().close();
			CashCalculator.setStage(null);
		}
	}
}
