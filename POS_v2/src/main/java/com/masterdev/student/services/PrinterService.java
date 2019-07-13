package com.masterdev.student.services;

import java.util.List;
import java.util.Vector;

import com.masterdev.student.pojos.ProductOnSale;
import com.masterdev.student.utils.printer.printer;

public class PrinterService {
	private printer myPrinter;
	
	public PrinterService() {
		myPrinter = new printer();
	}
	
	public void print(List<ProductOnSale> saleList, Float subtotal, Float discount, Float total, Float cash, Float change) {
		String strSubtotal = String.format("%.2f", subtotal);
		String strDiscount = String.format("%.2f", discount);
		String strTotal = String.format("%.2f", total);
		String strCash = String.format("%.2f", cash);
		String strChange = String.format("%.2f", change);
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		for(ProductOnSale pos : saleList) {
			Vector<String> row = new Vector<String>();
			String shortProduct = shortenString(pos.getProduct().getProduct(), 4);
			row.add(shortProduct);
			row.add(String.format("%.2f", pos.getUnitPrice()));
			String shortQuantity = shortenQuantity(pos.getQuantity());
			row.add(shortQuantity);
			String shortUnit = shortenUnit(pos.getUnit());
			row.add(shortUnit);
			row.add(String.format("%.2f", pos.getAmount()));
			data.add(row);
		}
		
		myPrinter.print(data, strSubtotal, strDiscount, strTotal, strCash, strChange);
	}
	
	public String shortenString(String string, Integer charNumber) {
		String result = "";
		char[] newString = new char[charNumber];
		for(int i=0; i<charNumber; i++) {
			newString[i] = string.charAt(i);
		}
		result = new String(newString);
		return result;
	}
	
	public String shortenQuantity(Float quantity) {
		String strQuantity = "";
		if(quantity % 1 == 0)
			strQuantity = String.format("%.0f", quantity);
		else
			strQuantity = String.format("%.2f", quantity);
		return strQuantity;
	}
	
	public String shortenUnit(String unit) {
		String result = "";
		Integer i = 0;
		Integer j = 0;
		char[] newString = new char[2];
		while(i < unit.length() && j < 2) {
			Boolean consonant = isConsonant(unit.charAt(i)); 
			if(consonant) {
				newString[j] = unit.charAt(i);
				j++;
			}
			i++;
		}
		result = new String(newString);
		return result;
	}
	
	public Boolean isConsonant(char character) {
		if((character >= 'A' && character <= 'Z') || (character >='a' && character <= 'z')) {
			if(character != 'a' && character != 'e' && character != 'i' && character != 'o' && character != 'u')
				return true;
			else
				return false;
		} else {
			return false;
		}
	}
}
