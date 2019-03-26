package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import com.masterdev.student.views.DepartmentForm;

public class PersonalController {
	
	@FXML Button btnAddDepartment;
	@FXML Button btnAddPersonal;
	
	@FXML 
	protected void openDepartmentForm() {
		DepartmentForm wf = new DepartmentForm();
		wf.launchDepartmentForm();
	}
	
	@FXML
	protected void openPersonalForm() {
		
	}
	
}
