package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.views.Dashboard;
import com.masterdev.student.views.DownloadingDataScreen;
import com.masterdev.student.views.Login;
import com.masterdev.student.services.AuthenticationService;
import com.masterdev.student.services.CashRegisterService;
import com.masterdev.student.entities.CashRegister;
import com.masterdev.student.entities.User;
import com.masterdev.student.exceptions.ToolkitException;
import com.masterdev.student.utils.Toolkit;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

public class LoginFormController  implements Initializable {
	@FXML JFXTextField txtUsername;
	@FXML JFXPasswordField txtPassword;
	
	private DownloadingDataScreen dds;
	
	public void initialize(URL location, ResourceBundle resources) {
		txtUsernameRequestsFocus();
	}
	
	public void txtUsernameRequestsFocus() {
		txtUsername.requestFocus();
	}
	
	public void txtPasswordRequestsFocus() {
		txtPassword.requestFocus();
	}
	
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
			if(user != null) {
				/*dds = new DownloadingDataScreen();
				dds.loadView();
				DownloadingDataScreen.getStage().setAlwaysOnTop(true);*/
				CashRegisterService service = new CashRegisterService();
				CashRegister defaultCashRegister = service.defaultCashRegisterExists(); 
				if(defaultCashRegister != null) {
					CashRegister unusedCashRegister = service.getNextUnusedCashRegister();
					if(unusedCashRegister != null) {
						log(user, unusedCashRegister, service);
					} else {
						log(user, defaultCashRegister, service);
					}
				} else {
					CashRegister newCashRegister = new CashRegister("Caja-1", 0.0f);
					service.addCashRegister(newCashRegister);
					log(user, newCashRegister, service);
				}
			}
			else {
				Dialogs d = new Dialogs();
				d.acceptDialog("Error de autenticación",
						"Nombre de usuario o contraseña incorrectos.",
						(StackPane)Login.getStage().getScene().getRoot(), txtUsername);
				cleanTextFields();
			}
		}
		else
		{
			Dialogs d = new Dialogs();
			d.acceptDialog("",
					"Asegúrate de haber llenado todos los campos correctamente.",
					(StackPane)Login.getStage().getScene().getRoot(), txtUsername);
			
		}
	}
	
	public void log(User user, CashRegister cashRegister, CashRegisterService service) {
		//DownloadingDataScreen.getStage().close();
		Login.getStage().close();
		Dashboard d = new Dashboard();
		d.launchDashboard();
		Dashboard.getDashboardController().setUser(user);
		cashRegister.setUsed(true);
		service.updateCashRegister(cashRegister);
		Dashboard.getDashboardController().setCashRegister(cashRegister);
		Dashboard.getDashboardController().setUsername();
	}
	
	public void cleanTextFields() {
		txtUsername.setText("");
		txtPassword.setText("");
	}
	
}
