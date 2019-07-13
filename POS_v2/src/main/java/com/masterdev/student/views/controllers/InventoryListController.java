package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.AutoCompletionBinding;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.masterdev.student.entities.Product;
import com.masterdev.student.entities.ProductBatch;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.TextFieldMethods;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.InventoryAddForm;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.converter.FloatStringConverter;

public class InventoryListController implements Initializable {
	
	@FXML TableView<Product> tabProducts;
	@FXML TableColumn<Product, String> colName;
	@FXML TableColumn<Product, String> colBrand;
	@FXML TableColumn<Product, Float> colQuantity;
	@FXML TableColumn<Product, String> colWholeUnit;
	@FXML TableColumn<Product, String> colRetailUnit;
	@FXML TableColumn<Product, Float> colWholeCost;
	@FXML TableColumn<Product, Float> colWholePrice;
	@FXML TableColumn<Product, Float> colRetailCost;
	@FXML TableColumn<Product, Float> colRetailPrice;
	@FXML TableColumn<Product, String> colInternalCode;
	@FXML TableColumn<Product, String> colBarcode;
	
	@FXML TextField txtSearchProduct;
	
	@FXML JFXTextField txtProduct;
	@FXML JFXTextField txtCategory;
	@FXML JFXTextField txtBarcode;
	@FXML JFXTextField txtInternalCode;
	@FXML JFXTextField txtBrand;
	@FXML JFXTextField txtNetContent;
	@FXML JFXTextField txtQuantity;
	@FXML JFXTextField txtBatches;
	@FXML JFXTextField txtWholeUnit;
	@FXML JFXTextField txtRetailUnit;
	@FXML JFXTextField txtWholePrice;
	@FXML JFXTextField txtRetailPrice;
	
	@FXML JFXButton btnAddProduct;
	@FXML JFXButton btnSearchProduct;
	@FXML JFXButton btnFilters;
	@FXML JFXButton btnDeleteProduct;
	@FXML JFXButton btnEditProduct;
	
	@FXML ImageView imgVwProduct;
	
	private AutoCompletionBinding<String> productSuggestions;
	
	//--------------------------------------- INITIALISING ------------------------------------//
	public void initialize(URL location, ResourceBundle resources) {
		//Following our standard process
		initialiseTooltipText();										//1. Initialising tooltip texts
		List<Product> products = showProductList();
		suggestProducts(products);										//2. Add word suggestions (if needed)
		initialiseListeners();											//3. Add table listeners
	}
	
	public void initialiseTooltipText() {
		tabProducts.setTooltip(new Tooltip("Selecciona un producto de la lista."));
	}
	
