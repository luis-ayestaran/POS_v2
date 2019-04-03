package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import com.masterdev.student.views.DepartmentForm;

public class PersonalController {
	private DashboardController dc;
	@FXML Button btnAddDepartment;
	@FXML Button btnAddPersonal;
	
	@FXML 
	protected void openDepartmentForm() {
		dc.responding("Hi");
		DepartmentForm wf = new DepartmentForm();
		wf.launchDepartmentForm();
	}
	
	@FXML
	protected void openPersonalForm() {
		
	}
	
	public void setDashboardController(DashboardController dc) {
		this.dc = dc;
	}
	
	public void communicate(DashboardController dc, String message) {
		setDashboardController(dc);
		System.out.println(message);
	}
	
}
