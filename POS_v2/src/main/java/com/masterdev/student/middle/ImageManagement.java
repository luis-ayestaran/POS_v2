package com.masterdev.student.middle;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.masterdev.student.views.InventoryAddForm;

import javafx.stage.FileChooser;

import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class ImageManagement {
	
	public void imageDragOver(DragEvent event) {
		if(event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	
	public Image imageDrop(DragEvent event) {
		List<File> files = event.getDragboard().getFiles();
		Image img = null;
		try {
			img = new Image(new FileInputStream(files.get(0)));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return img; //Sets the image we're dropping into the ImageView
	}
	
	public Image imageBrowse() {
		Image img = null;
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter JPGFilter = new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png", "*.gif");
		//FileChooser.ExtensionFilter PNGFilter = new FileChooser.ExtensionFilter("PNG Files (*.png)", "*.png");
		//FileChooser.ExtensionFilter GIFFilter = new FileChooser.ExtensionFilter("GIF Files (*.gif)", "*.gif");
		fileChooser.getExtensionFilters().addAll(JPGFilter); //, PNGFilter, GIFFilter);
        File file = fileChooser.showOpenDialog(InventoryAddForm.getStage());
        if (file != null) {
        	try {
        		img = new Image(new FileInputStream(file));
        	} catch (FileNotFoundException e) {
        		e.printStackTrace();
        	}
            //openFile(file);			Only if we want to open the image with another application
        }
		
		return img;
	}
	
	private void openFile(File file) {
		Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                ImageManagement.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
}
