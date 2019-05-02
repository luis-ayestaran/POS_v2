package com.masterdev.student.views.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.masterdev.student.views.InventoryAddForm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import com.masterdev.student.views.PurchaseUnitForm;
import com.masterdev.student.views.SalesUnitForm;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.FlagHandling;

public class InventoryAddFormController implements Initializable {
	
	@FXML TextField txtObligatory;
	
	@FXML TextField txtBarcode;
	@FXML TextField txtInnerKey;
	@FXML TextField txtName;
	
	@FXML ComboBox<String> cmbxCategory;
	@FXML Button btnCategorySearch;
	
	@FXML TextField txtBrand;
	@FXML TextField txtContent;
	
	@FXML TextField txtBatch;
	@FXML Button btnBatchSearch;
	
	@FXML TextField txtExistence;
	
	@FXML JFXDatePicker dtPckrEntryDate;
	@FXML JFXDatePicker dtPckrExpireDate;
	
	@FXML TextField txtMinStock;
	@FXML TextField txtMaxStock;
	
	@FXML TextField txtPurchaseUnit;
	@FXML Button btnPurchaseUnitAdd;
	
	@FXML TextField txtSalesUnit;
	@FXML Button btnSaleUnitAdd;
	
	@FXML Button btnSalePriceUnit;
	@FXML TextField txtSalePrice;
	
	@FXML JFXCheckBox chckbxInBulk;
	
	@FXML JFXButton btnAccept;
	@FXML JFXButton btnCancel;
	
	@FXML Button btnSearchHelp;
	@FXML Button btnInventoryHelp;
	@FXML Button btnSalesHelp;
	@FXML Button btnImageHelp;
	@FXML Button btnAccountingHelp;
	
	@FXML BorderPane brdrPnDragAndDrop;
	@FXML ImageView imgVwProduct;
	
	private FlagHandling salesUnitFlag;
	private Float wholeCost;
	private Float retailCost;
	private String unit;
	private String subunit;
	private Float subunitAmount;
	private Float wholeUtility;
	private Float retailUtility;
	private Float wholePrice;
	private Float retailPrice;
	
	
	//------------------------------- GETTING AND SETTING METHODS -------------------------------------------
	public Float getWholeCost() {
		return wholeCost;
	}
	
	public void setWholeCost(Float wholeCost) {
		this.wholeCost = wholeCost;
	}
	
	public Float getRetailCost() {
		return retailCost;
	}
	
	public void setRetailCost(Float retailCost) {
		this.retailCost = retailCost;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getSubunit() {
		return subunit;
	}
	
	public void setSubunit(String subunit) {
		this.subunit = subunit;
	}
	
	public Float getSubunitAmount() {
		return subunitAmount;
	}
	
	public void setSubunitAmount(Float subunitAmount) {
		this.subunitAmount = subunitAmount;
	}
	
	public Float getWholeUtility() {
		return wholeUtility;
	}
	
	public void setWholeUtility(Float wholeUtility) {
		this.wholeUtility = wholeUtility;
	}
	
	public Float getRetailUtility() {
		return retailUtility;
	}
	
	public void setRetailUtility(Float retailUtility) {
		this.retailUtility = retailUtility;
	}
	
	public Float getWholePrice() {
		return wholePrice;
	}
	
	public void setWholePrice(Float wholePrice) {
		this.wholePrice = wholePrice;
	}
	
	public Float getRetailPrice() {
		return retailPrice;
	}
	
	public void setRetailPrice(Float retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	//------------------------------- INITIALISING -------------------------------------------
	
	public void initialize(URL location, ResourceBundle resources) {
		btnSearchHelp.setTooltip(new Tooltip("Proporciona información general del producto."));
		btnInventoryHelp.setTooltip(new Tooltip("Proporciona información acerca del manejo de inventario del producto."));
		btnSalesHelp.setTooltip(new Tooltip("Proporciona información relacionada con la venta del producto y tus finanzas."));
		btnImageHelp.setTooltip(new Tooltip("Proporciona una imagen representativa del producto."));
		btnAccountingHelp.setTooltip(new Tooltip("Proporciona información del producto relacionada con la contabilidad."));
		
		salesUnitFlag = new FlagHandling();
		
		
	}
	
	//------------------------------- FLAG HANDLING -------------------------------------------
	public void enableSalesUnitForm() {
		salesUnitFlag.setEnabled(true);
	}
	
	public void disableSalesUnitForm() {
		salesUnitFlag.setEnabled(false);
	}
	
	//------------------------------- LOADING VIEWS -------------------------------------------
	public void loadPurchaseUnitFormView() {
		if(PurchaseUnitForm.getStage() != null) {
			PurchaseUnitForm.getStage().show();
			PurchaseUnitForm.getStage().setAlwaysOnTop(true);
			PurchaseUnitForm.getStage().setAlwaysOnTop(false);
		} else {
			PurchaseUnitForm view = new PurchaseUnitForm();
			view.loadView();
		}
	}
	
	public void loadSalesUnitFormView() {
		if(SalesUnitForm.getStage() != null) {
			SalesUnitForm.getStage().show();
			SalesUnitForm.getStage().setAlwaysOnTop(true);
			SalesUnitForm.getStage().setAlwaysOnTop(false);
		} else {
			SalesUnitForm view = new SalesUnitForm();
			view.loadView();
		}
	}
	
	//-------------------------------- POP-UP MENUS ------------------------------------------
	
	@FXML
	protected void clickedBtnPurchaseUnitAdd() {
		loadPurchaseUnitFormView();
		InventoryAddForm.getStage().showingProperty().and(PurchaseUnitForm.getStage().showingProperty());
		InventoryAddForm.getStage().alwaysOnTopProperty().and(PurchaseUnitForm.getStage().alwaysOnTopProperty());
	}
	
	@FXML
	protected void clickedBtnSalesUnitAdd() {
		if(salesUnitFlag.isEnabled()) {
			loadSalesUnitFormView();
			InventoryAddForm.getStage().showingProperty().and(SalesUnitForm.getStage().showingProperty());
			InventoryAddForm.getStage().alwaysOnTopProperty().and(SalesUnitForm.getStage().alwaysOnTopProperty());
		}
		else
		{
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al agregar unidad de venta",
			"Primero debes llenar el formulario de \"Unidad de compra\".",
			(StackPane)InventoryAddForm.getStage().getScene().getRoot());
		}
	}
	
	//-------------------------------- RECEIVING FROM AND SENDING INFORMATION TO POP-UPS -----------------------------------------
	public void setTxtPurchaseUnitContent(String content) {
		txtPurchaseUnit.setText(content);
	}
	
	public void setTxtSalesUnitContent(String content) {
		txtSalesUnit.setText(content);
	}
	
	//-------------------------------- HELPING DIALOGS -----------------------------------------
	@FXML
	protected void clickedBtnSearchHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("General",
				"Estos datos nos serán de ayuda para realizar búsquedas y/o consultas rápidas\ndel precio de un determinado producto, entre otras operaciones.",
				(StackPane)InventoryAddForm.getStage().getScene().getRoot());
	}
	
