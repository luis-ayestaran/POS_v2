package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.entities.ProductType;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.CategoryForm;
import com.masterdev.student.views.NewCategoryForm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;

public class NewCategoryFormController implements Initializable {
	
	@FXML TextField txtAddCategory;
	
	@FXML Button btnAddCategoryHelp;
	
	@FXML JFXButton btnCancel;
	@FXML JFXButton btnAccept;
	
	public void initialize(URL location, ResourceBundle resources) {
		initialiseTooltipText();
	}
	
	public void initialiseTooltipText() {
		btnAddCategoryHelp.setTooltip(new Tooltip("Nombre de la nueva categoría que quieres crear."));
	}
	
	//--------------------------------- Methods for handling data ----------------------------------//
	@FXML
	protected void acceptTransaction() {
		addCategory();
	}
	
	public void addCategory() {
		if(fieldsAreFilledUp()) {					//If we have all the information necessary, we:
			Integer result = createCategory(txtAddCategory.getText().trim());
			switch(result) {
				case 0: List<ProductType> categories = CategoryForm.getCategoryFormController().showCategoryList();
						CategoryForm.getCategoryFormController().cleanSearchField();						//Cleaning text field
						CategoryForm.getCategoryFormController().suggestCategories(categories);			//And adding the most recent category added
						CategoryForm.getCategoryFormController().updateInventoryAddFormData(false);
						ProductType newCategory = CategoryForm.getCategoryFormController().findCategory(txtAddCategory.getText().trim());
						CategoryForm.getCategoryFormController().selectAddedCategory(newCategory);
						closeStageCompletely();
						break;
				case 1: Dialogs d1 = new Dialogs();
						d1.acceptDialog("Error al guardar la categoría",
								"Hubo un error al intentar guardar la categoría. \nInténtalo nuevamente.",
								(StackPane)NewCategoryForm.getStage().getScene().getRoot(), txtAddCategory);
						break;
				case 2: Dialogs d2 = new Dialogs();
						d2.acceptDialog("Categoría existente",
								"Esta categoría ya fue registrada anteriormente.",
								(StackPane)NewCategoryForm.getStage().getScene().getRoot(), txtAddCategory);
						break;
			}
		} else {
			Dialogs d = new Dialogs();
			d.acceptDialog("Error al guardar la categoría",
					"Asegúrate de haber llenado el campo de texto correctamente.",
					(StackPane)NewCategoryForm.getStage().getScene().getRoot(), txtAddCategory);
		}
	}
	
	public Integer createCategory(String category) {
		ProductType productType = new ProductType(category);
		WarehouseService ws = new WarehouseService();
		Integer exit = ws.addProductType(productType);
		return exit;
	}
	
	public Boolean fieldsAreFilledUp() {
		if(!txtAddCategory.getText().trim().equals(""))
			return true;
		else
			return false;
	}
	
	@FXML
	protected void cancelTransaction() {
		closeStageCompletely();
	}
	
	//-------------------------------------------- METHODS FOR CLOSING THE OPERATION AND THE VIEW -------------------------------------------//
	
	public void closeStageCompletely() {
		if(NewCategoryForm.getStage() != null) {
			NewCategoryForm.getStage().close();
			NewCategoryForm.setStage(null);
		}
	}
	
	//-------------------------------- HELPING DIALOGS -----------------------------------------
	@FXML
	protected void clickedBtnAddCategoryHelp() {
		Dialogs d = new Dialogs();
		d.acceptDialog("Nombre actual",
				"Nombre de la nueva categoría que quieres crear.",
				(StackPane)NewCategoryForm.getStage().getScene().getRoot(), txtAddCategory);
	}
}
