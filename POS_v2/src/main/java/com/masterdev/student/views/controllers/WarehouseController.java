package com.masterdev.student.views.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;

import java.util.ResourceBundle;

import com.masterdev.student.views.WarehouseForm;

public class WarehouseController implements Initializable {
	@FXML Button btnAddWarehouse;
	@FXML Button btnAddAsset;
	
	@FXML TableView<String> tabWarehouse;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		//TableViewMethods tvm = new TableViewMethods();
		//tvm.setWarehouseTable(tabWarehouse);
		/*ObservableList<String> data = FXCollections.observableArrayList(
		            new String("Hello"),
		            new String("Goodbye"),
		            new String("What's up?"),
		            new String("How are you?"),
		            new String("How's everything?")
		);*/ 
		

	}
	
	@FXML 
	protected void openWarehouseForm() {
		WarehouseForm wf = new WarehouseForm();
		wf.launchWarehouseForm();
	}
	
	@FXML
	protected void openAssetForm() {
		
	}

}
