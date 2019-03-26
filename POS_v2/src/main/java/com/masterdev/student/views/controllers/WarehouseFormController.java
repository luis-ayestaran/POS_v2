package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;

import java.util.ResourceBundle;

import com.masterdev.student.middle.ComboBoxMethods;
import com.masterdev.student.views.WarehouseForm;

public class WarehouseFormController implements Initializable {
	@FXML TextField txtName_1;
	@FXML TextField txtName_2;
	@FXML TextField txtName_3;
	@FXML TextField txtName_4;
	@FXML TextField txtName_5;
	
	@FXML TextArea txtDescription_1;
	@FXML TextArea txtDescription_2;
	@FXML TextArea txtDescription_3;
	@FXML TextArea txtDescription_4;
	@FXML TextArea txtDescription_5;
	
	@FXML ComboBox<String> cmbResponsible_1;
	@FXML ComboBox<String> cmbResponsible_2;
	@FXML ComboBox<String> cmbResponsible_3;
	@FXML ComboBox<String> cmbResponsible_4;
	@FXML ComboBox<String> cmbResponsible_5;
	
	@FXML TextField txtLocation_1;
	@FXML TextField txtLocation_2;
	@FXML TextField txtLocation_3;
	@FXML TextField txtLocation_4;
	@FXML TextField txtLocation_5;
	
	@FXML Button btnCancel_1;
	@FXML Button btnCancel_2;
	@FXML Button btnCancel_3;
	@FXML Button btnCancel_4;
	@FXML Button btnCancel_5;
	
	@FXML Button btnAccept_1;
	@FXML Button btnAccept_2;
	@FXML Button btnAccept_3;
	@FXML Button btnAccept_4;
	@FXML Button btnAccept_5;
	
	public void initialize(URL location, ResourceBundle resources) {
		/*Initialising components from the upper bar (user-options */
		ComboBoxMethods cbm = new ComboBoxMethods();
		
		
		//Adding user options to the combo box
		String[] items_1 = {"Juan Pérez", "Rosa Hernández"};
		cbm.addItems(cmbResponsible_1, items_1);
		String[] items_2 = {"Jorge Rosales", "María Juárez"};
		cbm.addItems(cmbResponsible_2, items_2);
		String[] items_3 = {"José Espinosa", "Lourdes Nava"};
		cbm.addItems(cmbResponsible_3, items_3);
		String[] items_4 = {"Jonathan Varela", "Ricardo Rincón"};
		cbm.addItems(cmbResponsible_4, items_4);
		String[] items_5 = {"Estefanía Serrano", "Marco Viveros"};
		cbm.addItems(cmbResponsible_5, items_5);
	}
	
	@FXML
	protected void saveRegister_1() {
		System.out.println(txtName_1.getText()+"\n"+
							txtDescription_1.getText()+"\n"+
							cmbResponsible_1.getValue()+"\n"+
							txtLocation_1.getText());
		WarehouseForm.getStage().close();
	}
	
	@FXML
	protected void saveRegister_2() {
		System.out.println(txtName_2.getText()+"\n"+
				txtDescription_2.getText()+"\n"+
				cmbResponsible_2.getValue()+"\n"+
				txtLocation_2.getText());
		WarehouseForm.getStage().close();
	}
	
	@FXML
	protected void saveRegister_3() {
		System.out.println(txtName_3.getText()+"\n"+
				txtDescription_3.getText()+"\n"+
				cmbResponsible_3.getValue()+"\n"+
				txtLocation_3.getText());
		WarehouseForm.getStage().close();
	}
	
	@FXML
	protected void saveRegister_4() {
		System.out.println(txtName_4.getText()+"\n"+
				txtDescription_4.getText()+"\n"+
				cmbResponsible_4.getValue()+"\n"+
				txtLocation_4.getText());
		WarehouseForm.getStage().close();
	}
	
	@FXML
	protected void saveRegister_5() {
		System.out.println(txtName_5.getText()+"\n"+
				txtDescription_5.getText()+"\n"+
				cmbResponsible_5.getValue()+"\n"+
				txtLocation_5.getText());
		WarehouseForm.getStage().close();
	}
}
