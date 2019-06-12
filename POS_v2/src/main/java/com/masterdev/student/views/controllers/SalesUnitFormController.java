package com.masterdev.student.views.controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.MathematicMethods;
import com.masterdev.student.views.InventoryAddForm;
import com.masterdev.student.views.SalesUnitForm;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class SalesUnitFormController implements Initializable {
	
	@FXML private TextField txtWholeUnit;
	@FXML private TextField txtWholeUtility;
	@FXML private TextField txtWholePrice;
	@FXML private TextField txtRetailUnit;
	@FXML private TextField txtRetailUtility;
	@FXML private TextField txtRetailPrice;
	@FXML private TextField txtPurchaseWholeCost;
	@FXML private TextField txtPurchaseRetailCost;
	
	@FXML private Button btnWholeSaleHelp;
	@FXML private Button btnRetailSaleHelp;
	
	
	//-------------------------------- INITIALISING FIELDS -----------------------------------------
	public void initialize(URL location, ResourceBundle resources) {
		initialiseTxtFields();
		initialiseTxtFocusListeners();
	}
	
	public void initialiseTxtFields() {
		txtWholeUnit.setText(InventoryAddForm.getInventoryAddFormController().getUnit());
		txtRetailUnit.setText(InventoryAddForm.getInventoryAddFormController().getSubunit());
		txtPurchaseWholeCost.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getWholeCost()));
		txtPurchaseRetailCost.setText(String.format("%.2f", (InventoryAddForm.getInventoryAddFormController().getWholeCost() / InventoryAddForm.getInventoryAddFormController().getSubunitAmount())));
	}
	
	public void initialiseTxtFocusListeners() {
		txtWholeUtility.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if(!newPropertyValue)
		        {
		        	if(!txtWholeUtility.getText().trim().equals(""))
		        		calculateWholePrice();
		        }
		    }
		});
		
		txtWholePrice.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if(!newPropertyValue)
		        {
		        	if(!txtWholePrice.getText().trim().equals(""))
		        		calculateWholeUtility();
		        }
		    }
		});
		
		txtRetailUtility.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if(!newPropertyValue)
		        {
		        	if(!txtRetailUtility.getText().trim().equals(""))
		        		calculateRetailPrice();
		        }
		    }
		});
		
		txtRetailPrice.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if(!newPropertyValue)
		        {
		        	if(!txtRetailPrice.getText().trim().equals(""))
		        		calculateRetailUtility();
		        }
		    }
		});
	}
	
	//-------------------------------- UPDATING FIELDS (if necessary) -----------------------------------------
	public void updateFields() {
		if(InventoryAddForm.getInventoryAddFormController().getUnit() != null)
			txtWholeUnit.setText(InventoryAddForm.getInventoryAddFormController().getUnit());
		if(InventoryAddForm.getInventoryAddFormController().getSubunit() != null)
			txtRetailUnit.setText(InventoryAddForm.getInventoryAddFormController().getSubunit());
		if(InventoryAddForm.getInventoryAddFormController().getWholeCost() != null)
			txtPurchaseWholeCost.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getWholeCost()));
		if(InventoryAddForm.getInventoryAddFormController().getRetailCost() != null)
			txtPurchaseRetailCost.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getRetailCost()));
		if(InventoryAddForm.getInventoryAddFormController().getWholePrice() != null)
			txtWholePrice.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getWholePrice()));
		if(InventoryAddForm.getInventoryAddFormController().getRetailPrice() != null)
			txtRetailPrice.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getRetailPrice()));
		if(InventoryAddForm.getInventoryAddFormController().getWholeUtility() != null)
			txtWholeUtility.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getWholeUtility()));
		if(InventoryAddForm.getInventoryAddFormController().getRetailUtility() != null)
			txtRetailUtility.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getRetailUtility()));
	}
	
	//-------------------------------- MATHEMATIC METHODS -----------------------------------------
	@FXML
	protected void calculateWholePrice() {
		try {
			Double wholePurchaseCost = Double.parseDouble(txtPurchaseWholeCost.getText().trim());
			Double wholeUtility = Double.parseDouble(txtWholeUtility.getText().trim());
			MathematicMethods maths = new MathematicMethods();
			BigDecimal wholePrice = maths.calculatePrice(wholePurchaseCost, wholeUtility);
			txtWholePrice.setText(String.valueOf(wholePrice)/*String.format("%.2f", wholePrice)*/);
		} catch(NumberFormatException e) {
			Dialogs d = new Dialogs();
			d.acceptDialog("Error de entrada de datos",
					"Asegúrate de haber llenado todos los campos con número.",
					(StackPane)SalesUnitForm.getStage().getScene().getRoot());
		}
	}
	
	@FXML
	protected void calculateWholeUtility() {
		try {
			Double wholePurchaseCost = Double.parseDouble(txtPurchaseWholeCost.getText().trim());
			Double wholePrice = Double.parseDouble(txtWholePrice.getText().trim());
			MathematicMethods maths = new MathematicMethods();
			BigDecimal wholeUtility = maths.calculateUtility(wholePurchaseCost, wholePrice);
			txtWholeUtility.setText(String.valueOf(wholeUtility)/*String.format("%.2f", wholeUtility)*/);
		} catch(NumberFormatException e) {
			Dialogs d = new Dialogs();
			d.acceptDialog("Error de entrada de datos",
					"Asegúrate de haber llenado todos los campos con número.",
					(StackPane)SalesUnitForm.getStage().getScene().getRoot());
		}
	}
	
	@FXML
	public void calculateRetailPrice() {
		try {
			Double retailPurchaseCost = Double.parseDouble(txtPurchaseRetailCost.getText().trim());
			Double retailUtility = Double.parseDouble(txtRetailUtility.getText().trim());
			MathematicMethods maths = new MathematicMethods();
			BigDecimal retailPrice = maths.calculatePrice(retailPurchaseCost, retailUtility);
			txtRetailPrice.setText(String.valueOf(retailPrice)/*String.format("%.2f", retailPrice)*/);
		} catch(NumberFormatException e) {
			Dialogs d = new Dialogs();
			d.acceptDialog("Error de entrada de datos",
					"Asegúrate de haber llenado todos los campos con número.",
					(StackPane)SalesUnitForm.getStage().getScene().getRoot());
		}
	}
	
	@FXML
	public void calculateRetailUtility() {
		try {
			Double retailPurchaseCost = Double.parseDouble(txtPurchaseRetailCost.getText().trim());
			Double retailPrice = Double.parseDouble(txtRetailPrice.getText().trim());
			MathematicMethods maths = new MathematicMethods();
			BigDecimal retailUtility = maths.calculateUtility(retailPurchaseCost, retailPrice);
			txtRetailUtility.setText(String.valueOf(retailUtility)/*String.format("%.2f", retailUtility)*/);
		} catch(NumberFormatException e) {
			Dialogs d = new Dialogs();
			d.acceptDialog("Error de entrada de datos",
					"Asegúrate de haber llenado todos los campos con número.",
					(StackPane)SalesUnitForm.getStage().getScene().getRoot());
		}
	}
	
	//-------------------------------- HANDLING DATA -----------------------------------------
	
		@FXML
		protected void acceptTransaction() {
			if(fieldsAreFilledUp()) {
				try {
					unhighlightObligatoryFields();
					updateInventoryAddFormData();
					updateFields();
					SalesUnitForm.getStage().close();
				} catch (NumberFormatException e) {
					e.printStackTrace();
					Dialogs d = new Dialogs();
					d.acceptDialog("Error de entrada de datos",
							"Asegúrate de haber llenado todos los campos con número.",
							(StackPane)SalesUnitForm.getStage().getScene().getRoot());
				}
			} else {
				unhighlightObligatoryFields();
				highlightObligatoryFields();
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al agregar unidad de venta",
						"Asegúrate de haber llenado todos los campos correctamente",
						(StackPane)SalesUnitForm.getStage().getScene().getRoot());
			}
				 
		}
		
		public void updateInventoryAddFormData() throws NumberFormatException {
			InventoryAddForm.getInventoryAddFormController().setWholeUtility(Float.parseFloat(txtWholeUtility.getText()));
			InventoryAddForm.getInventoryAddFormController().setRetailUtility(Float.parseFloat(txtRetailUtility.getText()));
			InventoryAddForm.getInventoryAddFormController().setWholePrice(Float.parseFloat(txtWholePrice.getText()));
			InventoryAddForm.getInventoryAddFormController().setRetailPrice(Float.parseFloat(txtRetailPrice.getText()));
			
			InventoryAddForm.getInventoryAddFormController().setTxtSalesUnitContent(txtRetailUnit.getText());
		}
		
		public Boolean fieldsAreFilledUp() {
			if(!txtWholeUnit.getText().equals("") && !txtWholeUtility.getText().equals("") && !txtWholePrice.getText().equals("") && !txtRetailUnit.getText().equals("") && !txtRetailUtility.getText().equals("") && !txtRetailPrice.getText().equals(""))
				return true;
			else
				return false;
		}
	
	@FXML
	protected void cancelTransaction() {
		cancel();
	}
	
	public void cancel() {
		updateFields();
		SalesUnitForm.getStage().close();
		unhighlightObligatoryFields();
	}
	
	public void closeStageCompletely() {
		if(SalesUnitForm.getStage() != null) {
			SalesUnitForm.getStage().close();
			SalesUnitForm.setStage(null);
		}
	}
	
	//Just in case the user didn't notice the obligatory fields sign next to them.
		private void unhighlightObligatoryFields() {
			if(!txtWholePrice.getStyleClass().isEmpty())
				txtWholePrice.getStyleClass().removeAll("important");
			if(!txtRetailPrice.getStyleClass().isEmpty())
				txtRetailPrice.getStyleClass().removeAll("important");
			if(!txtWholeUtility.getStyleClass().isEmpty())
				txtWholeUtility.getStyleClass().removeAll("important");
			if(!txtRetailUtility.getStyleClass().isEmpty())
				txtRetailUtility.getStyleClass().removeAll("important");
		}
		
		private void highlightObligatoryFields() {
			if(txtWholePrice.getText().trim().equals(""))
				txtWholePrice.getStyleClass().add("important");
			if(txtRetailPrice.getText().trim().equals(""))
				txtRetailPrice.getStyleClass().add("important");
			if(txtWholeUtility.getText().trim().equals(""))
				txtWholeUtility.getStyleClass().add("important");
			if(txtRetailUtility.getText().trim().equals(""))
				txtRetailUtility.getStyleClass().add("important");
			
		}
	
}
