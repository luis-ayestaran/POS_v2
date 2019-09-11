package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.entities.Product;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.services.WarehouseService;
import com.masterdev.student.views.LookForProduct;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class LookForProductController implements Initializable {
	
	@FXML TextField txtSearchProduct;
	@FXML TextField btnSearchProduct;
	@FXML Label lblProductName;
	@FXML Label lblPrice;
	@FXML Label lblPromotion;
	@FXML JFXButton btnAccept;
	
	public void initialize(URL location, ResourceBundle resources) {
		txtSearchProduct.requestFocus();
	}
	
	@FXML
	protected void searchProduct() {
		if(!txtSearchProduct.getText().trim().equals("")) {
			Product p = new Product();
			Product productFound = checkProductExistence(p);
			if(productFound != null) {
				lblProductName.setText(productFound.getProduct());
				lblPrice.setText(String.format("%.2f", productFound.getRetailPrice()));
				lblPromotion.setText("Descuento: " + productFound.getDiscount() + "%");
			} else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Imagen",
					"No se encontraron coincidencias.",
					(StackPane)LookForProduct.getStage().getScene().getRoot(), txtSearchProduct);
			}
		}
	}
	
	public Product checkProductExistence(Product p) {
		WarehouseService service = new WarehouseService();
		p.setProduct(txtSearchProduct.getText().trim());
		p.setBarCode(null);
		p.setInternalCode(null);
		Product product = service.searchProduct(p);
		if(product == null) {
			p.setBarCode(txtSearchProduct.getText().trim());
			p.setProduct(null);
			p.setInternalCode(null);
			product = service.searchProduct(p);
			if(product == null) {
				p.setInternalCode(txtSearchProduct.getText().trim());
				p.setBarCode(null);
				p.setProduct(null);
				product = service.searchProduct(p);
			}
		}
		return product;
	}
	
	@FXML
	protected void acceptTransaction() {
		LookForProduct.getStage().close();
	}
	
}
