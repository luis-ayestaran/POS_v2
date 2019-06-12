package com.masterdev.student.middle;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.masterdev.student.views.Dashboard;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class Dialogs{
	
	private boolean flag;
	
	public Dialogs() {
		flag = false;
	}
	
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	public Boolean getFlag() {
		return flag;
	}
	
	public JFXDialog acceptDialog (String heading, String body, StackPane stackPane) {
		BoxBlur blur = new BoxBlur(2, 2, 2);
		JFXDialogLayout content= new JFXDialogLayout();
		Text head = new Text(heading);
		head.setStyle("-fx-font-size: 16px;");
		content.setHeading(head);
		Text bodyText = new Text(body);
		bodyText.setStyle("-fx-font-size: 14px;");
		content.setBody(bodyText);
		JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.BOTTOM);
		JFXButton button = new JFXButton("ACEPTAR");
		button.setStyle("-fx-text-fill: #085eb3;"
					+ "-fx-font-family: \"HelveticaNeueLT Pro 65 Md\";"
					+ "-fx-padding: 6px;"
					+ "-fx-font-size: 14px;"
					+ "-fx-font-weight: bold;"
					+ "-fx-background-color: transparent;"
					+ "-fx-effect: none;"
					+ "-fx-focus-traversable: false;");
		button.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event){
		    	stackPane.getChildren().get(0).setEffect(null);
		    	stackPane.getChildren().get(0).setDisable(false);
		        dialog.close();
		    }

		});
		content.setActions(button);
		dialog.setOverlayClose(false);
		
		stackPane.getChildren().get(0).setEffect(blur);
		stackPane.getChildren().get(0).setDisable(true);
		
		dialog.show();
		
		
		return dialog;
	}
	
	public static String inputDialog(String title, String header, String content) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		
		Optional<String> result = dialog.showAndWait();
		
		return result.get();
	}
	
	public static Boolean confirmationDialog(String title, String header, String content) {
		Boolean exit = false;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
	
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    exit = true;
		} else {
		    exit = false;
		}
		return exit;
	}
}
