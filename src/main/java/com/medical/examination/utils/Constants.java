package com.medical.examination.utils;

import java.util.Date;

import com.medical.examination.entity.Account;

public class Constants {
	public static final String RETURN_FAIL = "failed";
    public static final String RETURN_SUCCESS = "success";
    public static final String SUCCESS_CODE = "200";
    public static final String FAIL_CODE = "404";
    public static final String QUERY_SUCCESS = "Truy vấn thành công! ";
    public static final String QUERY_FAIL = "Truy vấn thất bại! ";
    public static final String DEFAULT_PASSWORD = "123456";
    public static final Account ACCOUNT_ADMIN = new Account(999999L, "admin", "Minhvu@fpt", 1L, "Admin", "IT center", null, -1L, 0L, 1L, 1L, "admin@gmail.com");
}
