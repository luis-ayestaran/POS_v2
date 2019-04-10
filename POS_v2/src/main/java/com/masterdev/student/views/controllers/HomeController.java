package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.jfoenix.controls.JFXButton;
import com.masterdev.student.middle.ComboBoxMethods;
import com.masterdev.student.middle.TextFieldMethods;
import com.masterdev.student.middle.animations.DashboardButtonAnimations;
import com.masterdev.student.middle.animations.HomeButtonAnimations;
import com.masterdev.student.views.Dashboard;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeController extends Controller implements Initializable {
	
	private DashboardController dashboardController;
	
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
	
	public void setDashboardController(DashboardController dashboardController) {
		this.dashboardController = dashboardController;
	}
	
	public DashboardController getDashboardController() {
		return dashboardController;
	}
	
	//--------------------------------------------------- SETTING QUICK BUTTONS' ACTIONS -------------------------------//
	@FXML
	protected void clickedSalesButton() {
		this.animationWithoutSubmenu();
	}
	
	public void animationWithoutSubmenu() {
		this.getDashboardController().setOptionSelected(true);
		this.getDashboardController().clickedSalesButton();
		this.getDashboardController().loadSalesFormView();
		this.getDashboardController().setOptionSelected(false);
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
		System.out.println(getDashboardController());
		this.getDashboardController().clickedPersButton();
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
