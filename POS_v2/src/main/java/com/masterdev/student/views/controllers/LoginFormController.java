package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import com.masterdev.student.middle.MiddleDemo;
import com.masterdev.student.middle.Validation;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.middle.Languages;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.Home;
import com.masterdev.student.views.Login;
import com.masterdev.student.views.LoginForm;
import com.masterdev.student.views.SignupForm;
import com.masterdev.student.services.AuthenticationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.masterdev.student.entities.User;
import com.masterdev.student.exceptions.ToolkitException;
import com.masterdev.student.utils.Toolkit;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.ValidationSupport;
//import org.controlsfx.validation.decoration.ValidationDecoration;
//import org.controlsfx.validation.decoration.GraphicValidationDecoration;

public class LoginFormController {
	@FXML JFXTextField txtUsername;
	@FXML JFXPasswordField txtPassword;
	
	@FXML
	protected void login(ActionEvent event) {
		if(!txtUsername.getText().equals("") && !txtPassword.getText().equals(""))
		{
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
			else
			{
				Dialogs.acceptDialog("Error de autenticación",
						"Nombre de usuario o contraseña incorrectos.",
						(StackPane)Login.getStage().getScene().getRoot());
				txtUsername.setText("");
				txtPassword.setText("");
			}
		}
		else
		{
			Dialogs.acceptDialog("",
					"Asegúrate de haber llenado todos los campos correctamente.",
					(StackPane)Login.getStage().getScene().getRoot());
			
		}
	}
}
