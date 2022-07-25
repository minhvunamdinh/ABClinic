package com.medical.examination.utils;

import java.util.Map;

public class FwResponse {
  private String errCode;
  private String errMsg;
  private Object data;

  public Object getData() {
    return data;
  }

  public String getErrCode() {
    return errCode;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }
  public void setResult(String errCode, String errMsg) {
      this.errCode = errCode;
      this.errMsg = errMsg;
  }

  public void setResult(String errCode, String errMsg, Object data) {
      this.errCode = errCode;
      this.errMsg = errMsg;
      this.data = data;
  }
}
