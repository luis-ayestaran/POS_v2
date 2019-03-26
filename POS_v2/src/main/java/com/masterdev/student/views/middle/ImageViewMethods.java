package com.masterdev.student.middle;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ImageViewMethods {
	
	public void makeRoundImage(ImageView imageView, Integer radius) {
		Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(radius);
        clip.setArcHeight(radius);
        imageView.setClip(clip);
        
        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imageView.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        imageView.setClip(null);
        
        // store the rounded image in the imageView.
        imageView.setImage(image);
	}
}