	public void initialiseListeners() {
		tabProducts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
		    @Override
		    public void changed(ObservableValue<? extends Product> observableValue, Product oldValue, Product newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabProducts.getSelectionModel().getSelectedItem() != null) 
		        {
		        	updateTableSelection();
		        }
	         }
	     });
	}
	
	public void updateTableSelection() {
		TableViewSelectionModel<Product> selectionModel = tabProducts.getSelectionModel();
        Product selectedItem = selectionModel.getSelectedItem();
        //Image productImage = new Image(selectedItem.getImage());
        //imgVwProduct.setImage(productImage);
        txtProduct.setText(selectedItem.getProduct());
     	txtCategory.setText(selectedItem.getProductType().getType());
     	txtBarcode.setText(selectedItem.getBarCode());
     	txtInternalCode.setText(selectedItem.getInternalCode());
     	txtBrand.setText(selectedItem.getBrand());
     	txtNetContent.setText(selectedItem.getNetContent());
     	txtQuantity.setText(String.format("%.2f", selectedItem.getQuantity()));
     	List<ProductBatch> batches = selectedItem.getProductBatches();
     	if(batches != null) {
     		txtBatches.setText(String.valueOf(batches.size()));
     	}
     	txtWholeUnit.setText(selectedItem.getPurchaseUnit());
     	txtRetailUnit.setText(selectedItem.getPurchaseSubunit());
     	txtWholePrice.setText(String.format("%.2f", selectedItem.getWholePrice()));
     	txtRetailPrice.setText(String.format("%.2f", selectedItem.getRetailPrice()));
         
	}
	
	//--------------------------------------- LOADING VIEWS ------------------------------------//
	public void loadInventoryAddFormView() {
		Dashboard.getDashboardController().inventoryAddFormWithoutMenu();
	}
	
	
	//--------------------------------------- METHODS FOR HANDLING DATA ------------------------------------//
	
	
	//--------------------------------------- METHODS FOR ADDING A PRODUCT ------------------------------------//
	@FXML
	protected void addProduct() {
		loadInventoryAddFormView();
	}
	
	//------------------------------- METHODS FOR SEARCHING A PRODUCT ----------------------------------//
	@FXML
	protected void searchProduct() {
		search();
	}
	
	public void search() {
		if(!txtSearchProduct.getText().trim().equals("")) {
			Product productFound = findProduct(txtSearchProduct.getText().trim());
			if(productFound != null) {
				showResult(productFound);
				selectFoundProduct(productFound);
			} else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Producto no encontrado",
						"No se encontraron coincidencias.",
						(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
			}
			cleanSearchField();
		}
	}
	
	public Product findProduct(String productName) {
		Product productReturn = null;
		Product p = new Product();
		p.setProduct(productName);
		WarehouseService ws = new WarehouseService();
		productReturn = ws.searchProduct(p);
		return productReturn;
	}
	
	public void showResult(Product result) {
		initCols();
		
		ArrayList<Product> data = new ArrayList<Product>();
		data.add(result);
		ObservableList<Product> categories = FXCollections.observableArrayList(data);
		
		tabProducts.setItems(categories);
	}
	
	protected void suggestProducts(List<Product> data) {
		if(data != null) {
			if(productSuggestions != null)
				productSuggestions.dispose(); 							//We get rid of the past list of word suggestions
			TextFieldMethods tfm = new TextFieldMethods();
			ArrayList<String> products = new ArrayList<String>();
			for(Product p : data) {
				products.add(p.getProduct());
			}
			productSuggestions = tfm.addWordSuggestions(txtSearchProduct, products);
		}
	}
	
	//Methods for catching the information coming from Adding and Editing category forms
	public void selectFoundProduct(Product product) {
		tabProducts.requestFocus();
		tabProducts.getSelectionModel().selectFirst();
	}
	
	public void selectAddedProduct(Product product) {
		tabProducts.requestFocus();
		tabProducts.getSelectionModel().selectLast();
		tabProducts.scrollTo(tabProducts.getSelectionModel().getSelectedIndex());
	}
	
	public void selectEditedProduct(Product product, Integer index) {
		tabProducts.requestFocus();
		tabProducts.getSelectionModel().select(index);
		//tabProducts.getSelectionModel().select(product);
		
		//tabCategories.scrollTo(tabCategories.getSelectionModel().getSelectedIndex());
	}
	
	public void cleanSearchField() {
		txtSearchProduct.setText("");
	}
	
	//------------------------------- METHODS FOR EDITING A PRODUCT ----------------------------------//
	
	
	//------------------------------- METHODS FOR SHOWING ALL PRODUCTS ----------------------------------//
	@FXML
	protected void showProducts() {
		showProductList();
	}
	
	public List<Product> showProductList() {
		initCols();												//Initialises the values of the products registered in the table
		WarehouseService service = new WarehouseService();
		List<Product> data = service.showProducts();
		setTableContent(data);
		cleanProductDetails();									//Cleans the fields filled with information about the selected product
		
		return data;
	}
	
	public void initCols() {
		colName.setCellValueFactory(new PropertyValueFactory<Product, String>("product"));
		colBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<Product, Float>("quantity"));
		colWholeUnit.setCellValueFactory(new PropertyValueFactory<Product, String>("purchaseUnit"));
		colRetailUnit.setCellValueFactory(new PropertyValueFactory<Product, String>("purchaseSubunit"));
		colWholeCost.setCellValueFactory(new PropertyValueFactory<Product, Float>("wholeCost"));
		colWholePrice.setCellValueFactory(new PropertyValueFactory<Product, Float>("wholePrice"));
		colRetailCost.setCellValueFactory(new PropertyValueFactory<Product, Float>("retailCost"));
		colRetailPrice.setCellValueFactory(new PropertyValueFactory<Product, Float>("retailPrice"));
		colInternalCode.setCellValueFactory(new PropertyValueFactory<Product,String>("internalCode"));
		colBarcode.setCellValueFactory(new PropertyValueFactory<Product, String>("barCode"));
		
		if(Dashboard.getDashboardController().getUser().getUserGroup().getGroup().equals("admin"))
			editableCols();
		else
			btnEditProduct.setVisible(false);
	}
	
	public void editableCols() {
		colName.setCellFactory(TextFieldTableCell.forTableColumn());
		colName.setOnEditCommit(e -> editProductName(e));
		colBrand.setCellFactory(TextFieldTableCell.forTableColumn());
		colBrand.setOnEditCommit(e ->  {
			Product p = e.getTableView().getItems().get(e.getTablePosition().getRow());
			p.setBrand(e.getNewValue().trim());
			updateProduct(p);
			refreshSelectionAfterEdition();
		});
		colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colQuantity.setOnEditCommit(e ->  {
			try {
				Product p = e.getTableView().getItems().get(e.getTablePosition().getRow());
				p.setQuantity(e.getNewValue());
				updateProduct(p);
				refreshSelectionAfterEdition();
			} catch(NumberFormatException exc) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al editar producto",
						"La cantidad debe ser un número.",
						(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
			}
		});
		colWholeUnit.setCellFactory(TextFieldTableCell.forTableColumn());
		colWholeUnit.setOnEditCommit(e ->  {
			Product p = e.getTableView().getItems().get(e.getTablePosition().getRow());
			p.setPurchaseUnit(e.getNewValue().trim());
			updateProduct(p);
			refreshSelectionAfterEdition();
		});
		colRetailUnit.setCellFactory(TextFieldTableCell.forTableColumn());
		colRetailUnit.setOnEditCommit(e ->  {
			Product p = e.getTableView().getItems().get(e.getTablePosition().getRow());
			p.setPurchaseSubunit(e.getNewValue().trim());
			updateProduct(p);
			refreshSelectionAfterEdition();
		});
		colWholeCost.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colWholeCost.setOnEditCommit(e ->  {
			try {
				Product p = e.getTableView().getItems().get(e.getTablePosition().getRow());
				p.setWholeCost(e.getNewValue());
				updateProduct(p);
				refreshSelectionAfterEdition();
			} catch(NumberFormatException exc) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al editar producto",
						"La cantidad debe ser un número.",
						(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
			}
		});
		colWholePrice.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colWholePrice.setOnEditCommit(e ->  {
			try {
				Product p = e.getTableView().getItems().get(e.getTablePosition().getRow());
				p.setWholePrice(e.getNewValue());
				updateProduct(p);
				refreshSelectionAfterEdition();
			} catch(NumberFormatException exc) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al editar producto",
						"La cantidad debe ser un número.",
						(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
			}
		});
		colRetailCost.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colRetailCost.setOnEditCommit(e ->  {
			try {
				Product p = e.getTableView().getItems().get(e.getTablePosition().getRow());
				p.setRetailCost(e.getNewValue());
				updateProduct(p);
				refreshSelectionAfterEdition();
			} catch(NumberFormatException exc) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al editar producto",
						"La cantidad debe ser un número.",
						(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
			}
		});
		colRetailPrice.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colRetailPrice.setOnEditCommit(e ->  {
			try {
				Product p = e.getTableView().getItems().get(e.getTablePosition().getRow());
				p.setRetailPrice(e.getNewValue());
				updateProduct(p);
				refreshSelectionAfterEdition();
			} catch(NumberFormatException exc) {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al editar producto",
						"La cantidad debe ser un número.",
						(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
			}
		});
		colInternalCode.setCellFactory(TextFieldTableCell.forTableColumn());
		colInternalCode.setOnEditCommit(e -> editProductInternalCode(e));
		colBarcode.setCellFactory(TextFieldTableCell.forTableColumn());
		colBarcode.setOnEditCommit(e -> editProductBarcode(e));
		
		tabProducts.setEditable(true);
	}
	
	public void setTableContent(List<Product> data) {
		if(data != null) {
			if(InventoryAddForm.getInventoryAddFormController() != null) {
				if(InventoryAddForm.getInventoryAddFormController().getProduct() != null) {
					if(InventoryAddForm.getInventoryAddFormController().getProduct().getQuantity() == null) {
						data.remove(data.size() - 1);
					}
				}
			}
			ObservableList<Product> products = FXCollections.observableArrayList(data);
			tabProducts.setItems(products);
		}
	}
	
	public void cleanProductDetails() {
		txtProduct.clear();
     	txtCategory.clear();
     	txtBarcode.clear();
     	txtInternalCode.clear();
     	txtBrand.clear();
     	txtNetContent.clear();
     	txtQuantity.clear();
     	txtBatches.clear();
     	txtWholeUnit.clear();
     	txtRetailUnit.clear();
     	txtWholePrice.clear();
     	txtRetailPrice.clear();
	}
	
	//Editing a product by its NAME*****
	public void editProductName(CellEditEvent<Product, String> e) {
		if(!e.getNewValue().trim().equals("")) {
			Product productFound = checkProductExistenceByName(e.getNewValue().trim());
			if(productFound == null) {
				changeProductName(e.getTableView().getItems().get(e.getTablePosition().getRow()), e.getNewValue().trim());
				e.getTableView().getItems().get(e.getTablePosition().getRow()).setProduct(e.getNewValue().trim());
				refreshSelectionAfterEdition();
			} else {
				if(productFound.getId() != e.getTableView().getItems().get(e.getTablePosition().getRow()).getId()) {
					Dialogs d = new Dialogs();
					d.acceptDialog("Error al editar producto",
							"El nombre que deseas proporcionar ya está en uso \npor otro producto.",
							(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
					//showProductList();
					tabProducts.refresh();
				} else {
					changeProductName(e.getTableView().getItems().get(e.getTablePosition().getRow()), e.getNewValue().trim());
					e.getTableView().getItems().get(e.getTablePosition().getRow()).setProduct(e.getNewValue().trim());
					refreshSelectionAfterEdition();
				}
			}
		}
	}
	
	public Product checkProductExistenceByName(String string) {
		WarehouseService service = new WarehouseService();
		Product p = new Product();
		p.setProduct(string);
		Product product = service.searchProduct(p);
		return product;
	}
	
	
	public void changeProductName(Product p, String newString) {
		p.setProduct(newString);
		WarehouseService service = new WarehouseService();
		service.updateProduct(p);
	}
	
	//Editing a product by its BARCODE*****
	public void editProductBarcode(CellEditEvent<Product, String> e) {
		if(!e.getNewValue().trim().equals("")) {
			Product productFound = checkProductExistenceByBarcode(e.getNewValue().trim());
			if(productFound == null) {
				changeProductBarcode(e.getTableView().getItems().get(e.getTablePosition().getRow()), e.getNewValue().trim());
				e.getTableView().getItems().get(e.getTablePosition().getRow()).setBarCode(e.getNewValue().trim());
				refreshSelectionAfterEdition();
			} else {
				if(productFound.getId() != e.getTableView().getItems().get(e.getTablePosition().getRow()).getId()) {
					Dialogs d = new Dialogs();
					d.acceptDialog("Error al editar producto",
							"El código de barras que deseas proporcionar ya \nestá en uso por otro producto.",
							(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
					//showProductList();
					tabProducts.refresh();
				} else {
					changeProductBarcode(e.getTableView().getItems().get(e.getTablePosition().getRow()), e.getNewValue().trim());
					e.getTableView().getItems().get(e.getTablePosition().getRow()).setBarCode(e.getNewValue().trim());
					refreshSelectionAfterEdition();
				}
			}
		}
	}
	
	public Product checkProductExistenceByBarcode(String string) {
		WarehouseService service = new WarehouseService();
		Product p = new Product();
		p.setBarCode(string);
		Product product = service.searchProduct(p);
		return product;
	}
	
	public void changeProductBarcode(Product p, String newString) {
		p.setBarCode(newString);
		WarehouseService service = new WarehouseService();
		service.updateProduct(p);
	}
	
	//Editing a product by its INTERNAL CODE*****
	public void editProductInternalCode(CellEditEvent<Product, String> e) {
		if(!e.getNewValue().trim().equals("")) {
			Product productFound = checkProductExistenceByInternalCode(e.getNewValue().trim());
			if(productFound == null) {
				changeProductInternalCode(e.getTableView().getItems().get(e.getTablePosition().getRow()), e.getNewValue().trim());
				e.getTableView().getItems().get(e.getTablePosition().getRow()).setInternalCode(e.getNewValue().trim());
				refreshSelectionAfterEdition();
			} else {
				if(productFound.getId() != e.getTableView().getItems().get(e.getTablePosition().getRow()).getId()) {
					Dialogs d = new Dialogs();
					d.acceptDialog("Error al editar producto",
							"El código de barras que deseas proporcionar ya \nestá en uso por otro producto.",
							(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
					//showProductList();
					tabProducts.refresh();
				} else {
					changeProductInternalCode(e.getTableView().getItems().get(e.getTablePosition().getRow()), e.getNewValue().trim());
					e.getTableView().getItems().get(e.getTablePosition().getRow()).setInternalCode(e.getNewValue().trim());
					refreshSelectionAfterEdition();
				}
			}
		}
	}
	
	public Product checkProductExistenceByInternalCode(String string) {
		WarehouseService service = new WarehouseService();
		Product p = new Product();
		p.setInternalCode(string);
		Product product = service.searchProduct(p);
		return product;
	}
	
	public void changeProductInternalCode(Product p, String newString) {
		p.setInternalCode(newString);
		WarehouseService service = new WarehouseService();
		service.updateProduct(p);
	}
	
	//Common methods when editing
	public void refreshSelectionAfterEdition() {
		tabProducts.getSortOrder().clear();
		Product selectedItem = tabProducts.getSelectionModel().getSelectedItem();
		Integer selectedIndex = tabProducts.getSelectionModel().getSelectedIndex();
		showProductList();
		//tabProducts.refresh();
		selectEditedProduct(selectedItem, selectedIndex);
	}
	
	public void updateProduct(Product p) {
		WarehouseService service = new WarehouseService();
		service.updateProduct(p);
	}
	
	//Checking if any of the products in the list was saved incorrectly --------------
	public List<Product> debugProductList(List<Product> list) {
		for(int i=0; i<list.size(); i++) {
			debugProduct(list.get(i));
		}
		return list;
	}
	
	public Product debugProduct(Product p) {
		if(p.getProduct().equals(""))
			p.setProduct("");
		if(p.getBarCode().equals(""))
			p.setBarCode("");
		return p;
	}
	
	
	//--------------------------------------- HELPING DIALOGS -----------------------------------------------
	@FXML
	protected void clickedBtnSearchHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Lista de productos",
				"Obtén una vista rápida del producto que desees, buscándolo en la tabla de productos.",
				(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
	}
	
	@FXML
	protected void clickedBtnDetailsHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Detalle del producto",
				"Los detalles del producto que selecciones en la lista aparecerán mostrados aquí.",
				(StackPane)Dashboard.getStage().getScene().getRoot(), txtSearchProduct);
	}
	
}