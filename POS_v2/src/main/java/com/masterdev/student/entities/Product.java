package com.masterdev.student.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	@Column(name = "product_id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="producttype_id")
	private ProductType productType;
	
	@OneToMany(mappedBy="product", fetch = FetchType.EAGER)
	private List<ProductBatch> productBatches;

	private String product;
	private String brand;
	private Float wholeCost;
	private Float retailCost;
	private Float wholePrice;
	private Float retailPrice;
	private Float wholeUtility;
	private Float retailUtility;
	private Float quantity;
	private String purchaseUnit;
	private String purchaseSubunit;
	private String netContent;
	private Float purchaseSubunitAmount;
	/*private String saleWholeUnit;
	private String saleRetailUnit;*/
	private Float minStock;
	private Float maxStock;
	private Float discount;
	private Float taxes;
	private String internalCode;
	private String barCode;
	private Boolean inBulk;
	private Float unitsSold;
	
	/*@Lob
    @Column(name="product_image", columnDefinition="mediumblob")*/
	private /*byte[]*/ String image;
	
	public Product() {}
	
	public Product(String product,
			String brand,
			Float wholeCost,
			Float retailCost,
			Float wholePrice,
			Float retailPrice,
			Float quantity,
			String purchaseUnit,
			String purchaseSubunit,
			String netContent,
			Float purchaseSubunitAmount,
			String saleWholeUnit,
			String saleRetailUnit,
			Float wholeUtility,
			Float retailUtility,
			Float minStock,
			Float maxStock,
			Float discount,
			Float taxes,
			String internalCode,
			String barCode,
			String image,
			ProductType productType,
			Boolean inBulk,
			Float unitsSold) {
		this.setProduct(product);
		this.setBrand(brand);
		this.setWholeCost(wholeCost);
		this.setRetailCost(retailCost);
		this.setWholePrice(wholePrice);
		this.setRetailPrice(retailPrice);
		this.setQuantity(quantity);
		this.setPurchaseUnit(purchaseUnit);
		this.setPurchaseSubunit(purchaseSubunit);
		this.setNetContent(netContent);
		this.setPurchaseSubunitAmount(purchaseSubunitAmount);
		/*this.setSaleWholeUnit(saleWholeUnit);
		this.setSaleRetailUnit(saleRetailUnit);*/
		this.setWholeUtility(wholeUtility);
		this.setRetailUtility(retailUtility);
		this.setMinStock(minStock);
		this.setMaxStock(maxStock);
		this.setDiscount(discount);
		this.setTaxes(taxes);
		this.setInternalCode(internalCode);
		this.setBarCode(barCode);
		this.setImage(image);
		this.setProductType(productType);
		this.setInBulk(inBulk);
		this.setUnitsSold(unitsSold);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Float getWholeCost() {
		return wholeCost;
	}
	public void setWholeCost(Float wholeCost) {
		this.wholeCost = wholeCost;
	}
	public Float getRetailCost() {
		return retailCost;
	}
	public void setRetailCost(Float retailCost) {
		this.retailCost = retailCost;
	}
	public Float getWholePrice() {
		return wholePrice;
	}
	public void setWholePrice(Float wholePrice) {
		this.wholePrice = wholePrice;
	}
	public Float getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Float retailPrice) {
		this.retailPrice = retailPrice;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public String getPurchaseUnit() {
		return purchaseUnit;
	}
	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}
	public String getPurchaseSubunit() {
		return purchaseSubunit;
	}
	public void setPurchaseSubunit(String purchaseSubunit) {
		this.purchaseSubunit = purchaseSubunit;
	}
	public String getNetContent() {
		return netContent;
	}
	public void setNetContent(String netContent) {
		this.netContent = netContent;
	}
	public Float getPurchaseSubunitAmount() {
		return purchaseSubunitAmount;
	}
	public void setPurchaseSubunitAmount(Float purchaseSubunitAmount) {
		this.purchaseSubunitAmount = purchaseSubunitAmount;
	}
	/*public String getSaleWholeUnit() {
		return saleWholeUnit;
	}
	public void setSaleWholeUnit(String saleWholeUnit) {
		this.saleWholeUnit = saleWholeUnit;
	}
	public String getSaleRetailUnit() {
		return saleRetailUnit;
	}
	public void setSaleRetailUnit(String saleRetailUnit) {
		this.saleRetailUnit = saleRetailUnit;
	}*/
	public Float getWholeUtility() {
		return wholeUtility;
	}
	public void setWholeUtility(Float wholeUtility) {
		this.wholeUtility = wholeUtility;
	}
	public Float getRetailUtility() {
		return retailUtility;
	}
	public void setRetailUtility(Float retailUtility) {
		this.retailUtility = retailUtility;
	}
	public Float getMinStock() {
		return minStock;
	}
	public void setMinStock(Float minStock) {
		this.minStock = minStock;
	}
	public Float getMaxStock() {
		return maxStock;
	}
	public void setMaxStock(Float maxStock) {
		this.maxStock = maxStock;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	public Float getTaxes() {
		return taxes;
	}
	public void setTaxes(Float taxes) {
		this.taxes = taxes;
	}
	public String getInternalCode() {
		return internalCode;
	}
	public void setInternalCode(String internalCode) {
		this.internalCode = internalCode;
	}
	public List<ProductBatch> getProductBatches() {
		return productBatches;
	}
	public void setPorductBatches(List<ProductBatch> productBatches) {
		this.productBatches = productBatches;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public Boolean isInBulk() {
		return inBulk;
	}
	public void setInBulk(Boolean inBulk) {
		this.inBulk = inBulk;
	}
	public Float getUnitsSold() {
		return unitsSold;
	}
	public void setUnitsSold(Float unitsSold) {
		this.unitsSold = unitsSold;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ID: ");
		sb.append(this.getId());
		sb.append(", ");
		sb.append("Product: ");
		sb.append(this.getProduct());
		sb.append(", ");
		sb.append("Brand: ");
		sb.append(this.getBrand());
		sb.append(", ");
		sb.append("Whole Cost: ");
		sb.append(this.getWholeCost());
		sb.append(", ");
		sb.append("Retail Cost: ");
		sb.append(this.getRetailCost());
		sb.append(", ");
		sb.append("Whole Price: ");
		sb.append(this.getWholePrice());
		sb.append(", ");
		sb.append("Retail Price: ");
		sb.append(this.getRetailPrice());
		sb.append(", ");
		sb.append("Quantity: ");
		sb.append(this.getQuantity());
		sb.append(", ");
		sb.append("Purchase unit: ");
		sb.append(this.getPurchaseUnit());
		sb.append(", ");
		sb.append("Purchase subunit: ");
		sb.append(this.getPurchaseSubunit());
		sb.append(", ");
		sb.append("Purchase subunits per unit: ");
		sb.append(this.getPurchaseSubunitAmount());
		sb.append(", ");
		/*sb.append("Whole sale Unit: ");
		sb.append(this.getSaleWholeUnit());
		sb.append(", ");
		sb.append("Retail sale Unit: ");
		sb.append(this.getSaleRetailUnit());
		sb.append(", ");*/
		sb.append("Whole Utility: ");
		sb.append(this.getWholeUtility());
		sb.append(", ");
		sb.append("Retail Utility: ");
		sb.append(this.getRetailUtility());
		sb.append(", ");
		sb.append("Min Stock: ");
		sb.append(this.getMinStock());
		sb.append(", ");
		sb.append("Max Stock: ");
		sb.append(this.getMaxStock());
		sb.append(", ");
		/*sb.append("Number of batches: ");
		sb.append(this.getProductBatches().size());
		sb.append(", ");
		sb.append("Number of batches: ");
		sb.append(this.getProductBatches().size());
		sb.append(", ");*/
		sb.append("Barcode: ");
		sb.append(this.getBarCode());
		sb.append(", ");
		sb.append("In bulk: ");
		sb.append(this.isInBulk());
		sb.append(", ");
		sb.append("Units sold: ");
		sb.append(this.getUnitsSold());
		sb.append(" ]");
		return sb.toString(); 
	}
}
