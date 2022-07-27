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
	private double totalCostPrice;
	@Column(name = "total_sell_price")
	private double totalSellPrice;
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
	
}
