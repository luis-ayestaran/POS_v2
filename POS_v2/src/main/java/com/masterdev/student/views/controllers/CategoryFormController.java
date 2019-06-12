package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.AutoCompletionBinding;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.entities.ProductType;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.TextFieldMethods;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.CategoryForm;
import com.masterdev.student.views.EditCategoryForm;
import com.masterdev.student.views.InventoryAddForm;
import com.masterdev.student.views.NewCategoryForm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryFormController implements Initializable {
	
	@FXML TextField txtSearchCategory;
	@FXML TextField txtAddCategory;
	@FXML TextField txtEditCategory;
	@FXML TextField txtSelectedCategory;
	
	@FXML JFXButton btnSearchCategory;
	@FXML JFXButton btnAddCategory;
	@FXML JFXButton btnEditCategory;
	@FXML JFXButton btnShowCategories;
	@FXML JFXButton btnCancel;
	@FXML JFXButton btnAccept;
	
	@FXML Button btnSelectedCategoryHelp;
	
	@FXML TableView<ProductType> tabCategories;
	@FXML TableColumn<ProductType, String> colCategoryName;
	
	private AutoCompletionBinding<String> categorySuggestions;
	
	//-------------------------------- INITIALISING -------------------------------------------------//
	
	public void initialize(URL location, ResourceBundle resources) {
		//Following our standard process
		initialiseTooltipText();											//1. Initialising tooltip texts
		List<ProductType> categories = showCategoryList();					
		suggestCategories(categories);										//2. Add word suggestions (if needed)
		initialiseListeners();												//3. Add table listeners
		
		//Checking if a category's been selected before opening this view
		if(InventoryAddForm.getInventoryAddFormController().getCategoryName() != null)
			txtSelectedCategory.setText(InventoryAddForm.getInventoryAddFormController().getCategoryName());
	}
	
	private void initialiseTooltipText() {
		btnSelectedCategoryHelp.setTooltip(new Tooltip("Selecciona una categoría de la lista."));
	}
	
	
	private void initialiseListeners() {
		tabCategories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductType>() {
		    @Override
		    public void changed(ObservableValue<? extends ProductType> observableValue, ProductType oldValue, ProductType newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(tabCategories.getSelectionModel().getSelectedItem() != null) 
		        {
		           TableViewSelectionModel<ProductType> selectionModel = tabCategories.getSelectionModel();
		           ProductType selectedItem = selectionModel.getSelectedItem();
		           /*ObservableList selectedCells = selectionModel.getSelectedCells();
		           TablePosition tablePosition = (TablePosition) selectedCells.get(0);
		           String selected = (String) tablePosition.getTableColumn().getCellData(newValue);*/
		           txtSelectedCategory.setText(selectedItem.getType());
		         }
	         }
	     });
	}
	
	//------------------------------- LOADING VIEWS -------------------------------------------
		public void loadNewCategoryFormView() {
			if(NewCategoryForm.getStage() != null) {
				NewCategoryForm.getStage().show();
				NewCategoryForm.getStage().setAlwaysOnTop(true);
				NewCategoryForm.getStage().setAlwaysOnTop(false);
			} else {
				NewCategoryForm view = new NewCategoryForm();
				view.loadView();
			}
		}
		
		public void loadEditCategoryFormView() {
			if(EditCategoryForm.getStage() != null) {
				EditCategoryForm.getStage().show();
				EditCategoryForm.getStage().setAlwaysOnTop(true);
				EditCategoryForm.getStage().setAlwaysOnTop(false);
			} else {
				EditCategoryForm view = new EditCategoryForm();
				view.loadView();
			}
		}
		
	/*--------------------------------- Methods for handling data ----------------------------------*/
	@FXML
	protected void acceptTransaction() {
		if(fieldsAreFilledUp()) {
			//If we have all the information necessary, we:
			unhighlightObligatoryFields();			//If we had a warning about obligatory fields, it will disappear.
			updateInventoryAddFormData(true);		//Update the data in the calling window,
			updateFields();							//Update the info in our fields,
			CategoryForm.getStage().close();		//Close the view without losing information (which saves processing time),
		} else {									//If we don't:
			unhighlightObligatoryFields();
			highlightObligatoryFields();		//The window will help us highlighting the fields that we must complete before continuing
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al agregar categoría",
					"Asegúrate de haber llenado todos los campos correctamente.",
					(StackPane)CategoryForm.getStage().getScene().getRoot());
		}
			 
	}
	
	@FXML 
	protected void cancelTransaction() {
		cancel();
	}
	
	public void cancel() {
		updateInventoryAddFormData(false);
		updateFields();
		CategoryForm.getStage().close();
	}
	
	//--------------------------- Updating info inside and outside this view ---------------------------------//
	public void updateInventoryAddFormData(Boolean acceptProcess) {
		InventoryAddForm.getInventoryAddFormController().refreshCategories();		//Refreshes categories in InventoryAddForm's combobox
		if(acceptProcess)
			InventoryAddForm.getInventoryAddFormController().setCmbxCategoryContent(txtSelectedCategory.getText());	//If we accepted, it renews the selected category in InventoryAddForm
		else
			InventoryAddForm.getInventoryAddFormController().setCmbxCategoryContent(InventoryAddForm.getInventoryAddFormController().getCategoryName());  //If not, it keeps the selected category
	}
	
	public void updateFields() {
		if(InventoryAddForm.getInventoryAddFormController().getCategoryName() != null)
			txtSelectedCategory.setText(InventoryAddForm.getInventoryAddFormController().getCategoryName());
		else
			txtSelectedCategory.setText("");
	}
	
	//
	public Boolean fieldsAreFilledUp() {
		if(!txtSelectedCategory.getText().trim().equals(""))
			return true;
		else
			return false;
	}
	
	//Methods for catching the information coming from Adding and Editing category forms
	public void selectFoundCategory(ProductType category) {
		txtSelectedCategory.setText(category.getType());
		tabCategories.requestFocus();
		tabCategories.getSelectionModel().selectFirst();
	}
	
	public void selectAddedCategory(ProductType category) {
		txtSelectedCategory.setText(category.getType());
		tabCategories.requestFocus();
		tabCategories.getSelectionModel().selectLast();
		tabCategories.scrollTo(tabCategories.getSelectionModel().getSelectedIndex());
	}
	
	public void selectEditedCategory(ProductType category, Integer index) {
		txtSelectedCategory.setText(category.getType());
		tabCategories.requestFocus();
		tabCategories.getSelectionModel().select(index);  
		//tabCategories.scrollTo(tabCategories.getSelectionModel().getSelectedIndex());
	}
	
	//Just in case the user didn't notice the obligatory fields sign next to them.
		private void unhighlightObligatoryFields() {
			if(!txtSelectedCategory.getStyleClass().isEmpty())
				txtSelectedCategory.getStyleClass().removeAll("important");
		}
		
		private void highlightObligatoryFields() {
			if(txtSelectedCategory.getText().trim().equals(""))
				txtSelectedCategory.getStyleClass().add("important");
		}
	
	//------------------------------- METHODS FOR SEARCHING A CATEGORY ----------------------------------//
	@FXML
	protected void searchCategory() {
		if(!txtSearchCategory.getText().trim().equals("")) {
			ProductType categoryFound = findCategory(txtSearchCategory.getText().trim());
			if(categoryFound != null) {
				showResult(categoryFound);
				selectFoundCategory(categoryFound);
			} else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Categoría no encontrada",
						"No se encontraron coincidencias.",
						(StackPane)CategoryForm.getStage().getScene().getRoot());
			}
			cleanSearchField();
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
	
	protected void suggestCategories(List<ProductType> data) {
		if(data != null) {
			if(categorySuggestions != null)
				categorySuggestions.dispose(); 							//We get rid of the past list of word suggestions
			TextFieldMethods tfm = new TextFieldMethods();
			ArrayList<String> categories = new ArrayList<String>();
			for(ProductType p : data) {
				categories.add(p.getType());
			}
			categorySuggestions = tfm.addWordSuggestions(txtSearchCategory, categories);
		}
	}
	
	//--------------------------------------- METHODS FOR ADDING A CATEGORY ------------------------------------//
	@FXML 
	protected void addCategory() {
		loadNewCategoryFormView();
	}
	
	//------------------------------- METHODS FOR EDITING A CATEGORY --------------------------------------//
	@FXML
	protected void editCategory() {
		ProductType selectedCategory = tabCategories.getSelectionModel().getSelectedItem();
		Integer index = tabCategories.getSelectionModel().getSelectedIndex();
		if(selectedCategory != null) {
			loadEditCategoryFormView();
			EditCategoryForm.getEditCategoryFormController().setCurrentCategory(selectedCategory, index);
		} else {
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al editar categoría",
					"Asegúrate de haber seleccionado, en la LISTA DE CATEGORÍAS, la categoría que vas a editar.",
					(StackPane)CategoryForm.getStage().getScene().getRoot());
		}
	}
	
	
	//------------------------------- METHODS FOR SHOWING THE CATEGORY LIST --------------------------------------//
	@FXML
	protected void showCategories() {
		showCategoryList();
	}
	
	public List<ProductType> showCategoryList() {
		colCategoryName.setCellValueFactory(new PropertyValueFactory<ProductType,String>("type"));
		
		WarehouseService service = new WarehouseService();
		List<ProductType> data = service.showProductTypes();
		if(data != null) {
			ObservableList<ProductType> categories = FXCollections.observableArrayList(data);
			tabCategories.setItems(categories);
		}
		
		return data;
	}
	
	//------------------------------------ Methods for cleaning the graphic components --------------------------//
	public void cleanSearchField() {
		txtSearchCategory.setText("");
	}
	
	public void cleanResultField() {
		txtSelectedCategory.setText("");
	}
	
	//--------------------------------------- Methods for closing the view completely ------------------------------------//
		public void closeStageCompletely() {
			//Verifying if there's a pop-up menu open to close them
			if(NewCategoryForm.getNewCategoryFormController() != null)
				NewCategoryForm.getNewCategoryFormController().closeStageCompletely();
			if(EditCategoryForm.getEditCategoryFormController() != null)
				EditCategoryForm.getEditCategoryFormController().closeStageCompletely();
			
			//Closing this stage completely
			if(CategoryForm.getStage() != null) {
				CategoryForm.getStage().close();
				CategoryForm.setStage(null);
			}
		}
	
	//-------------------------------- HELPING DIALOGS -----------------------------------------
	@FXML
	protected void clickedBtnSelectedCategoryHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Categoría seleccionada",
				"La categoría que selecciones en la lista de categorías \naparecerá en el cuadro de texto 'Categoría seleccionada'. \nEste campo es obligatorio.",
				(StackPane)CategoryForm.getStage().getScene().getRoot());
	}
}
