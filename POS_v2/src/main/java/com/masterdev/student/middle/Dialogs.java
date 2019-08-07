package com.masterdev.student.middle;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
	
	public JFXDialog acceptDialog (String heading, String body, StackPane stackPane, Node focusable) {
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
		        if(focusable != null) {
		        	focusable.requestFocus();
		        }
		    }

		});
		content.setActions(button);
		dialog.setOverlayClose(false);
		
		stackPane.getChildren().get(0).setEffect(blur);
		stackPane.getChildren().get(0).setDisable(true);
		
		dialog.show();
		
		
		return dialog;
	}
	
	public String inputDialog(String title, String header, String content) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		dialog.setGraphic(null);
		
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/stylesheets/textInputDialog.css").toExternalForm());
		dialogPane.getStyleClass().add("textInputDialog");
		
		Stage stage = (Stage)dialogPane.getScene().getWindow();
		Image icon = new Image("/stylesheets/images/LOGO.png");
		stage.getIcons().add(icon);
		
		Optional<String> result = dialog.showAndWait();
		if(result != null)
			return result.get();
		else
			return null;
	}
	
	public Boolean acceptWaitingDialog(String title, String header, String content) {
		Boolean exit = true;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/stylesheets/confirmationDialog.css").toExternalForm());
		dialogPane.getStyleClass().add("confirmationDialog");
		dialogPane.setMinHeight(200);
		dialogPane.setMinWidth(350);
		
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.setGraphic(null);
		
		Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
		Image icon = new Image("/stylesheets/images/LOGO.png");
		stage.getIcons().add(icon);
	    ButtonType buttonTypeAccept = new ButtonType("ACEPTAR", ButtonData.OK_DONE);
	    alert.getButtonTypes().setAll(buttonTypeAccept);
	    
	    Button accept = (Button) dialogPane.lookupButton(buttonTypeAccept);
		accept.getStyleClass().add("acceptButton");
	    
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeAccept){
		    exit = true;
		} else {
		    exit = false;
		}
		
		alert.setOnCloseRequest(e -> {
			Button acceptButton = ( Button ) alert.getDialogPane().lookupButton( buttonTypeAccept );
		    acceptButton.fire();
		});
		
		return exit;
	}
	
	public Boolean confirmationDialog(String title, String header, String content) {
		Boolean exit = true;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/stylesheets/confirmationDialog.css").toExternalForm());
		dialogPane.getStyleClass().add("confirmationDialog");
		dialogPane.setMinHeight(200);
		dialogPane.setMinWidth(350);
		
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.setGraphic(null);
		
		Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
		Image icon = new Image("/stylesheets/images/LOGO.png");
		stage.getIcons().add(icon);
	    ButtonType buttonTypeCancel = new ButtonType("CANCELAR", ButtonData.CANCEL_CLOSE);
	    ButtonType buttonTypeAccept = new ButtonType("ACEPTAR", ButtonData.OK_DONE);
	    alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeAccept);
	    
	    Button accept = (Button) dialogPane.lookupButton(buttonTypeAccept);
		accept.getStyleClass().add("acceptButton");
		Button cancel = (Button) dialogPane.lookupButton(buttonTypeCancel);
		cancel.getStyleClass().add("cancelButton");
	    
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeAccept){
		    exit = true;
		} else {
		    exit = false;
		}
		
		alert.setOnCloseRequest(e -> {
			Button acceptButton = ( Button ) alert.getDialogPane().lookupButton( buttonTypeAccept );
		    acceptButton.fire();
		});
		
		return exit;
	}
}
