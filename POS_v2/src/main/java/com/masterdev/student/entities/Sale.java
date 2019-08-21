package com.masterdev.student.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sale")
public class Sale {

	@Id
	@Column(name="folio")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long folio;
	private Date date;
	private Float total;
	
	@OneToMany(mappedBy="sale", fetch = FetchType.EAGER)
	private List<SaleDetail> detail;
	
	public Sale() {}
	
	public Sale(Date date) {
		this.setDate(date);
	}
	
	public Sale(Date date, Float total) {
		this.setDate(date);
		this.setTotal(total);
	}
	
	public Sale(Date date, Float total, List<SaleDetail> detail) {
		this(date,total);
		this.setDetail(detail);
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

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public List<SaleDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<SaleDetail> detail) {
		this.detail = detail;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		sb.append("Folio: ");
		sb.append(this.getFolio());
		sb.append(", ");
		sb.append("Date: ");
		sb.append(this.getDate());
		sb.append(" ]");
		return sb.toString();
	}
	
}
