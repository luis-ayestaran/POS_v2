package com.masterdev.student.views.controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import com.masterdev.student.entities.Product;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.MathematicMethods;
import com.masterdev.student.views.InventoryEditForm;
import com.masterdev.student.views.SalesUnitEdition;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class SalesUnitEditionController implements Initializable {
	
	@FXML private TextField txtWholeQuantity;
	@FXML private TextField txtWholeUnit;
	@FXML private TextField txtWholeUtility;
	@FXML private TextField txtWholePrice;
	@FXML private TextField txtRetailUnit;
	@FXML private TextField txtRetailQuantity;
	@FXML private TextField txtRetailUtility;
	@FXML private TextField txtRetailPrice;
	@FXML private TextField txtPurchaseWholeCost;
	@FXML private TextField txtPurchaseRetailCost;
	
	@FXML private Button btnWholeSaleHelp;
	@FXML private Button btnRetailSaleHelp;
	
	
	//-------------------------------- INITIALISING FIELDS -----------------------------------------
	public void initialize(URL location, ResourceBundle resources) {
		initialiseTxtFields();
		addProductData();
		initialiseTxtFocusListeners();
	}
	
	public void initialiseTxtFields() {
		txtWholeQuantity.setText("1.00");
		/*txtWholeUnit.setText(InventoryAddForm.getInventoryAddFormController().getUnit());
		txtRetailUnit.setText(InventoryAddForm.getInventoryAddFormController().getSubunit());
		txtPurchaseWholeCost.setText(String.format("%.2f", InventoryAddForm.getInventoryAddFormController().getWholeCost()));
		txtPurchaseRetailCost.setText(String.format("%.2f", (InventoryAddForm.getInventoryAddFormController().getWholeCost() / InventoryAddForm.getInventoryAddFormController().getSubunitAmount())));*/
	}
	
	public void addProductData() {
		txtWholeUnit.setText(InventoryEditForm.getInventoryEditFormController().getProduct().getPurchaseUnit());
		txtRetailUnit.setText(InventoryEditForm.getInventoryEditFormController().getProduct().getPurchaseSubunit());
		txtRetailQuantity.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getProduct().getPurchaseSubunitAmount()));
		txtPurchaseWholeCost.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getWholeCost()));
		txtPurchaseRetailCost.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getRetailCost()));
		txtWholeUtility.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getWholeUtility()));
		txtRetailUtility.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getRetailUtility()));
		txtWholePrice.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getWholePrice()));
		txtRetailPrice.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getRetailPrice()));
	}
	
	public void initialiseTxtFocusListeners() {
		txtRetailQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					if(!txtPurchaseWholeCost.getText().trim().equals("")) {
						txtPurchaseRetailCost.setText(String.format("%.2f",  Float.parseFloat(txtPurchaseWholeCost.getText()) / Float.parseFloat(newValue)));
					}
				} else {
					txtPurchaseRetailCost.setText("0.00");
					//amount1000 = 0.0f;
				}
				//setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)SalesUnitEdition.getStage().getScene().getRoot(), txtRetailQuantity);
				txtRetailQuantity.setText(oldValue);
			}
		});
		
		txtPurchaseWholeCost.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					if(!txtRetailQuantity.getText().trim().equals("")) {
						txtPurchaseRetailCost.setText(String.format("%.2f",  Float.parseFloat(newValue) / Float.parseFloat(txtRetailQuantity.getText())));
					}
				} else {
					txtPurchaseRetailCost.setText("0.00");
					//amount1000 = 0.0f;
				}
				//setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)SalesUnitEdition.getStage().getScene().getRoot(), txtPurchaseWholeCost);
				txtPurchaseWholeCost.setText(oldValue);
			}
		});
		
		/*txtWholeUtility.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if(!newValue.trim().equals("")) {
					if(!txtRetailQuantity.getText().equals("")) {
						calculateWholePrice();
					}
				} else {
					txtWholePrice.setText(txtPurchaseWholeCost.getText());
					//amount1000 = 0.0f;
				}
				//setTotal();
			} catch(NumberFormatException e) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de insertar solo números.",
						(StackPane)SalesUnitForm.getStage().getScene().getRoot(), txtPurchaseWholeCost);
				txtPurchaseWholeCost.setText(oldValue);
			}
		});*/
		
		
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
		if(InventoryEditForm.getInventoryEditFormController().getUnit() != null)
			txtWholeUnit.setText(InventoryEditForm.getInventoryEditFormController().getUnit());
		if(InventoryEditForm.getInventoryEditFormController().getSubunit() != null)
			txtRetailUnit.setText(InventoryEditForm.getInventoryEditFormController().getSubunit());
		if(InventoryEditForm.getInventoryEditFormController().getWholeCost() != null)
			txtPurchaseWholeCost.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getWholeCost()));
		if(InventoryEditForm.getInventoryEditFormController().getRetailCost() != null)
			txtPurchaseRetailCost.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getRetailCost()));
		if(InventoryEditForm.getInventoryEditFormController().getWholePrice() != null)
			txtWholePrice.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getWholePrice()));
		if(InventoryEditForm.getInventoryEditFormController().getRetailPrice() != null)
			txtRetailPrice.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getRetailPrice()));
		if(InventoryEditForm.getInventoryEditFormController().getWholeUtility() != null)
			txtWholeUtility.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getWholeUtility()));
		if(InventoryEditForm.getInventoryEditFormController().getRetailUtility() != null)
			txtRetailUtility.setText(String.format("%.2f", InventoryEditForm.getInventoryEditFormController().getRetailUtility()));
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
					(StackPane)SalesUnitEdition.getStage().getScene().getRoot(), txtWholeUtility);
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
					(StackPane)SalesUnitEdition.getStage().getScene().getRoot(), txtWholePrice);
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
					(StackPane)SalesUnitEdition.getStage().getScene().getRoot(), txtRetailUtility);
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
					(StackPane)SalesUnitEdition.getStage().getScene().getRoot(), txtRetailPrice);
		}
	}
	
	@FXML
	protected void calculateRetailCost() {
		
	}
	
	//-------------------------------- HANDLING DATA -----------------------------------------
	
		@FXML
		protected void acceptTransaction() {
			if(fieldsAreFilledUp()) {
				try {
					unhighlightObligatoryFields();
					updateInventoryAddFormData();
					//updateFields();
					SalesUnitEdition.getStage().close();
				} catch (NumberFormatException e) {
					e.printStackTrace();
					Dialogs d = new Dialogs();
					d.acceptDialog("Error de entrada de datos",
							"Asegúrate de haber llenado todos los campos con número.",
							(StackPane)SalesUnitEdition.getStage().getScene().getRoot(), txtWholeUtility);
				}
			} else {
				unhighlightObligatoryFields();
				highlightObligatoryFields();
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al agregar unidad de venta",
						"Asegúrate de haber llenado todos los campos correctamente",
						(StackPane)SalesUnitEdition.getStage().getScene().getRoot(), txtWholeUtility);
			}
		}
		
		public void updateInventoryAddFormData() throws NumberFormatException {
			InventoryEditForm.getInventoryEditFormController().setUnit(txtWholeUnit.getText());
			InventoryEditForm.getInventoryEditFormController().setSubunit(txtRetailUnit.getText());
			InventoryEditForm.getInventoryEditFormController().setSubunitAmount(Float.parseFloat(txtRetailQuantity.getText()));
			InventoryEditForm.getInventoryEditFormController().setWholeCost(Float.parseFloat(txtPurchaseWholeCost.getText()));
			InventoryEditForm.getInventoryEditFormController().setRetailCost(Float.parseFloat(txtPurchaseRetailCost.getText()));
			InventoryEditForm.getInventoryEditFormController().setWholeUtility(Float.parseFloat(txtWholeUtility.getText()));
			InventoryEditForm.getInventoryEditFormController().setRetailUtility(Float.parseFloat(txtRetailUtility.getText()));
			InventoryEditForm.getInventoryEditFormController().setWholePrice(Float.parseFloat(txtWholePrice.getText()));
			InventoryEditForm.getInventoryEditFormController().setRetailPrice(Float.parseFloat(txtRetailPrice.getText()));
			
			InventoryEditForm.getInventoryEditFormController().setTxtSalesUnitContent(txtRetailUnit.getText());
		}
		
		public Boolean fieldsAreFilledUp() {
			if(!txtWholeUnit.getText().equals("") && !txtWholeQuantity.getText().equals("") &&!txtWholeUtility.getText().equals("") && !txtWholePrice.getText().equals("") && !txtRetailUnit.getText().equals("") && !txtRetailQuantity.getText().equals("") && !txtRetailUtility.getText().equals("") && !txtRetailPrice.getText().equals(""))
				return true;
			else
				return false;
		}
	
	@FXML
	protected void cancelTransaction() {
		cancel();
	}
	
	public void cancel() {
		//updateFields();
		closeStageCompletely();
		unhighlightObligatoryFields();
	}
	
	public void closeStageCompletely() {
		if(SalesUnitEdition.getStage() != null) {
			SalesUnitEdition.getStage().close();
			SalesUnitEdition.setStage(null);
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
