package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.controlsfx.control.textfield.AutoCompletionBinding;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.entities.ProductType;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.TextFieldMethods;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.CategoryForm;
import com.masterdev.student.views.InventoryAddForm;
import com.masterdev.student.views.PurchaseUnitForm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryFormController implements Initializable {
	
	@FXML TextField txtSearchCategory;
	@FXML TextField txtSelectedCategory;
	
	@FXML JFXButton btnSearchCategory;
	@FXML JFXButton btnAddCategory;
	@FXML JFXButton btnEditCategory;
	@FXML JFXButton btnShowCategories;
	@FXML JFXButton btnCancel;
	@FXML JFXButton btnAccept;
	
	@FXML TableView<ProductType> tabCategories;
	@FXML TableColumn<ProductType, String> colCategoryName;
	
	private AutoCompletionBinding<String> categorySuggestions;
	
	public void initialize(URL location, ResourceBundle resources) {
		showCategoryList();
		
		//Adding a listener to the table
		tabCategories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductType>() {
		    @Override
		    public void changed(ObservableValue<? extends ProductType> observableValue, ProductType oldValue, ProductType newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabCategories.getSelectionModel().getSelectedItem() != null) 
		        {    
		           TableViewSelectionModel<ProductType> selectionModel = tabCategories.getSelectionModel();
		           ObservableList selectedCells = selectionModel.getSelectedCells();
		           TablePosition tablePosition = (TablePosition) selectedCells.get(0);
		           String selected = (String) tablePosition.getTableColumn().getCellData(newValue);
		           txtSelectedCategory.setText(selected);
		         }
	         }
	     });
		
		suggestCategories();
	}
	
	/*--------------------------------- Methods for handling data ----------------------------------*/
	@FXML
	protected void acceptTransaction() {
		if(fieldsAreFilledUp()) {
				updateInventoryAddFormData();
				updateFields();
				CategoryForm.getStage().close();
		} else {
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al agregar categoría",
					"Asegúrate de haber llenado todos los campos correctamente.",
					(StackPane)CategoryForm.getStage().getScene().getRoot());
		}
			 
	}
	
	@FXML 
	protected void cancelTransaction() {
		CategoryForm.getStage().close();
	}
	
	public void updateInventoryAddFormData() throws NumberFormatException {
		InventoryAddForm.getInventoryAddFormController().refreshCategories();
		InventoryAddForm.getInventoryAddFormController().setCmbxCategoryContent(txtSelectedCategory.getText());
	}
	
	public void updateFields() {
		txtSelectedCategory.setText(InventoryAddForm.getInventoryAddFormController().getCategoryName());
	}
	
	public Boolean fieldsAreFilledUp() {
		if(!txtSelectedCategory.getText().equals(""))
			return true;
		else
			return false;
	}
	
	//--------------------------------------- Methods for finishing the transaction ------------------------------------//
	public void closeStageCompletely() {
		if(CategoryForm.getStage() != null) {
			CategoryForm.getStage().close();
			CategoryForm.setStage(null);
		}
	}
	
	//--------------------------------------- Methods for adding a category ------------------------------------//
	@FXML 
	protected void addCategory() {
		if(!txtSearchCategory.getText().equals("")) {
			Integer result = createCategory();
			switch(result) {
				case 1: Dialogs d1 = new Dialogs();
						d1.acceptDialog("Error al guardar la categoría",
									"Hubo un error al intentar guardar la categoría. \nInténtalo nuevamente.",
									(StackPane)CategoryForm.getStage().getScene().getRoot());
						break;
				case 2: Dialogs d2 = new Dialogs();
						d2.acceptDialog("Categoría existente",
								"Esta categoría ya fue registrada anteriormente.",
								(StackPane)CategoryForm.getStage().getScene().getRoot());
						break;
			}
			cleanTextFields();
			showCategoryList();
			categorySuggestions.dispose(); 	//We get rid of the past list of word suggestions
			suggestCategories();			//And add one the most recent one
		}
	}
	
	public Integer createCategory() {
		ProductType productType = new ProductType(txtSearchCategory.getText());
		WarehouseService ws = new WarehouseService();
		Integer exit = ws.addProductType(productType);
		return exit;
	}
	
	//------------------------------- METHODS FOR SEARCHING A CATEGORY ----------------------------------//
	@FXML
	protected void searchCategory() {
		if(!txtSearchCategory.getText().equals("")) {
			ProductType categoryFound = findCategory(txtSearchCategory.getText());
			if(categoryFound != null)
				showResult(categoryFound);
			else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Categoría no encontrada",
						"No se encontraron coincidencias.",
						(StackPane)CategoryForm.getStage().getScene().getRoot());
			}
			cleanTextFields();
		}
	}
	
	public ProductType findCategory(String categoryName) {
		ProductType productTypeReturn = null;
		ProductType pt = new ProductType(categoryName);
		WarehouseService ws = new WarehouseService();
		productTypeReturn = ws.searchProductType(pt);
		return productTypeReturn;
	}
	
	public void showResult(ProductType result) {
		colCategoryName.setCellValueFactory(new PropertyValueFactory<ProductType,String>("type"));
		
		ArrayList<ProductType> data = new ArrayList<ProductType>();
		data.add(result);
		ObservableList<ProductType> categories = FXCollections.observableArrayList(data);
		
		tabCategories.setItems(categories);
	}
	
	protected void suggestCategories() {
		TextFieldMethods tfm = new TextFieldMethods();
		WarehouseService service = new WarehouseService();
		List<ProductType> data = service.showProductTypes();
		ArrayList<String> categories = new ArrayList<String>();
		for(ProductType p : data) {
			categories.add(p.getType());
		}
		categorySuggestions = tfm.addWordSuggestions(txtSearchCategory, categories);
	}
	
	//------------------------------- Methods for editing a category --------------------------------------//
	@FXML
	protected void editCategory() {
		ProductType selectedCategory = tabCategories.getSelectionModel().getSelectedItem();
		if(selectedCategory != null) {
			if(!txtSearchCategory.getText().equals("")) {
				ProductType newCategory = findCategory(selectedCategory.getType());
				changeCategoryName(newCategory, txtSearchCategory.getText());
				
				txtSelectedCategory.setText(txtSearchCategory.getText());
				cleanTextFields();
				showCategoryList();
				categorySuggestions.dispose(); 	//We get rid of the past list of word suggestions
				suggestCategories();			//And add one the most recent one
				
			} else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al editar categoría",
						"Asegúrate de haber escrito el nuevo nombre de la\ncategoría en el cuadro de texto.",
						(StackPane)CategoryForm.getStage().getScene().getRoot());
			}
		} else {
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al editar categoría",
					"Asegúrate de haber seleccionado una categoría en la tabla \"Categorías\".",
					(StackPane)CategoryForm.getStage().getScene().getRoot());
		}
	}
	
	public void changeCategoryName(ProductType productType, String newCategoryName) {
		productType.setType(newCategoryName);
		WarehouseService ws = new WarehouseService();
		ws.updateProductType(productType);
	}
	
	
	//------------------------------- Methods for showing the category list --------------------------------------//
	@FXML
	protected void showCategories() {
		showCategoryList();
	}
	
	public void showCategoryList() {
		colCategoryName.setCellValueFactory(new PropertyValueFactory<ProductType,String>("type"));
		
		WarehouseService service = new WarehouseService();
		List<ProductType> data = service.showProductTypes();
		ObservableList<ProductType> categories = FXCollections.observableArrayList(data);
		
		tabCategories.setItems(categories);
	}
	
	//------------------------------------ METHODS FOR CLEANING THE GRAPHIC COMPONENTS --------------------------//
	public void cleanTextFields() {
		txtSearchCategory.setText("");
	}
}
