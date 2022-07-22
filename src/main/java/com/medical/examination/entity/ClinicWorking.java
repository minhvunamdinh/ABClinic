package com.medical.examination.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clinic_working")
public class ClinicWorking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@Column(name = "no")
	private Long no;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "status")
	private Long status;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clinicWorking", cascade = CascadeType.ALL)
	List<TestResult> lstTestResult = new ArrayList<TestResult>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clinicWorking", cascade = CascadeType.ALL)
	List<Invoice> lstInvoice = new ArrayList<Invoice>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public List<TestResult> getLstTestResult() {
		return lstTestResult;
	}
	public void setLstTestResult(List<TestResult> lstTestResult) {
		this.lstTestResult = lstTestResult;
	}
	public List<Invoice> getLstInvoice() {
		return lstInvoice;
	}
	public void setLstInvoice(List<Invoice> lstInvoice) {
		this.lstInvoice = lstInvoice;
	}
	
}
