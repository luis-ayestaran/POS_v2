package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.MathematicMethods;
import com.masterdev.student.views.InventoryAddForm;
import com.masterdev.student.views.PurchaseUnitForm;
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
		txtWholeUnit.setText(InventoryAddForm.getInventoryAddFormController().getUnit());
		txtRetailUnit.setText(InventoryAddForm.getInventoryAddFormController().getSubunit());
		txtPurchaseWholeCost.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getWholeCost()));
		txtPurchaseRetailCost.setText(String.format("%.2f", (InventoryAddForm.getInventoryAddFormController().getWholeCost() / InventoryAddForm.getInventoryAddFormController().getSubunitAmount())));
		
		addTxtFocusListeners();
	}
	
	public void addTxtFocusListeners() {
		txtWholeUtility.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if(!newPropertyValue)
		        {
		        	if(!txtWholeUtility.getText().equals(""))
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
		        	if(!txtWholePrice.getText().equals(""))
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
		        	if(!txtRetailUtility.getText().equals(""))
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
		        	if(!txtRetailPrice.getText().equals(""))
		        		calculateRetailUtility();
		        }
		    }
		});
	}
	
	//-------------------------------- UPDATING FIELDS (if necessary) -----------------------------------------
	public void updateFields() {
		txtPurchaseWholeCost.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getWholeCost()));
		txtWholeUnit.setText(InventoryAddForm.getInventoryAddFormController().getUnit());
		txtRetailUnit.setText(InventoryAddForm.getInventoryAddFormController().getSubunit());
	}
	
	//-------------------------------- MATHEMATIC METHODS -----------------------------------------
	@FXML
	protected void calculateWholePrice() {
		try {
			float wholePurchaseCost = Float.parseFloat(txtPurchaseWholeCost.getText());
			float wholeUtility = Float.parseFloat(txtWholeUtility.getText());
			MathematicMethods maths = new MathematicMethods();
			float wholePrice = maths.calculatePrice(wholePurchaseCost, wholeUtility);
			txtWholePrice.setText(String.format("%.2f", wholePrice));
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Dialogs d = new Dialogs();
			d.acceptDialog("Error de entrada de datos",
					"Asegúrate de haber llenado todos los campos con número.",
					(StackPane)SalesUnitForm.getStage().getScene().getRoot());
		}
	}
	
	@FXML
	protected void calculateWholeUtility() {
		try {
			float wholePurchaseCost = Float.parseFloat(txtPurchaseWholeCost.getText());
			System.out.println(wholePurchaseCost);
			float wholePrice = Float.parseFloat(txtWholePrice.getText());
			MathematicMethods maths = new MathematicMethods();
			float wholeUtility = maths.calculateUtility(wholePurchaseCost, wholePrice);
			txtWholeUtility.setText(String.format("%.2f", wholeUtility));
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Dialogs d = new Dialogs();
			d.acceptDialog("Error de entrada de datos",
					"Asegúrate de haber llenado todos los campos con número.",
					(StackPane)SalesUnitForm.getStage().getScene().getRoot());
		}
	}
	
	@FXML
	public void calculateRetailPrice() {
		try {
			float retailPurchaseCost = Float.parseFloat(txtPurchaseRetailCost.getText());
			float retailUtility = Float.parseFloat(txtRetailUtility.getText());
			MathematicMethods maths = new MathematicMethods();
			float retailPrice = maths.calculatePrice(retailPurchaseCost, retailUtility);
			txtRetailPrice.setText(String.format("%.2f", retailPrice));
		} catch(NumberFormatException e) {
			e.printStackTrace();
			Dialogs d = new Dialogs();
			d.acceptDialog("Error de entrada de datos",
					"Asegúrate de haber llenado todos los campos con número.",
					(StackPane)SalesUnitForm.getStage().getScene().getRoot());
		}
	}
	
	@FXML
	public void calculateRetailUtility() {
		try {
			float retailPurchaseCost = Float.parseFloat(txtPurchaseRetailCost.getText());
			float retailPrice = Float.parseFloat(txtRetailPrice.getText());
			MathematicMethods maths = new MathematicMethods();
			float retailUtility = maths.calculateUtility(retailPurchaseCost, retailPrice);
			txtRetailUtility.setText(String.format("%.2f", retailUtility));
		} catch(NumberFormatException e) {
			e.printStackTrace();
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
		closeStageCompletely();
	}
	
	public void closeStageCompletely() {
		if(SalesUnitForm.getStage() != null) {
			SalesUnitForm.getStage().close();
			SalesUnitForm.setStage(null);
		}
	}
	
}
