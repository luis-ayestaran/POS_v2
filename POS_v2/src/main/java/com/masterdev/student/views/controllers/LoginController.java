package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import com.masterdev.student.middle.MiddleDemo;
import com.masterdev.student.middle.Validation;
import com.masterdev.student.middle.Languages;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.Login;
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
	
	private ValidationSupport validationSupport;
	
	@FXML TextField txtUsername;
	@FXML PasswordField txtPassword;
	
	public void initialize(URL location, ResourceBundle resources) {
		
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
	protected void login(ActionEvent event) {
		User user = null;
		try {
			user = new User( txtUsername.getText(), Toolkit.strToMD5(new String(txtPassword.getText())) );
			System.out.println(user.getUsername()+"\n"+user.getPassword());
			AuthenticationService authServ = new AuthenticationService();
			user = authServ.searchUser(user);
		} catch (ToolkitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Languages.getLanguage());
		if(user != null)
		{
			Login.getStage().close();
			new Dashboard().launchDashboard();
		}
	}
	
	//Just an example of a JavaFX controller
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
        MiddleDemo md = new MiddleDemo(23);
        System.out.println(md.getDemo());
    }
}
