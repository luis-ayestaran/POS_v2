package com.masterdev.student.pojos;

import java.util.Date;

import com.masterdev.student.entities.Product;
import com.masterdev.student.entities.Sale;
import com.masterdev.student.entities.SaleDetail;

public class SalesHistoryEntry {
	private Long folio;
	private String strConventionalDate;
	private String strDate;
	private String strTime;
	private Float total;
	private Date date;
	private Sale sale;
	
	public SalesHistoryEntry() {}
	
	public SalesHistoryEntry(Long folio, String strConventionalDate, String strDate, String strTime, Float total, Date date, Sale sale) {
		this.setFolio(folio);
		this.setStrConventionalDate(strConventionalDate);
		this.setStrDate(strDate);
		this.setStrTime(strTime);
		this.setTotal(total);
		this.setDate(date);
		this.setSale(sale);
	}
	
	public Long getFolio() {
		return folio;
	}
	
	public void setFolio(Long folio) {
		this.folio = folio;
	}
	
	public String getStrConventionalDate() {
		return strConventionalDate;
	}
	
	public void setStrConventionalDate(String strConventionalDate) {
		this.strConventionalDate = strConventionalDate;
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
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Sale getSale() {
		return sale;
	}
	
	public void setSale(Sale sale) {
		this.sale = sale;
	}
}
