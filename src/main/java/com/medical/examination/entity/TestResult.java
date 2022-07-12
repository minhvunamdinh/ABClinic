package com.medical.examination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private String examinationFee;
	@Column(name = "time_return")
	private String timeReturn;
	@Column(name = "status")
	private String status;
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
	public String getExaminationFee() {
		return examinationFee;
	}
	public void setExaminationFee(String examinationFee) {
		this.examinationFee = examinationFee;
	}
	public String getTimeReturn() {
		return timeReturn;
	}
	public void setTimeReturn(String timeReturn) {
		this.timeReturn = timeReturn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
