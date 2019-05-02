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
@Table(name="product_batch")
public class ProductBatch {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	@Column(name="productbatch_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	public Product product;
	
	@Column(nullable=false)
	private Float quantity;
	private Date entryDate;
	private Date dischargeDate;
	
	public ProductBatch() {}
	
	public ProductBatch(Product product, Float quantity, Date entryDate, Date dischargeDate) {
		this.setProduct(product);
		this.setQuantity(quantity);
		this.setEntryDate(entryDate);
		this.setDischargeDate(dischargeDate);
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
}
