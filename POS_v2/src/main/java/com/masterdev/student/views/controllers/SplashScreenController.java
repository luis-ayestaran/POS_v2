package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.masterdev.student.views.Login;
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
			try {
				Thread.sleep(3000);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						loadLogin();
						SplashScreen.getStage().close();
					}
				});
			} catch(InterruptedException e) {
				Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	public void loadLogin() {
		Login view = new Login();
		view.loadView();
	}
}
