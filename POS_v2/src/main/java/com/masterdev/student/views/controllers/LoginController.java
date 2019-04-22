package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;

import com.masterdev.student.middle.Languages;

import com.masterdev.student.views.LoginForm;
import com.masterdev.student.views.SignupForm;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
	
	@FXML BorderPane brdForms;
	
	public void initialize(URL location, ResourceBundle resources) {
		loadLoginForm();
	}
	
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
	
	//------------------------------------------------
	@FXML
	protected void loadLoginForm() {
		LoginForm lf = new LoginForm();
		brdForms.setCenter(lf.loadView());
    }
	
	public void loadLoginFormView() {
		loadLoginForm();
	}
	
	//--------------------------------------------------
	@FXML
	protected void loadSignupForm() {
		SignupForm sf = new SignupForm();
		brdForms.setCenter(sf.loadView());
    }
	
	public void loadSignupFormView() {
		loadSignupForm();
	}
}
