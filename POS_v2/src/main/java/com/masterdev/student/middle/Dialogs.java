package com.masterdev.student.middle;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
	
	public void acceptDialog (String heading, String body, StackPane stackPane) {
		JFXDialogLayout content= new JFXDialogLayout();
		content.setHeading(new Text(heading));
		content.setBody(new Text(body));
		JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
		JFXButton button = new JFXButton("ACEPTAR");
		button.setTextFill(Color.web("#4F9F64"));
		button.setStyle("-fx-font-weight: bold;");
		button.setId("dialog-buttons");
		button.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event){
		        dialog.close();
		    }

		});
		content.setActions(button);
		dialog.setStyle("-fx-font-size: 14px;");
		dialog.show();
		dialog.requestFocus();
	}
	
	public void acceptCancelDialog (String heading, String body, StackPane stackPane) {
		JFXDialogLayout content= new JFXDialogLayout();
		content.setHeading(new Text(heading));
		content.setBody(new Text(body));
		JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
		JFXButton cancelButton = new JFXButton("CANCELAR");
		cancelButton.setTextFill(Color.web("#4F9F64"));
		cancelButton.setStyle("-fx-font-weight: bold;");
		cancelButton.setId("dialog-buttons");
		cancelButton.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event){
		    	setFlag(false);
		    	System.out.println(getFlag());
		        dialog.close();
		    }

		});
		JFXButton acceptButton = new JFXButton("ACEPTAR");
		acceptButton.setTextFill(Color.web("#4F9F64"));
		acceptButton.setStyle("-fx-font-weight: bold;");
		acceptButton.setId("dialog-buttons");
		acceptButton.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event){
		    	setFlag(true);
		    	System.out.println(getFlag());
		        dialog.close();
		    }

		});
		content.setActions(acceptButton, cancelButton);
		dialog.setStyle("-fx-font-size: 14px;");
		dialog.show();
	}
	
	public static String inputDialog(String title, String header, String content) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		Optional<String> result = dialog.showAndWait();
		
		return result.get();
	}
}
