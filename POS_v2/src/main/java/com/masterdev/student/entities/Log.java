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
@Table(name="log")
public class Log {
	@Id
	@Column(name="folio")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long folio;
	private Date date;
	@ManyToOne
	@JoinColumn(name="user_id")
	public User user;
	
	public Log() {}
	
	public Log(Date date, User user) {
		this.setDate(date);
		this.setUser(user);
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		sb.append("Admin.: ");
		sb.append(this.getUser());
		sb.append(" ]");
		return sb.toString();
	}
	
}
