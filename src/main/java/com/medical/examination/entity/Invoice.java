package com.medical.examination.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@SuperBuilder
@Table(name = "invoice")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "lst_test")
	private String lstTest;
	@Column(name = "lst_medicine")
	private String lstMedicine;
	@Column(name = "total_cost_price")
	private double totalCostPrice;
	@Column(name = "total_sell_price")
	private double totalSellPrice;
	@Column(name = "created_date")
	private Date createdDate;
	@ManyToOne
	@JoinColumn(name = "clinic_working_id")
	private ClinicWorking clinicWorking;
	@Column(name = "is_discounted")
	private Long isDiscounted;
	@Column(name = "test_result")
	private String testResult;
	@Column(name = "diagnostic_result")
	private String diagnosticResult;
	@Column(name = "lst_cost_price")
	private String lstCostPrice;
	@Column(name = "lst_sell_price")
	private String lstSellPrice;
	@Column(name = "time_return")
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date timeReturn;
	@Column(name = "conclusion")
	private String conclusion;
	@Column(name = "prescription")
	private String prescription;

	public Invoice() {

	}

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
	public String getLstMedicine() {
		return lstMedicine;
	}
	public void setLstMedicine(String lstMedical) {
		this.lstMedicine = lstMedical;
	}
	public double getTotalCostPrice() {
		return totalCostPrice;
	}
	public void setTotalCostPrice(double totalCostPrice) {
		this.totalCostPrice = totalCostPrice;
	}
	public double getTotalSellPrice() {
		return totalSellPrice;
	}
	public void setTotalSellPrice(double totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public ClinicWorking getClinicWorking() {
		return clinicWorking;
	}
	public void setClinicWorking(ClinicWorking clinicWorking) {
		this.clinicWorking = clinicWorking;
	}
	public Long getIsDiscounted() {
		return isDiscounted;
	}
	public void setIsDiscounted(Long isDiscounted) {
		this.isDiscounted = isDiscounted;
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
	public String getLstCostPrice() {
		return lstCostPrice;
	}
	public void setLstCostPrice(String lstCostPrice) {
		this.lstCostPrice = lstCostPrice;
	}
	public String getLstSellPrice() {
		return lstSellPrice;
	}
	public void setLstSellPrice(String lstSellPrice) {
		this.lstSellPrice = lstSellPrice;
	}
	public Date getTimeReturn() {
		return timeReturn;
	}
	public void setTimeReturn(Date timeReturn) {
		this.timeReturn = timeReturn;
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
	
}
