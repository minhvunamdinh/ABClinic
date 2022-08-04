package com.medical.examination.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "fullname")
	@NotEmpty(message = "Thông tin bắt buộc!")
	@Size(min = 6, max = 50, message = "Độ dài phải từ 6 đến 50 ký tự")
	private String fullname;
	@Column(name = "phone")
	@NotEmpty(message = "Thông tin bắt buộc!")
	@Size(min = 10, max = 10, message = "Số điện thoại phải có 10 chữ số")
	private String phone;
	@Column(name = "gender")
	@NotNull(message = "Thông tin bắt buộc!")
	private Long gender;
	@Column(name = "job")
	@NotEmpty(message = "Thông tin bắt buộc!")
	private String job;
	@Column(name = "address")
	@NotEmpty(message = "Thông tin bắt buộc!")
	private String address;
	@Column(name = "dob")
	@DateTimeFormat (pattern="yyyy-MM-dd")
	@NotNull(message = "Thông tin bắt buộc!")
	private Date dob;
	@Column(name = "country")
	@NotEmpty(message = "Thông tin bắt buộc!")
	private String country;
	@Column(name = "email")
	@NotBlank(message = "Thông tin bắt buộc!")
	@Email(message = "Sai định dạng email!")
	private String email;
	@Column(name = "`desc`")
	private String desc;
	@Column(name = "created_date")
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date createdDate;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.REMOVE)
	//@JsonIgnore
	private List<ClinicWorking> lstClinicWorking = new ArrayList<ClinicWorking>();
	
	public List<ClinicWorking> getLstClinicWorking() {
		return lstClinicWorking;
	}
	public void setLstClinicWorking(List<ClinicWorking> lstClinicWorking) {
		this.lstClinicWorking = lstClinicWorking;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getGender() {
		return gender;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
