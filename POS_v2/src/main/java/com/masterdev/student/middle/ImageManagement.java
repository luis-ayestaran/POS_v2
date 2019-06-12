package com.masterdev.student.middle;

import java.awt.Desktop;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.masterdev.student.views.InventoryAddForm;
import java.io.FileReader;
import java.io.FileWriter;

import javafx.stage.FileChooser;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class ImageManagement {
	
	public void imageDragOver(DragEvent event) {
		if(event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	
	public File imageDrop(DragEvent event) {
		List<File> files = event.getDragboard().getFiles();
		return files.get(0);		//REMOVE IF DOESN'T WORK
		/*Image img = null;
		try {
			img = new Image(new FileInputStream(files.get(0)));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return img; //Sets the image we're dropping into the ImageView*/
		
	}
	
	public File imageBrowse() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png", "*.gif");
		fileChooser.getExtensionFilters().addAll(imageFilter); 
        File file = fileChooser.showOpenDialog(InventoryAddForm.getStage());
        return file;
        /*if (file != null) {
        	try {
        		img = new Image(new FileInputStream(file));
        	} catch (FileNotFoundException e) {
        		e.printStackTrace();
        	}
            //openFile(file);			Only if we want to open the image with another application
        }
		
		return img;*/
	}
	
	public void openFile(File file) {		//Only if we want to open the image with another application
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
	
	public void copyImage(String from, String to) {
		FileReader fileReader = null;
	    FileWriter fileWriter = null;
	    try {;
	    	File file = new File(to);
	        file.createNewFile();
	        fileReader = new FileReader(from);
	        fileWriter = new FileWriter(to);
	        Integer c = fileReader.read();
	        while(c != -1) {
	            fileWriter.write(c);
	            c = fileReader.read();
	        }
	    } catch(IOException e) {
	        e.printStackTrace();
	    } finally {
	        close(fileReader);
	        close(fileWriter);
	    }
	}
	
	public void close(Closeable stream) {
	    try {
	        if (stream != null) {
	            stream.close();
	        }
	    } catch(IOException e) {
	        //...
	    }
	}
	
	public byte[] FileToBlob(File file) {
		byte[] fileContent = new byte[(int) file.length()];
		FileInputStream inputStream = null;
		try {
			// create an input stream pointing to the file
			inputStream = new FileInputStream(file);
			// read the contents of file into byte array
			inputStream.read(fileContent);
		} catch (IOException e) {
			try {
				throw new IOException("No fue posible convertir el archivo a imagen. " + e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			// close input stream
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return fileContent;
	}
}
