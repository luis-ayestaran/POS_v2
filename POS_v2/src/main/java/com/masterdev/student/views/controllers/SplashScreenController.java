package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.masterdev.student.services.AuthenticationService;
import com.masterdev.student.views.Login;
import com.masterdev.student.views.LoginForm;
import com.masterdev.student.views.SplashScreen;

import javafx.application.Platform;
import javafx.fxml.Initializable;

public class SplashScreenController implements Initializable {
	
	public void initialize(URL location, ResourceBundle resources) {
		new ShowScreen().start();
	}
	
	class ShowScreen extends Thread {
		@Override 
		public void run() {
			/*try {
				Thread.sleep(3000);*/
			AuthenticationService service = new AuthenticationService();
			service.showUsers();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					loadLogin();
					SplashScreen.getStage().close();
				}
			});				
			/*} catch(InterruptedException e) {
				Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, e);
			}*/
		}
	}
	
	public void loadLogin() {
		Login view = new Login();
		view.loadView();
		LoginForm.getLoginFormController().txtUsernameRequestsFocus();
	}
}
