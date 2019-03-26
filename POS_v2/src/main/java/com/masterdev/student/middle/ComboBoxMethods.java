package com.masterdev.student.middle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.ComboBox;

public class ComboBoxMethods {
	
	//We add the "options" to ur comboboxes
	public void addItems(ComboBox<String> comboBox, String[] items) {
		ObservableList<String> options = FXCollections.observableArrayList();
		for(int i=0; i<items.length ; i++)
		{
			options.add(items[i]);
		}
		comboBox.getItems().addAll(options);
	}
}
