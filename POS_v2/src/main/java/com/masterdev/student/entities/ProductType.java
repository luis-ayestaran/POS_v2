package com.masterdev.student.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="product_type")
public class ProductType {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	@Column(name="producttype_id")
	private Long id;
	
	@Column(nullable=false, length=255)
	private String type;
	
	@OneToMany(mappedBy="productType")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Product> products;
	
	public ProductType() {}
	
	public ProductType(String type) {
		this.setType(type);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Float getProductsSold() {
		Float productsSold = 0.0f;
		List<Product> products = getProducts();
		System.out.println(getType() + ": " + products);
		for(Product p : products) {
			productsSold += p.getUnitsSold();
		}
		return productsSold;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ID: ");
		sb.append(this.getId());
		sb.append(", ");
		sb.append("Type: ");
		sb.append(this.getType());
		sb.append(" ]");
		return sb.toString(); 
	}
}
