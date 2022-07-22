package com.medical.examination.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MedicalExaminationRequest {
	private Long customerId;
	@NotNull(message = "Thông tin bắt buộc!")
	@NotEmpty(message = "Thông tin bắt buộc!")
	private String fullname;
	@NotNull(message = "Thông tin bắt buộc!")
	private String phone;
	@DateTimeFormat (pattern="yyyy-MM-dd")
//	@NotNull(message = "Thông tin bắt buộc!")
	private Date dob;
	@NotNull(message = "Thông tin bắt buộc!")
	private String email;
	@NotNull(message = "Thông tin bắt buộc")
	private String country;
	@NotNull(message = "Thông tin bắt buộc")
	private String address;
	@NotNull(message = "Thông tin bắt buộc")
	private String job;
//	@NotNull(message = "Thông tin bắt buộc")
	private Long gender;
//	@NotNull(message = "Thông tin bắt buộc")
	private Long accountId;
	private String desc;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Long getGender() {
		return gender;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
