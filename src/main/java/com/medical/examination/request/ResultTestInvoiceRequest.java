package com.medical.examination.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ResultTestInvoiceRequest {
	private String code;
	private String testResult;
	private String diagnosticResult;
	private String lstTest;
	private String lstMedicine;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date timeReturn;
	private Double totalCostPrice;
	private Double totalSellPrice;
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
	public String getLstMedicine() {
		return lstMedicine;
	}
	public void setLstMedicine(String lstMedicine) {
		this.lstMedicine = lstMedicine;
	}
	public Date getTimeReturn() {
		return timeReturn;
	}
	public void setTimeReturn(Date timeReturn) {
		this.timeReturn = timeReturn;
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
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public String getDiagnosticResult() {
		return diagnosticResult;
	}
	public void setDiagnosticResult(String diagnosticResult) {
		this.diagnosticResult = diagnosticResult;
	}
	
}
