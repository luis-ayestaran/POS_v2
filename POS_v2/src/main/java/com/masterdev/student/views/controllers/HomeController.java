package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.masterdev.student.middle.animations.HomeButtonAnimations;
import com.masterdev.student.views.Dashboard;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {
	
	//QUICK ACCESS BUTTONS
	@FXML VBox btnSales;
	@FXML VBox btnInventory;
	@FXML VBox btnPrice;
	@FXML VBox btnEmployee;
	@FXML VBox btnSupplier;
	
	//Button View More in the Balance pane
	@FXML Button btnBalanceViewMore;
	@FXML FontAwesomeIconView icoBalanceViewMore;
	//Button View More in the Product pane
	@FXML Button btnProductViewMore;
	@FXML FontAwesomeIconView icoProductViewMore;
	//Button View More in the Personal pane
	@FXML Button btnPersonalViewMore;
	@FXML FontAwesomeIconView icoPersonalViewMore;
	
	private LineChart chart;
	
	//--------------------------------------------------- INITIALISING -------------------------------------------//
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	
	//--------------------------------------------------- ANIMATING BUTTONS -------------------------------------------//
	@FXML
	protected void clickedBVMButton() {
		HomeButtonAnimations dba = new HomeButtonAnimations();
		dba.clickedButton(btnBalanceViewMore, icoBalanceViewMore, HomeButtonAnimations.ARROWICON);
		//this.getDashboardController().loadBalanceView();
	}
	
	@FXML
	protected void pressedBVMButton() {
		HomeButtonAnimations dba = new HomeButtonAnimations();
		dba.pressedButton(btnBalanceViewMore, icoBalanceViewMore, HomeButtonAnimations.ARROWICON);
	}
	
	@FXML
	protected void releasedBVMButton() {
		HomeButtonAnimations dba = new HomeButtonAnimations();
		dba.releasedButton(btnBalanceViewMore, icoBalanceViewMore, HomeButtonAnimations.ARROWICON);
	}
	
	@FXML
	protected void enteredBVMButton() {
		HomeButtonAnimations dba = new HomeButtonAnimations();
		dba.enteredButton(btnBalanceViewMore, icoBalanceViewMore, HomeButtonAnimations.ARROWICON);
	}
	
	@FXML
	protected void exitedBVMButton() {
		HomeButtonAnimations dba = new HomeButtonAnimations();
		dba.exitedButton(btnBalanceViewMore, icoBalanceViewMore, HomeButtonAnimations.ARROWICON);
	}
	
	//Personal
	@FXML
	protected void clickedPVMButton() {
		HomeButtonAnimations dba = new HomeButtonAnimations();
		dba.clickedButton(btnPersonalViewMore, icoPersonalViewMore, HomeButtonAnimations.ARROWICON);
		Dashboard.getDashboardController().personnelListWithoutMenu();
	}
	
	@FXML
	protected void pressedPVMButton() {
		HomeButtonAnimations dba = new HomeButtonAnimations();
		dba.pressedButton(btnPersonalViewMore, icoPersonalViewMore, HomeButtonAnimations.ARROWICON);
	}
	
	@FXML
	protected void releasedPVMButton() {
		HomeButtonAnimations dba = new HomeButtonAnimations();
		dba.releasedButton(btnPersonalViewMore, icoPersonalViewMore, HomeButtonAnimations.ARROWICON);
	}
	
	@FXML
	protected void enteredPVMButton() {
		HomeButtonAnimations dba = new HomeButtonAnimations();
		dba.enteredButton(btnPersonalViewMore, icoPersonalViewMore, HomeButtonAnimations.ARROWICON);
	}
	
	@FXML
	protected void exitedPVMButton() {
		HomeButtonAnimations dba = new HomeButtonAnimations();
		dba.exitedButton(btnPersonalViewMore, icoPersonalViewMore, HomeButtonAnimations.ARROWICON);
	}
	
	//
}