	@FXML
	protected void clickedBtnInventoryHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Inventario",
				"Estos datos serán útiles para administrar tu inventario, recibir notificaciones del estado de los productos,\nalertas de fechas de caducidad y recordatorios de reabastecimiento.",
				(StackPane)InventoryAddForm.getStage().getScene().getRoot());
	}
	
	@FXML
	protected void clickedBtnSalesHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Estadísticas y ventas",
				"Estos datos nos servirán para la realización de ventas, cálculo de estadísticas\n(porcentajes de utilidad, flujo de entrada y salida de productos, etc.), y más.",
				(StackPane)InventoryAddForm.getStage().getScene().getRoot());
	}
	
	@FXML
	protected void clickedBtnImageHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Imagen",
				"Personaliza el registro de tu producto con una imagen representativa para una búsqueda y visualización más sencilla.",
				(StackPane)InventoryAddForm.getStage().getScene().getRoot());
	}
	
	@FXML
	protected void clickedBtnAccountingHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Contabilidad",
				"Estos datos nos servirán para una mejor administración de tus finanzas, realizar cálculo de impuestos, etc.",
				(StackPane)InventoryAddForm.getStage().getScene().getRoot());
	}
	
	//------------------------------------- FINISHING PRODUCT ADDING -------------------------------
	
	@FXML
	protected void acceptTransaction() {
		addProduct();
		closeStageCompletely();
	}
	
	@FXML
	protected void cancelTransaction() {
		closeStageCompletely();
	}
	
	public Integer addProduct() {
		Integer exit = -1;
		
		return exit;
	}
	
	public Boolean isInteger(String string) {
		Boolean valueReturn = true;
		for(int i=0;i<string.length();i++)
		{
			if(string.charAt(i) < '0' || string.charAt(i) > '9')
			{
				valueReturn = false;
				break;
			}
		}
		return valueReturn;
	}
	
	public void closeStageCompletely() {
		if(PurchaseUnitForm.getPurchaseUnitFormController() != null)
			PurchaseUnitForm.getPurchaseUnitFormController().closeStageCompletely();
		if(SalesUnitForm.getSalesUnitFormController() != null)
			SalesUnitForm.getSalesUnitFormController().closeStageCompletely();
		InventoryAddForm.getStage().close();
		InventoryAddForm.setStage(null);
	}
	
	//------------------------- Methods for dragging and dropping images ------------------------
	@FXML
	protected void handleDragOver(DragEvent event) {
		if(event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	
	@FXML
	protected void handleDrop(DragEvent event) {
		List<File> files = event.getDragboard().getFiles();
		Image img = null;
		try {
			img = new Image(new FileInputStream(files.get(0)));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		imgVwProduct.setImage(img); //Sets the image we're dropping into the ImageView
	}
	
	
}
