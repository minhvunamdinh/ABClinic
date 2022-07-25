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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "fullname")
	private String fullname;
	@Column(name = "phone")
	private String phone;
	@Column(name = "gender")
	private Long gender;
	@Column(name = "job")
	private String job;
	@Column(name = "address")
	private String address;
	@Column(name = "dob")
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date dob;
	@Column(name = "country")
	private String country;
	@Column(name = "email")
	private String email;
	@Column(name = "`desc`")
	private String desc;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.REMOVE)
	@JsonIgnore
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

}
