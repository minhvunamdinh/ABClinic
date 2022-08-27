package com.medical.examination.request;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MedicalExaminationRequest {
	private Long customerId;
	@NotNull(message = "Thông tin bắt buộc!")
	@NotEmpty(message = "Thông tin bắt buộc!")
	@Size(min = 1, max = 50, message = "Độ dài phải từ 1 đến 50 ký tự")
	private String fullname;
	@NotNull(message = "Thông tin bắt buộc!")
	@NotEmpty(message = "Thông tin bắt buộc!")
//	@Size(min = 10, max = 10, message = "Số điện thoại phải có 10 chữ số")
	@Pattern(regexp = "^[0][0-9]{9}$",message = "Số điện thoại phải bắt đầu với 0 và có 10 chữ số")
	private String phone;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	@NotNull(message = "Thông tin bắt buộc!")
	private Date dob;
	@NotBlank(message = "Thông tin bắt buộc!")
	@Email(message = "Sai định dạng email!")
	@Size(min = 1, max = 50, message = "Độ dài phải từ 1 đến 50 ký tự")
	private String email;
	@NotNull(message = "Thông tin bắt buộc")
	@NotEmpty(message = "Thông tin bắt buộc!")
	@Size(min = 1, max = 50, message = "Độ dài phải từ 1 đến 50 ký tự")
	private String country;
	@NotNull(message = "Thông tin bắt buộc")
	@NotEmpty(message = "Thông tin bắt buộc!")
	@Size(min = 1, max = 50, message = "Độ dài phải từ 1 đến 50 ký tự")
	private String address;
	@NotNull(message = "Thông tin bắt buộc")
	@NotEmpty(message = "Thông tin bắt buộc!")
	@Size(min = 1, max = 50, message = "Độ dài phải từ 1 đến 50 ký tự")
	private String job;
	@NotNull(message = "Thông tin bắt buộc")
	private Long gender;
	@NotNull(message = "Thông tin bắt buộc")
	private Long accountId;

	@Size(min = 0, max = 500, message = "Độ dài phải từ 1 đến 500 ký tự")
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
	@AssertTrue(message = "Ngày sinh phải nhỏ hơn ngày hiện tại!")
    public boolean isDobGreater() {
        if(this.dob != null && this.dob.compareTo(new Date()) > 0) {
        	return false;
        }
        return true;
    }
}
