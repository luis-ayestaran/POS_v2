package com.masterdev.student.middle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import com.masterdev.student.views.controllers.DashboardController;

public class DashboardButtonAnimations {
	
	// SIDEBAR CONSTANTS
	public static final String BASETEXTCOLOR = "#bbb";
	public static final String HOVERTEXTCOLOR = "#ddd";
	public static final String CLICKEDTEXTCOLOR = "#ddd";
	public static final String PRESSEDTEXTCOLOR = "#fff";
	
	public static final String BASEHBOXCOLOR = "transparent";
	public static final String HOVERHBOXCOLOR = "#DA8A15";
	public static final String CLICKEDHBOXCOLOR = "#DA8A15";
	public static final String PRESSEDHBOXCOLOR = "#EDA741";
	
	public static final String DBICON = "DASHBOARD";
	public static final String PERSICON = "USERS";
	public static final String RESICON = "CUBES";
	public static final String STATICON = "LINE_CHART";
	public static final String PROJICON = "LIST";
	public static final String DOCICON = "FOLDER";
	
	//USER OPTIONS BAR CONSTANTS
	public static final String BASEBUTTONCOLOR = "transparent";
	public static final String HOVERBUTTONCOLOR = "whitesmoke";
	public static final String CLICKEDBUTTONCOLOR = "whitesmoke";
	public static final String PRESSEDBUTTONCOLOR = "#eee";
	
	public static final String BASEICONCOLOR = "#555";
	public static final String HOVERICONCOLOR = "#555";
	public static final String CLICKEDICONCOLOR = "#555";
	public static final String PRESSEDICONCOLOR = "#444";
	
	public static final String SEARICON = "SEARCH";
	public static final String NOTICON = "BELL";
	public static final String SETICON = "GEAR";
	public static final String HELPICON = "QUESTION_CIRCLE";
	
	//---------------------------------------------------- SIDEBAR ANIMATIONS ----------------------------------------------------------------------//
	
	//These methods set the new colours our buttons will have
	public void clickedButton(HBox hbox, Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(hbox, button, icon, DashboardButtonAnimations.CLICKEDHBOXCOLOR, DashboardButtonAnimations.CLICKEDTEXTCOLOR, DashboardButtonAnimations.CLICKEDTEXTCOLOR, iconImage);
	}
	
	public void pressedButton(HBox hbox, Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(hbox, button, icon, DashboardButtonAnimations.PRESSEDHBOXCOLOR, DashboardButtonAnimations.PRESSEDTEXTCOLOR, DashboardButtonAnimations.PRESSEDTEXTCOLOR, iconImage);
	}
	
	public void releasedButton(HBox hbox, Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(hbox, button, icon, DashboardButtonAnimations.HOVERHBOXCOLOR, DashboardButtonAnimations.HOVERTEXTCOLOR, DashboardButtonAnimations.HOVERTEXTCOLOR, iconImage);
	}
	
	public void enteredButton(HBox hbox, Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(hbox, button, icon, DashboardButtonAnimations.HOVERHBOXCOLOR, DashboardButtonAnimations.HOVERTEXTCOLOR, DashboardButtonAnimations.HOVERTEXTCOLOR, iconImage);
	}
	
	public void exitedButton(HBox hbox, Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(hbox, button, icon, DashboardButtonAnimations.BASEHBOXCOLOR, DashboardButtonAnimations.BASETEXTCOLOR, DashboardButtonAnimations.BASETEXTCOLOR, iconImage);
	}
	
	
	//This is where the magic of the color change happens :v
	private void animateButton(HBox hbox, Button button, FontAwesomeIconView icon, String hboxColor, String buttonColor, String iconColor, String iconImage) {
		hbox.setStyle("-fx-background-color: "+hboxColor+";");
		button.setStyle("-fx-text-fill: "+buttonColor+";");
		icon.setGlyphName(iconImage);
		icon.setStyle("-fx-fill: "+iconColor+";");
	}
	
	//-------------------------------------------------------- USER OPTIONS ANIMATIONS ----------------------------------------------------------//
	
	public void clickedButton(Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, icon, DashboardButtonAnimations.CLICKEDBUTTONCOLOR, DashboardButtonAnimations.CLICKEDICONCOLOR, iconImage);
	}
	
	public void pressedButton(Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, icon, DashboardButtonAnimations.PRESSEDBUTTONCOLOR, DashboardButtonAnimations.PRESSEDICONCOLOR, iconImage);
	}
	
	public void releasedButton(Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, icon, DashboardButtonAnimations.HOVERBUTTONCOLOR, DashboardButtonAnimations.HOVERICONCOLOR, iconImage);
	}
	
	public void enteredButton(Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, icon, DashboardButtonAnimations.HOVERBUTTONCOLOR, DashboardButtonAnimations.HOVERICONCOLOR, iconImage);
	}
	
	public void exitedButton(Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, icon, DashboardButtonAnimations.BASEBUTTONCOLOR, DashboardButtonAnimations.BASEICONCOLOR, iconImage);
	}
	
	private void animateButton(Button button, FontAwesomeIconView icon, String buttonColor, String iconColor, String iconImage) {
		button.setStyle("-fx-background-color: "+buttonColor+";");
		icon.setGlyphName(iconImage);
		icon.setStyle("-fx-fill: "+iconColor+";");
	}
}
