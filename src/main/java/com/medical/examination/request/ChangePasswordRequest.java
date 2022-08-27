package com.medical.examination.request;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChangePasswordRequest {
	@NotNull(message = "Thông tin bắt buộc")
	@Size(min = 6, max = 50, message = "Độ dài phải từ 6 đến 50 ký tự")
//	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
//    message = "Không đúng định dạng mật khẩu, mật khẩu phải có đủ chữ hoa, chữ thường, số, ký tự đặc biệt")
	private String oldPassword;
	@NotNull(message = "Thông tin bắt buộc")
	@Size(min = 6, max = 50, message = "Độ dài phải từ 6 đến 50 ký tự")
//	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
//    message = "Không đúng định dạng mật khẩu, mật khẩu phải có đủ chữ hoa, chữ thường, số, ký tự đặc biệt")
	private String newPassword;
	private String newPasswordRepeat;
	
	@AssertTrue(message = "Mật khẩu mới không khớp!")
    public boolean isPasswordsEqual() {
        return (newPassword == null) ? false : newPassword.equals(newPasswordRepeat);
    }

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}

	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}
	
}
