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
@Table(name="check_in")
public class CheckIn {
	@Id
	@Column(name="checkin_id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long id;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	private Date date;
	
	public CheckIn() {}
	
	public CheckIn(User user, Date date) {
		this.setUser(user);
		this.setDate(date);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		sb.append("Id: ");
		sb.append(this.getId());
		sb.append(", ");
		sb.append("User: ");
		sb.append(this.getUser().getName());
		sb.append(", ");
		sb.append("Date: ");
		sb.append(this.getDate());
		sb.append(" ]");
		return sb.toString();
	}
}
