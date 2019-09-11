package com.masterdev.student.views.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.masterdev.student.entities.Product;
import com.masterdev.student.entities.ProductBatch;
import com.masterdev.student.entities.ProductType;
import com.masterdev.student.middle.ComboBoxMethods;
import com.masterdev.student.middle.DatePickerMethods;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.FlagHandling;
import com.masterdev.student.middle.ImageManagement;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.CategoryEdition;
import com.masterdev.student.views.InventoryEditForm;
import com.masterdev.student.views.InventoryList;
import com.masterdev.student.views.PurchaseUnitForm;
import com.masterdev.student.views.SalesForm;
import com.masterdev.student.views.SalesUnitEdition;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class InventoryEditFormController implements Initializable {
	
	private final String DEFAULT_IMAGE = "/stylesheets/images/image-icon.png";
	
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
	
	@FXML TextField txtTaxes;
	@FXML TextField txtDiscount;
	
	@FXML JFXButton btnAccept;
	@FXML JFXButton btnCancel;
	
	@FXML Button btnSearchHelp;
	@FXML Button btnInventoryHelp;
	@FXML Button btnSalesHelp;
	@FXML Button btnImageHelp;
	@FXML Button btnAccountingHelp;
	
	@FXML BorderPane brdrPnDragAndDrop;
	@FXML ImageView imgVwProduct;
	
	private FlagHandling productBatchFlag;
	private FlagHandling salesUnitFlag;
	
	private Product product;
	private String categoryName;
	private ProductBatch productBatch;
	private Float wholeCost;
	private Float retailCost;
	private String unit;
	private String subunit;
	private Float subunitAmount;
	private Float wholeUtility;
	private Float retailUtility;
	private Float wholePrice;
	private Float retailPrice;
	private File imageFile;
	
	//------------------------------- GETTING AND SETTING METHODS -------------------------------------------
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public ProductBatch getProductBatch() {
		return productBatch;
	}
	
	public void setProductBatch(ProductBatch productBatch) {
		this.productBatch = productBatch;
	}
	
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
	
	public File getImageFile() {
		return imageFile;
	}
	
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}
	
	//------------------------------- INITIALISING -------------------------------------------
	
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Over here!");
		//Initialise date pickers
		initialiseDatePickers();
		
		//We let the form create one product batch
		productBatchFlag = new FlagHandling();
		enableBatchCreation();
		
		//salesUnitFlag = new FlagHandling();
		
		initialiseComboBoxListenters();
		//Refreshing the category list
		refreshCategories();
		
		txtName.requestFocus();
	}
	
	public void initialiseTooltipTexts() {
		btnSearchHelp.setTooltip(new Tooltip("Proporciona información general del producto."));
		btnInventoryHelp.setTooltip(new Tooltip("Proporciona información acerca del manejo de inventario del producto."));
		btnSalesHelp.setTooltip(new Tooltip("Proporciona información relacionada con la venta del producto y tus finanzas."));
		btnImageHelp.setTooltip(new Tooltip("Proporciona una imagen representativa del producto."));
		btnAccountingHelp.setTooltip(new Tooltip("Proporciona información del producto relacionada con la contabilidad."));
	}
	
	public void initialiseDatePickers() {
		//We initialise DatePickers converters
		DatePickerMethods dpm = new DatePickerMethods();
		dpm.setConverter(dtPckrEntryDate);
		dpm.setConverter(dtPckrExpireDate);
	}
	
	public void initialiseComboBoxListenters() {
		//Adding a listener to the category combobox
		cmbxCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	        	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	        		if(cmbxCategory.getSelectionModel().getSelectedItem() != null) 
			        {
		        		setCategoryName(newValue);
		        		if(CategoryEdition.getStage() != null)
		        			CategoryEdition.getCategoryEditionController().updateFields();
			        }
	        	}
	    });
	}
	
	public void setProductData(Product product) {
		updateData(product);
		updateFields();
	}
	
	public void updateData(Product p) {
		setProduct(p);
		setCategoryName(p.getProductType().getType());
		setWholeCost(p.getWholeCost());
		setRetailCost(p.getRetailCost());
		setUnit(p.getPurchaseUnit());
		setSubunit(p.getPurchaseSubunit());
		setSubunitAmount(p.getPurchaseSubunitAmount());
		setWholeUtility(p.getWholeUtility());
		setRetailUtility(p.getRetailUtility());
		setWholePrice(p.getWholePrice());
		setRetailPrice(p.getRetailPrice());
	}
	
	public void updateFields() {
		txtName.setText(getProduct().getProduct());
		cmbxCategory.getSelectionModel().select(getCategoryName());
		txtBarcode.setText(getProduct().getBarCode());
		txtInnerKey.setText(getProduct().getInternalCode());
		txtBrand.setText(getProduct().getBrand());
		txtContent.setText(getProduct().getNetContent());
		txtMinStock.setText(String.format("%.2f", getProduct().getMinStock()));
		txtMaxStock.setText(String.format("%.2f", getProduct().getMaxStock()));
		txtSalesUnit.setText(getProduct().getPurchaseSubunit());
		if(getProduct().isInBulk()) {
			chckbxInBulk.setSelected(true);
		}
		txtTaxes.setText(String.format("%.2f", getProduct().getTaxes()));
		txtDiscount.setText(String.format("%.2f", getProduct().getDiscount()));
		Image productImage = new Image(getProduct().getImage());
        imgVwProduct.setImage(productImage);
	}
	
	//------------------------------- FLAG HANDLING -------------------------------------------
	public void enableBatchCreation() {
		productBatchFlag.setEnabled(true);
	}
	
	public void disableBatchCreation() {
		productBatchFlag.setEnabled(false);
	}
	
	public void enableSalesUnitForm() {
		salesUnitFlag.setEnabled(true);
	}
	
	public void disableSalesUnitForm() {
		salesUnitFlag.setEnabled(false);
	}
	
	//------------------------------- LOADING VIEWS -------------------------------------------
	public void loadCategoryFormView() {
		if(CategoryEdition.getStage() != null) {
			CategoryEdition.getStage().show();
			CategoryEdition.getStage().setAlwaysOnTop(true);
			CategoryEdition.getStage().setAlwaysOnTop(false);
		} else {
			CategoryEdition view = new CategoryEdition();
			view.loadView();
		}
	}
	
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
	
	public void loadSalesUnitEditionView() {
		if(SalesUnitEdition.getStage() != null) {
			SalesUnitEdition.getStage().show();
			SalesUnitEdition.getStage().setAlwaysOnTop(true);
			SalesUnitEdition.getStage().setAlwaysOnTop(false);
		} else {
			SalesUnitEdition view = new SalesUnitEdition();
			view.loadView();
		}
	}
	
	//-------------------------------- COMBO-BOX OPTIONS ------------------------------------------
	public void refreshCategories() {
		cmbxCategory.getItems().clear(); //Clearing the ComboBox items before adding the new ones
		WarehouseService service = new WarehouseService();
		List<ProductType> data = service.showProductTypes();
		System.out.println(data);
		ComboBoxMethods cbm = new ComboBoxMethods();
		cbm.addCategoryItems(cmbxCategory, data);
	}
	
	//-------------------------------- POP-UP MENUS ------------------------------------------
	
	@FXML
	protected void clickedBtnPurchaseUnitAdd() {
		loadPurchaseUnitFormView();
		InventoryEditForm.getStage().showingProperty().and(PurchaseUnitForm.getStage().showingProperty());
		InventoryEditForm.getStage().alwaysOnTopProperty().and(PurchaseUnitForm.getStage().alwaysOnTopProperty());
	}
	
	@FXML
	protected void clickedBtnSalesUnitAdd() {
		//if(salesUnitFlag.isEnabled()) {
			loadSalesUnitEditionView();
			InventoryEditForm.getStage().showingProperty().and(SalesUnitEdition.getStage().showingProperty());
			InventoryEditForm.getStage().alwaysOnTopProperty().and(SalesUnitEdition.getStage().alwaysOnTopProperty());
		/*}
		else
		{
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al agregar unidad de venta",
			"Primero debes llenar el formulario de \"Unidad de compra\".",
			(StackPane)InventoryEditForm.getStage().getScene().getRoot(), txtName);
		}*/
	}
	
	@FXML
	protected void clickedBtnCategorySearch() {
		loadCategoryFormView();
		if(CategoryEdition.getStage() != null)
			CategoryEdition.getCategoryEditionController().showCategories();
		InventoryEditForm.getStage().showingProperty().and(CategoryEdition.getStage().showingProperty());
		InventoryEditForm.getStage().alwaysOnTopProperty().and(CategoryEdition.getStage().alwaysOnTopProperty());
	}
	
	@FXML
	protected void clickedBtnBatchSearch() {
		initialiseProductBatch();
	}
	
	public void initialiseProductBatch() {
		if(productBatchFlag.isEnabled()) {
			if(productIdentifiersAreFilled()) {
				Product product = compareInDetail();
				if(product == null) {
					Product p = getProduct();
					ProductBatch pb = createProductBatch(p);
					txtBatch.setText(String.valueOf(pb.getId()));		//Updating the UI
					disableBatchCreation();								//Prevent from creating more than one batch
					
					unhighlightProductIdentifiers();
				} else {
					String name = txtName.getText().trim();
					String barcode = txtBarcode.getText().trim();
					String innerKey = txtInnerKey.getText().trim();
					productExists(product, name, barcode, innerKey);
				}
			} else {
				unhighlightProductIdentifiers();
				highlightProductIdentifiers();
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al agregar lote",
						"Asegúrate de haber llenado primero los campos \"Nombre\" Y \"Categoría\".",
						(StackPane)InventoryEditForm.getStage().getScene().getRoot(), txtName);
			}
		}
	}
	
	public ProductBatch createProductBatch(Product p) {
		WarehouseService service = new WarehouseService();
		ProductType productType = new ProductType(getCategoryName());
		ProductType pt = service.searchProductType(productType);
		p.setProduct(txtName.getText().trim());
		p.setBarCode(txtBarcode.getText().trim());
		p.setInternalCode(txtInnerKey.getText().trim());
		p.setProductType(pt);
		service.updateProduct(p);
		
		ProductBatch pb = new ProductBatch();
		pb.setProduct(p);
		service.addProductBatch(pb);
		
		//Updating InventoryAddForm data
		setProduct(p);
		setProductBatch(pb);
		
		return pb;
	}
	
	//Checking the minimum requirements for creating a ProductBatch
	public Boolean productIdentifiersAreFilled() {
		String category = cmbxCategory.getSelectionModel().getSelectedItem();
		if(!txtName.getText().trim().equals("") && category != null)
			return true;
		else
			return false;
	}
	
	//Just in case the user didn't notice the obligatory fields sign next to them.
	public void unhighlightProductIdentifiers() {
		if(!txtName.getStyleClass().isEmpty())
			txtName.getStyleClass().removeAll("important");
		if(!cmbxCategory.getStyleClass().isEmpty())
			cmbxCategory.getStyleClass().removeAll("important");
	}
	
	public void highlightProductIdentifiers() {
		if(txtName.getText().trim().equals(""))
			txtName.getStyleClass().add("important");
		if(cmbxCategory.getSelectionModel().getSelectedItem() == null)
			cmbxCategory.getStyleClass().add("important");
	}
	
	//Checking if the user provided a number or a string
	public Boolean isNumber(String string) {
		String s = string.trim();
		Boolean result = true;
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i) < '0' || s.charAt(i) > '9')
				result = false;
		}
		return result;
	}
	
	
	//------------------------------------- FINISHING PRODUCT ADDING -------------------------------
	
	@FXML
	protected void acceptTransaction() {
		if(fieldsAreFilledUp()) {
			try {
				editProduct();
			} catch(NumberFormatException e) {
				e.printStackTrace();
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de entrada de datos",
						"Asegúrate de haber llenado el campo \"Existencia\", \"Min. Stock\" y \"Max. Stock\" con número.",
						(StackPane)InventoryEditForm.getStage().getScene().getRoot(), txtName);
			}
		} else {
			unhighlightObligatoryFields();
			highlightObligatoryFields();
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al agregar producto",
					"Asegúrate de haber llenado todos los campos correctamente.",
					(StackPane)InventoryEditForm.getStage().getScene().getRoot(), txtName);
		}
	}
	
	public void editProduct() throws NumberFormatException {
		
		WarehouseService service = new WarehouseService();
		
		//Checking if the product we want to add isn't already in the database
		Product p = getProduct();		//******************* IMPORTANT: this is the product we'll modify or create, depending on the case ****************//
		Product productFound;		//*************** This is just a product we're going to use to check if we already have it in the DB 
		
		productFound = compareInDetail();
		if(productFound == null) {
			ProductType pt = new ProductType(getCategoryName());
			pt = service.searchProductType(pt);
			ProductBatch pb = getProductBatch();
			
			//Editing them
			//------------------------------------------OBLIGATORY FIELDS ******
			System.out.println(p);
			editProductData(p, pt);							//Setting product identifying information
			service.updateProduct(p);                      	//******************* UPDATING THE ORIGINAL PRODUCT DRAFT *************************//
			
			//----------------------- PRODUCT BATCH ------------------------------------------//
			if(pb != null) {
				editProductBatchData(pb, p);							//Setting batch identifying information
				service.updateProductBatch(pb);				//******************* UPDATING THE ORIGINAL PRODUCT BATCH DRAFT *************************//
			}
			closeStageCompletely();
			updateDashboardViewsData();					//UPDATING INFO. IN THE DASHBOARD
		} else {
			String name = txtName.getText().trim();
			String barcode = txtBarcode.getText().trim();
			String innerKey = txtInnerKey.getText().trim();
			productExists(productFound, name, barcode, innerKey);
		}
	}
	
	//Checking if the product exists
	public Product checkProductExistence(Product p) {
		WarehouseService service = new WarehouseService();
		p.setProduct(txtName.getText().trim());
		p.setBarCode(null);
		p.setInternalCode(null);
		Product product = service.searchProduct(p);
		if(product == null) {
			if(!txtBarcode.getText().trim().equals("")) {
				p.setBarCode(txtBarcode.getText().trim());
				p.setProduct(null);
				p.setInternalCode(null);
				product = service.searchProduct(p);
				if(product == null) {
					if(!txtInnerKey.getText().trim().equals("")) {
						p.setInternalCode(txtInnerKey.getText().trim());
						p.setBarCode(null);
						p.setProduct(null);
						product = service.searchProduct(p);
					}
				}
			}
		}
		return product;
	}
	
	public Product compareInDetail() {
		Product p = new Product();
		Product productFound = null;
		Product productReturn = null;
		WarehouseService service = new WarehouseService();
		//Checking if the name is already used by another product
		p.setProduct(txtName.getText().trim());
		productFound = service.searchProduct(p);
		if(productFound != null) {
			if(productFound.getId() != getProduct().getId()) {
				return productFound;
			}
		}
		//Checking if the barcode is already used by another product
		if(!txtBarcode.getText().trim().equals("")) {
			p.setProduct(null);
			p.setBarCode(txtBarcode.getText().trim());
			productFound = service.searchProduct(p);
			if(productFound != null) {
				if(productFound.getId() != getProduct().getId()) {
					return productFound;
				}
			}
		}
		//Checking if the internal code is already used by another product
		if(!txtInnerKey.getText().trim().equals("")) {
			p.setProduct(null);
			p.setBarCode(null);
			p.setInternalCode(txtInnerKey.getText().trim());
			productFound = service.searchProduct(p);
			if(productFound != null) {
				if(productFound.getId() != getProduct().getId()) {
					return productFound;
				}
			}
		}
		return productReturn;
	}
	
	//Editing product data
	public void editProductData(Product p, ProductType pt) {
		p.setProduct(txtName.getText().trim()); 	//******
		p.setProductType(pt);				//******
		if(!txtBarcode.getText().trim().equals(""))		
			p.setBarCode(txtBarcode.getText().trim());
		else
			p.setBarCode("");
		p.setInternalCode(txtInnerKey.getText().trim());
		
		//Setting other details
		p.setBrand(txtBrand.getText().trim());
		p.setNetContent(txtContent.getText().trim());
		
		//Setting purchase and sale information (finances)
		p.setWholeCost(getWholeCost());
		p.setRetailCost(getRetailCost());
		p.setWholePrice(getWholePrice());
		p.setRetailPrice(getRetailPrice());
		p.setWholeUtility(getWholeUtility());
		p.setRetailUtility(getRetailUtility());
		if(chckbxInBulk.isSelected())
			p.setInBulk(true);
		else
			p.setInBulk(false);
		
		//Setting information about inventory management
		if(txtExistence.getText().trim().equals("")) {
			p.setQuantity(p.getQuantity() + 0.0F);
			//pb.setQuantity(0.0F);
		} else {
			p.setQuantity(p.getQuantity() + Float.parseFloat(txtExistence.getText().trim()));
			//pb.setQuantity(Float.parseFloat(txtExistence.getText().trim()));
		}
		p.setPurchaseUnit(getUnit());	//******
		p.setPurchaseSubunit(getSubunit());	//******
		p.setPurchaseSubunitAmount(getSubunitAmount());	//******
		
		if(txtMinStock.getText().trim().equals(""))
			p.setMinStock(0.0F);
		else
			p.setMinStock(Float.parseFloat(txtMinStock.getText().trim()));
		if(txtMaxStock.getText().trim().equals(""))
			p.setMaxStock(0.0F);
		else
			p.setMaxStock(Float.parseFloat(txtMaxStock.getText().trim()));
		
		//----------------------- STILL TESTING ------------------------------------------//
		File file = null;
		if(getImageFile() != null) {
			file = getImageFile();
			String path = "/stylesheets/images/product_images/" + file.getName();
			copyFile(file.getAbsolutePath(), path);
			p.setImage(/*file.getAbsolutePath());*/path);
		}
		else {
			p.setImage(DEFAULT_IMAGE);
		}
		
		if(txtTaxes.getText().trim().equals(""))
			p.setTaxes(0.0f);
		else
			p.setTaxes(Float.parseFloat(txtTaxes.getText().trim()));
		
		if(txtDiscount.getText().trim().equals(""))
			p.setDiscount(0.0f);
		else
			p.setDiscount(Float.parseFloat(txtDiscount.getText().trim()));
		
		p.setUnitsSold(0.0f);
	}
	
	//------------------------------------------ STILL TESTING: COPYING FILES -------------------------------//
	public void copyFile(String from, String to) {
		ImageManagement im = new ImageManagement();
		im.copyImage(from, to);
	}
	
	public void editProductBatchData(ProductBatch pb, Product p) {
		pb.setProduct(p);
		
		if(txtExistence.getText().trim().equals("")) {
			pb.setQuantity(0.0F);
			pb.setRemaining(0.0F);			// CHANGED
		} else {
			pb.setQuantity(Float.parseFloat(txtExistence.getText().trim()));
			pb.setRemaining(Float.parseFloat(txtExistence.getText().trim()));  // CHANGED
		}
		
		if(dtPckrEntryDate.getValue() != null) {
			LocalDate localDate = dtPckrEntryDate.getValue();
			pb.setEntryDate(Date.valueOf(localDate));
		} else {
			LocalDate localDate = LocalDate.now();
			pb.setEntryDate(Date.valueOf(localDate));
		}
		
		if(dtPckrExpireDate.getValue() != null) {
			LocalDate localDate = dtPckrExpireDate.getValue();
			pb.setDischargeDate(Date.valueOf(localDate));
		} else {
			LocalDate localDate = LocalDate.now();
			pb.setDischargeDate(Date.valueOf(localDate));
		}
	}
	
	public void productExists(Product productFound, String name, String barcode, String innerKey) {
		String message = "";
		if(name.trim().equals(""))
			name = null;
		if(barcode.trim().equals(""))
			barcode = null;
		if(innerKey.trim().equals(""))
			innerKey = null;
		if(productFound.getProduct().toLowerCase().equals(name.toLowerCase())) {
			message += "El nombre";
			if(productFound.getBarCode().equals(barcode)) {
				if(productFound.getInternalCode().equals(innerKey)) {
					message += ", el código de barras y la clave interna";
				} else {
					message += " y el código de barras";
				}
			} else {
				if(productFound.getInternalCode().equals(innerKey)) {
					message += " y la clave interna";
				}
			}
			message += " ";
		} else {
			if(productFound.getBarCode().equals(barcode)) {
				message += "El código de barras ";
				if(productFound.getInternalCode().equals(innerKey)) {
					message += "y la clave interna ";
				}
			} else {
				if(productFound.getInternalCode().equals(innerKey)) {
					message += "La clave interna ";
				}
			}
		}
		message += "del producto que deseas agregar al inventario ya está registrado. \n\n¿Deseas ir a la ventana \"Editar producto\" para editarlo?";
		Dialogs d = new Dialogs();
		Boolean exit = d.confirmationDialog("Confirmación", "Error al guardar el producto", message + "\n");
		if(exit) {
			exitView();
		} else {
			cleanView(productFound);
		}
	}
	
	
	//--------------------------------------------------------------------------------------------//
	public Boolean fieldsAreFilledUp() {
		if(!txtName.getText().trim().equals("") && cmbxCategory.getSelectionModel().getSelectedItem() != null && !cmbxCategory.getSelectionModel().getSelectedItem().trim().equals("") /*&& !txtPurchaseUnit.getText().trim().equals("") && !txtSalesUnit.getText().trim().equals("")*/)
			return true;
		else
			return false;
	}
	
	//Cancelling transaction
	@FXML
	protected void cancelTransaction() {
		cancel();
	}
	
	public void cancel() {
		/*if(formHasInformation()) {
			Dialogs d = new Dialogs();
			Boolean exit = d.confirmationDialog("Confirmación", "Estás a punto de salir sin guardar", "Probablemente tengas datos no guardados. \n¿Estás seguro de cancelar el proceso? \n");
			if(exit) {
				exitView();
			}
		} else {*/
			exitView();
		//}
		if(InventoryList.getInventoryListController() != null)
			InventoryList.getInventoryListController().showProductList();		//Refresh the product list in case it is open
	}
	
	public void exitView() {
		cleanDrafts();
		closeStageCompletely();
	}
	
	public Boolean formHasInformation() {
		Boolean result = false;
		if(!txtName.getText().trim().equals("") || cmbxCategory.getSelectionModel().getSelectedItem() != null
			|| !txtBarcode.getText().trim().equals("") || !txtInnerKey.getText().trim().equals("") || !txtBrand.getText().trim().equals("")
			|| !txtContent.getText().trim().equals("") || !txtBatch.getText().trim().equals("") || !txtExistence.getText().trim().equals("")
			|| dtPckrEntryDate.getValue() != null || dtPckrExpireDate.getValue() != null || !txtMinStock.getText().trim().equals("")
			|| !txtMaxStock.getText().trim().equals("") || /*!txtPurchaseUnit.getText().trim().equals("") ||*/ !txtSalesUnit.getText().trim().equals("")){
			result = true;
		}
		return result;
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
	
	//Just in case the user didn't notice the obligatory fields sign next to them.
	private void unhighlightObligatoryFields() {
		if(!cmbxCategory.getStyleClass().isEmpty())
			cmbxCategory.getStyleClass().removeAll("important");
		if(!txtName.getStyleClass().isEmpty())
			txtName.getStyleClass().removeAll("important");
		/*if(!txtPurchaseUnit.getStyleClass().isEmpty())
			txtPurchaseUnit.getStyleClass().removeAll("important");*/
		if(!txtSalesUnit.getStyleClass().isEmpty())
			txtSalesUnit.getStyleClass().removeAll("important");
	}
	
	private void highlightObligatoryFields() {
		if(txtName.getText().trim().equals(""))
			txtName.getStyleClass().add("important");
		if(cmbxCategory.getSelectionModel().getSelectedItem() == null || cmbxCategory.getSelectionModel().getSelectedItem().trim().equals(""))
			cmbxCategory.getStyleClass().add("important");
		/*if(txtPurchaseUnit.getText().trim().equals(""))
			txtPurchaseUnit.getStyleClass().add("important");*/
		if(txtSalesUnit.getText().trim().equals(""))
			txtSalesUnit.getStyleClass().add("important");
	}
	
	//
	
	public void closeStageCompletely() {
		closePopUpMenus();
		
		//Closing this stage completely
		if(InventoryEditForm.getStage() != null) {
			InventoryEditForm.getStage().close();
			InventoryEditForm.setStage(null);
		}
	}
	
	//-------------------------------- RECEIVING FROM AND SENDING INFORMATION TO POP-UPS -----------------------------------------//
	public void setTxtPurchaseUnitContent(String content) {
		txtPurchaseUnit.setText(content);
	}
	
	public void setTxtSalesUnitContent(String content) {
		txtSalesUnit.setText(content);
	}
	
	public void setCmbxCategoryContent(String content) {
		cmbxCategory.getSelectionModel().select(content);
	}
	
	//-------------------------------- UPDATING OTHER VIEWS' DATA -----------------------------------------//
	public void updateDashboardViewsData() {
		List<Product> products = null;
		//INVENTORY LIST *****
		if(InventoryList.getInventoryListController() != null) {
			products = InventoryList.getInventoryListController().showProductList();		//Refresh the product list in case it is open
			InventoryList.getInventoryListController().suggestProducts(products);
		}
		//SALES FORM *****
		if(SalesForm.getSalesFormController() != null) {
			if(products == null)
				products = SalesForm.getSalesFormController().getProductList();		//Refresh the product list in case it is open
			SalesForm.getSalesFormController().suggestProducts(products);
		}
	}
	
	
	//------------------------- Methods for dragging and dropping images ------------------------
	@FXML
	protected void handleDragOver(DragEvent event) {
		ImageManagement management = new ImageManagement();
		management.imageDragOver(event);
	}
	
	@FXML
	protected void handleDrop(DragEvent event) {
		File file = null;
		ImageManagement management = new ImageManagement();
		file = management.imageDrop(event);
		setImageFile(file);
		Image img = null;
		try {
			img = new Image(new FileInputStream(file));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		if(img != null)
			imgVwProduct.setImage(img); //Sets the image we're dropping into the ImageView
	}
	
	@FXML
	protected void openFileChooser() {
		File file = null;
		ImageManagement management = new ImageManagement();
		file = management.imageBrowse();
		setImageFile(file);
		Image img = null;
		if (file != null) {
        	try {
        		img = new Image(new FileInputStream(file));
        	} catch (FileNotFoundException e) {
        		e.printStackTrace();
        	}
            //openFile(file);			Only if we want to open the image with another application
        }
		if(img != null)
			imgVwProduct.setImage(img); //Sets the image we've selected into the ImageView
	}
	
	//-------------------------------- Methods for cleaning temporary Java Objects and values ------------------------
	public void cleanObjects() {
		setProduct(null);
		setCategoryName(null);
		setProductBatch(null);
		setWholeCost(null);
		setRetailCost(null);
		setUnit(null);
		setSubunit(null);
		setSubunitAmount(null);
		setWholeUtility(null);
		setRetailUtility(null);
		setWholePrice(null);
		setRetailPrice(null);
		setImageFile(null);
	}
	
	//-------------------------------- Methods for cleaning Database temporary registers ------------------------
	public void cleanDrafts() {
		if(getProduct() != null && getProductBatch() != null) {
			WarehouseService service = new WarehouseService();
			service.deleteProductBatch(getProductBatch());
			service.deleteProduct(getProduct());
		}
	}
	
	//-------------------------------- Methods for cleaning graphic components ------------------------
	public void cleanView(Product p) {
		//cleanDrafts();
		cleanFields(p);
		//cleanObjects();
		//closePopUpMenus();
	}
	
	public void cleanFields(Product p) {
		if(txtName.getText().trim().toLowerCase().equals(p.getProduct().toLowerCase()))
			txtName.setText("");
		if(txtBarcode.getText().trim().equals(p.getBarCode()))
			txtBarcode.setText("");
		if(txtInnerKey.getText().trim().equals(p.getInternalCode()))
			txtInnerKey.setText("");
		/*txtName.setText("");
		cmbxCategory.getSelectionModel().clearSelection();
		txtBarcode.setText("");
		txtInnerKey.setText("");
		txtBrand.setText("");
		txtContent.setText("");
		txtBatch.setText("");
		txtExistence.setText("");
		dtPckrEntryDate.setValue(null);
		dtPckrExpireDate.setValue(null);
		txtMinStock.setText("");
		txtMaxStock.setText("");
		//txtPurchaseUnit.setText("");
		txtSalesUnit.setText("");
		imgVwProduct.setImage(new Image(InventoryAddForm.getInventoryAddFormController().DEFAULT_IMAGE));*/
	}
	
	public void closePopUpMenus() {
		//Verifying if there's a pop-up menu open to close them
		if(PurchaseUnitForm.getPurchaseUnitFormController() != null)
			PurchaseUnitForm.getPurchaseUnitFormController().closeStageCompletely();
		if(SalesUnitEdition.getSalesUnitEditionController() != null)
			SalesUnitEdition.getSalesUnitEditionController().closeStageCompletely();
		if(CategoryEdition.getCategoryEditionController() != null)
			CategoryEdition.getCategoryEditionController().closeStageCompletely();
	}
	
	//-------------------------------- HELPING DIALOGS -----------------------------------------
	@FXML
	protected void clickedBtnSearchHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("General",
				"Estos datos nos serán de ayuda para realizar búsquedas y/o consultas rápidas\ndel precio de un determinado producto, entre otras operaciones.",
				(StackPane)InventoryEditForm.getStage().getScene().getRoot(), txtName);
	}
	
	@FXML
	protected void clickedBtnInventoryHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Inventario",
				"Estos datos serán útiles para administrar tu inventario, recibir notificaciones del estado de los productos,\nalertas de fechas de caducidad y recordatorios de reabastecimiento.",
				(StackPane)InventoryEditForm.getStage().getScene().getRoot(), txtName);
	}
	
	@FXML
	protected void clickedBtnSalesHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Estadísticas y ventas",
				"Estos datos nos servirán para la realización de ventas, cálculo de estadísticas\n(porcentajes de utilidad, flujo de entrada y salida de productos, etc.), y más.",
				(StackPane)InventoryEditForm.getStage().getScene().getRoot(), txtName);
	}
	
	@FXML
	protected void clickedBtnImageHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Imagen",
				"Personaliza el registro de tu producto con una imagen representativa para una búsqueda y visualización más sencilla.",
				(StackPane)InventoryEditForm.getStage().getScene().getRoot(), txtName);
	}
	
	@FXML
	protected void clickedBtnAccountingHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Contabilidad",
				"Estos datos nos servirán para una mejor administración de tus finanzas, realizar cálculo de impuestos, etc.",
				(StackPane)InventoryEditForm.getStage().getScene().getRoot(), txtName);
	}
}
