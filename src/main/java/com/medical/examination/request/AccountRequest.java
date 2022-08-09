package com.medical.examination.request;

import java.util.Date;

import javax.persistence.Column;
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
public class AccountRequest {
	@NotNull(message = "Thông tin bắt buộc")
    @Size(min = 1, max = 50, message = "Độ dài phải từ 6 đến 255 ký tự")
	private String username;
	@NotNull(message = "Thông tin bắt buộc")
    //@Size(min = 6, max = 20, message = "Độ dài phải từ 6 đến 20 ký tự")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
    message = "Không đúng định dạng mật khẩu, mật khẩu phải có đủ chữ hoa, chữ thường, số, ký tự đặc biệt")
	private String password;
	private String passwordRepeat;
	@NotNull(message = "Thông tin bắt buộc")
	private Long isActive;
	@NotNull(message = "Thông tin bắt buộc")
    @Size(min = 6, max = 50, message = "Độ dài phải từ 6 đến 255 ký tự")
	private String fullname;
	@NotNull(message = "Thông tin bắt buộc")
    @Size(min = 6, max = 255, message = "Độ dài phải từ 6 đến 255 ký tự")
	private String address;
	@NotNull(message = "Thông tin bắt buộc")
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date dob;
	@NotNull(message = "Thông tin bắt buộc")
	private Long gender;
	@NotNull(message = "Thông tin bắt buộc")
	private Long role;
	private Long isWorking;
	private Long status;
	@Email(message = "Không đúng định dạng Email" )
	@NotBlank(message = "Thông tin bắt buộc")
	private String email;
	@Column(name = "phone")
	@NotEmpty(message = "Thông tin bắt buộc!")
	@Size(min = 10, max = 10, message = "Số điện thoại phải có 10 chữ số")
	private String phone;
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
	public String getPasswordRepeat() {
		return passwordRepeat;
	}
	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@AssertTrue(message = "Mật khẩu không khớp!")
    public boolean isPasswordsEqual() {
        return (password == null) ? false : password.equals(passwordRepeat);
    }
}
