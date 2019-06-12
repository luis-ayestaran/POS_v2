package com.masterdev.student.middle;

import java.util.ArrayList;
import java.util.List;

import com.masterdev.student.entities.ProductType;

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
	
	public void addCategoryItems(ComboBox<String> comboBox, List<ProductType> items) {
		if(items != null) {
			ObservableList<String> options = FXCollections.observableArrayList();
			for(int i=0; i<items.size() ; i++) {
				options.add(items.get(i).getType());
			}
			comboBox.getItems().addAll(options);
		}
	}
}
