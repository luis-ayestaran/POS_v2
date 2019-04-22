package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.entities.Product;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class InventoryListController implements Initializable {
	
	@FXML TableView<Product> tblProducts;
	@FXML JFXButton btnSearch;
	@FXML JFXButton btnFilters;
	@FXML JFXButton btnDelete;
	@FXML JFXButton btnEdit;
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}