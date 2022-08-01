package com.medical.examination.findparams;

public class TestResultFindParams {
	private Long id;
	private String code;
	private Long status;
	private boolean findCustomerReturning;
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
	
}
