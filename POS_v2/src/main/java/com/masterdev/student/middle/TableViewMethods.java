package com.masterdev.student.middle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.controlsfx.control.textfield.TextFields;


public class TableViewMethods {
	
	private TableView<String> personalTable;
	private TableView<String> warehouseTable;
	
	public TableView<String> getPersonalTable() {
		return personalTable;
	}
	
	public void setPersonalTable(TableView<String> personalTable) {
		this.personalTable = personalTable;
	}
	
	public TableView<String> getWarehouseTable() {
		return warehouseTable;
	}
	
	public void setWarehouseTable(TableView<String> warehouseTable) {
		System.out.println(warehouseTable);
		this.warehouseTable = warehouseTable;
		System.out.println(getWarehouseTable());
	}
	
	public void addPersonalRow(String[] data) {
		addRow(this.getPersonalTable(), data);
	}
	
	public void addWarehouseRow(String[] data) {
		addRow(this.getWarehouseTable(), data);
	}
	
	public void addRow(TableView<String> tableView, String[] data) {
		
	}
	
}
