package com.masterdev.student.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cash_register")
public class CashRegister {
	@Id
	@Column(name="cashregister_id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long id;
	private String name;
	private Float remaining;
	private Boolean used;
	
	public CashRegister() {}
	
	public CashRegister(String name, Float remaining) {
		this.setName(name);
		this.setRemaining(remaining);
		this.setUsed(false);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Float getRemaining() {
		return remaining;
	}

	public void setRemaining(Float remaining) {
		this.remaining = remaining;
	}
	
	public Boolean isUsed() {
		if(used) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setUsed(Boolean used) {
		this.used = used;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		sb.append("Id: ");
		sb.append(this.getId());
		sb.append(", ");
		sb.append("Name: ");
		sb.append(this.getName());
		sb.append(", ");
		sb.append("Amount: ");
		sb.append(this.getRemaining());
		sb.append(", ");
		sb.append("Used: ");
		sb.append(this.isUsed());
		sb.append(" ]");
		return sb.toString();
	}
	
}
