package com.masterdev.student.pojos;

import com.masterdev.student.entities.Sale;
import com.masterdev.student.views.controllers.SalesFormController;

import javafx.scene.layout.StackPane;

public class SaleWaiting {
	
	private Long folio;
	private String client;
	private String strDate;
	private String strTime;
	private Float total;
	private Sale sale;
	private StackPane view;
	private SalesFormController data;
	
	public SaleWaiting() {}
	
	public SaleWaiting(Long folio, String client, String strDate, String strTime, Float total, Sale sale, StackPane view, SalesFormController data) {
		this.setFolio(folio);
		this.setClient(client);
		this.setStrDate(strDate);
		this.setStrTime(strTime);
		this.setTotal(total);
		this.setSale(sale);
		this.setView(view);
		this.setData(data);
	}
	
	public Long getFolio() {
		return folio;
	}
	
	public void setFolio(Long folio) {
		this.folio = folio;
	}
	
	public String getClient() {
		return client;
	}
	
	public void setClient(String client) {
		this.client = client;
	}
	
	public String getStrDate() {
		return strDate;
	}
	
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	
	public String getStrTime() {
		return strTime;
	}
	
	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}
	
	public Float getTotal() {
		return total;
	}
	
	public void setTotal(Float total) {
		this.total = total;
	}
	
	public Sale getSale() {
		return sale;
	}
	
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	public StackPane getView() {
		return view;
	}
	
	public void setView(StackPane view) {
		this.view = view;
	}
	
	public SalesFormController getData() {
		return data;
	}
	
	public void setData(SalesFormController data) {
		this.data = data;
	}
	
}
