package com.masterdev.student.views.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;

import java.util.ResourceBundle;

import com.masterdev.student.middle.TextFieldMethods;
import com.masterdev.student.middle.ComboBoxMethods;
import com.masterdev.student.middle.DashboardButtonAnimations;
import com.masterdev.student.middle.ImageViewMethods;
import com.masterdev.student.views.Home;
import com.masterdev.student.views.Personal;
import com.masterdev.student.views.Warehouse;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;


public class DashboardController implements Initializable{
	//Methods to animate the sidebar buttons-----------------------------------------------------
	
	//Dashboard button elements
	@FXML Button btnDashboard;
	@FXML HBox hboxDashboard;
	@FXML FontAwesomeIconView icoDashboard;
	//Personal button elements
	@FXML Button btnPersonal;
	@FXML HBox hboxPersonal;
	@FXML FontAwesomeIconView icoPersonal;
	//Resources button elements
	@FXML Button btnResources;
	@FXML HBox hboxResources;
	@FXML FontAwesomeIconView icoResources;
	//Statistics button elements
	@FXML Button btnStatistics;
	@FXML HBox hboxStatistics;
	@FXML FontAwesomeIconView icoStatistics;
	//Projects button elements
	@FXML Button btnProjects;
	@FXML HBox hboxProjects;
	@FXML FontAwesomeIconView icoProjects;
	//Documents button elements
	@FXML Button btnDocuments;
	@FXML HBox hboxDocuments;
	@FXML FontAwesomeIconView icoDocuments;
	
	//Main view elements
	@FXML ScrollPane sclMainView;
	
	//--------------------------------------------------------------- INITIALISING COMPONENTS ----------------------------------------------//
	@FXML ComboBox<String> cmbAccount;
	@FXML ImageView imgAccount;
	
	public void initialize(URL location, ResourceBundle resources) {
		/*Initialising components from the upper bar (user-options */
		TextFieldMethods tfm = new TextFieldMethods();
		ComboBoxMethods cbm = new ComboBoxMethods();
		ImageViewMethods ivm = new ImageViewMethods();
		
		//Starting the focus listener gor the search Text Field
		tfm.addTextFieldFocusListener(txtSearch, "Buscar"); 
		
		//Adding word suggestions for auto-completion. ************** STILL IMPROVABLE ******************
		String[] wordSuggestions = {"Word", "Office", "Microsoft", "HP", "Apple", "Resident Evil", "IOS", "Android", "Google", "Go", "Amazon", "Amazing", "Facebook", "WhatsApp", "WWW"};
		tfm.addWordSuggestions(txtSearch, wordSuggestions);
		
		//Adding user options to the combo box
		String[] items = {"Configurar cuenta", "Cerrar sesión"};
		cbm.addItems(cmbAccount, items);
		
		//Making a rounder user image
		Integer radius = 20;
		ivm.makeRoundImage(imgAccount, radius);
		
		//Charging the home screen
		Home home = new Home();
		sclMainView.setContent(home.showHome());
	}
	
	
	//--------------------------------------------------------------- SIDEBAR ANIMATIONS ---------------------------------------------------//
	
