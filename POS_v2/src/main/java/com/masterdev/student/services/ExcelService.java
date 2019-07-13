package com.masterdev.student.services;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class ExcelService {
	Fillo fillo;
	Connection connection;
	
	public ExcelService() {
		fillo = new Fillo();
	}
	
	// ---------------------- GETTING AND SETTING METHODS -------------------------//
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	// 
	public void connect(String document) {
		try {
			setConnection(fillo.getConnection("C:\\Users\\LuisF\\git\\POS_v2\\POS_v2\\src\\main\\resources\\documents\\"+document));
		} catch (FilloException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insert(String strQuery) {
		try {
			connection.executeUpdate(strQuery);
			connection.close();
		} catch (FilloException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
}
