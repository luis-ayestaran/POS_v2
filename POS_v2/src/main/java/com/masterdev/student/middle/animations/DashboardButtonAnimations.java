package com.masterdev.student.middle.animations;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import org.controlsfx.control.PopOver;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class DashboardButtonAnimations {
	
	// SIDEBAR CONSTANTS
	public static final String BASETEXTCOLOR = "#bbb";
	public static final String HOVERTEXTCOLOR = "#085eb3";//"#ddd";
	public static final String CLICKEDTEXTCOLOR = "#1c8fd0";//"#ddd";
	public static final String PRESSEDTEXTCOLOR = "#1c8fd0";//"#fff";
	
	public static final String BASEHBOXCOLOR = "transparent";
	public static final String HOVERHBOXCOLOR = "#ddd";//"#39839B";
	public static final String CLICKEDHBOXCOLOR = "whitesmoke";//"#39839B";
	public static final String PRESSEDHBOXCOLOR = "whitesmoke";//"#5aa0b8";
	
	public static final String DBICON = "HOME";
	public static final String SALESICON = "MONEY";
	public static final String INVICON = "CLIPBOARD";
	public static final String SERVICON = "WRENCH";
	public static final String PERSICON = "USERS";
	public static final String RESICON = "CUBES";
	public static final String STATICON = "LINE_CHART";
	public static final String PROVICON = "TRUCK";
	public static final String PROJICON = "LIST";
	public static final String DOCICON = "FOLDER";
	
	//USER OPTIONS BAR CONSTANTS
	public static final String BASEBUTTONCOLOR = "transparent";
	public static final String HOVERBUTTONCOLOR = "whitesmoke";
	public static final String CLICKEDBUTTONCOLOR = "whitesmoke";
	public static final String PRESSEDBUTTONCOLOR = "#eee";
	
	public static final String BASEICONCOLOR = "#999";
	public static final String HOVERICONCOLOR = "#085eb3";
	public static final String CLICKEDICONCOLOR = "#1c8fd0";
	public static final String PRESSEDICONCOLOR = "#1c8fd0";
	
	public static final String SEARICON = "SEARCH";
	public static final String NOTICON = "BELL";
	public static final String SETICON = "GEAR";
	public static final String HELPICON = "QUESTION_CIRCLE";
	
	//---------------------------------------------------- SIDEBAR ANIMATIONS ----------------------------------------------------------------------//
	
	//These methods set the new colours our buttons will have
	public void clickedButton(HBox hbox, Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(hbox, button, icon, DashboardButtonAnimations.CLICKEDHBOXCOLOR, DashboardButtonAnimations.CLICKEDTEXTCOLOR, DashboardButtonAnimations.CLICKEDTEXTCOLOR, iconImage);
		hbox.setId("hbox-clicked");
		button.setId("button-clicked");
		icon.setId("glyph-clicked");
	}
	
	public void pressedButton(HBox hbox, Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(hbox, button, icon, DashboardButtonAnimations.PRESSEDHBOXCOLOR, DashboardButtonAnimations.PRESSEDTEXTCOLOR, DashboardButtonAnimations.PRESSEDTEXTCOLOR, iconImage);
	}
	
	public void releasedButton(HBox hbox, Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(hbox, button, icon, DashboardButtonAnimations.HOVERHBOXCOLOR, DashboardButtonAnimations.HOVERTEXTCOLOR, DashboardButtonAnimations.HOVERTEXTCOLOR, iconImage);
	}
	
	public void enteredButton(HBox hbox, Button button, FontAwesomeIconView icon, String iconImage) {
		if(!button.isFocused())
			animateButton(hbox, button, icon, DashboardButtonAnimations.HOVERHBOXCOLOR, DashboardButtonAnimations.HOVERTEXTCOLOR, DashboardButtonAnimations.HOVERTEXTCOLOR, iconImage);
	}
	
	public void exitedButton(HBox hbox, Button button, FontAwesomeIconView icon, String iconImage) {
		if(!button.isFocused()) {
			animateButton(hbox, button, icon, DashboardButtonAnimations.BASEHBOXCOLOR, DashboardButtonAnimations.BASETEXTCOLOR, DashboardButtonAnimations.BASETEXTCOLOR, iconImage);
			hbox.setId("hbox-base");
			button.setId("button-base");
			icon.setId("glyph-base");
		}
	}
	
	
	//This is where the magic of the color change happens :v
	private void animateButton(HBox hbox, Button button, FontAwesomeIconView icon, String hboxColor, String buttonColor, String iconColor, String iconImage) {
		hbox.setStyle("-fx-background-color: "+hboxColor+";");
		button.setStyle("-fx-text-fill: "+buttonColor+";");
		icon.setGlyphName(iconImage);
		icon.setStyle("-fx-fill: "+iconColor+";");
	}
	
	//-------------------------------------------------------- USER OPTIONS ANIMATIONS ----------------------------------------------------------//
	
	public void clickedButton(Button button, Circle buttonReminder, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, buttonReminder, icon, DashboardButtonAnimations.CLICKEDBUTTONCOLOR, DashboardButtonAnimations.CLICKEDICONCOLOR, iconImage);
	}
	
	public void pressedButton(Button button, Circle buttonReminder, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, buttonReminder, icon, DashboardButtonAnimations.PRESSEDBUTTONCOLOR, DashboardButtonAnimations.PRESSEDICONCOLOR, iconImage);
	}
	
	public void releasedButton(Button button, Circle buttonReminder, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, buttonReminder, icon, DashboardButtonAnimations.HOVERBUTTONCOLOR, DashboardButtonAnimations.HOVERICONCOLOR, iconImage);
	}
	
	public void enteredButton(Button button, Circle buttonReminder, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, buttonReminder, icon, DashboardButtonAnimations.HOVERBUTTONCOLOR, DashboardButtonAnimations.HOVERICONCOLOR, iconImage);
	}
	
	public void exitedButton(Button button, Circle buttonReminder, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, buttonReminder, icon, DashboardButtonAnimations.BASEBUTTONCOLOR, DashboardButtonAnimations.BASEICONCOLOR, iconImage);
		buttonReminder.setStyle("-fx-stroke: #fff;");
	}
	
	private void animateButton(Button button, Circle buttonReminder, FontAwesomeIconView icon, String buttonColor, String iconColor, String iconImage) {
		button.setStyle("-fx-background-color: "+buttonColor+";");
		buttonReminder.setStyle("-fx-stroke: "+buttonColor+";");
		icon.setGlyphName(iconImage);
		icon.setStyle("-fx-fill: "+iconColor+";");
	}
	
	//-------------------------------------------------------- POPOVER ANIMATIONS -------------------------------------------------------//
	public static void setPopOverStyle(JFXButton[] buttons, VBox vBox, PopOver popOver) {
		for(JFXButton b : buttons) {
			b.setId("pop-over-buttons");
		}
		vBox.setId("pop-over-vbox");
		popOver.setDetachable(false);
		popOver.setArrowSize(0);
		popOver.setCornerRadius(0);
		popOver.setId("pop-over");
	}
}
