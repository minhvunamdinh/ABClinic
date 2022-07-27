package com.medical.examination.findparams;

public class InvoiceFindParams {
	private Long id;
	private String code;
	private String lstTest;
	private String lstMedical;
	private Double totalCostPrice;
	private Double totalSellPrice;
	private Long month;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLstTest() {
		return lstTest;
	}
	public void setLstTest(String lstTest) {
		this.lstTest = lstTest;
	}
	public String getLstMedical() {
		return lstMedical;
	}
	public void setLstMedical(String lstMedical) {
		this.lstMedical = lstMedical;
	}
	public Double getTotalCostPrice() {
		return totalCostPrice;
	}
	public void setTotalCostPrice(Double totalCostPrice) {
		this.totalCostPrice = totalCostPrice;
	}
	public Double getTotalSellPrice() {
		return totalSellPrice;
	}
	public void setTotalSellPrice(Double totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}
	public Long getMonth() {
		return month;
	}
	public void setMonth(Long month) {
		this.month = month;
	}
}
