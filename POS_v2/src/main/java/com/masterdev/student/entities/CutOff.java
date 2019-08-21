package com.masterdev.student.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cut_off")
public class CutOff {
	@Id
	@Column(name="folio")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long folio;
	private Date date;
	private Float remaining;
	private Float amount;
	@ManyToOne
	@JoinColumn(name="user_id")
	public User user;
	@ManyToOne
	@JoinColumn(name="cashregister_id")
	public CashRegister cashRegister;
	
	public CutOff() {}
	
	public CutOff(Date date, Float remaining, Float amount, User user, CashRegister cashRegister) {
		this.setDate(date);
		this.setRemaining(remaining);
		this.setAmount(amount);
		this.setUser(user);
		this.setCashRegister(cashRegister);
	}
	
	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public CashRegister getCashRegister() {
		return cashRegister;
	}

	public void setCashRegister(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		sb.append("Folio: ");
		sb.append(this.getFolio());
		sb.append(", ");
		sb.append("Date: ");
		sb.append(this.getDate());
		sb.append(", ");
		sb.append("Amount: ");
		sb.append(this.getAmount());
		sb.append(", ");
		sb.append("Admin.: ");
		sb.append(this.getUser());
		sb.append(" ]");
		return sb.toString();
	}
	
}
