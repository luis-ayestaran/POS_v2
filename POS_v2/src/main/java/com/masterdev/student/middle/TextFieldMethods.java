package com.masterdev.student.middle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.control.TextField;

import org.controlsfx.control.textfield.TextFields;

public class TextFieldMethods {

	public void addTextFieldFocusListener(final TextField textField, final String defaultText) {
		textField.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		        	if(textField.getText().equals(defaultText))
		        		textField.setText("");
		        }
		        else
		        {
		        	if(textField.getText().equals(""))
		        		textField.setText(defaultText);
		        }
		    }
		});
	}
	
	public void addWordSuggestions(TextField textField, String[] wordSuggestions) {
		TextFields.bindAutoCompletion(textField, wordSuggestions);
	}
}
