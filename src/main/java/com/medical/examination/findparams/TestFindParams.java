package com.medical.examination.findparams;

public class TestFindParams {
	private Long id;
	private String testName;
	private Double costPrice;
	private Double sellPrice;
	private Long testTypeId;
	private Long status;
	
	public Long getTestTypeId() {
		return testTypeId;
	}
	public void setTestTypeId(Long testTypeId) {
		this.testTypeId = testTypeId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public TestFindParams(Long testTypeId, Long status) {
		this.testTypeId = testTypeId;
		this.status = status;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
}
