package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import com.masterdev.student.middle.MiddleDemo;
import com.masterdev.student.middle.Authentication;
import com.masterdev.student.middle.Languages;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.Login;

public class FXMLExampleController {
	
	@FXML
	protected void toEnglish(ActionEvent event) {
		Languages lan = new Languages();
		lan.setEnglish();
	}
	
	@FXML
	protected void toSpanish(ActionEvent event) {
		Languages lan = new Languages();
		lan.setSpanish();
	}
	
	@FXML TextField txtUsername;
	@FXML TextField txtPassword;
	
	@FXML
	protected void login(ActionEvent event) {
		System.out.println(txtUsername.getText());
		System.out.println(txtPassword.getText());
		System.out.println(Languages.getLanguage());
		Authentication a = new Authentication();
		if(a.isValidUser())
		{
			Login.getStage().close();
			new Dashboard().launchDashboard();
		}
	}
	
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
        MiddleDemo md = new MiddleDemo(23);
        System.out.println(md.getDemo());
    }
}
