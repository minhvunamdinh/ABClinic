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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "is_active")
	private Long isActive;
	@Column(name = "fullname")
	private String fullname;
	@Column(name = "address")
	private String address;
	@Column(name = "dob")
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date dob;
	@Column(name = "gender")
	private Long gender;
	@Column(name = "role")
	private Long role;
	@Column(name = "is_working")
	private Long isWorking;
	@Column(name = "status")
	private Long status;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.REMOVE)
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	public Long getIsActive() {
		return isActive;
	}
	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}
	public Long getGender() {
		return gender;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
	public Long getRole() {
		return role;
	}
	public void setRole(Long role) {
		this.role = role;
	}
	public Long getIsWorking() {
		return isWorking;
	}
	public void setIsWorking(Long isWorking) {
		this.isWorking = isWorking;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Account(String username, String password, Long isActive, String fullname, String address, Date dob,
			Long gender, Long role, Long isWorking, Long status, String email, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.fullname = fullname;
		this.address = address;
		this.dob = dob;
		this.gender = gender;
		this.role = role;
		this.isWorking = isWorking;
		this.status = status;
		this.email = email;
		this.phone = phone;
	}
	public Account(Long id, String username, String password, Long isActive, String fullname, String address, Date dob,
			Long gender, Long role, Long isWorking, Long status, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.fullname = fullname;
		this.address = address;
		this.dob = dob;
		this.gender = gender;
		this.role = role;
		this.isWorking = isWorking;
		this.status = status;
		this.email = email;
	}
	
	public Account() {}
	
}
