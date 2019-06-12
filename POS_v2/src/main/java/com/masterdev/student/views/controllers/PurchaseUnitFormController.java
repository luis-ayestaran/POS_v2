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
		initialiseTootipTexts();
	}
	
	public void initialiseTootipTexts() {
		btnPriceHelp.setTooltip(new Tooltip("Costo en el que compras una unidad de producto."));
		btnUnitHelp.setTooltip(new Tooltip("Unidad en la que compras el producto a tu proveedor."));
		btnSubunitHelp.setTooltip(new Tooltip("Unidades más pequeñas, contenidas en la unidad que compras a tu proveedor."));
		btnSubunitAmountHelp.setTooltip(new Tooltip("Cantidad de subunidades por unidad de producto."));
	}
	
	//-------------------------------- HANDLING DATA -----------------------------------------
	
	@FXML
	protected void acceptTransaction() {
		accept();
	}
	
	public void accept() throws NumberFormatException{
		try {
			if(Float.parseFloat(txtCost.getText().trim()) > 0.0F && Float.parseFloat(txtSubunitAmount.getText().trim()) > 0.0F) {
				if(fieldsAreFilledUp()) {
					updateInventoryAddFormData();
					updateFields();
					PurchaseUnitForm.getStage().close();
					if(SalesUnitForm.getStage() != null) {
						SalesUnitForm.getSalesUnitFormController().updateFields();
						if(InventoryAddForm.getInventoryAddFormController().getWholeUtility() != null && InventoryAddForm.getInventoryAddFormController().getRetailUtility() != null) {
							InventoryAddForm.getInventoryAddFormController().setTxtSalesUnitContent(txtSubunit.getText().trim());
							SalesUnitForm.getSalesUnitFormController().calculateWholePrice();
							SalesUnitForm.getSalesUnitFormController().calculateRetailPrice();
							SalesUnitForm.getSalesUnitFormController().updateInventoryAddFormData();
						}
					}
					unhighlightObligatoryFields();
				} else {
					unhighlightObligatoryFields();
					highlightObligatoryTextFields();
					Dialogs d = new Dialogs();
					d.acceptDialog("Error al agregar unidad de compra",
							"Asegúrate de haber llenado todos los campos correctamente.",
							(StackPane)PurchaseUnitForm.getStage().getScene().getRoot());
				}
			} else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al agregar unidad de compra",
						"Los campos \"Costo compra\" y \"Subunidades por unidad\" deben \ncontener una cantidad mayor a 0.",
						(StackPane)PurchaseUnitForm.getStage().getScene().getRoot());
			}
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Dialogs d = new Dialogs();
			d.acceptDialog("Error de entrada de datos",
					"Asegúrate de haber llenado los campos \"Costo compra\" \n y \"Subunidades por unidad\" con número.",
					(StackPane)PurchaseUnitForm.getStage().getScene().getRoot());
		}
	}
	
	public void updateInventoryAddFormData() throws NumberFormatException {
		InventoryAddForm.getInventoryAddFormController().setWholeCost(Float.parseFloat(txtCost.getText().trim()));
		InventoryAddForm.getInventoryAddFormController().setRetailCost(Float.parseFloat(txtCost.getText()) / Float.parseFloat(txtSubunitAmount.getText().trim()));
		InventoryAddForm.getInventoryAddFormController().setUnit(txtUnit.getText().trim());
		InventoryAddForm.getInventoryAddFormController().setSubunit(txtSubunit.getText().trim());
		InventoryAddForm.getInventoryAddFormController().setSubunitAmount(Float.parseFloat(txtSubunitAmount.getText().trim()));
		
		InventoryAddForm.getInventoryAddFormController().enableSalesUnitForm();
		InventoryAddForm.getInventoryAddFormController().setTxtPurchaseUnitContent(txtUnit.getText().trim());
	}
	
	public void updateFields() {
		if(InventoryAddForm.getInventoryAddFormController().getWholeCost() != null)
			txtCost.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getWholeCost()));
		if(InventoryAddForm.getInventoryAddFormController().getUnit() != null)
			txtUnit.setText(InventoryAddForm.getInventoryAddFormController().getUnit());
		if(InventoryAddForm.getInventoryAddFormController().getSubunit() != null)
			txtSubunit.setText(InventoryAddForm.getInventoryAddFormController().getSubunit());
		if(InventoryAddForm.getInventoryAddFormController().getSubunitAmount() != null)
			txtSubunitAmount.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getSubunitAmount()));
	}
	
	public Boolean fieldsAreFilledUp() {
		if(!txtCost.getText().trim().equals("") && !txtUnit.getText().trim().equals("") && !txtSubunit.getText().trim().equals("") && !txtSubunitAmount.getText().trim().equals(""))
			return true;
		else
			return false;
	}
	
	@FXML
	protected void cancelTransaction() {
		cancel();
	}
	
	public void cancel() {
		PurchaseUnitForm.getStage().close();
		unhighlightObligatoryFields();
		updateFields();
	}
	
	public void closeStageCompletely() {
		if(PurchaseUnitForm.getStage() != null) {
			PurchaseUnitForm.getStage().close();
			PurchaseUnitForm.setStage(null);
		}
	}
	
	//Just in case the user didn't notice the obligatory fields sign next to them.
	private void unhighlightObligatoryFields() {
		if(!txtCost.getStyleClass().isEmpty())
			txtCost.getStyleClass().removeAll("important");
		if(!txtUnit.getStyleClass().isEmpty())
			txtUnit.getStyleClass().removeAll("important");
		if(!txtSubunit.getStyleClass().isEmpty())
			txtSubunit.getStyleClass().removeAll("important");
		if(!txtSubunitAmount.getStyleClass().isEmpty())
			txtSubunitAmount.getStyleClass().removeAll("important");
	}
	
	private void highlightObligatoryTextFields() {
		if(txtCost.getText().trim().equals(""))
			txtCost.getStyleClass().add("important");
		if(txtUnit.getText().trim().equals(""))
			txtUnit.getStyleClass().add("important");
		if(txtSubunit.getText().trim().equals(""))
			txtSubunit.getStyleClass().add("important");
		if(txtSubunitAmount.getText().trim().equals(""))
			txtSubunitAmount.getStyleClass().add("important");
		
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
