package com.medical.examination.findparams;


import java.util.Date;
public class AccountFindParams {
	private Long id;
	private String username;
	private String password;
	private Long isActive;
	private String fullname;
	private String address;
	private Date dob;
	private Long gender;
	private Long role;
	private Long isWorking;
	private Long status;
	private boolean isBossUsing;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public boolean isBossUsing() {
		return isBossUsing;
	}
	public void setBossUsing(boolean isBossUsing) {
		this.isBossUsing = isBossUsing;
	}
	
}
