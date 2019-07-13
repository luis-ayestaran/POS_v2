package com.masterdev.student.pojos;

import com.masterdev.student.entities.Product;
import com.masterdev.student.entities.SaleDetail;

public class ProductOnSale {
	private String description;
	private String barcode;
	private Float unitPrice;
	private Float quantity;
	private String unit;
	private Float discount;
	private Float amount;
	private Product product;
	private SaleDetail saleDetail;
	
	public ProductOnSale() {}
	
	public ProductOnSale(String description, String barcode, Float unitPrice, Float quantity, String unit, Float discount, Float amount, Product product, SaleDetail saleDetail) {
		setDescription(description);
		setBarcode(barcode);
		setUnitPrice(unitPrice);
		setQuantity(quantity);
		setUnit(unit);
		setDiscount(discount);
		setAmount(amount);
		setProduct(product);
		setSaleDetail(saleDetail);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public Float getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public Float getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Float getDiscount() {
		return discount;
	}
	
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	
	public Float getAmount() {
		Float amount = (1 - getDiscount() / 100) * (getUnitPrice() * getQuantity());
		return amount;
	}
	
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public SaleDetail getSaleDetail() {
		return saleDetail;
	}
	
	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}
}
