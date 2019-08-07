package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;

import com.masterdev.student.middle.Languages;

import com.masterdev.student.views.LoginForm;
import com.masterdev.student.views.SignupForm;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
	
	@FXML Button btnLogin;
	@FXML Button btnSignup;
	
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
	
	//------------------------------------------------ LOADING VIEWS ------------------------------------------------//
	public void loadLoginFormView() {
		if(LoginForm.getNode() != null) {
			brdForms.setCenter(LoginForm.getNode());
		} else {
			LoginForm view = new LoginForm();
			brdForms.setCenter(view.loadView());
		}
	}
	
	public void loadSignupFormView() {
		if(SignupForm.getNode() != null) {
			brdForms.setCenter(SignupForm.getNode());
		} else {
			SignupForm view = new SignupForm();
			brdForms.setCenter(view.loadView());
		}
	}
	
	//------------------------------------------------
	@FXML
	protected void loadLoginForm() {
		loadLogin();
	}
	
	public void loadLogin() {
		animateLoginButton();
		loadLoginFormView();
		LoginForm.getLoginFormController().txtUsernameRequestsFocus();
	}
	
	public void animateLoginButton() {
		btnLogin.setStyle("-fx-text-fill: #1c8fd0;");
		btnSignup.setStyle("-fx-text-fill: #999;");
	}
	
	//--------------------------------------------------
	@FXML
	protected void loadSignupForm() {
		loadSignup();
    }
	
	public void loadSignup() {
		animateSignupButton();
		loadSignupFormView();
		SignupForm.getSignupFormController().txtNameRequestsFocus();
	}
	
	public void animateSignupButton() {
		btnSignup.setStyle("-fx-text-fill: #1c8fd0;");
		btnLogin.setStyle("-fx-text-fill: #999;");
	}
}
