package com.medical.examination.request;

public class DiscountRequest {
	private String[] percentArray;
	private Double[] incomeArray;
	public String[] getPercentArray() {
		return percentArray;
	}
	public void setPercentArray(String[] percentArray) {
		this.percentArray = percentArray;
	}
	public Double[] getIncomeArray() {
		return incomeArray;
	}
	public void setIncomeArray(Double[] incomeArray) {
		this.incomeArray = incomeArray;
	}
	
}
