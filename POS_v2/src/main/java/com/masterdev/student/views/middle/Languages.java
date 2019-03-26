package com.masterdev.student.middle;

import java.io.IOException;

import com.masterdev.student.views.Login;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Languages {
	
	private static String lan = "Spanish";
	
	public static String getLanguage() {
		return lan;
	}
	
	public static void setLanguage(String newLan) {
		lan = newLan;
	}
	
	public void setEnglish() {
		//Generando cambios
		setLanguage("English");
		Thread thread = new Thread(new Runnable() {
		    public void run() {
		    	//Método que actualiza la interfaz gráfica
		        Platform.runLater(new Runnable() {		//Realizamos cambios
		            public void run() {
		            	BorderPane root = null;
		            	try {
		                	root = (BorderPane) FXMLLoader.load(getClass().getResource("/fxml/login_en.fxml"));
		                } catch(IOException e) {
		                	e.printStackTrace();
		                }
		                Scene scene = new Scene(root, 800, 480);
		                Stage stage = Login.getStage();
		            	stage.setScene(scene);
		            }
		        });
		    }
		});
		thread.setDaemon(true); //Aseguramos que el hilo termine su ejecución al cerrar la app
		thread.start();
	}
	
	public void setSpanish() {
		setLanguage("Spanish");
		Thread thread = new Thread(new Runnable() {
		    public void run() {
		        Platform.runLater(new Runnable() {
		            public void run() {
		            	BorderPane root = null;
		            	try {
		                	root = (BorderPane) FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
		                } catch(IOException e) {
		                	e.printStackTrace();
		                }
		                Scene scene = new Scene(root, 800, 480);
		                Stage stage = Login.getStage();
		            	stage.setScene(scene);
		            }
		        });
		    }
		});
		thread.setDaemon(true);
		thread.start();
	}
	
}
