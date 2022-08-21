package com.medical.examination.request;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.AssertTrue;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ResultTestInvoiceRequest {
	private String code;
	private String testResult;
	private String diagnosticResult;
	private String lstTest;
	private String lstTestId;
	private String lstMedicine;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date timeReturn;
	private Double totalCostPrice;
	private Double totalSellPrice;
	private String conclusion;
	private String prescription;
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
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getLstTestId() {
		return lstTestId;
	}
	public void setLstTestId(String lstTestId) {
		this.lstTestId = lstTestId;
	}
	
}
