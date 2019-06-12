package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.AutoCompletionBinding;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.entities.Product;
import com.masterdev.student.middle.TextFieldMethods;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.Dashboard;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;

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
	@FXML TableColumn<Product, Long> colBarcode;
	
	@FXML TextField txtSearchProduct;
	
	@FXML JFXButton btnAddProduct;
	@FXML JFXButton btnSearchProduct;
	@FXML JFXButton btnFilters;
	@FXML JFXButton btnDeleteProduct;
	@FXML JFXButton btnEditProduct;
	
	private AutoCompletionBinding<String> productSuggestions;
	
	//--------------------------------------- INITIALISING ------------------------------------//
	public void initialize(URL location, ResourceBundle resources) {
		List<Product> products = showProductList();
		suggestProducts(products);										//2. Add word suggestions (if needed)
		initialiseListeners();
	}
	
	public void initialiseListeners() {
		tabProducts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
		    @Override
		    public void changed(ObservableValue<? extends Product> observableValue, Product oldValue, Product newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabProducts.getSelectionModel().getSelectedItem() != null) 
		        {
		           TableViewSelectionModel<Product> selectionModel = tabProducts.getSelectionModel();
		           Product selectedItem = selectionModel.getSelectedItem();
		           /*ObservableList selectedCells = selectionModel.getSelectedCells();
		           TablePosition tablePosition = (TablePosition) selectedCells.get(0);
		           String selected = (String) tablePosition.getTableColumn().getCellData(newValue);*/
		           System.out.println(selectedItem);
		           //txtSelectedCategory.setText(selectedItem.getType());
		         }
	         }
	     });
	}
	
	//--------------------------------------- LOADING VIEWS ------------------------------------//
	@FXML
	protected void loadInventoryAddFormView() {
		Dashboard.getDashboardController().inventoryAddFormWithoutMenu();
	}
	
	//--------------------------------------- METHODS FOR HANDLING THE DATA ------------------------------------//
	
	
	//--------------------------------------- METHODS FOR ADDING A PRODUCT ------------------------------------//
	
	
	//------------------------------- METHODS FOR SEARCHING A PRODUCT ----------------------------------//
	protected void suggestProducts(List<Product> data) {
		if(productSuggestions != null)
			productSuggestions.dispose(); 							//We get rid of the past list of word suggestions
		TextFieldMethods tfm = new TextFieldMethods();
		ArrayList<String> products = new ArrayList<String>();
		for(Product p : data) {
			products.add(p.getProduct());
		}
		productSuggestions = tfm.addWordSuggestions(txtSearchProduct, products);
	}
	
	//------------------------------- METHODS FOR EDITING A PRODUCT ----------------------------------//
	
	
	//------------------------------- METHODS FOR SHOWING ALL PRODUCTS ----------------------------------//
	@FXML
	protected void showProducts() {
		showProductList();
	}
	
	public List<Product> showProductList() {
		colName.setCellValueFactory(new PropertyValueFactory<Product,String>("product"));
		colBrand.setCellValueFactory(new PropertyValueFactory<Product,String>("brand"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<Product,Float>("quantity"));
		colWholeUnit.setCellValueFactory(new PropertyValueFactory<Product,String>("purchaseUnit"));
		colRetailUnit.setCellValueFactory(new PropertyValueFactory<Product,String>("purchaseSubunit"));
		colWholeCost.setCellValueFactory(new PropertyValueFactory<Product,Float>("wholeCost"));
		colWholePrice.setCellValueFactory(new PropertyValueFactory<Product,Float>("wholePrice"));
		colRetailCost.setCellValueFactory(new PropertyValueFactory<Product,Float>("retailCost"));
		colRetailPrice.setCellValueFactory(new PropertyValueFactory<Product,Float>("retailPrice"));
		colBarcode.setCellValueFactory(new PropertyValueFactory<Product,Long>("barCode"));
		
		WarehouseService service = new WarehouseService();
		List<Product> data = service.showProducts();
		//List<Product> productList = service.showProducts();
		//List<Product> data = null;
		if(data != null) {//productList != null) {
			//data = debugProductList(productList);
			ObservableList<Product> products = FXCollections.observableArrayList(data);
			tabProducts.setItems(products);
		}
		return data;
	}
	
	//Checking if any of the products in the list was saved incorrectly 
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
	
}