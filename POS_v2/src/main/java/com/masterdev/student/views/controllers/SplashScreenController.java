package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.masterdev.student.entities.User;
import com.masterdev.student.middle.Dialogs;
import com.masterdev.student.services.AuthenticationService;
import com.masterdev.student.views.CategoryForm;
import com.masterdev.student.views.Login;
import com.masterdev.student.views.LoginForm;
import com.masterdev.student.views.SplashScreen;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

public class SplashScreenController implements Initializable {
	
	public void initialize(URL location, ResourceBundle resources) {
		new ShowScreen().start();
	}
	
	class ShowScreen extends Thread {
		@Override 
		public void run() {
			AuthenticationService service = new AuthenticationService();
			List<User> users = service.showUsers();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					loadLogin(users);
					SplashScreen.getStage().close();
				}
			});
		}
	}
	
	public void loadLogin(List<User> users) {
		Login view = new Login();
		view.loadView();
		if(users == null) {
			Dialogs d = new Dialogs();
			d.acceptDialog("BIENVENIDO A ALORA POS",
					"Comencemos a utilizar nuestro punto de venta",
					(StackPane)Login.getStage().getScene().getRoot(), LoginForm.getLoginFormController().txtUsername);
		} else {
			LoginForm.getLoginFormController().txtUsernameRequestsFocus();
		}
	}
}
