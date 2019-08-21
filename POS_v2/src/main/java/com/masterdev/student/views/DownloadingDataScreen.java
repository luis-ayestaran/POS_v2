package com.masterdev.student.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DownloadingDataScreen {
	private static Stage stage;
	
	public DownloadingDataScreen() {}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage s) {
		stage = s;
	}

	public void loadView() {
		FXMLLoader loader = null;
		StackPane node = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/fxml/downloadingDataScreen.fxml"));
			node = (StackPane) loader.load();
			Scene scene = new Scene(node, 400, 300);
			Stage stage = new Stage();
			setStage(stage);
			getStage().setScene(scene);
			getStage().initStyle(StageStyle.UNDECORATED);
			Image icon = new Image("/stylesheets/images/LOGO.png");
			getStage().getIcons().add(icon);
			getStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
