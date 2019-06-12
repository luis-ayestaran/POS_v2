package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.Login;
import com.masterdev.student.services.AuthenticationService;
import com.masterdev.student.entities.User;
import com.masterdev.student.exceptions.ToolkitException;
import com.masterdev.student.utils.Toolkit;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

public class LoginFormController {
	@FXML JFXTextField txtUsername;
	@FXML JFXPasswordField txtPassword;
	
	@FXML
	protected void login() {
		if(!txtUsername.getText().equals("") && !txtPassword.getText().equals(""))
		{
			User user = null;
			try {
				user = new User( txtUsername.getText(), Toolkit.strToMD5(new String(txtPassword.getText())) );
				AuthenticationService authServ = new AuthenticationService();
				user = authServ.searchUser(user);
			} catch (ToolkitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(user != null)
			{
				Login.getStage().close();
				Dashboard d = new Dashboard();
				d.setWorker(user.getName() + " " + user.getLastName());
				d.launchDashboard();
				
			}
			else
			{
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de autenticación",
						"Nombre de usuario o contraseña incorrectos.",
						(StackPane)Login.getStage().getScene().getRoot());
				cleanTextFields();
			}
		}
		else
		{
			Dialogs d = new Dialogs();
			d.acceptDialog("",
					"Asegúrate de haber llenado todos los campos correctamente.",
					(StackPane)Login.getStage().getScene().getRoot());
			
		}
	}
	
	public void cleanTextFields() {
		txtUsername.setText("");
		txtPassword.setText("");
	}
}
