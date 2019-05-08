package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.views.InventoryAddForm;
import com.masterdev.student.views.PurchaseUnitForm;
import com.masterdev.student.views.SalesUnitForm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;

public class PurchaseUnitFormController implements Initializable {
	
	@FXML TextField txtCost;
	@FXML TextField txtUnit;
	@FXML TextField txtSubunit;
	@FXML TextField txtSubunitAmount;
	
	@FXML JFXButton btnAccept;
	@FXML JFXButton btnCancel;
	
	@FXML Button btnPriceHelp;
	@FXML Button btnUnitHelp;
	@FXML Button btnSubunitHelp;
	@FXML Button btnSubunitAmountHelp;
	
	public void initialize(URL location, ResourceBundle resources) {
		btnPriceHelp.setTooltip(new Tooltip("Costo en el que compras una unidad de producto."));
		btnUnitHelp.setTooltip(new Tooltip("Unidad en la que compras el producto a tu proveedor."));
		btnSubunitHelp.setTooltip(new Tooltip("Unidades más pequeñas, contenidas en la unidad que compras a tu proveedor."));
		btnSubunitAmountHelp.setTooltip(new Tooltip("Cantidad de subunidades por unidad de producto."));
		
	}
	
	//-------------------------------- HANDLING DATA -----------------------------------------
	
	@FXML
	protected void acceptTransaction() {
		if(fieldsAreFilledUp()) {
			try { 
				updateInventoryAddFormData();
				updateFields();
				PurchaseUnitForm.getStage().close();
				if(SalesUnitForm.getSalesUnitFormController() != null)
					SalesUnitForm.getSalesUnitFormController().updateFields();
			} catch(NumberFormatException e) {
				e.printStackTrace();
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de haber llenado los campos \"Costo compra\" \n y \"Subunidades por unidad\" con número.",
						(StackPane)PurchaseUnitForm.getStage().getScene().getRoot());
			}
		} else {
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al agregar unidad de compra",
					"Asegúrate de haber llenado todos los campos correctamente",
					(StackPane)PurchaseUnitForm.getStage().getScene().getRoot());
		}
			 
	}
	
	public void updateInventoryAddFormData() throws NumberFormatException {
		InventoryAddForm.getInventoryAddFormController().setWholeCost(Float.parseFloat(txtCost.getText()));
		InventoryAddForm.getInventoryAddFormController().setRetailCost(Float.parseFloat(txtCost.getText()) / Float.parseFloat(txtSubunitAmount.getText()));
		InventoryAddForm.getInventoryAddFormController().setUnit(txtUnit.getText());
		InventoryAddForm.getInventoryAddFormController().setSubunit(txtSubunit.getText());
		InventoryAddForm.getInventoryAddFormController().setSubunitAmount(Float.parseFloat(txtSubunitAmount.getText()));
		
		InventoryAddForm.getInventoryAddFormController().enableSalesUnitForm();
		InventoryAddForm.getInventoryAddFormController().setTxtPurchaseUnitContent(txtUnit.getText());
	}
	
	public void updateFields() {
		txtCost.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getWholeCost()));
		txtUnit.setText(InventoryAddForm.getInventoryAddFormController().getUnit());
		txtSubunit.setText(InventoryAddForm.getInventoryAddFormController().getSubunit());
		txtSubunitAmount.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getSubunitAmount()));
	}
	
	public Boolean fieldsAreFilledUp() {
		if(!txtCost.getText().equals("") && !txtUnit.getText().equals("") && !txtSubunit.getText().equals("") && !txtSubunitAmount.getText().equals(""))
			return true;
		else
			return false;
	}
	
	@FXML
	protected void cancelTransaction() {
		PurchaseUnitForm.getStage().close();
	}
	
	public void closeStageCompletely() {
		if(PurchaseUnitForm.getStage() != null) {
			PurchaseUnitForm.getStage().close();
			PurchaseUnitForm.setStage(null);
		}
	}
	
	//-------------------------------- HELPING DIALOGS -----------------------------------------
		@FXML
		protected void clickedBtnPriceHelp() {
			Dialogs d = new Dialogs();
			d.acceptDialog("Costo de compra",
					"Costo en el que compras una \nunidad de producto a tu proveedor. \nEjemplo: compras una caja de leche a $150.00. \n\nSOLO ESCRIBE LA CANTIDAD NUMÉRICA (sin $)",
					(StackPane)PurchaseUnitForm.getStage().getScene().getRoot());
		}
		
		@FXML
		protected void clickedBtnUnitHelp() {
			Dialogs d = new Dialogs();
			d.acceptDialog("Unidad de compra",
					"Unidad en la que tu proveedor te abastece \nde producto. \nEjemplo: compras COSTALES de azúcar.",
					(StackPane)PurchaseUnitForm.getStage().getScene().getRoot());
		}
		
		@FXML
		protected void clickedBtnSubunitHelp() {
			Dialogs d = new Dialogs();
			d.acceptDialog("Subunidad de compra",
					"Unidades más pequeñas, contenidas en la \nunidad que compras a tu proveedor. \nEjemplo: compras cajas de galletas \nque contienen PAQUETES.", 
					(StackPane)PurchaseUnitForm.getStage().getScene().getRoot());
		}
		
		@FXML
		protected void clickedBtnSubunitAmountHelp() {
			Dialogs d = new Dialogs();
			d.acceptDialog("Subunidades por unidad",
					"Cantidad de subunidades que contiene la \nunidad de compra. \nEjemplo: una caja de leche contiene 12 piezas.",
					(StackPane)PurchaseUnitForm.getStage().getScene().getRoot());
		}
		
		@FXML
		protected void clickedBtnAccountingHelp() {
			Dialogs d = new Dialogs();
			d.acceptDialog("Contabilidad",
					"Estos datos nos servirán para una mejor administración de tus finanzas, realizar cálculo de impuestos, etc.",
					(StackPane)PurchaseUnitForm.getStage().getScene().getRoot());
		}
	
}
