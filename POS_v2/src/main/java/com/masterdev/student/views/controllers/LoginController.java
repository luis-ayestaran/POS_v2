package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;

import com.masterdev.student.middle.MiddleDemo;
import com.masterdev.student.middle.Validation;
import com.masterdev.student.middle.Languages;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.Home;
import com.masterdev.student.views.Login;
import com.masterdev.student.views.LoginForm;
import com.masterdev.student.views.SignupForm;
import com.masterdev.student.services.AuthenticationService;
import com.masterdev.student.entities.User;
import com.masterdev.student.exceptions.ToolkitException;
import com.masterdev.student.utils.Toolkit;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.ValidationSupport;
//import org.controlsfx.validation.decoration.ValidationDecoration;
//import org.controlsfx.validation.decoration.GraphicValidationDecoration;

public class LoginController implements Initializable{
	
	@FXML BorderPane brdForms;
	
	public void initialize(URL location, ResourceBundle resources) {
		LoginForm lf = new LoginForm();
		brdForms.setCenter(lf.show());
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
	
	@FXML
	protected void loadLoginForm(ActionEvent event) {
		LoginForm lf = new LoginForm();
		brdForms.setCenter(lf.show());
    }
	
	@FXML
	protected void loadSignupForm(ActionEvent event) {
		SignupForm sf = new SignupForm();
		brdForms.setCenter(sf.show());
    }
}
