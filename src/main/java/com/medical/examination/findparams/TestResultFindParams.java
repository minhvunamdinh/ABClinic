package com.medical.examination.findparams;

public class TestResultFindParams {
	private Long id;
	private String code;
	private Long status;
	private Long accountId;
	private Long customerId;
	private Long isCalled;
	private boolean findCustomerReturning = true;
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
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public boolean isFindCustomerReturning() {
		return findCustomerReturning;
	}
	public void setFindCustomerReturning(boolean findCustomerReturning) {
		this.findCustomerReturning = findCustomerReturning;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getIsCalled() {
		return isCalled;
	}
	public void setIsCalled(Long isCalled) {
		this.isCalled = isCalled;
	}
	
}
