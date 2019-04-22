package com.masterdev.student.views.controllers;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SalesFormController implements Initializable {
	//@FXML TextField txtDate;
	//@FXML Button btnDate;
	
	@FXML TextField txtCash;
	@FXML Button btnCash;
	
	//@FXML TextField txtEmployee;
	//@FXML Button btnEmployee;
	
	@FXML TextField txtClient;
	@FXML Button btnClient;
	
	@FXML TextField txtSearch;
	@FXML JFXButton btnSearch;

	@FXML TextField txtWeigh;
	@FXML JFXButton btnWeigh;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*LocalDate localDate = LocalDate.now();
		Date now = Date.valueOf(localDate);
		String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(now);
		txtDate.setText(dateString);*/
	}
}