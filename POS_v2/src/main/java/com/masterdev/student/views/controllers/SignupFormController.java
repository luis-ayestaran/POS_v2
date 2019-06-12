package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.masterdev.student.entities.User;
import com.masterdev.student.entities.UserGroup;
import com.masterdev.student.exceptions.ToolkitException;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.services.AuthenticationService;
import com.masterdev.student.utils.Toolkit;
import com.masterdev.student.views.Login;

public class SignupFormController implements Initializable {
	
	@FXML JFXTextField txtName;
	@FXML JFXTextField txtFathSurname;
	@FXML JFXTextField txtMothSurname;
	@FXML JFXTextField txtUsername;
	
	@FXML JFXPasswordField txtPassword;
	@FXML JFXPasswordField txtRepPswd;
	
	@FXML JFXCheckBox chckbxAdmin;
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML 
	protected void signup() {
		if(!txtName.getText().equals("") && !txtFathSurname.getText().equals("") && !txtMothSurname.getText().equals("") && !txtUsername.getText().equals("") && !txtPassword.getText().equals("") && !txtRepPswd.getText().equals(""))	//Si el usuario no ha llenado todos los campos,lanza una advertencia
		{
			if(txtPassword.getText().equals(txtRepPswd.getText()))
			{
				try {
					UserGroup userGroup;
					if(chckbxAdmin.isSelected())
					{
						userGroup = new UserGroup("admin");
					}
					else
					{
						userGroup = new UserGroup("user");
					}
					User user = new User(txtName.getText(), txtFathSurname.getText(), txtMothSurname.getText(), txtUsername.getText(),Toolkit.strToMD5(new String(txtPassword.getText())), userGroup);
					AuthenticationService authServ = new AuthenticationService();
					User u = authServ.searchUser(user);
					if(u != null)
					{
						Dialogs d = new Dialogs();
						d.acceptDialog("Usuario en existencia",
								"Este usuario ya fue registrado.",
								(StackPane)Login.getStage().getScene().getRoot());
						cleanTextFields();
					}
					else
					{
						
						if(user.getUserGroup().getGroup().equals("admin"))
						{
							if(authServ.adminExists())	
							{
								String usrnm = Dialogs.inputDialog("Autenticación", "Ejecutar como administrador", "Proporciona un nombre de usuario de administrador.");
								String pswd = Dialogs.inputDialog("Autenticación", "Ejecutar como administrador", "Proporciona la contraseña de administrador.");
								User userParam = new User(usrnm, Toolkit.strToMD5(pswd));
								userParam = authServ.searchUser(userParam);		
								if(userParam == null)		
								{
									Dialogs d = new Dialogs();
									d.acceptDialog("Error de autenticación",
											"Usuario o contraseña incorrectos.",
											(StackPane)Login.getStage().getScene().getRoot());
								}
								else
								{
									if(userParam.getUserGroup().getGroup().equals("admin"))
									{
										registrate(authServ, user, userGroup);
									}
									else
									{
										Dialogs d = new Dialogs();
										d.acceptDialog("Error de autenticación",
											"Usuario o contraseña incorrectos.",
											(StackPane)Login.getStage().getScene().getRoot());
									}
								}
							}
							else
							{
								registrate(authServ, user, userGroup);
							}
						}
						else		//Si se registrará como usuario, lo ingresa a la base de datos
						{
							registrate(authServ, user, userGroup);
						}
					}
				} catch (ToolkitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else		//Si no son iguales las contraseñas, lanza una advertencia
			{
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de registro",
						"Las contraseñas no coinciden.",
						(StackPane)Login.getStage().getScene().getRoot());
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
	
	public void registrate(AuthenticationService as, User u, UserGroup ug) {
		as.registrateUser(u, ug);
		cleanTextFields();
		Dialogs d = new Dialogs();
		d.acceptDialog("Registro realizado",
				"Usuario registrado con éxito.",
				(StackPane)Login.getStage().getScene().getRoot());
		Login.getLoginController().loadLoginFormView();
	}
	
	public void cleanTextFields() {
		txtName.setText("");
		txtFathSurname.setText("");
		txtMothSurname.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
		txtRepPswd.setText("");
	}
}