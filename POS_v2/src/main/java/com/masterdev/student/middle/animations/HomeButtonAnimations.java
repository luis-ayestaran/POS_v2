package com.masterdev.student.middle.animations;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class HomeButtonAnimations {
	public static final String BASEBUTTONCOLOR = "transparent";
	public static final String HOVERBUTTONCOLOR = "transparent";
	public static final String CLICKEDBUTTONCOLOR = "transparent";
	public static final String PRESSEDBUTTONCOLOR = "transparent";
	
	public static final String BASETEXTCOLOR = "#085eb3";
	public static final String HOVERTEXTCOLOR = "#1c8fd0";
	public static final String CLICKEDTEXTCOLOR = "#1c8fd0";
	public static final String PRESSEDTEXTCOLOR = "#1c8fd0";
	
	public static final String BASEICONCOLOR = "#085eb3";
	public static final String HOVERICONCOLOR = "#1c8fd0";
	public static final String CLICKEDICONCOLOR = "#1c8fd0";
	public static final String PRESSEDICONCOLOR = "#1c8fd0";
	
	public static final String ARROWICON = "ANGLE_RIGHT";
	
	public void clickedButton(Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, icon, HomeButtonAnimations.CLICKEDBUTTONCOLOR, HomeButtonAnimations.CLICKEDICONCOLOR, iconImage);
	}
	
	public void pressedButton(Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, icon, HomeButtonAnimations.PRESSEDBUTTONCOLOR, HomeButtonAnimations.PRESSEDICONCOLOR, iconImage);
	}
	
	public void releasedButton(Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, icon, HomeButtonAnimations.HOVERBUTTONCOLOR, HomeButtonAnimations.HOVERICONCOLOR, iconImage);
	}
	
	public void enteredButton(Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, icon, HomeButtonAnimations.HOVERBUTTONCOLOR, HomeButtonAnimations.HOVERICONCOLOR, iconImage);
	}
	
	public void exitedButton(Button button, FontAwesomeIconView icon, String iconImage) {
		animateButton(button, icon, HomeButtonAnimations.BASEBUTTONCOLOR, HomeButtonAnimations.BASEICONCOLOR, iconImage);
	}
	
	private void animateButton(Button button, FontAwesomeIconView icon, String buttonColor, String iconColor, String iconImage) {
		button.setStyle("-fx-background-color: "+buttonColor+";");
		icon.setGlyphName(iconImage);
		icon.setStyle("-fx-fill: "+iconColor+";");
	}
	
}
