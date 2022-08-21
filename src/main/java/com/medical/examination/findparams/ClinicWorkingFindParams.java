package com.medical.examination.findparams;

import java.util.Date;

public class ClinicWorkingFindParams {
	private Long accountId;
	private Long customerId;
	private Date createdDate;
	private Long status;
	private boolean isFindDataInCurrentDate = true;
	private boolean isFindMoreStatus;
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
	public boolean getIsFindDataInCurrentDate() {
		return isFindDataInCurrentDate;
	}
	public void setIsFindDataInCurrentDate(boolean isFindDataInCurrentDate) {
		this.isFindDataInCurrentDate = isFindDataInCurrentDate;
	}
	public boolean getIsFindMoreStatus() {
		return isFindMoreStatus;
	}
	public void setIsFindMoreStatus(boolean isFindMoreStatus) {
		this.isFindMoreStatus = isFindMoreStatus;
	}
	
}
