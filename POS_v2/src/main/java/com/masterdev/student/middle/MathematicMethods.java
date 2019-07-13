package com.masterdev.student.middle;

import java.math.BigDecimal;
import java.util.List;

import com.masterdev.student.entities.SaleDetail;
import com.masterdev.student.pojos.ProductOnSale;

public class MathematicMethods {
	
	public BigDecimal calculatePrice(Double purchaseCost, Double utility) {
		Double price = (1 + (utility / 100)) * purchaseCost;
		BigDecimal bd = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
	
	public BigDecimal calculateUtility(Double purchaseCost, Double price) {
		Double utility = ((price / purchaseCost) - (int) 1) * (int) 100;
		BigDecimal bd = BigDecimal.valueOf(utility).setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
	
	public Float calculateWholeDiscount(List<ProductOnSale> sale) {
		Float discount = 0f;
		for(ProductOnSale pos : sale) {
			discount += ( pos.getDiscount() / 100 )* pos.getUnitPrice() * pos.getQuantity();
		}
		return discount;
	}
	
	public Float calculateSubtotal(List<ProductOnSale> sale) {
		Float subtotal = 0f;
		for(ProductOnSale pos : sale) {
			subtotal += (pos.getUnitPrice() * pos.getQuantity());
		}
		return subtotal;
	}
	
	public Float calculateTotal(List<ProductOnSale> sale) {
		Float total = 0f;
		for(ProductOnSale pos : sale) {
			total += pos.getAmount();
		}
		return total;
	}
}
