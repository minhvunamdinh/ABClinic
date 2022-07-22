package com.medical.examination.utils;

import com.medical.examination.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class AccountDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7232295614731313239L;

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
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public AccountDetail(Long id, String username, String password, Long isActive, String fullname, String address,
			Date dob, Long gender, Long role, Long isWorking, Long status, String email) {
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

	public static AccountDetail build(Account account) {
		return new AccountDetail(account.getId(), account.getUsername(), account.getPassword(), account.getIsActive(),
				account.getFullname(), account.getAddress(), account.getDob(), account.getGender(), account.getRole(),
				account.getIsWorking(), account.getStatus(), account.getEmail());
	}

}
