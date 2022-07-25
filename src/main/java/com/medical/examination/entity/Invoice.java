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

@Entity
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
	private Double totalCostPrice;
	@Column(name = "total_sell_price")
	private Double totalSellPrice;
	@Column(name = "created_date")
	private Date createdDate;
	@ManyToOne
	@JoinColumn(name = "clinic_working_id")
	private ClinicWorking clinicWorking;
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
	
}
