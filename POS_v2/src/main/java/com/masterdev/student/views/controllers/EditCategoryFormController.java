package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.entities.ProductType;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.CategoryForm;
import com.masterdev.student.views.EditCategoryForm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;

public class EditCategoryFormController implements Initializable {
	@FXML TextField txtCurrentCategory;
	@FXML TextField txtEditCategory;
	
	@FXML Button btnCurrentCategoryHelp;
	@FXML Button btnEditCategoryHelp;
	
	@FXML JFXButton btnCancel;
	@FXML JFXButton btnAccept;
	
	private ProductType productType;
	private Integer index;
	private String currentName;
	private String newName;
	
	//----------------------------------- getting and setting methods -------------------------------------------------//
	public ProductType getProductType() {
		return productType;
	}
	
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	public Integer getIndex() {
		return index;
	}
	
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public String getCurrentName() {
		return currentName;
	}
	
	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}
	
	public String getNewName() {
		return newName;
	}
	
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	//-------------------------------- INITIALISING -------------------------------------------------//
	
	public void initialize(URL location, ResourceBundle resources) {
		initialiseTooltipText();
	}
	
	public void initialiseTooltipText() {
		btnCurrentCategoryHelp.setTooltip(new Tooltip("Nombre de la categoría que quieres editar."));
		btnEditCategoryHelp.setTooltip(new Tooltip("Nuevo nombre que le asignarás a la categoría."));
	}
	
	public void setCurrentCategory(ProductType currentCategory, Integer index) {
		setIndex(index);
		setProductType(currentCategory);
		setCurrentName(currentCategory.getType());
		txtCurrentCategory.setText(getCurrentName());
	}
	
	
	@FXML
	protected void acceptTransaction() {
		editCategory();
	}
	
	public void editCategory() {
		if(fieldsAreFilledUp()) {
			ProductType newCategory = CategoryForm.getCategoryFormController().findCategory(txtEditCategory.getText().trim());
			if(newCategory == null) {
				changeCategoryName(getProductType(), txtEditCategory.getText().trim());
				List<ProductType> categories = CategoryForm.getCategoryFormController().showCategoryList();
				CategoryForm.getCategoryFormController().cleanSearchField();						//Cleaning text field
				CategoryForm.getCategoryFormController().suggestCategories(categories);			//And adding the most recent category added
				CategoryForm.getCategoryFormController().updateInventoryAddFormData(false);
				CategoryForm.getCategoryFormController().selectEditedCategory(getProductType(), getIndex());
				closeStageCompletely();
			} else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error al editar categoría",
					"El nombre que deseas proporcionar ya está en uso \npor otra categoría.",
					(StackPane)EditCategoryForm.getStage().getScene().getRoot(), txtEditCategory);
			}
			
		} else {
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al editar categoría",
					"Asegúrate de haber escrito el nuevo nombre de la\ncategoría en el cuadro de texto.",
					(StackPane)EditCategoryForm.getStage().getScene().getRoot(), txtEditCategory);
		}
	}
	
	public Boolean fieldsAreFilledUp() {
		if(!txtEditCategory.getText().trim().equals(""))
			return true;
		else
			return false;
	}
	
	public void changeCategoryName(ProductType productType, String newCategoryName) {
		productType.setType(newCategoryName);
		WarehouseService service = new WarehouseService();
		service.updateProductType(productType);
	}
	
	@FXML
	protected void cancelTransaction() {
		closeStageCompletely();
	}
	
	//-------------------------------------------- METHODS FOR CLOSING THE OPERATION AND THE VIEW -------------------------------------------//
	
	public void closeStageCompletely() {
		if(EditCategoryForm.getStage() != null) {
			EditCategoryForm.getStage().close();
			EditCategoryForm.setStage(null);
		}
	}
	
	//-------------------------------- HELPING DIALOGS -----------------------------------------
	@FXML
	protected void clickedBtnCurrentCategoryHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Nombre actual",
				"Nombre actual de la categoría que seleccionaste para editar.",
				(StackPane)EditCategoryForm.getStage().getScene().getRoot(), txtEditCategory);
	}
	
	@FXML
	protected void clickedBtnEditCategoryHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Nuevo nombre",
				"Nuevo nombre que le asignarás a la categoría.",
				(StackPane)EditCategoryForm.getStage().getScene().getRoot(), txtEditCategory);
	}
}

