package com.medical.examination.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "test_result")
public class TestResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code")
	private String code;
	@ManyToOne
	@JoinColumn(name = "clinic_working_id")
	private ClinicWorking clinicWorking;
	@Column(name = "test_result")
	private String testResult;
	@Column(name = "lst_medicine")
	private String lstMedicine;
	@Column(name = "lst_test")
	private String lstTest;
	@Column(name = "examination_card")
	private String examinationCard;
	@Column(name = "examination_fee")
	private Double examinationFee;
	@Column(name = "time_return")
	private Date timeReturn;
	@Column(name = "status")
	private Long status;
	@Column(name = "diagnostic_result")
	private String diagnosticResult;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "total_cost_price")
	private Double totalCostPrice;
	@Column(name = "total_sell_price")
	private Double totalSellPrice;
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
	public ClinicWorking getClinicWorking() {
		return clinicWorking;
	}
	public void setClinicWorking(ClinicWorking clinicWorking) {
		this.clinicWorking = clinicWorking;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public String getLstMedicine() {
		return lstMedicine;
	}
	public void setLstMedicine(String lstMedicine) {
		this.lstMedicine = lstMedicine;
	}
	public String getLstTest() {
		return lstTest;
	}
	public void setLstTest(String lstTest) {
		this.lstTest = lstTest;
	}
	public String getExaminationCard() {
		return examinationCard;
	}
	public void setExaminationCard(String examinationCard) {
		this.examinationCard = examinationCard;
	}
	public Double getExaminationFee() {
		return examinationFee;
	}
	public void setExaminationFee(Double examinationFee) {
		this.examinationFee = examinationFee;
	}
	public Date getTimeReturn() {
		return timeReturn;
	}
	public void setTimeReturn(Date timeReturn) {
		this.timeReturn = timeReturn;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getDiagnosticResult() {
		return diagnosticResult;
	}
	public void setDiagnosticResult(String diagnosticResult) {
		this.diagnosticResult = diagnosticResult;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

}
