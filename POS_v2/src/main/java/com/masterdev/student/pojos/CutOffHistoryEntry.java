package com.masterdev.student.pojos;

import java.util.Date;

import com.masterdev.student.entities.CutOff;

public class CutOffHistoryEntry {
	private Long folio;
	private String strConventionalDate;
	private String strDate;
	private String strTime;
	private Date date;
	private String admin;
	private Float remaining;
	private Float amount;
	private CutOff cutOff;
	
	public CutOffHistoryEntry() {}
	
	public CutOffHistoryEntry(Long folio, String strConventionalDate, String strDate, String strTime, Date date, String admin, Float remaining, Float amount, CutOff cutOff) {
		this.setFolio(folio);
		this.setStrConventionalDate(strConventionalDate);
		this.setStrDate(strDate);
		this.setStrTime(strTime);
		this.setDate(date);
		this.setAdmin(admin);
		this.setRemaining(remaining);
		this.setAmount(amount);
		this.setCutOff(cutOff);
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
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getAdmin() {
		return admin;
	}
	
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	public Float getRemaining() {
		return remaining;
	}
	
	public void setRemaining(Float remaining) {
		this.remaining = remaining;
	}
	
	public Float getAmount() {
		return amount;
	}
	
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	public CutOff getCutOff() {
		return cutOff;
	}
	
	public void setCutOff(CutOff cutOff) {
		this.cutOff = cutOff;
	}
}
