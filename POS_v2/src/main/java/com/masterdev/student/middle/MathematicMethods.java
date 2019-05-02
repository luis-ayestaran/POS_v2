package com.masterdev.student.middle;

public class MathematicMethods {
	
	public Float calculatePrice(Float purchaseCost, Float utility) {
		float price = (1 + (utility / 100)) * purchaseCost;
		return price;
	}
	
	public Float calculateUtility(Float purchaseCost, Float price) {
		float utility = ((price / purchaseCost) - 1) * 100;
		return utility;
	}
	
}
