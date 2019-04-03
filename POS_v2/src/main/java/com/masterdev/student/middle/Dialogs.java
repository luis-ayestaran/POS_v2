package com.masterdev.student.middle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.masterdev.student.views.Login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Dialogs{
	
	public static void acceptDialog (String heading, String body, StackPane stackPane) {
		JFXDialogLayout content= new JFXDialogLayout();
		content.setHeading(new Text(heading));
		content.setBody(new Text(body));
		JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
		JFXButton button = new JFXButton("ACEPTAR");
		button.setTextFill(Color.web("#4F9F64"));
		button.setStyle("-fx-font-weight: bold;");
		button.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event){
		        dialog.close();
		    }

		});
		content.setActions(button);
		dialog.setStyle("-fx-font-size: 14px;");
		dialog.show();
	}

}
