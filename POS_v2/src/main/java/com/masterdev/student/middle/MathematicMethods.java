package com.masterdev.student.middle;

import java.math.BigDecimal;

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
	
}
