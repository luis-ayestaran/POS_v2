package com.masterdev.student.views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.masterdev.student.middle.animations.HomeButtonAnimations;
import com.masterdev.student.views.Dashboard;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
	
	@FXML private AreaChart<String, Number> chrtToday;
	@FXML private AreaChart<String, Number> chrtWeek;
	@FXML private AreaChart<String, Number> chrtMonth;
	@FXML private AreaChart<String, Number> chrtYear;
	
	//--------------------------------------------------- INITIALISING -------------------------------------------//
	public void initialize(URL location, ResourceBundle resources) {
		
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Entrada");
		series1.getData().add(new XYChart.Data("0", 23));
        series1.getData().add(new XYChart.Data("1", 14));
        series1.getData().add(new XYChart.Data("2", 15));
        series1.getData().add(new XYChart.Data("3", 24));
        series1.getData().add(new XYChart.Data("4", 34));
        series1.getData().add(new XYChart.Data("5", 36));
        series1.getData().add(new XYChart.Data("6", 22));
        series1.getData().add(new XYChart.Data("7", 23));
        series1.getData().add(new XYChart.Data("8", 14));
        series1.getData().add(new XYChart.Data("9", 15));
        series1.getData().add(new XYChart.Data("10", 24));
        series1.getData().add(new XYChart.Data("11", 34));
        series1.getData().add(new XYChart.Data("12", 23));
        series1.getData().add(new XYChart.Data("13", 14));
        series1.getData().add(new XYChart.Data("14", 15));
        series1.getData().add(new XYChart.Data("15", 24));
        series1.getData().add(new XYChart.Data("16", 34));
        series1.getData().add(new XYChart.Data("17", 36));
        series1.getData().add(new XYChart.Data("18", 22));
        series1.getData().add(new XYChart.Data("19", 23));
        series1.getData().add(new XYChart.Data("20", 14));
        series1.getData().add(new XYChart.Data("21", 15));
        series1.getData().add(new XYChart.Data("22", 24));
        series1.getData().add(new XYChart.Data("23", 34));
        series1.getData().add(new XYChart.Data("24", 34));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Salida");
        series2.getData().add(new XYChart.Data("0", 30));
        series2.getData().add(new XYChart.Data("1", 20));
        series2.getData().add(new XYChart.Data("2", 20));
        series2.getData().add(new XYChart.Data("3", 30));
        series2.getData().add(new XYChart.Data("4", 40));
        series2.getData().add(new XYChart.Data("5", 40));
        series2.getData().add(new XYChart.Data("6", 30));
        series2.getData().add(new XYChart.Data("7", 30));
        series2.getData().add(new XYChart.Data("8", 20));
        series2.getData().add(new XYChart.Data("9", 20));
        series2.getData().add(new XYChart.Data("10", 30));
        series2.getData().add(new XYChart.Data("11", 40));
        series2.getData().add(new XYChart.Data("12", 30));
        series2.getData().add(new XYChart.Data("13", 20));
        series2.getData().add(new XYChart.Data("14", 20));
        series2.getData().add(new XYChart.Data("15", 30));
        series2.getData().add(new XYChart.Data("16", 40));
        series2.getData().add(new XYChart.Data("17", 40));
        series2.getData().add(new XYChart.Data("18", 30));
        series2.getData().add(new XYChart.Data("19", 30));
        series2.getData().add(new XYChart.Data("20", 20));
        series2.getData().add(new XYChart.Data("21", 20));
        series2.getData().add(new XYChart.Data("22", 30));
        series2.getData().add(new XYChart.Data("23", 40));
        series2.getData().add(new XYChart.Data("24", 40));
       
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Utilidad");
        series3.getData().add(new XYChart.Data("0", 45));
        series3.getData().add(new XYChart.Data("1", 50));
        series3.getData().add(new XYChart.Data("2", 50));
        series3.getData().add(new XYChart.Data("3", 65));
        series3.getData().add(new XYChart.Data("4", 50));
        series3.getData().add(new XYChart.Data("5", 55));
        series3.getData().add(new XYChart.Data("6", 50));
        series3.getData().add(new XYChart.Data("7", 55));
        series3.getData().add(new XYChart.Data("8", 65));
        series3.getData().add(new XYChart.Data("9", 50));
        series3.getData().add(new XYChart.Data("10", 75));
        series3.getData().add(new XYChart.Data("11", 50));
        series3.getData().add(new XYChart.Data("12", 50));
        series3.getData().add(new XYChart.Data("13", 55));
        series3.getData().add(new XYChart.Data("14", 45));
        series3.getData().add(new XYChart.Data("15", 50));
        series3.getData().add(new XYChart.Data("16", 55));
        series3.getData().add(new XYChart.Data("17", 65));
        series3.getData().add(new XYChart.Data("18", 50));
        series3.getData().add(new XYChart.Data("19", 40));
        series3.getData().add(new XYChart.Data("20", 55));
        series3.getData().add(new XYChart.Data("21", 45));
        series3.getData().add(new XYChart.Data("22", 35));
        series3.getData().add(new XYChart.Data("23", 45));
        series3.getData().add(new XYChart.Data("24", 45));
       
        chrtToday.getData().addAll(series1, series2, series3);
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