	//Animating the dashboard button
		@FXML
		protected void clickedDBButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			btnDashboard.requestFocus();
			System.out.println("Dashboard button requests focus");
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			
			//Opening the view of another FXML
			Home home = new Home();
			sclMainView.setContent(home.showHome());
		}
		
		@FXML
		protected void pressedDBButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
		}
		
		@FXML
		protected void releasedDBButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
		}
		
		@FXML
		protected void enteredDBButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
		}
		
		@FXML
		protected void exitedDBButton() {
			if(!btnDashboard.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			}
		}
	
	//Animating the personal button
		
		@FXML
		protected void clickedPersButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			btnPersonal.requestFocus();
			System.out.println("Personal button requests focus");
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			
			Personal personal = new Personal();
			sclMainView.setContent(personal.showPersonal());
		}
		
		@FXML
		protected void pressedPersButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
		}
		
		@FXML
		protected void releasedPersButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
		}
		
		@FXML
		protected void enteredPersButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
		}
		
		@FXML
		protected void exitedPersButton() {
			if(!btnPersonal.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			}
		}
	
	//Animating the resources button
	
		@FXML
		protected void clickedResButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			btnResources.requestFocus();
			System.out.println("Resources button requests focus");
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			
			Warehouse resources = new Warehouse();
			sclMainView.setContent(resources.showResources());
		}
		
		@FXML
		protected void pressedResButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
		}
		
		@FXML
		protected void releasedResButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
		}
		
		@FXML
		protected void enteredResButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
		}
		
		@FXML
		protected void exitedResButton() {
			if(!btnResources.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			}
		}
		
	//Animating the statistics button
	
		@FXML
		protected void clickedStatButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			btnStatistics.requestFocus();
			System.out.println("Statistics button requests focus");
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
		}
		
		@FXML
		protected void pressedStatButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
		}
		
		@FXML
		protected void releasedStatButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
		}
		
		@FXML
		protected void enteredStatButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
		}
		
		@FXML
		protected void exitedStatButton() {
			if(!btnStatistics.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			}
		}
		
	//Animating the projects button
	
		@FXML
		protected void clickedProjButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			btnProjects.requestFocus();
			System.out.println("Statistics button requests focus");
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
		}
		
		@FXML
		protected void pressedProjButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
		}
		
		@FXML
		protected void releasedProjButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
		}
		
		@FXML
		protected void enteredProjButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
		}
		
		@FXML
		protected void exitedProjButton() {
			if(!btnProjects.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
			}
		}
		
	//Animating the documents button
		
		@FXML
		protected void clickedDocButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.clickedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			btnDocuments.requestFocus();
			System.out.println("Documents button requests focus");
			/*Changing the other buttons to their original state*/
			dba.exitedButton(hboxDashboard, btnDashboard, icoDashboard, DashboardButtonAnimations.DBICON);
			dba.exitedButton(hboxPersonal, btnPersonal, icoPersonal, DashboardButtonAnimations.PERSICON);
			dba.exitedButton(hboxResources, btnResources, icoResources, DashboardButtonAnimations.RESICON);
			dba.exitedButton(hboxStatistics, btnStatistics, icoStatistics, DashboardButtonAnimations.STATICON);
			dba.exitedButton(hboxProjects, btnProjects, icoProjects, DashboardButtonAnimations.PROJICON);
		}
		
		@FXML
		protected void pressedDocButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.pressedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
		}
		
		@FXML
		protected void releasedDocButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.releasedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
		}
		
		@FXML
		protected void enteredDocButton() {
			DashboardButtonAnimations dba = new DashboardButtonAnimations();
			dba.enteredButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
		}
		
		@FXML
		protected void exitedDocButton() {
			if(!btnDocuments.isFocused())
			{
				DashboardButtonAnimations dba = new DashboardButtonAnimations();
				dba.exitedButton(hboxDocuments, btnDocuments, icoDocuments, DashboardButtonAnimations.DOCICON);
			}
		}
	
	//-------------------------------------------------------- UPPER BAR (USER OPTIONS) ANIMATIONS --------------------------------------------//
		
	//Methods for searching
	@FXML TextField txtSearch;
	
	@FXML
	protected void search() {
		if(txtSearch.getText().equals("Buscar"))
			cleanTxtSearch();
	}
	
	private void cleanTxtSearch() {
		txtSearch.setText("");
	}
	
	public TextField getTxtSearch() {
		return txtSearch;
	}
	
	//Search button elements
	@FXML Button btnSearch;
	@FXML FontAwesomeIconView icoSearch;
	//Notification button elements
	@FXML Button btnNotification;
	@FXML FontAwesomeIconView icoNotification;
	//Settings button elements
	@FXML Button btnSettings;
	@FXML FontAwesomeIconView icoSettings;
	//Help button elements
	@FXML Button btnHelp;
	@FXML FontAwesomeIconView icoHelp;
	
	//Search button animations
	@FXML 
	protected void clickedSearButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnSearch, icoSearch, DashboardButtonAnimations.SEARICON);
	}
	
	@FXML 
	protected void pressedSearButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.pressedButton(btnSearch, icoSearch, DashboardButtonAnimations.SEARICON);
	}
	
	@FXML
	protected void releasedSearButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnSearch, icoSearch, DashboardButtonAnimations.SEARICON);
	}
	
	@FXML
	protected void enteredSearButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnSearch, icoSearch, DashboardButtonAnimations.SEARICON);
	}
	
	@FXML
	protected void exitedSearButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnSearch, icoSearch, DashboardButtonAnimations.SEARICON);
	}
	
	//Notification button animations
	@FXML 
	protected void clickedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	@FXML 
	protected void pressedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.pressedButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	@FXML
	protected void releasedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	@FXML
	protected void enteredNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	@FXML
	protected void exitedNotButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnNotification, icoNotification, DashboardButtonAnimations.NOTICON);
	}
	
	//Settings button animations
	@FXML 
	protected void clickedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnSettings, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML 
	protected void pressedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.pressedButton(btnSettings, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML
	protected void releasedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnSettings, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML
	protected void enteredSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnSettings, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	@FXML
	protected void exitedSetButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnSettings, icoSettings, DashboardButtonAnimations.SETICON);
	}
	
	//Help button animations
	@FXML 
	protected void clickedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.clickedButton(btnHelp, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	@FXML 
	protected void pressedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.pressedButton(btnHelp, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	@FXML
	protected void releasedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.releasedButton(btnHelp, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	@FXML
	protected void enteredHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.enteredButton(btnHelp, icoHelp, DashboardButtonAnimations.HELPICON);
	}
	
	@FXML
	protected void exitedHelpButton() {
		DashboardButtonAnimations dba = new DashboardButtonAnimations();
		dba.exitedButton(btnHelp, icoHelp, DashboardButtonAnimations.HELPICON);
	}
}
